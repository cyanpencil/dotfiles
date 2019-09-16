/*
 * Copyright (c) 2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetsstrasse 6, CH-8092 Zurich. Attn: Systems Group.
 */

/*
 * \file fs.c
 * \brief Filesystem support library
 */

#include <aos/aos.h>
#include <fs/fs.h>
#include <fs/dirent.h>
#include <fs/ramfs.h>
#include <fs/mmchsfs.h>
#include <aos/aos_rpc.h>
#include <urpc.h>
#include <aos/deferred.h>

#include "fs_internal.h"

mmchsfs_mount_t mount;

/**
 * @brief initializes the filesystem library
 *
 * @return SYS_ERR_OK on success
 *         errval on failure
 *
 * NOTE: This has to be called before any access to the files */
errval_t filesystem_init(void) {
    errval_t err;

    // Connect to the mmchs driver
    char* buf;
    err = aos_rpc_new_remote_bind_4ever("mmchs_service", BASE_PAGE_SIZE, (void**) &buf);
    DBGERR(err, "Error binding to mmchs_service\n");
    urpc_s_init((char*) buf, &mmchs_urpc, true);
    char* msg;
    size_t len;
    err = urpc_read_msg(&mmchs_urpc, (void**) &msg, &len);
    DBGERR(err, "Error receiving message over urpc\n");
    if (strcmp("ACK", msg)) {
        return MMC_ERR_TRANSFER;
    }
    mmchs_block_read_res* res;
    err = read_block(0, &res);
    DBGERR(err, "Error reading block 0\n");
    // Check magic numbers of Sector 0
    assert(res->chunk[510] == 0x55 && res->chunk[511] == 0xaa);

    memcpy(&BPB, res->chunk, sizeof(BPB_t));
    free(res);
    FS_DBG("(@%d) File System Type %s\n", offsetof(struct BPB_s, _BS_FilSysType), BPB._BS_FilSysType);

    // err = ramfs_mount("/", &ram_mount);
    // if (err_is_fail(err)) {
        // return err;
    // }


    return SYS_ERR_OK;
}

errval_t filesystem_init_from_init(struct aos_rpc* chan) {
    errval_t err;

    // Connect to the mmchs driver
    char* buf = NULL;
    struct capref frame;
    size_t bytes;
    size_t size = BASE_PAGE_SIZE;
    err = frame_alloc(&frame, size, &bytes);
    DBGERR(err, "Error allocating frame\n");
    err = paging_map_frame_attr(get_current_paging_state(), (void**) &buf, size, frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Error binding frame with service\n");
    err = aos_rpc_send_req(RPC_BIND_FRAME, chan, "AAAAAAAA", 8, frame);
    DBGERR(err, "Error binding frame to service\n");

    urpc_s_init((char*) buf, &mmchs_urpc, true);
    char* msg;
    size_t len;
    err = urpc_read_msg(&mmchs_urpc, (void**) &msg, &len);
    DBGERR(err, "Error receiving message over urpc\n");
    if (strcmp("ACK", msg)) {
        return MMC_ERR_TRANSFER;
    }
    mmchs_block_read_res* res;
    err = read_block(0, &res);
    DBGERR(err, "Error reading block 0\n");
    // Check magic numbers of Sector 0
    assert(res->chunk[510] == 0x55 && res->chunk[511] == 0xaa);

    memcpy(&BPB, res->chunk, sizeof(BPB_t));
    free(res);
    FS_DBG("(@%d) File System Type %s\n", offsetof(struct BPB_s, _BS_FilSysType), BPB._BS_FilSysType);

    // err = ramfs_mount("/", &ram_mount);
    // if (err_is_fail(err)) {
        // return err;
    // }


    return SYS_ERR_OK;
}

errval_t filesystem_init_from_init_cross(struct capref frame) {
    errval_t err;

    // Connect to the mmchs driver
    char* buf = NULL;
    size_t size = BASE_PAGE_SIZE;
    err = paging_map_frame_attr(get_current_paging_state(), (void**) &buf, size, frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Error binding frame with service\n");

    urpc_s_init((char*) buf, &mmchs_urpc, true);
    char* msg;
    size_t len;
    err = urpc_read_msg(&mmchs_urpc, (void**) &msg, &len);
    DBGERR(err, "Error receiving message over urpc\n");
    if (strcmp("ACK", msg)) {
        return MMC_ERR_TRANSFER;
    }
    mmchs_block_read_res* res;
    err = read_block(0, &res);
    DBGERR(err, "Error reading block 0\n");
    // Check magic numbers of Sector 0
    assert(res->chunk[510] == 0x55 && res->chunk[511] == 0xaa);

    memcpy(&BPB, res->chunk, sizeof(BPB_t));
    free(res);
    FS_DBG("(@%d) File System Type %s\n", offsetof(struct BPB_s, _BS_FilSysType), BPB._BS_FilSysType);

    // err = ramfs_mount("/", &ram_mount);
    // if (err_is_fail(err)) {
        // return err;
    // }


    return SYS_ERR_OK;
}

/**
 * @brief mounts the URI at a given path
 *
 * @param path  path to mount the URI
 * @param uri   uri to mount
 *
 * @return SYS_ERR_OK on success
 *         errval on error
 *
 * This mounts the uri at a given, existing path.
 *
 * path: service-name://fstype/params
 */
errval_t filesystem_mount(const char *path, const char *uri)
{
    errval_t err;
    char* uri_m = malloc(strlen(uri)+1);
    strcpy(uri_m, uri);
    char* ptr = NULL;
    char* service_name = strtok_r((char*)uri_m, "://", &ptr);
    if (service_name == NULL) {
        return VFS_ERR_BAD_URI;
    }
    char* fs_type = strtok_r(NULL, "/", &ptr);
    if (fs_type == NULL) {
        return VFS_ERR_BAD_URI;
    }
    // char* params = strtok_r(NULL, "\0", &ptr);
    // if (fs_type == NULL) {
        // return VFS_ERR_BAD_URI;
    // }
    // FS_DBG("Mounting ~> Service: %s, FS: %s, Params: %s\n", 
            // service_name,
            // fs_type,
            // params);

    if (!strcmp(service_name, "mmchs")) {
        if (!strcmp(fs_type, "fat32")) {
            err = mmchsfs_mount(uri, path, &mount);
            DBGERRC(err, "Error mounting \n");

            /* register libc fopen/fread and friends */
            fs_libc_init(mount);
        } else {
            return VFS_ERR_NOT_SUPPORTED;
        }
    } else {
        USER_PANIC("Only MMCHS supported\n");
    }

    return SYS_ERR_OK;
}
