/**
 * \file
 * \brief Hello world application
 */

/*
 * Copyright (c) 2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, CAB F.78, Universitaetstr. 6, CH-8092 Zurich,
 * Attn: Systems Group.
 */


#include <stdio.h>
#include <ctype.h>

#include <aos/aos.h>
#include <aos/threads.h>
#include <aos/aos_rpc.h>
#include <aos/deferred.h>
#include <urpc.h>
#include <fs/fs.h>
#include <omap_timer/timer.h> 

#include "mmchs.h"

#define max(x, y) (x > y ? x : y)

#define MAX_CLIENTS 255
#define POLL_INTERVAL 10 // In us

static volatile uint8_t clients_no = 0;
static urpc_s urpc[MAX_CLIENTS];
static volatile bool urpc_thread_started = false;
static struct thread* urpc_thread = NULL;
static volatile uint64_t trans_seq = 0;

static char len_buf[max(MCACHE_LINE_SIZE, sizeof(size_t))];
// mmchs_block_write_req being the greatest request
static char req_buf[ROUND_UP(sizeof(mmchs_block_write_req), MCACHE_LINE_SIZE)];

dlinked_list_t open_handles;
dlinked_list_t lock_handles;
typedef struct f_handle_s {
    dlinked_node_t node;
    size_t count;
    char* path;
} f_handle_t;

static size_t in_list(dlinked_list_t *list, char* path) {
    // MMCHS_DBG("Looking for %s\n", path);
    f_handle_t *lh = dlist_head(list);
    while (lh) {
        if (!strcmp(lh->path, path)) {
            return 1;
        }
        lh = dlist_next(lh);
    }
    return 0;
}

static void inc_handle(dlinked_list_t *list, char* path) {
    f_handle_t *lh = dlist_head(list);
    while (lh) {
        if (!strcmp(lh->path, path)) {
            lh->count++;
            return;
        }
        lh = dlist_next(lh);
    }
    // MMCHS_DBG("Creating handle for %s\n", path);
    // Means we didn't find it
    f_handle_t *h = malloc(sizeof(f_handle_t));
    h->path = strdup(path);
    h->count = 1;
    dlist_tail_insert(list, h);
}

static void dec_handle(dlinked_list_t *list, char* path) {
    f_handle_t *lh = dlist_head(list);
    while (lh) {
        if (!strcmp(lh->path, path)) {
            lh->count--;
            if (!lh->count) {
                // Remove this from the list
                dlist_remove(list, lh);
                free(lh->path);
                free(lh);
            }
            return;
        }
        lh = dlist_next(lh);
    }
    // We shall never reach this
    assert(1);
}

static void urpc_handler(void* args) {
    errval_t err;
    urpc_thread_started = true;
    while (true) {
        for (uint8_t i = 0; i < clients_no; i++) {
            if (!urpc_message_avail(&urpc[i])) 
                continue;
            // mmchs_block_req *req;
            // size_t len;
            // err = urpc_read_msg(&urpc[i], (void**) &req, &len);
            // DBGERRC(err, "Error receiving message over urpc\n");

            mmchs_block_req *req = (mmchs_block_req*) req_buf;
            size_t len;
            b_read(&urpc[i], (void*) len_buf, max(MCACHE_LINE_SIZE, sizeof(size_t)));
            memcpy(&len, len_buf, sizeof(size_t));
            size_t buf_size = ROUND_UP(len, MCACHE_LINE_SIZE);
            b_read(&urpc[i], req_buf, buf_size);

            // MMCHS_DBG("Request %s block \x1b[32;1m%zu\x1b[0m\n", req->op == 0 ? "READ" : "WRITE", req->block_no);
            switch (req->op) {
                case READ: {
                    mmchs_block_read_req *read_req = (mmchs_block_read_req*) req;
                    mmchs_block_read_res res;
                    err = mmchs_read_block(read_req->base.block_no, res.chunk);
                    DBGERRC(err, "Error reading block from device\n");
                    res.base.err = err;

                    err = urpc_write_msg(&urpc[i], &res, sizeof(mmchs_block_read_res));
                    DBGERRC(err, "Error sending message over urpc\n");
                    break;
                }
                case WRITE: {
                    mmchs_block_write_req *write_req = (mmchs_block_write_req*) req;
                    err = mmchs_write_block(write_req->base.block_no, write_req->chunk);
                    DBGERRC(err, "Error writing to device\n");
                    mmchs_block_write_res res;
                    res.base.err = err;

                    err = urpc_write_msg(&urpc[i], &res, sizeof(mmchs_block_write_res));
                    DBGERRC(err, "Error sending message over urpc\n");
                    break;
                }
                case GET_SEQ: {
                    mmchs_block_seq_res res;
                    res.base.err = SYS_ERR_OK;
                    res.seq = trans_seq;
                    err = urpc_write_msg(&urpc[i], &res, sizeof(mmchs_block_seq_res));
                    DBGERRC(err, "Error sending message over urpc\n");
                    break;
                }
                case INC_SEQ: {
                    mmchs_block_seq_res res;
                    res.base.err = SYS_ERR_OK;
                    res.seq = ++trans_seq;
                    MMCHS_DBG("Incremented transaction sequence: %lld\n", trans_seq);
                    err = urpc_write_msg(&urpc[i], &res, sizeof(mmchs_block_seq_res));
                    DBGERRC(err, "Error sending message over urpc\n");
                    break;
                }
                case HANDLE: {
                    mmchs_block_handle_req *handle_req = (mmchs_block_handle_req*) req;
                    errval_t ret_err = SYS_ERR_OK;
                    char* path = handle_req->filename;
                    switch (handle_req->op) {
                        case HANDLE_OPEN:
                            if (in_list(&lock_handles, path)) {
                                // MMCHS_DBG("Cannot add handle, file is locked %s\n", path);
                                ret_err = FS_ERR_LOCKED_FILE;
                            } else {
                                // MMCHS_DBG("Add handle for %s\n", path);
                                inc_handle(&open_handles, path);
                                ret_err = SYS_ERR_OK;
                            }
                            break;
                        case HANDLE_CLOSE:
                            // MMCHS_DBG("Close handle for %s\n", path);
                            assert(in_list(&open_handles, path));
                            dec_handle(&open_handles, path);
                            ret_err = SYS_ERR_OK;
                            break;
                        case HANDLE_LOCK:
                            if (in_list(&open_handles, path) > 1 || in_list(&lock_handles, path)) {
                                // MMCHS_DBG("Cannot lock handle, file is busy %s\n", path);
                                ret_err = FS_ERR_BUSY;
                            } else {
                                // MMCHS_DBG("Lock handle %s\n", path);
                                inc_handle(&lock_handles, path);
                                ret_err = SYS_ERR_OK;
                            }
                            break;
                        case HANDLE_UNLOCK:
                            // MMCHS_DBG("Unlock handle for %s\n", handle_req->filename);
                            assert(in_list(&open_handles, path) < 2);
                            assert(in_list(&lock_handles, path));
                            dec_handle(&lock_handles, path);
                            ret_err = SYS_ERR_OK;
                            break;
                        default:
                            // This shall never happen
                            assert(1);
                    }

                    mmchs_block_res res;
                    res.err = ret_err;
                    err = urpc_write_msg(&urpc[i], &res, sizeof(mmchs_block_res));
                    DBGERRC(err, "Error sending message over urpc\n");
                    break;
                }
            }
        }
        barrelfish_usleep(POLL_INTERVAL);
    }
}

