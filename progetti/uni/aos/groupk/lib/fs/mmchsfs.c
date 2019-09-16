/*
 * Copyright (c) 2009, 2011, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <aos/aos.h>

#include <aos/deferred.h>
#include <fs/fs.h>
#include <fs/mmchsfs.h>
#include <fs/ramfs.h>

#include "fs_internal.h"

#define BULK_MEM_SIZE       (1U << 16)      // 64kB
#define BULK_BLOCK_SIZE     BULK_MEM_SIZE   // (it's RPC)

#define max(x, y) (x >= y ? x : y)
#define min(x, y) (x <= y ? x : y)

// Transaction number of modifications apported to the FS
static uint64_t trans_seq = 0; 

/**
 * @brief an entry in the mmchsfs
 */
struct mmchsfs_dirent
{
    dlinked_node_t node;
    char *name;                     ///< name of the file or directory
    uint32_t size;                    ///< the size of the direntry in bytes or files
    size_t refcount;                ///< reference count for open handles
    struct mmchsfs_dirent *parent;    ///< parent directory
    // XXX: This speeds up reverse lookup
    size_t parent_sector; ///< sector relative to the parent first sector in which the DirEntry is located
    size_t parent_in_sec_off; ///< offset in bytes inside sector where stored in parent

    dlinked_list_t *children; ///< a dlinked_list of children

    bool is_root;               ///< flag indicating whether this is root
    bool is_dir;                    ///< flag indicationg this is a dir
    bool is_long_dir;               ///< flag indicationg this is a long dir section
    char* long_name;               ///< long version of the name
    bool was_explored;           ///< flag indicating whether the content of this directory has been explored

    size_t cluster; ///< first sector of cluster of this dirent
    char attr;       ///< attributes of the dir

    union {
        void *data;                 ///< file data pointer
        struct mmchsfs_dirent *dir;   ///< directory pointer
    };
};

/**
 * @brief a handle to the open
 */
struct mmchsfs_handle
{
    struct fs_handle common;
    char *path;
    bool isdir;
    struct mmchsfs_dirent *dirent;
    union {
        off_t file_pos;
        struct mmchsfs_dirent *dir_pos;
    };
    uint32_t last_cluster;
};

struct mmchsfs_mount {
    struct mmchsfs_dirent *root;
};

inline static uint32_t compose_uint32_t(uint16_t hi, uint16_t lo) {
    return (hi << 16) | (lo);
}

/*
 * Given a 16-bit char encoded array, returns an 8-bit char encoded array
 * considering only the low 8-bits
 * @param buf16 -> the 16-bit encoded array
 * @param len -> the len of the 16-bit encoded array in bytes
 */
inline static char* array_16_to_8(char* buf16, size_t len) {
    assert(len % 2 == 0);
    char* buf = calloc(len / 2 + 1, sizeof(char));
    for (int i = 0; i < len; i += 2) {
        buf[i/2] = isprint(buf16[i]) ? buf16[i] : '\0';
    }
    return buf;
}

/*
 * Given an 8-bit char encoded array, returns a 16-bit char encoded array
 * non NULL-terminated
 */
inline static char* array_8_to_16(char* buf, size_t len) {
    char* buf16 = calloc(len * 2, sizeof(char));
    for (int i = 0; i < len; i++) {
        buf16[i*2] = buf[i];
    }
    return buf;
}

/*
 * ====================================
 * Utility functions to work with FAT32
 * ====================================
 */
inline static void initialize_constants(void) {
    BPB_RootEntCnt = *((uint16_t*) BPB._RootEntCnt);
    BPB_BytesPerSec = *((uint16_t*) BPB._BytesPerSec);
    BPB_ResvdSecCnt = *((uint16_t*) BPB._ResvdSecCnt);
    BPB_NumFATs = *((uint8_t*) BPB._NumFATs);
    BPB_FATSz32 = *((uint32_t*) BPB._FATSz32);
    BPB_TotSec32 = *((uint32_t*)BPB._TotSec32);
    BPB_SecPerClus = *((uint8_t*) BPB._SecPerClus);

    FATSz = BPB_FATSz32;
    RootDirSectors  = ((BPB_RootEntCnt * 32) + (BPB_BytesPerSec - 1)) / BPB_BytesPerSec;
    FirstDataSector = BPB_ResvdSecCnt + (BPB_NumFATs * FATSz) + RootDirSectors;

    DataSec =  BPB_TotSec32 - (BPB_ResvdSecCnt + (BPB_NumFATs * FATSz) + RootDirSectors);
    CountOfClusters = DataSec / BPB_SecPerClus;
    // Check that the FS is FAT32
    assert(CountOfClusters >= 65525);
}

inline static void FAT_dumpDirEntry(DirEntry_t *Dir) {
    FS_DBG("<DirEntry Name: %s, Attr: 0x%x, Size: %d, Clust: %d />\n",
            Dir->_Name,
            *((uint8_t*) Dir->_Attr),
            *((uint32_t*) Dir->_FileSize),
            compose_uint32_t(*((uint16_t*) Dir->_FstClusHI), *((uint16_t*) Dir->_FstClusLO)));
}

inline static void FAT_dump_dirent(struct mmchsfs_dirent *d) {
    FS_DBG("<dirent Name: %s, Attr: 0x%x, Size: %d, Parent: %s, Cluster: %d, Type: %s />\n",
            d->name,
            d->attr,
            d->size,
            d->is_root ? "None (root)" : d->parent->name,
            d->cluster, 
            d->is_dir ? "Dir" : "File");
}

static void FAT_invalidate_explore(struct mmchsfs_dirent *dir) {
    if (!dir) return;
    // FS_DBG("Invalidating children of %s\n", dir->name);
    if (dir->children && 
            strncmp(dir->name, ".", 1) &&
            strncmp(dir->name, "..", 2)) {
        struct mmchsfs_dirent *child = dlist_head(dir->children);
        while (child) {
            FAT_invalidate_explore(child);
            child = dlist_next(child);
        }
    }
    // FS_DBG("Invalidating %s\n", dir->name);
    dir->was_explored = false;
}

/*
 * Check the transaction sequence of the FS, and if needed invalidate the cache
 */
static errval_t FAT_check_seq(bool first_time) {
    errval_t err;
    size_t len;
    mmchs_block_req req;
    req.op = GET_SEQ;
    err = urpc_write_msg(&mmchs_urpc, (void*) &req, sizeof(mmchs_block_req));
    DBGERR(err, "Error writing message over urpc\n");

    mmchs_block_seq_res *res;
    err = urpc_read_msg(&mmchs_urpc, (void**) &res, &len);
    DBGERR(err, "Error receiving message from urpc\n");
    DBGERR(res->base.err, "Error in the driver\n");
    if (!first_time && res->seq != trans_seq) {
        // FS_DBG("Filesystem has been modified, invalidating cache\n");
        FAT_invalidate_explore(sd_root);
    }
    trans_seq = res->seq;
    free(res);
    return res->base.err;
}

/*
 * Increment the transaction sequence of the FS
 */
static errval_t FAT_inc_seq(void) {
    errval_t err;
    size_t len;
    mmchs_block_req req;
    req.op = INC_SEQ;
    err = urpc_write_msg(&mmchs_urpc, (void*) &req, sizeof(mmchs_block_req));
    DBGERR(err, "Error writing message over urpc\n");

    mmchs_block_seq_res *res;
    err = urpc_read_msg(&mmchs_urpc, (void**) &res, &len);
    DBGERR(err, "Error receiving message from urpc\n");
    DBGERR(res->base.err, "Error in the driver\n");
    trans_seq = res->seq;
    free(res);
    return res->base.err;
}

/*
 * Open handle globally
 */
static errval_t FAT_global_handle(const char* filename, enum HANDLE_OP op) {
    errval_t err;
    size_t filename_len = strlen(filename);
    if (filename_len > MAX_FILE_NAME) {
        return VFS_ERR_NOT_SUPPORTED;
    }
    // FS_DBG("Globally requesting to %d\n", op);
    size_t len;
    mmchs_block_handle_req req;
    req.base.op = HANDLE;
    req.op = op;
    strcpy(req.filename, filename);
    err = urpc_write_msg(&mmchs_urpc, (void*) &req, sizeof(mmchs_block_handle_req));
    DBGERR(err, "Error writing message over urpc\n");

    mmchs_block_res *res;
    err = urpc_read_msg(&mmchs_urpc, (void**) &res, &len);
    DBGERR(err, "Error receiving message from urpc\n");
    err = res->err;
    free(res);
    return err;
}

