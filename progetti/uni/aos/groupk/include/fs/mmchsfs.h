/*
 * Copyright (c) 2016, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetstr 6, CH-8092 Zurich. Attn: Systems Group.
 */

#ifndef FS_MMCHSFS_H_
#define FS_MMCHSFS_H_

#include <fs/fs.h>

typedef void *ramfs_handle_t;
typedef void *ramfs_mount_t;

typedef void *mmchsfs_handle_t;
typedef void *mmchsfs_mount_t;
struct mmchsfs_dirent *sd_root;


/*
 * The BPB section of the FAT filesystem
 */
typedef struct BPB_s {
    // FAT independent
    char _BS_jmpBoot[3];
    char _BS_OEMName[8];
    char _BytesPerSec[2];
    char _SecPerClus[1];
    char _ResvdSecCnt[2];
    char _NumFATs[1];
    char _RootEntCnt[2];
    char _TotSec16[2];
    char _Media[1];
    char _FATSz16[2];
    char _BPB_SecPerTrk[2];
    char _NumHead[2];
    char _HiddSec[4];
    char _TotSec32[4];

    // FAT32 specific
    char _FATSz32[4];
    char _ExtFlags[2];
    char _FSVer[2];
    char _RootClus[4];
    char _FSInfo[2];
    char _BkBootSec[2];
    char _Reserved[12];
    char _BS_DrvNum[1];
    char _BS_Reserved1[1];
    char _BS_BootSig[1];
    char _BS_VoIID[4];
    char _BS_VoILab[11];
    char _BS_FilSysType[8];
} BPB_t;

typedef struct DirEntry_s {
    char _Name[11];
    char _Attr[1];
    char _NTRes[1];
    char _CrtTimeTenth[1];
    char _CrtTime[2];
    char _CrtDate[2];
    char _LstAccDate[2];
    char _FstClusHI[2];
    char _WrtTime[2];
    char _WrtDate[2];
    char _FstClusLO[2];
    char _FileSize[4];
} DirEntry_t;

typedef struct LongDirEntry_s {
    char _Ord[1];
    char _Name1[10];
    char _Attr[1];
    char _Type[1];
    char _Chksum[1];
    char _Name2[12];
    char _FstClusLO[2];
    char _Name3[4];
} LongDirEntry_t;

/*
 * Useful constant needed by the FAT filesystem
 */
#define FAT32_EOC 0x0ffffff8
#define DIR_ATTR_READ_ONLY 0x01
#define DIR_ATTR_HIDDEN 0x02
#define DIR_ATTR_SYSTEM 0x04
#define DIR_ATTR_VOLUME_ID 0x08
#define DIR_ATTR_DIRECTORY 0x10
#define DIR_ATTR_ARCHIVE 0x20
#define DIR_ATTR_LONG_NAME \
    (DIR_ATTR_READ_ONLY | \
    DIR_ATTR_HIDDEN | \
    DIR_ATTR_SYSTEM | \
    DIR_ATTR_VOLUME_ID)
/*
 * BPB typed values
 */
uint16_t BPB_RootEntCnt;
uint16_t BPB_BytesPerSec;
uint16_t BPB_ResvdSecCnt;
uint8_t  BPB_NumFATs;
uint32_t BPB_FATSz32;
uint32_t BPB_TotSec32;
uint8_t  BPB_SecPerClus;
uint32_t FATSz;
size_t RootDirSectors;
size_t FirstDataSector;
size_t CountOfClusters;
size_t DataSec;

errval_t read_block(size_t block_num, mmchs_block_read_res** ret_res);
errval_t write_block(size_t block_num, char* buf);

errval_t write_part_block(size_t block_num, size_t in_block_off, size_t bytes, char* buf);

errval_t mmchsfs_open(void *st, const char *path, mmchsfs_handle_t *rethandle);

errval_t mmchsfs_create(void *st, const char *path, mmchsfs_handle_t *rethandle);

errval_t mmchsfs_remove(void *st, const char *path);

errval_t mmchsfs_read(void *st, mmchsfs_handle_t handle, void *buffer, size_t bytes,
                    size_t *bytes_read);

errval_t mmchsfs_write(void *st, mmchsfs_handle_t handle, void *buffer,
                     size_t bytes, size_t *bytes_written);

errval_t mmchsfs_truncate(void *st, mmchsfs_handle_t handle, size_t bytes);

errval_t mmchsfs_tell(void *st, mmchsfs_handle_t handle, size_t *pos);

errval_t mmchsfs_stat(void *st, mmchsfs_handle_t inhandle, struct fs_fileinfo *info);

errval_t mmchsfs_seek(void *st, mmchsfs_handle_t handle, enum fs_seekpos whence,
                    off_t offset);

errval_t mmchsfs_close(void *st, mmchsfs_handle_t inhandle);

errval_t mmchsfs_opendir(void *st, const char *path, mmchsfs_handle_t *rethandle);

errval_t mmchsfs_dir_read_next(void *st, mmchsfs_handle_t inhandle, char **retname,
                             struct fs_fileinfo *info);

errval_t mmchsfs_closedir(void *st, mmchsfs_handle_t dhandle);

errval_t mmchsfs_mkdir(void *st, const char *path);

errval_t mmchsfs_rmdir(void *st, const char *path);

errval_t mmchsfs_mount(const char *uri, const char* path, mmchsfs_mount_t *retst);

#endif /* FS_MMCHSFS_H_ */