static void start_thread(void) {
    urpc_thread = thread_create((thread_func_t) urpc_handler, NULL);
    if (urpc_thread == NULL) {
        debug_printf("Error starting URPC thread\n");
    }
}

void init_service(void) {
    errval_t err;

    // debug_printf("============================================\n");
    // debug_printf("               Benchmark Tests              \n");
    // debug_printf("============================================\n");
    // err = omap_timer_init();
    // assert(err_is_ok(err));
    // omap_timer_ctrl(true);
    // struct str_int { char* name; size_t size; };
    // struct str_int benchs[] = {
        // // {"/bench512",  0x200 },
        // // {"/bench1K",  0x400 },
        // {"/bench4K",  0x1000 },
        // {"/bench16K",  0x4000 },
        // {"/bench32K",  0x8000 },
        // {"/bench64K",  0x10000 },
        // // {"/bench1M",  1048576 }
    // };
    // const int benchs_no = sizeof(benchs) / sizeof(struct str_int);
    // char bench_buf[512];
    // for (int c = 0; c < 5; c++) {
        // for (int i = 0; i < benchs_no; ++i) {
            // uint64_t bench_start, bench_end;
            // bench_start = omap_timer_read();
            // for (int b_off = 0; b_off < benchs[i].size / 512; ++b_off) {
                // mmchs_read_block(b_off, bench_buf);
            // }
            // bench_end = omap_timer_read();
            // debug_printf("%s, %zu\n", benchs[i].name, omap_timer_to_ms(bench_end - bench_start));
        // }
    // }
    // debug_printf("============================================\n");
    // debug_printf("                 End Tests                  \n");
    // debug_printf("============================================\n");

    // Initialize file_handles dlinked_list
    dlist_init(&open_handles);
    dlist_init(&lock_handles);

    // Advertise to init that I'm offering mmchs_service
    start_thread();
    while (!urpc_thread_started) ;
    err = aos_rpc_register_service(get_init_rpc(), "mmchs_service");
    DBGERRV(err, "Error registering service\n");
    while (true) {
        assert(clients_no < MAX_CLIENTS);
        // Blocking until someone gives me a shared frame
        MMCHS_DBG("Waiting for requests ...\n");
        char *buf = aos_rpc_receive_bind();
        if (buf == NULL) {
            debug_printf("[\x1b[31mx\x1b[0m]Error receiving bind\n");
            continue;
        }

        urpc_s_init(buf, &urpc[clients_no], false);
        err = urpc_write_msg(&urpc[clients_no], "ACK", strlen("ACK"));
        DBGERRV(err, "Error receiving message over urpc\n");
        MMCHS_DBG("Added client %d\n", clients_no);
        /*
         * Here we increase the number of clients at the end 
         * so that the URPC thread will be aware of the
         * existence of the new client once everything is set up
         */
        clients_no++;
    }
    USER_PANIC("[ mmchs ] No more listening to incoming requests");
}