/*
 * Read a block from the SD card
 */
errval_t read_block(size_t block_num, mmchs_block_read_res** ret_res) {
    errval_t err;
    size_t len;
    mmchs_block_read_req req;
    req.base.block_no = block_num;
    req.base.op = READ;
    err = urpc_write_msg(&mmchs_urpc, (void*) &req, sizeof(mmchs_block_read_req));
    DBGERR(err, "Error writing message over urpc\n");

    mmchs_block_read_res *res;
    err = urpc_read_msg(&mmchs_urpc, (void**) &res, &len);
    DBGERR(err, "Error receiving message from urpc\n");

    // mmchs_block_read_res *res = (mmchs_block_read_res*) res_buf;
    // b_read(&mmchs_urpc, (void*) len_buf, max(MCACHE_LINE_SIZE, sizeof(size_t)));
    // memcpy(&len, len_buf, sizeof(size_t));
    // size_t buf_size = ROUND_UP(len, MCACHE_LINE_SIZE);
    // b_read(&mmchs_urpc, res_buf, buf_size);

    DBGERR(res->base.err, "Error in the driver\n");
    *ret_res = res;
    return res->base.err;
}

/*
 * Write a block to the SD card
 */
errval_t write_block(size_t block_num, char* buf) {
    errval_t err;
    size_t len;
    mmchs_block_write_req req;
    req.base.block_no = block_num;
    req.base.op = WRITE;
    memcpy(req.chunk, buf, 512);
    err = urpc_write_msg(&mmchs_urpc, (void*) &req, sizeof(mmchs_block_write_req));
    DBGERR(err, "Error writing message over urpc\n");

    mmchs_block_write_res *res;
    err = urpc_read_msg(&mmchs_urpc, (void**) &res, &len);
    DBGERR(err, "Error receiving message from urpc\n");
    DBGERR(res->base.err, "Error in the driver\n");
    return res->base.err;
}

/*
 * Write part of a block to the SD card
 */

errval_t write_part_block(size_t block_num, size_t in_block_off, size_t bytes, char* buf) {
    errval_t err = SYS_ERR_OK;
    /*
     * Since we can only write at least a block, we first need to get 
     * the target sector, and we update only what needed
     */
    // FS_DBG("Carrying out partial write: %d bytes %d block w/ off %d\n", bytes, block_num, in_block_off);
    mmchs_block_read_res *old_block;
    err = read_block(block_num, &old_block);
    DBGERR(err, "Error reading old block\n");
    
    // printf("Before: \n");
    // for (int i = 0; i < 512; i++) {
        // printf("%c ", isprint(old_block->chunk[i]) ? old_block->chunk[i]  : '.');
        // if (!((i+1) % 32))
            // printf("\n");
    // }
    // printf("\n");
    // printf("Inserting: \n");
    // for (int i = 0; i < bytes; i++) {
        // printf("%c ", isprint(buf[i]) ? buf[i]  : '.');
        // if (!((i+1) % 32))
            // printf("\n");
    // }
    // printf("\n");

    size_t to_write = min(bytes, BPB_BytesPerSec - in_block_off);
    memcpy(old_block->chunk + in_block_off, buf, to_write);
    // FS_DBG("Writing partial sector from %zu to %zu\n", in_block_off, to_write);
    err = write_block(block_num, old_block->chunk);
    DBGERR(err, "Error writing block\n");

    // mmchs_block_read_res *res;
    // err = read_block(block_num, &res);
    // DBGERR(err, "Error reading old block\n");
    // printf("After: \n");
    // for (int i = 0; i < 512; i++) {
        // printf("%c ", isprint(res->chunk[i]) ? res->chunk[i]  : '.');
        // if (!((i+1) % 32))
            // printf("\n");
    // }
    // printf("\n");

    free(old_block);
    return err;
}


/*
 * Return FirstSectorofCluster of a given cluster N
 */
inline static size_t FAT_cluster_base_sec(size_t N) {
    assert(N >= 2);
    // TODO: Shift can be used instead of '*' since 
    // BPB._SecPerClus is always a multiple of 2
    return ((N - 2) * BPB_SecPerClus) + FirstDataSector;
}

/*
 * Return a free FAT cluster
 */
static errval_t FAT_free_cluster(uint32_t* value) {
    errval_t err;
    mmchs_block_read_res *res;
    uint32_t c = 0;
    for (int i = 0; i < BPB_FATSz32 / BPB_BytesPerSec; i++) {
        int sec = BPB_ResvdSecCnt + i;
        err = read_block(sec, &res);
        DBGERR(err, "Error reading block from mmchs device\n");
        uint32_t *FAT32_entry = (uint32_t*) res->chunk;
        for (int j = 0; j < BPB_BytesPerSec / sizeof(uint32_t); j++, c++) {
            // FS_DBG("Cluster %d is %s\n", c, FAT32_entry[j] == 0x0 ? "empty" : "occupied");
            if (FAT32_entry[j] == 0x0) {
                *value = c;
                return SYS_ERR_OK;
            }
        }
        free(res);
    }
    return FS_ERR_NOTFOUND;
}

/*
 * Returns the offset in number of clusters
 */
inline static size_t FAT_clust_off(size_t off) {
    return off / (BPB_BytesPerSec * BPB_SecPerClus);
}

/*
 * Returns the offset in number of sectors
 */
inline static size_t FAT_sec_off(size_t off) {
    return (off % (BPB_BytesPerSec * BPB_SecPerClus)) / BPB_BytesPerSec;
}

/*
 * Returns the offset in bytes inside a sector
 */
inline static size_t FAT_in_sec_off(size_t off) {
    return (off % (BPB_BytesPerSec * BPB_SecPerClus)) % BPB_BytesPerSec;
}

inline static bool FAT_is_EOF(size_t FAT_entry) {
    return FAT_entry >= FAT32_EOC;
}
inline static bool FAT_is_empty(size_t FAT_entry) {
    return FAT_entry == 0x0;
}
// static bool FAT_is_uninit_DirEntry(DirEntry_t* Dir) {
    // for (int i = 0; i < sizeof(DirEntry_t); i++) {
        // if (((char*) Dir)[i] != 0x0) return false;
    // }
    // return true;
// }

/*
 * Return the FAT entry in FAT FAT_table 
 * relative to a given cluster N
 */
static errval_t FAT_getNextEntry(size_t N, uint32_t* value) {
    errval_t err;
    size_t FATOffset = N * 4;
    // This is for multi-FAT tables setup
    // size_t ThisFATSecNum = BPB_ResvdSecCnt + (FATOffset / BPB_BytesPerSec) + FATSz*FAT_Table_no;
    size_t ThisFATSecNum = BPB_ResvdSecCnt + (FATOffset / BPB_BytesPerSec);
    size_t ThisFATEntOFFset = FATOffset % BPB_BytesPerSec;
    // FS_DBG("Reading at offset 0x%zx of sector %zu\n", ThisFATEntOFFset, ThisFATSecNum);
    
    mmchs_block_read_res *res;
    err = read_block(ThisFATSecNum, &res);
    DBGERR(err, "Error reading block from mmchs device\n");
    uint32_t FATClusEntryVal = (*((uint32_t *) &res->chunk[ThisFATEntOFFset])) & 0x0FFFFFFF;
    // FS_DBG("Next cluster 0x%"PRIx32"\n", FATClusEntryVal);

    *value = FATClusEntryVal;
    free(res);
    return SYS_ERR_OK;
}


/*
 * Set the FAT entry in FAT FAT_table 
 * relative to a given cluster N
 */
static errval_t set_FAT_entry(size_t N, size_t FAT_table, uint32_t value) {
    assert(N >= 2);
    errval_t err;
    size_t FATOffset = N * 4;
    size_t ThisFATSecNum = BPB_ResvdSecCnt + (FATOffset / BPB_BytesPerSec) + FAT_table * FATSz;
    size_t ThisFATEntOFFset = FATOffset % BPB_BytesPerSec;
    // FS_DBG("Reading at offset 0x%zx of sector %zu\n", ThisFATEntOFFset, ThisFATSecNum);
    
    mmchs_block_read_res *res;
    err = read_block(ThisFATSecNum, &res);
    DBGERR(err, "Error reading block from mmchs device\n");
    uint32_t FATClusEntryVal = value & 0x0FFFFFFF;
    // FS_DBG("Updating FAT_entry: previous: %x (%s), now %x\n", 
            // res->chunk[ThisFATEntOFFset],
            // (FAT_is_empty(res->chunk[ThisFATEntOFFset]) ? "Empty" : 
                // (FAT_is_EOF(res->chunk[ThisFATEntOFFset]) ? "EOF" : "Cluster")),
            // FATClusEntryVal);
    (*((uint32_t *) &res->chunk[ThisFATEntOFFset])) &= 0xF0000000;
    (*((uint32_t *) &res->chunk[ThisFATEntOFFset])) |= FATClusEntryVal;
    err = write_block(ThisFATSecNum, res->chunk);
    DBGERR(err, "Error writing block\n");

    free(res);
    return SYS_ERR_OK;
}

