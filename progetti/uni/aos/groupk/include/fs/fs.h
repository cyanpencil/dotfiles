/**
 * \file fs.h
 * \brief
 */

#ifndef INCLUDE_FS_FS_H_
#define INCLUDE_FS_FS_H_

#include <urpc.h>

#define MCACHE_LINE_SIZE            32
#define MAX_FILE_NAME 128

// Urpc with the mmchs driver
urpc_s mmchs_urpc;

// The mount point for the SD-Card
char* sd_mountpoint;
size_t sd_mountpoint_len;

inline void set_sd_mountpoint(const char* mountpoint) {
    sd_mountpoint = strdup(mountpoint);
    sd_mountpoint_len = strlen(sd_mountpoint);
}
/*
 * Checks whether the path is in the SD mountpoint
 * (i.e. if the path starts with sd_mountpoint
 */
inline bool is_entity_in_sd(const char* path) {
    return strncmp(path, sd_mountpoint, sd_mountpoint_len) == 0;
}


#define FS_DBG(msg, ...) ;
/*
    do { \
        debug_printf("[\x1b[36mFs\x1b[0m] "msg, ##__VA_ARGS__); \
    } while (0);*/


#define FS_PATH_SEP '/'

/// Enum defining interpretation of offset argument to #vfs_seek
enum fs_seekpos {
    FS_SEEK_SET,   ///< Offset relative to start of file
    FS_SEEK_CUR,   ///< Offset relative to current position
    FS_SEEK_END,   ///< Offset relative to end of file
};

enum fs_filetype {
    FS_FILE,       ///< Regular file
    FS_DIRECTORY,  ///< Directory
};

/// Data returned from #fs_stat
struct fs_fileinfo {
    enum fs_filetype type;  ///< Type of the object
    size_t size;            ///< Size of the object (in bytes, for a regular file)
};


/*
 * Request to/Response from the mmchs driver
 */
enum MMCHS_OP {
    READ,
    WRITE,
    GET_SEQ,
    INC_SEQ,
    HANDLE
};

enum HANDLE_OP {
    HANDLE_OPEN,
    HANDLE_CLOSE,
    HANDLE_LOCK,
    HANDLE_UNLOCK
};

// Abstract request
typedef struct mmchs_block_req_s {
    enum MMCHS_OP op;
    size_t block_no;
} mmchs_block_req;

// Read request
typedef struct mmchs_block_read_req_s {
    mmchs_block_req base;
} mmchs_block_read_req;

// Write request
typedef struct mmchs_block_write_req_s {
    mmchs_block_req base;
    char chunk[BASE_PAGE_SIZE/2];
} mmchs_block_write_req;

// Handle request
typedef struct mmchs_block_handle_req_s {
    mmchs_block_req base;
    char filename[MAX_FILE_NAME];
    enum HANDLE_OP op;
} mmchs_block_handle_req;

// Abstract response
typedef struct mmchs_block_res_s {
    errval_t err;
} mmchs_block_res;

// Read response
typedef struct mmchs_block_read_res_s {
    mmchs_block_res base;
    char chunk[BASE_PAGE_SIZE/2];
} mmchs_block_read_res;

// Write response
typedef struct mmchs_block_write_res_s {
    mmchs_block_res base;
} mmchs_block_write_res;

// Seq response
typedef struct mmchs_block_seq_res_s {
    mmchs_block_res base;
    uint64_t seq;
} mmchs_block_seq_res;

/*
 * Copyright (c) 2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetsstrasse 6, CH-8092 Zurich. Attn: Systems Group.
 */

/**
 * @brief initializes the filesystem library
 *
 * @return SYS_ERR_OK on success
 *         errval on failure
 *
 * NOTE: This has to be called before any access to the files
 */
errval_t filesystem_init(void);
errval_t filesystem_init_from_init(struct aos_rpc* chan);
errval_t filesystem_init_from_init_cross(struct capref frame);

/**
 * @brief mounts the URI at a give path
 *
 * @param path  path to mount the URI
 * @param uri   uri to mount
 *
 * @return SYS_ERR_OK on success
 *         errval on error
 *
 * This mounts the uri at a given, existing path.
 */
errval_t filesystem_mount(const char *path, const char *uri);


/*
 * ===========================================================================
 * Below: part of the master solution
 * ===========================================================================
 */

/**
 * @brief acquires a block from the fil
 * @param id    id of the block to read
 * @param data  returns data
 *
 * @return SYS_ERR_OK on success, errval on failure
 */
errval_t filesystem_block_acquire(size_t id, uint8_t *data);

/**
 * @brief writes back a block to disk
 * @param id    id of the block to read
 * @param data  data to write
 *
 * @return SYS_ERR_OK on success, errval on failure
 */
errval_t filesystem_block_write_back(size_t id, uint8_t *data);

#endif /* INCLUDE_FS_FS_H_ */