static errval_t FAT_clear_chain(uint32_t cluster) {
    errval_t err;
    uint32_t next = cluster;
    while (!FAT_is_EOF(next) && !FAT_is_empty(next)) {
        cluster = next;
        err = FAT_getNextEntry(cluster, &next);
        DBGERR(err, "Error getting next cluster while clearing chain\n");
        // FS_DBG("Setting 0x%x as empty\n", cluster);
        err = set_FAT_entry(cluster, 0, 0x0); // Set the next of the cluster to 0x0, meaning it is free
        DBGERR(err, "Error reading parent sector for deletion\n");
    }
    return SYS_ERR_OK;
}

inline static struct mmchsfs_dirent* DirEntry_to_dirent(DirEntry_t* d) {
    struct mmchsfs_dirent* dirent = calloc(1, sizeof(struct mmchsfs_dirent));
    dirent->attr = *d->_Attr;
    dirent->cluster = compose_uint32_t(*((uint16_t*)d->_FstClusHI), *((uint16_t*)d->_FstClusLO));
    dirent->is_dir = dirent->attr & DIR_ATTR_DIRECTORY;
    dirent->is_long_dir = dirent->attr & DIR_ATTR_LONG_NAME;
    dirent->name = strdup(d->_Name);
    dirent->size = *((uint32_t*) d->_FileSize);
    return dirent;
}

inline static DirEntry_t* dirent_to_DirEntry(struct mmchsfs_dirent* d) {
    DirEntry_t* Dir = calloc(1, sizeof(DirEntry_t));
    *Dir->_Attr = d->attr;
    if (d->is_dir)
        *Dir->_Attr |= DIR_ATTR_DIRECTORY;
    if (d->is_long_dir)
        *Dir->_Attr |= DIR_ATTR_LONG_NAME;
    *((uint16_t*) Dir->_FstClusHI) = (uint16_t) (d->cluster >> 16) & 0xFFFF;
    *((uint16_t*) Dir->_FstClusLO) = (uint16_t) (d->cluster) & 0xFFFF;
    assert(d->cluster == compose_uint32_t(*((uint16_t*) Dir->_FstClusHI), *((uint16_t*) Dir->_FstClusLO)));
    char* point_cur = strchr(d->name, '.');
    size_t prefix_len = point_cur ? point_cur - d->name : 8;
    memcpy(Dir->_Name, d->name, min(8, prefix_len));
    if (point_cur != NULL) {
        memcpy(Dir->_Name + 8, point_cur + 1, 3);
    }
    *((uint32_t*)Dir->_FileSize) = d->size;
    return Dir;
}

inline static bool is_long_DirEntry(DirEntry_t* d) {
    return *((uint8_t*)d->_Attr) & 0xf;
}
inline static bool is_long_sub_DirEntry(DirEntry_t* d) {
    return false;
}

/*
 * Return a free FAT spot for a DirEntry
 */
static errval_t FAT_free_DirEntry(struct mmchsfs_dirent *dir, uint32_t* ret_off) {
    assert(dir->is_dir);
    errval_t err = SYS_ERR_OK;
    // FS_DBG("Finding free entry in %s (@%d)\n", dir->name, dir->cluster);
    size_t clust = dir->cluster;
    size_t base_sec = FAT_cluster_base_sec(clust);
    uint32_t bytes_off = 0;
    while (true) {
        for (int sec_off = 0; sec_off < BPB_SecPerClus; sec_off++) {
            mmchs_block_read_res *res;
            err = read_block(base_sec + sec_off, &res);
            DBGERR(err, "Error reading sector");

            DirEntry_t *cur = (DirEntry_t*) res->chunk;
            for (int k = 0;
                    k < BPB_BytesPerSec / sizeof(DirEntry_t);
                    k++, cur++, bytes_off += sizeof(DirEntry_t)) {
                struct mmchsfs_dirent* entry = DirEntry_to_dirent(cur);
                if (cur->_Name[0] == 0xe5 || cur->_Name[0] == 0x0) {
                    // FS_DBG("Found empty entry %d\n", bytes_off);
                    *ret_off = bytes_off;
                    return SYS_ERR_OK;
                }
                if (entry->is_long_dir) {
                    continue;
                }
            }
            free(res);
        }

        size_t FAT_entry;
        err = FAT_getNextEntry(clust, &FAT_entry);
        DBGERR(err, "Error retrieving FAT_entry\n");
        if (FAT_is_EOF(FAT_entry)) {
            break;
        }
        clust = FAT_entry;
        base_sec = FAT_cluster_base_sec(clust);
    }
    return FS_ERR_NOTFOUND;
}



// Convert a name to FAT_name
// @return the FAT short name if name is valid, otherwise NULL
static char* to_FAT_name(const char* name) {
    // TODO: only supporting short names
    char* point_pos = strchr(name , '.');
    if (point_pos != strrchr(name, '.')) {
        return NULL;
    }
    size_t name_len = strlen(name);
    if (point_pos) name_len--; // If there is a point we do not count it
    size_t name_prefix_len = point_pos ? point_pos - name : name_len;
    assert(name_prefix_len >= 1 && name_prefix_len <= 8 && name_len <= 11);
    if (name_prefix_len < 1 ||
        name_prefix_len > 8 ||
        name_len > 11)
        return NULL;
    char* FAT_name = calloc(sizeof(char), 12);
    int i = 0;
    // Copy up to 8 chars
    for (; i < min(8, name_prefix_len); i++) {
        FAT_name[i] = toupper(name[i]);
    }
    // Insert according space
    for (; i < 8; i++) { FAT_name[i] = ' '; }
    // Add, if present, extension
    if (point_pos) {
        size_t ext_len = strlen(point_pos+1);
        assert(ext_len == 3);
        if (ext_len != 3) {
            return NULL;
        } else {
            for (i = 0; i < 3; i++) {
                FAT_name[8+i] = toupper(point_pos[i+1]);
            }
        }
    }
    return FAT_name;
}

static bool FAT_name_cmp(char* path, char* name) {
    // TODO: now working !!only with 11 bytes!! long names
    // Here we treat dot and dotdot folder differently
    // FS_DBG("|%s| <> |%s|\n", path, name);
    if (!strcmp(".", name)) return strncmp(".", path, 1) == 0;
    if (!strcmp("..", name)) return strncmp("..", path, 2) == 0;

    char* point_pos = strchr(name , '.');
    size_t name_len = strlen(name);
    size_t name_prefix_len = point_pos ? point_pos - name : name_len;
    if (name_prefix_len > 8 || name_len > 11) return false;
    return !strncasecmp(path, name, min(8, name_prefix_len)) && 
            (point_pos == NULL || !strncasecmp(point_pos + 1, path + 8, 3));
}

/*
 * Updates the DirEntry_t of an entity in the SD
 * 
 * @param d: the dirent with the new attributes
 * @param dir_name: If not NULL use this to find target DirEntry
 */
static errval_t FAT_updateDirEntry(struct mmchsfs_dirent *d, char* old_name) {
    errval_t err = SYS_ERR_OK;
    if (d->is_root) {
        // FS_DBG("Skipping updating %s (root)\n", d->name);
        return err;
    }
    // FS_DBG("Updating dir_entry for %s\n", d->name);
    // Get parent's sector 
    size_t sec = d->parent_sector;
    // char* dir_name = old_name == NULL ? d->name : old_name;
    mmchs_block_read_res *res;
    err = read_block(sec, &res);
    DBGERR(err, "Error reading sector");

    DirEntry_t *cur = (DirEntry_t*) (res->chunk + d->parent_in_sec_off);
    // FS_DBG("Found: \n"); FAT_dumpDirEntry(cur);
    // Update fields
    DirEntry_t *Dir = dirent_to_DirEntry(d);
    memcpy(cur, Dir, sizeof(DirEntry_t));
    // FS_DBG("New: \n"); FAT_dumpDirEntry(cur);
    // Write the sector back to update
    err = write_block(sec, res->chunk);
    DBGERR(err, "Error writing to SD\n");

    free(Dir);
    free(res);
    return err;
}

/*
 * Read a generic buf from the SD card
 */
static errval_t read_buf(char* buf, size_t bytes, size_t clust, size_t off, size_t *ret_bytes, size_t *last_clust) {
    /*
     * We need to consider 3 different offsets here:
     *     - clust_off: the nth cluster at which the h->file_pos is pointing
     *     - sec_off:   the sector of the nth cluster at which  ...
     *     - in_sec_off: the offset inside the sector of the nth cluster ...
     */
    errval_t err = SYS_ERR_OK;
    size_t clust_off = off / (BPB_BytesPerSec * BPB_SecPerClus);
    // TODO: Check if this works
    if (*last_clust == 0) {
        for (int i = 0; i < clust_off; i++) {
            // FS_DBG("Seeking cluster #%d\n", i+1);
            size_t FAT_entry;
            err = FAT_getNextEntry(clust, &FAT_entry);
            DBGERR(err, "Error retrieving FAT_entry\n");
            if (FAT_is_EOF(FAT_entry)) {
                return FS_ERR_OFF_BOUNDS;
            }
            clust = FAT_entry;
        }
    } else {
        // FS_DBG("Using last cached cluster\n");
        clust = *last_clust;
    }
    // FS_DBG("Reading \x1b[33m%zu\x1b[0m bytes with offset \x1b[33m%zu\x1b[0m from cluster \x1b[33m%zu\x1b[0m (<> \x1b[33m%zu\x1b[0m)\n", bytes, off, clust, *last_clust);
    size_t base_sec = FAT_cluster_base_sec(clust);
    /*
     * The offset of the sector inside the cluster
     * It is equal to the number of sector needed to reach the offset inside
     * the previously calculated clust_off
     */
    size_t sec_off = (off % (BPB_BytesPerSec * BPB_SecPerClus)) / BPB_BytesPerSec;
    /*
     * The offset inside the sector is equal to the remainder of the previous calculus for the sector
     * offset
     */
    size_t in_sec_off = (off % (BPB_BytesPerSec * BPB_SecPerClus)) % BPB_BytesPerSec;
    size_t left = bytes;
    size_t read = 0;
    mmchs_block_read_res *res;
    while (left > 0) {
        // FS_DBG("Cluster: %d, Sector: %d, Sector Offset: %d / %d, In Sector Offset: %d\n", 
          // clust, base_sec, sec_off, BPB_SecPerClus, in_sec_off);
        for (int i = sec_off; i < BPB_SecPerClus && left > 0; i++) {
            // FS_DBG("(Sec %d) Left: %d, Read: %d\n", base_sec + i, left, read);
            // First time we read, we consider the in_sec_off
            err = read_block(base_sec + i, &res);
            DBGERR(err, "Error reading block\n");
            size_t to_read = min(BPB_BytesPerSec, left) - in_sec_off;
            memcpy(buf + read, res->chunk + in_sec_off, to_read);
            // Update bytes `left` and bytes `read` according to how much we read
            read += to_read;
            left = max(0, left - to_read);
            // For subsequent reading, we do not consider the in_sec_off anymore
            in_sec_off = 0; 
            // Free the mmchs_block_read_req allocated by read_block
            free(res); 
        }
        // If we are not done yet, we need to go to the next cluster
        if (left > 0) {
            size_t FAT_entry;
            err = FAT_getNextEntry(clust, &FAT_entry);
            DBGERR(err, "Error retrieving FAT_entry\n");
            if (FAT_is_EOF(FAT_entry)) {
                return FS_ERR_OFF_BOUNDS;
            }
            clust = FAT_entry;
            base_sec = FAT_cluster_base_sec(clust);
            sec_off = 0;
            // FS_DBG("Next cluster: %d, Sector: %d, Sector Offset: %d\n", 
                    // clust, base_sec, sec_off);
        }
    }
    
    *ret_bytes = read;
    /*
     * Check if the last read moved the offset of the handle
     * so to go in the next cluster
     */
    size_t new_clust_off = (off + read) / (BPB_BytesPerSec * BPB_SecPerClus);
    if (new_clust_off > clust_off) {
        size_t FAT_entry;
        err = FAT_getNextEntry(clust, &FAT_entry);
        DBGERR(err, "Error retrieving FAT_entry\n");
        *last_clust = FAT_entry;
    } else {
        *last_clust = clust;
    }
    return err;
}

/*
 * Write a generic buf to the SD card
 */
static errval_t write_buf(char* buf, size_t bytes, size_t clust, size_t off, size_t *ret_bytes, size_t *last_clust) {
    /*
     * We need to consider 3 different offsets here:
     *     - clust_off: the nth cluster at which the off is pointing
     *     - sec_off:   the sector of the nth cluster at which  ...
     *     - in_sec_off: the offset inside the sector of the nth cluster ...
     */
    errval_t err = SYS_ERR_OK;
    size_t clust_off = off / (BPB_BytesPerSec * BPB_SecPerClus);
    // TODO: Check if this works
    if (*last_clust == 0) {
        for (int i = 0; i < clust_off; i++) {
            // FS_DBG("Seeking cluster #%d\n", i+1);
            size_t FAT_entry;
            err = FAT_getNextEntry(clust, &FAT_entry);
            DBGERR(err, "Error retrieving FAT_entry\n");
            if (FAT_is_EOF(FAT_entry)) {
                return FS_ERR_OFF_BOUNDS;
            }
            clust = FAT_entry;
        }
    } else {
        clust = *last_clust;
    }
    size_t base_sec = FAT_cluster_base_sec(clust);
    // The offset of the sector is equal to the number of sector needed to reach the offset inside
    // the previously calculated clust_off
    size_t sec_off = (off % (BPB_BytesPerSec * BPB_SecPerClus)) / BPB_BytesPerSec;
    // The offset inside the sector is equal to the remainder of the previous calculus for the sector
    // offset
    size_t in_sec_off = (off % (BPB_BytesPerSec * BPB_SecPerClus)) % BPB_BytesPerSec;
    size_t left = bytes;
    size_t written = 0;
    if (in_sec_off > 0) {
        size_t to_write = max(left, BPB_BytesPerSec - in_sec_off);
        // FS_DBG("Writing %d bytes with %d in_sec_off\n", to_write, in_sec_off);
        err = write_part_block(base_sec + sec_off, in_sec_off, to_write, buf);
        DBGERR(err, "Error writing partial block\n");
        // Update the number of bytes `left` and `written`
        left -= to_write;
        written += to_write;
        // Update the sector since this one has been already written
        sec_off++;
    }
    while (left > 0) {
        // FS_DBG("[Write] Cluster: %d, Sector: %d, Sector Offset: %d\n", 
                // clust, base_sec, sec_off);
        for (; sec_off < BPB_SecPerClus && left > 0; sec_off++) {
            // FS_DBG("Left: %d, Written: %d\n", left, written);
            if (left < BPB_BytesPerSec) {
                size_t to_write = left;
                err = write_part_block(base_sec + sec_off, 0, left, buf + written);
                DBGERR(err, "Error writing partial block\n");
                // Update the number of bytes `left` and `written`
                left -= to_write; // This be 0
                written += to_write;
                break;
            }

            err = write_block(base_sec + sec_off, buf + written);
            DBGERR(err, "Error reading block\n");
            /*
             * Here `max(BPB_BytesPerSec, left)` is always the first
             * otherwise we would have been in the previous `if`
             */
            size_t to_write = BPB_BytesPerSec;
            // Update bytes `left` and bytes `read` according to how much we read
            written += to_write;
            // Always equals to `BPB_BytesPerSec` for same reason as above
            left -= BPB_BytesPerSec;
        }
        // If we are not done yet, we need to go to the next cluster
        if (left > 0) {
            uint32_t FAT_entry;
            err = FAT_getNextEntry(clust, &FAT_entry);
            DBGERR(err, "Error retrieving FAT_entry\n");
            if (FAT_is_EOF(FAT_entry) || FAT_is_empty(FAT_entry)) {
                // Get a free cluster and set it as the next of 
                // the current `clust`
                uint32_t next_clust;
                err = FAT_free_cluster(&next_clust);
                DBGERR(err, "Error getting a free cluster\n");
                err = set_FAT_entry(clust, 0, next_clust);
                DBGERR(err, "Error settings FAT entry\n");
                err = set_FAT_entry(next_clust, 0, FAT32_EOC);
                DBGERR(err, "Error settings FAT entry\n");
                clust = next_clust;
            } else {
                clust = FAT_entry;
            }
            // FS_DBG("Switching to cluster 0x%x\n", clust);
            base_sec = FAT_cluster_base_sec(clust);
            sec_off = 0;
        }
    }
    
    *ret_bytes = written;
    /*
     * Check if the last write moved the offset of the handle
     * so to go in the next cluster
     */
    size_t new_clust_off = (off + written) / (BPB_BytesPerSec * BPB_SecPerClus);
    if (new_clust_off > clust_off) {
        uint32_t FAT_entry;
        err = FAT_getNextEntry(clust, &FAT_entry);
        DBGERR(err, "Error retrieving FAT_entry\n");
        if (FAT_is_EOF(FAT_entry) || FAT_is_empty(FAT_entry)) {
            // Get a free cluster and set it as the next of 
            // the current `clust`
            uint32_t next_clust;
            err = FAT_free_cluster(&next_clust);
            DBGERR(err, "Error getting a free cluster\n");
            err = set_FAT_entry(clust, 0, next_clust);
            DBGERR(err, "Error settings FAT entry\n");
            err = set_FAT_entry(next_clust, 0, FAT32_EOC);
            DBGERR(err, "Error settings FAT entry\n");
            // FS_DBG("Allocating cluster 0x%x\n", next_clust);
            *last_clust = next_clust;
        } else {
            *last_clust = FAT_entry;
        }
    } else {
        *last_clust = clust;
    }
    return err;
}


static char* FAT_get_full_path(struct mmchsfs_handle *handle) {
    return strdup(handle->dirent->name);
}
/*
 * ====================================
 * ====================================
 */


/*
 * Explore the content of the directory for later queries
 */
static errval_t mmchsfs_exploredir(struct mmchsfs_dirent* dir) {
    assert(dir->is_dir);
    errval_t err = SYS_ERR_OK;
    // FS_DBG("Exploring %s (@%d)\n", dir->name, dir->cluster);
    dir->children = malloc(sizeof(dlinked_list_t));
    dlist_init(dir->children);
    size_t clust = dir->cluster;
    size_t base_sec = FAT_cluster_base_sec(clust);
    while (true) {
        for (int sec_off = 0; sec_off < BPB_SecPerClus; sec_off++) {
            mmchs_block_read_res *res;
            err = read_block(base_sec + sec_off, &res);
            DBGERR(err, "Error reading sector");

            bool in_long_dir = false;
            size_t missing_long = -1;
            char* long_name = NULL;
            char* long_name_cur = NULL;

            DirEntry_t *cur = (DirEntry_t*) res->chunk;
            for (int k = 0; k < BPB_BytesPerSec / sizeof(DirEntry_t); k++, cur++) {
                struct mmchsfs_dirent* next = DirEntry_to_dirent(cur);
                if (cur->_Name[0] == 0xe5) {
                    // FS_DBG("Skipping free entry\n");
                    continue;
                } else if (cur->_Name[0] == 0x0) {
                    // FS_DBG("Finished entries in this dir\n");
                    goto FINISH_EXPLORE;
                }
                if (next->is_long_dir) {
                    LongDirEntry_t *long_cur = (LongDirEntry_t*) cur;
                    uint8_t ord = *((uint8_t*) long_cur->_Ord) ;
                    if (ord & 0x40) {
                        ord &= (~0x40);
                        in_long_dir = true;
                        missing_long = ord;
                        // Number of long name entries * bytes per entry + 1 for NULL-terminated string
                        size_t long_name_len = (missing_long) * 13 + 1;
                        long_name = calloc(long_name_len, sizeof(char));
                        long_name_cur = long_name + long_name_len - 1;
                        // FS_DBG("Long starting point @ %p\n", long_name);
                    }
                    if (in_long_dir) {
                        // FS_DBG("#%x |%s|%s|%s|\n", ord, array_16_to_8(long_cur->_Name1, 10), array_16_to_8(long_cur->_Name2, 12) , array_16_to_8(long_cur->_Name3, 4));
                        assert(missing_long > 0);

                        char* long_part = array_16_to_8(long_cur->_Name3, 4);
                        long_name_cur -= strlen(long_part);
                        // FS_DBG("%s %d @ %p\n", long_part, strlen(long_part), long_name_cur);
                        memcpy(long_name_cur, long_part, strlen(long_part));

                        long_part = array_16_to_8(long_cur->_Name2, 12);
                        long_name_cur -= strlen(long_part);
                        // FS_DBG("%s %d @ %p\n", long_part, strlen(long_part), long_name_cur);
                        memcpy(long_name_cur, long_part, strlen(long_part));

                        long_part = array_16_to_8(long_cur->_Name1, 10);
                        long_name_cur -= strlen(long_part);
                        // FS_DBG("%s %d @ %p\n", long_part, strlen(long_part), long_name_cur);
                        memcpy(long_name_cur, long_part, strlen(long_part));

                        missing_long--;
                    }
                    continue;
                }
                /*
                 * It is important to first check for the `dotdot` folder
                 * since the `dot` folder is a subset
                 */
                if (FAT_name_cmp(next->name, "..")) {
                    next->was_explored = true;
                    next->parent_sector = dir->parent->parent_sector;
                    next->parent_in_sec_off = dir->parent->parent_in_sec_off;
                    next->parent = dir->parent->parent;
                    next->children = dir->parent->children;
                } else if (FAT_name_cmp(next->name, ".")) {
                    next->was_explored = true;
                    next->parent_sector = dir->parent_sector;
                    next->parent_in_sec_off = dir->parent_in_sec_off;
                    next->parent = dir->parent;
                    next->children = dir->children;
                } else {
                    if (in_long_dir && !missing_long) {
                        assert(long_name != NULL && long_name_cur != NULL);
                        next->is_long_dir = true;
                        next->long_name = strdup(long_name_cur);
                        // FS_DBG("Short: %s, Long: %s\n", next->name, long_name_cur);
                    }
                    next->was_explored = false;
                    next->parent_sector = base_sec + sec_off;
                    next->parent_in_sec_off = k * sizeof(DirEntry_t);
                    next->parent = dir;

                    in_long_dir = false;
                    if (long_name) {
                        free(long_name);
                        long_name = NULL;
                        long_name_cur = NULL;
                    }
                    missing_long = -1;
                }
                dlist_tail_insert(dir->children, next);
                // FS_DBG("(%s) Added %s\n", next->is_dir ? "Dir" : "File", next->name);
            }
        }

        size_t FAT_entry;
        err = FAT_getNextEntry(clust, &FAT_entry);
        DBGERR(err, "Error retrieving FAT_entry\n");
        if (FAT_is_EOF(FAT_entry)) {
            break;
        }
        clust = FAT_entry;
        base_sec = FAT_cluster_base_sec(clust);
    }

FINISH_EXPLORE:
    dir->was_explored = true;
    return err;
}

static struct mmchsfs_handle *handle_open(struct mmchsfs_dirent *d)
{
    struct mmchsfs_handle *h = calloc(1, sizeof(*h));
    if (h == NULL) {
        return NULL;
    }

    d->refcount++;
    h->isdir = d->is_dir;
    h->dirent = d;

    errval_t err = FAT_global_handle(FAT_get_full_path(h), HANDLE_OPEN);
    if (err == FS_ERR_BUSY) {
        return NULL;
    }
    if (err_is_fail(err)) {
        DBGERRC(err, "Error opening handle globally\n");
        return NULL;
    }

    return h;
}

static inline void handle_close(struct mmchsfs_handle *h, char* old_path) {
    assert(h->dirent->refcount > 0);
    h->dirent->refcount--;

    errval_t err = FAT_global_handle(old_path ? old_path : FAT_get_full_path(h), HANDLE_CLOSE);
    DBGERRV(err, "Error opening handle globally\n");

    free(h->path);
    free(h);
}


static void dirent_remove_and_free(struct mmchsfs_dirent *entry) {
    if (entry == NULL) return;
    dlist_remove(entry->parent->children, entry);
    free(entry->name);
    free(entry);
}

static void dirent_insert(struct mmchsfs_dirent *parent, struct mmchsfs_dirent *entry) {
    assert(parent);
    assert(parent->is_dir);

    entry->parent = parent;
    dlist_tail_insert(parent->children, entry);
}

static struct mmchsfs_dirent *dirent_create(const char *name, bool is_dir)
{
    struct mmchsfs_dirent *d = calloc(1, sizeof(*d));
    if (d == NULL) {
        return NULL;
    }

    d->is_dir = is_dir;
    d->name = strdup(name);
    d->was_explored = false;

    return d;
}

static errval_t find_dirent(struct mmchsfs_dirent *root, const char *name,
                            struct mmchsfs_dirent **ret_de) {
    errval_t err;
    if (!root->is_dir) {
        return FS_ERR_NOTDIR;
    }
    // FS_DBG("Root: %s, Exploring %s\n", root->name, name);

    if (!root->was_explored) {
      // FS_DBG("%s not yet explored\n", root->name);
      err = mmchsfs_exploredir(root);
      DBGERR(err, "Error exploring directory\n");
    }

    struct mmchsfs_dirent* c = dlist_head(root->children);
    while (c != NULL) {
        if (c->is_long_dir) {
            // FS_DBG("|%s| ?= |%s|\n", c->long_name, name);
            if (!strcmp(c->long_name, name)) {
                *ret_de = c;
                return SYS_ERR_OK;
            }
        } else if (FAT_name_cmp(c->name, (char*) name)) {
            *ret_de = c;
            return SYS_ERR_OK;
        }
        c = dlist_next(c);
    }
    return FS_ERR_NOTFOUND;
}

static errval_t resolve_path(struct mmchsfs_dirent *root, const char *path,
                             struct mmchsfs_handle **ret_fh)
{
    errval_t err;

    // skip leading /
    size_t pos = 0;
    if (path[0] == FS_PATH_SEP) {
        pos++;
    }

    struct mmchsfs_dirent *next_dirent;

    while (path[pos] != '\0') {
        char *nextsep = strchr(&path[pos], FS_PATH_SEP);
        size_t nextlen;
        if (nextsep == NULL) {
            nextlen = strlen(&path[pos]);
        } else {
            nextlen = nextsep - &path[pos];
        }

        char pathbuf[nextlen + 1];
        memcpy(pathbuf, &path[pos], nextlen);
        pathbuf[nextlen] = '\0';

        // FS_DBG("> Finding %s from root %s\n", pathbuf, root->name);
        err = find_dirent(root, pathbuf, &next_dirent);
        if (err_is_fail(err)) {
            return err;
        }

        if (!next_dirent->is_dir && nextsep != NULL) {
            return FS_ERR_NOTDIR;
        }

        root = next_dirent;
        if (nextsep == NULL) {
            break;
        }

        pos += nextlen + 1;
    }

    /* create the handle */

    if (ret_fh) {
        struct mmchsfs_handle *fh = handle_open(root);
        if (fh == NULL) {
            return LIB_ERR_MALLOC_FAIL;
        }

        fh->path = strdup(path);
        //fh->common.mount = root;

        *ret_fh = fh;
    }

    return SYS_ERR_OK;
}

errval_t mmchsfs_open(void *st, const char *_path, mmchsfs_handle_t *rethandle) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    const char* path = _path + sd_mountpoint_len;
    // FS_DBG("Looking for %s\n", path);
    struct mmchsfs_mount *mount = st;

    struct mmchsfs_handle *handle;
    err = resolve_path(mount->root, path, &handle);
    if (err_is_fail(err)) {
        return err;
    }

    if (handle->isdir) {
        handle_close(handle, NULL);
        return FS_ERR_NOTFILE;
    }

    *rethandle = handle;

    return SYS_ERR_OK;
}

errval_t mmchsfs_create(void *st, const char *_path, mmchsfs_handle_t *rethandle) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    const char* path = _path + sd_mountpoint_len;
    // FS_DBG("Looking for %s\n", path);

    struct mmchsfs_mount *mount = st;
    struct mmchsfs_handle *handle;
    err = resolve_path(mount->root, path, &handle);
    if (err_is_ok(err)) {
        return FS_ERR_EXISTS;
    }


    struct mmchsfs_handle *parent = NULL;
    const char *childname;

    // find parent directory
    char *lastsep = strrchr(path, FS_PATH_SEP);
    if (lastsep != NULL) {
        childname = lastsep + 1;

        size_t pathlen = lastsep - path;
        char pathbuf[pathlen + 1];
        memcpy(pathbuf, path, pathlen);
        pathbuf[pathlen] = '\0';

        // resolve parent directory
        err = resolve_path(mount->root, pathbuf, &parent);
        if (err_is_fail(err)) {
            return err;
        } else if (!parent->isdir) {
            return FS_ERR_NOTDIR; // parent is not a directory
        }
    } else {
        childname = path;
    }

    childname = to_FAT_name(childname);
    struct mmchsfs_dirent *dirent = dirent_create(childname, false);
    if (dirent == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    if (parent) {
        dirent_insert(parent->dirent, dirent);
        handle_close(parent, NULL);
    } else {
        dirent_insert(mount->root, dirent);
    }
    
    // FS_DBG("Creating |%s| in |%s|\n", dirent->name, dirent->parent->name);

    // Find a free cluster to assign to the new DirEntry
    uint32_t free_clust;
    err = FAT_free_cluster(&free_clust);
    DBGERR(err, "Error getting a free cluster\n");
    // Mark the next cluster of `free_clust` as EOC
    err = set_FAT_entry(free_clust, 0, FAT32_EOC);
    DBGERR(err, "Error settings FAT entry\n");

    dirent->cluster = free_clust;

    // Find a free DirEntry in parent
    dirent->is_dir = false;
    size_t parent_off = 0;
    err = FAT_free_DirEntry(dirent->parent, &parent_off);
    DBGERR(err, "Error finding free entry\n");
    dirent->parent_sector = FAT_cluster_base_sec(dirent->parent->cluster) + FAT_sec_off(parent_off);
    dirent->parent_in_sec_off = FAT_in_sec_off(parent_off);
    // Insert DirEntry in parent
    DirEntry_t *DirEntry = dirent_to_DirEntry(dirent);
    // FAT_dumpDirEntry(dirent_to_DirEntry(dirent->parent));
    // FAT_dumpDirEntry(DirEntry);

    err = write_part_block(dirent->parent_sector, dirent->parent_in_sec_off, sizeof(DirEntry_t), (char*) DirEntry);
    DBGERR(err, "Error writing new entry in parent\n");

    err = FAT_inc_seq();
    DBGERR(err, "Error checking sequence\n");

    if (rethandle) {
        struct mmchsfs_handle *fh = handle_open(dirent);
        if (fh  == NULL) {
            return LIB_ERR_MALLOC_FAIL;
        }
        fh->path = strdup(path);
        *rethandle = fh;
    }

    return SYS_ERR_OK;
}

errval_t mmchsfs_remove(void *st, const char *_path) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    const char* path = _path + sd_mountpoint_len;
    // FS_DBG("Removing %s\n", path);

    struct mmchsfs_mount *mount = st;
    struct mmchsfs_handle *handle;
    err = resolve_path(mount->root, path, &handle);
    if (err_is_fail(err)) {
        return err;
    }

    if (handle->isdir) {
        return FS_ERR_NOTFILE;
    }

    char* old_full_path = FAT_get_full_path(handle);
    err = FAT_global_handle(old_full_path, HANDLE_LOCK);
    if (err == FS_ERR_BUSY) {
        handle_close(handle, NULL);
        return FS_ERR_BUSY;
    }
    DBGERR(err, "Error opening handle globally\n");


    struct mmchsfs_dirent *dirent = handle->dirent;
    if (dirent->refcount != 1) {
        err = FAT_global_handle(FAT_get_full_path(handle), HANDLE_UNLOCK);
        DBGERR(err, "Error opening handle globally\n");

        handle_close(handle, NULL);
        return FS_ERR_BUSY;
    }

    /*
     * Remove the DirEntry from the parent, and set the
     * entry in the FAT to empty
     */
    // TODO: Only short names
    // FS_DBG("Removing %s from %s\n", dirent->name, dirent->parent->name);
    char* old_name = strdup(dirent->name);
    // TODO: Set this to 0x0 if it is the last entry of the dir
    dirent->name[0] = 0xe5; // Meaning that this is free
    err = FAT_updateDirEntry(dirent, old_name);
    DBGERR(err, "Error updating DirEntry\n");
    err = FAT_clear_chain(dirent->cluster);
    DBGERR(err, "Error clearing chain\n");

    err = FAT_inc_seq();
    DBGERR(err, "Error checking sequence\n");

    err = FAT_global_handle(old_full_path, HANDLE_UNLOCK);
    DBGERR(err, "Error opening handle globally\n");

    dirent_remove_and_free(dirent);

    return SYS_ERR_OK;
}

errval_t mmchsfs_read(void *st, mmchsfs_handle_t handle, void *buffer, size_t bytes,
                           size_t *bytes_read) {
    errval_t err;
    struct mmchsfs_handle *h = handle;

    if (h->isdir) {
        return FS_ERR_NOTFILE;
    }

    assert(h->file_pos >= 0);

    if (h->dirent->size < h->file_pos) {
        bytes = 0;
    } else if (h->dirent->size < h->file_pos + bytes) {
        bytes = h->dirent->size - h->file_pos;
        assert(h->file_pos + bytes == h->dirent->size);
    }

    err = read_buf(buffer, bytes, h->dirent->cluster, h->file_pos, bytes_read, &h->last_cluster);
    DBGERR(err, "Error reading from file\n");

    h->file_pos += bytes;
    *bytes_read = bytes;

    return SYS_ERR_OK;
}

errval_t mmchsfs_write(void *st, mmchsfs_handle_t handle, void *buffer,
                            size_t bytes, size_t *bytes_written) {
    errval_t err;
    struct mmchsfs_handle *h = handle;
    assert(h->file_pos >= 0);

    if (h->isdir) {
        return FS_ERR_NOTFILE;
    }

    // FS_DBG("Writing to %s\n", h->dirent->name);

    // If we are writing over the end of the file, we need 
    // to add EOF at the end of the buffer
    // if (h->file_pos + bytes >= h->dirent->size && ((uint8_t*)buffer)[bytes-1] != EOF) {
      // USER_PANIC("NYI");
    // }

    err = write_buf(buffer, bytes, h->dirent->cluster, h->file_pos, bytes_written, &h->last_cluster);
    DBGERR(err, "Error writing to file\n");

    // TODO: Seems like we cannot now the flags the file,
    // was opened with, so it is only assumed O_TRUNC
    h->file_pos += *bytes_written;
    h->dirent->size = h->file_pos;
    FAT_updateDirEntry(h->dirent, NULL);

    return SYS_ERR_OK;
}


errval_t mmchsfs_truncate(void *st, mmchsfs_handle_t handle, size_t bytes) {
    errval_t err;
    struct mmchsfs_handle *h = handle;

    if (h->isdir) {
        return FS_ERR_NOTFILE;
    }

    char *buffer = calloc(sizeof(char), bytes);
    if (buffer == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }
    size_t bytes_written;
    err = write_buf(buffer, bytes, h->dirent->cluster, h->file_pos, &bytes_written, &h->last_cluster);
    DBGERR(err, "Error writing to file\n");

    // Remove the next clusters
    uint32_t FAT_next_entry = -1;
    err = FAT_getNextEntry(h->last_cluster, &FAT_next_entry);
    DBGERR(err, "Error etrieving next entry\n");
    err = FAT_clear_chain(FAT_next_entry);
    DBGERR(err, "Error cleaning remainder chain of truncated file\n");
    h->dirent->size = bytes;
   free(buffer);

    return SYS_ERR_OK;
}

errval_t mmchsfs_tell(void *st, mmchsfs_handle_t handle, size_t *pos)
{
    struct mmchsfs_handle *h = handle;
    if (h->isdir) {
        *pos = 0;
    } else {
        *pos = h->file_pos;
    }
    return SYS_ERR_OK;
}

errval_t mmchsfs_stat(void *st, mmchsfs_handle_t inhandle, struct fs_fileinfo *info)
{
    struct mmchsfs_handle *h = inhandle;

    assert(info != NULL);
    info->type = h->isdir ? FS_DIRECTORY : FS_FILE;
    info->size = h->dirent->size;

    return SYS_ERR_OK;
}

errval_t mmchsfs_seek(void *st, mmchsfs_handle_t handle, enum fs_seekpos whence,
                     off_t offset)
{
    struct mmchsfs_handle *h = handle;
    struct fs_fileinfo info;
    errval_t err;

    switch (whence) {
    case FS_SEEK_SET:
        assert(offset >= 0);
        if (h->isdir) {
            h->dir_pos = dlist_head(h->dirent->children);
            for (size_t i = 0; i < offset; i++) {
                if (h->dir_pos  == NULL) {
                    break;
                }
                h->dir_pos = dlist_next(h->dir_pos);
            }
        } else {
            // Invalidate last_clust, next time a search will be needed
            h->last_cluster = 0;
            h->file_pos = offset;
        }
        break;

    case FS_SEEK_CUR:
        if (h->isdir) {
            assert(!"NYI");
        } else {
            assert(offset >= 0 || -offset <= h->file_pos);
            // Invalidate last_clust, next time a search will be needed
            h->last_cluster = 0;
            h->file_pos += offset;
        }

        break;

    case FS_SEEK_END:
        if (h->isdir) {
            assert(!"NYI");
        } else {
            err = mmchsfs_stat(st, handle, &info);
            if (err_is_fail(err)) {
                return err;
            }
            assert(offset >= 0 || -offset <= info.size);
            // Invalidate last_clust, next time a search will be needed
            h->last_cluster = 0;
            h->file_pos = info.size + offset;
        }
        break;

    default:
        USER_PANIC("invalid whence argument to mmchsfs seek");
    }

    return SYS_ERR_OK;
}

errval_t mmchsfs_close(void *st, mmchsfs_handle_t inhandle) {
    errval_t err = SYS_ERR_OK;
    struct mmchsfs_handle *handle = inhandle;
    if (handle->isdir) {
        return FS_ERR_NOTFILE;
    }

    handle_close(handle, NULL);
    return err;
}

errval_t mmchsfs_opendir(void *st, const char *_path, mmchsfs_handle_t *rethandle) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    // Skip the mountpoint
    const char* path = _path + sd_mountpoint_len;
    struct mmchsfs_mount *mount = st;

    struct mmchsfs_handle *handle;
    err = resolve_path(mount->root, path, &handle);
    if (err_is_fail(err)) {
        return err;
    }

    if (!handle->isdir) {
        handle_close(handle, NULL);
        return FS_ERR_NOTDIR;
    }

    if (!handle->dirent->was_explored) {
        err = mmchsfs_exploredir(handle->dirent);
        DBGERR(err, "Error exploring directory\n");
    }

    // Set `dir_pos` to the first children
    handle->dir_pos = dlist_head(handle->dirent->children);
    *rethandle = handle;

    return SYS_ERR_OK;
}

errval_t mmchsfs_dir_read_next(void *st, mmchsfs_handle_t inhandle, char **retname,
                              struct fs_fileinfo *info)
{
    struct mmchsfs_handle *h = inhandle;

    if (!h->isdir) {
        return FS_ERR_NOTDIR;
    }

    struct mmchsfs_dirent *d = h->dir_pos;
    if (d == NULL) {
        return FS_ERR_INDEX_BOUNDS;
    }


    *retname = calloc(128, 1);
    if (retname != NULL) {
        if (d->is_long_dir){
            if (d->is_dir) {
                strcpy(*retname, "\x1b[32;1m");
                strcat(*retname, d->long_name);
                strcat(*retname, "\x1b[0m");
            } else {
                *retname = strdup(d->long_name);
            }
        }
        else {
            if (d->is_dir) {
                strcpy(*retname, "\x1b[32;1m");
                strcat(*retname, d->name);
                strcat(*retname, "\x1b[0m");
            } else {
                *retname = strdup(d->name);
            }
        }
    }

    if (info != NULL) {
        info->type = d->is_dir ? FS_DIRECTORY : FS_FILE;
        info->size = d->size;
    }

    h->dir_pos = dlist_next(d);

    return SYS_ERR_OK;
}

errval_t mmchsfs_closedir(void *st, mmchsfs_handle_t dhandle)
{
    struct mmchsfs_handle *handle = dhandle;
    if (!handle->isdir) {
        return FS_ERR_NOTDIR;
    }

    handle_close(handle, NULL);
    // Already carried out by the previous instruction
    // free(handle->path);
    // free(handle);

    return SYS_ERR_OK;
}

errval_t mmchsfs_mkdir(void *st, const char *_path) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    const char* path = _path + sd_mountpoint_len;

    struct mmchsfs_mount *mount = st;

    err = resolve_path(mount->root, path, NULL);
    if (err_is_ok(err)) {
        return FS_ERR_EXISTS;
    }

    struct mmchsfs_handle *parent  = NULL;
    const char *childname;

    // find parent directory
    char *lastsep = strrchr(path, FS_PATH_SEP);
    if (lastsep != NULL) {
        childname = lastsep + 1;

        size_t pathlen = lastsep - path;
        char pathbuf[pathlen + 1];
        memcpy(pathbuf, path, pathlen);
        pathbuf[pathlen] = '\0';

        // resolve parent directory
        err = resolve_path(mount->root, pathbuf, &parent);
        if (err_is_fail(err)) {
            handle_close(parent, NULL);
            return err;
        } else if (!parent->isdir) {
            handle_close(parent, NULL);
            return FS_ERR_NOTDIR; // parent is not a directory
        }
    } else {
        childname = path;
    }

    // FS_DBG("|%s| -> |%s|\n", childname, to_FAT_name(childname));
    childname = to_FAT_name(childname);
    struct mmchsfs_dirent *dirent = dirent_create(childname, true);
    if (dirent == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    if (parent) {
        dirent_insert(parent->dirent, dirent);
        handle_close(parent, NULL);
    } else {
        dirent_insert(mount->root, dirent);
    }

    // FS_DBG("Creating |%s| in |%s|\n", dirent->name, dirent->parent->name);

    // Find a free cluster to assign to the new DirEntry
    uint32_t free_clust;
    err = FAT_free_cluster(&free_clust);
    DBGERR(err, "Error getting a free cluster\n");
    // Mark the next cluster of `free_clust` as EOC
    err = set_FAT_entry(free_clust, 0, FAT32_EOC);
    DBGERR(err, "Error settings FAT entry\n");

    dirent->cluster = free_clust;

    // Find a free DirEntry in parent
    dirent->is_dir = true;
    size_t parent_off = 0;
    err = FAT_free_DirEntry(dirent->parent, &parent_off);
    DBGERR(err, "Error finding free entry\n");
    dirent->parent_sector = FAT_cluster_base_sec(dirent->parent->cluster) + FAT_sec_off(parent_off);
    dirent->parent_in_sec_off = FAT_in_sec_off(parent_off);
    // Insert DirEntry in parent
    DirEntry_t *DirEntry = dirent_to_DirEntry(dirent);
    // FAT_dumpDirEntry(dirent_to_DirEntry(dirent->parent));
    // FAT_dumpDirEntry(DirEntry);

    err = write_part_block(dirent->parent_sector, dirent->parent_in_sec_off, sizeof(DirEntry_t), (char*) DirEntry);
    DBGERR(err, "Error writing new entry in parent\n");

    // Clean the cluster relative to the new Dir
    char *zeros = calloc(sizeof(char), BPB_BytesPerSec);
    size_t base_sec = FAT_cluster_base_sec(dirent->cluster);
    for (int i = 0; i < BPB_SecPerClus; i++) {
        err = write_block(base_sec + i, zeros);
        DBGERR(err, "Error zeroing out section\n");
    }

    // Add dot and dotdot entries as 1st and 2nd entries
    struct mmchsfs_dirent dot = {0};
    dot.name = ".";
    dot.is_dir = true;
    dot.cluster = dirent->cluster;
    DirEntry_t *Dot = dirent_to_DirEntry(&dot);
    // FAT_dumpDirEntry(Dot);
    memcpy(Dot->_Name, ".", 1);
    err = write_part_block(FAT_cluster_base_sec(dirent->cluster), 0, sizeof(DirEntry_t), (char*) Dot);
    DBGERR(err, "Error writing dot entry in parent\n");
    free(Dot);

    struct mmchsfs_dirent dotdot = {0};
    dotdot.name = "..";
    dotdot.is_dir = true;
    dotdot.cluster = dirent->parent->cluster;
    DirEntry_t *Dotdot = dirent_to_DirEntry(&dotdot);
    memcpy(Dotdot->_Name, "..", 2);
    // FAT_dumpDirEntry(Dotdot);
    err = write_part_block(FAT_cluster_base_sec(dirent->cluster), sizeof(DirEntry_t), sizeof(DirEntry_t), (char*) Dotdot);
    DBGERR(err, "Error writing dot entry in parent\n");
    free(Dotdot);

    err = FAT_inc_seq();
    DBGERR(err, "Error checking sequence\n");

    return SYS_ERR_OK;
}



errval_t mmchsfs_rmdir(void *st, const char *_path) {
    errval_t err;

    err = FAT_check_seq(false);
    DBGERR(err, "Error checking sequence\n");

    if (!is_entity_in_sd(_path)) {
        debug_printf("Path not belonging to this mount\n");
        return FS_ERR_NOTFOUND;
    }
    const char* path = _path + sd_mountpoint_len;
    // FS_DBG("Removing %s\n", path);

    struct mmchsfs_mount *mount = st;
    struct mmchsfs_handle *handle;
    err = resolve_path(mount->root, path, &handle);
    if (err_is_fail(err)) {
        return err;
    }

    char* old_full_path = FAT_get_full_path(handle);
    err = FAT_global_handle(old_full_path, HANDLE_LOCK);
    if (err == FS_ERR_BUSY) {
        handle_close(handle, NULL);
        return FS_ERR_BUSY;
    }
    DBGERR(err, "Error opening handle globally\n");


    if (!handle->isdir) {
        err = FS_ERR_NOTDIR;
        handle_close(handle, NULL);
        goto out;
    }

    struct mmchsfs_dirent *dirent = handle->dirent;
    if (dirent->refcount != 1) {
        err = FAT_global_handle(FAT_get_full_path(handle), HANDLE_UNLOCK);
        DBGERR(err, "Error opening handle globally\n");

        handle_close(handle, NULL);
        return FS_ERR_BUSY;
    }


    if (!dirent->was_explored) {
        err = mmchsfs_exploredir(dirent);
        if (!err_is_ok(err)) {
            handle_close(handle, NULL);
            goto out;
        }
    }
    assert(dirent->was_explored);
    if (dirent->children != NULL && dlist_head(dirent->children) != NULL) {
        // Check if only two children are dot and dotdot
        bool empty = true;
        struct mmchsfs_dirent *d = dlist_head(dirent->children);
        while (d) {
            if (FAT_name_cmp(d->name, "..") || FAT_name_cmp(d->name, ".")) {
                d = dlist_next(d);
                continue;
            }
            d = dlist_next(d);
            empty = false;
            break;
        }
        if (!empty) {
            err = FS_ERR_NOTEMPTY;
            handle_close(handle, NULL);
            goto out;
        }
    }

    /*
     * Remove the DirEntry from the parent, and set the
     * entry in the FAT to empty
     */
    // TODO: Only short names
    // FS_DBG("Removing %s from %s\n", dirent->name, dirent->parent->name);
    char* old_name = strdup(dirent->name);
    // TODO: Set this to 0x0 if it is the last entry of the dir
    dirent->name[0] = 0xe5; // Meaning that this is free
    err = FAT_updateDirEntry(dirent, old_name);
    DBGERR(err, "Error updating DirEntry\n");
    err = FAT_clear_chain(dirent->cluster);
    DBGERR(err, "Error clearing chain\n");

    dirent_remove_and_free(handle->dirent);
    handle_close(handle, old_name);

    err = FAT_inc_seq();
    DBGERR(err, "Error checking sequence\n");

    out:

    err = FAT_global_handle(old_full_path, HANDLE_UNLOCK);
    DBGERR(err, "Error opening handle globally\n");

    return err;
}


errval_t mmchsfs_mount(const char *uri, const char* path, mmchsfs_mount_t *retst) {
    /* Setup channel and connect to service */
    /* TODO: setup channel to init for multiboot files */

    errval_t err = SYS_ERR_OK;
    struct mmchsfs_mount *mount = calloc(1, sizeof(struct mmchsfs_mount));
    if (mount == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    struct mmchsfs_dirent *mmchsfs_root;

    mmchsfs_root = calloc(1, sizeof(*mmchsfs_root));
    if (mmchsfs_root == NULL) {
       free(mmchsfs_mount);
        return LIB_ERR_MALLOC_FAIL;
    }

    mmchsfs_root->size = 0;
    mmchsfs_root->is_dir = true;
    mmchsfs_root->name = "/";
    mmchsfs_root->parent = NULL;
    mmchsfs_root->cluster = 2; // First cluster is always 2
    mmchsfs_root->is_root = true; // First cluster is always 2

    mount->root = mmchsfs_root;
    *retst = mount;

    sd_root = mmchsfs_root;
    set_sd_mountpoint(path);
    initialize_constants();
    
    /*
     * We pre-explore the root directory since it is going
     * to be the starting point for all of the
     * subsequent searches
     */
    err = mmchsfs_exploredir(mount->root);
    DBGERR(err, "Error exploring root\n");

    err = FAT_check_seq(true);
    DBGERR(err, "Error getting sequence of first time\n");

    FS_DBG("[ Stats ] BPB_FATSz32: %d, BPB_BytesPerSec: %d, BPB_SecPerClus: %d\n", BPB_FATSz32, BPB_BytesPerSec, BPB_SecPerClus);
    return err;
}
