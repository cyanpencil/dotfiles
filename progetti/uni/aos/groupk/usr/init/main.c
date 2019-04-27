/**
 * \file
 * \brief init process for child spawning
 */

/*
 * Copyright (c) 2007, 2008, 2009, 2010, 2016, ETH Zurich.  * All rights reserved.  *
 * This file is distributed under the terms in the attached LICENSE file.  * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#include <stdio.h>
#include <stdlib.h>

#include <aos/aos.h>
#include <aos/inthandler.h>
#include <aos/waitset.h>
#include <aos/morecore.h>
#include <aos/paging.h>
#include <aos/aos_rpc.h>
#include <aos/coreboot.h>
#include <aos/caddr.h>
#include <aos/deferred.h>
#include <spawn/spawn.h>
#include <urpc.h>
#include <aos/threads.h>

#include <mm/mm.h>
#include "mem_alloc.h"

#include <test/test.h>
#include <monitor.h>
#include <spawncore.h>

#define CORES_NO 2

coreid_t my_core_id;
struct bootinfo *bi;

static errval_t map_regions(struct bootinfo * _bi, urpc_s * urpc) {
    errval_t err;
    size_t len;

    struct frame_identity* modstrings_fi;
    err = urpc_read_msg(urpc, (void**) &modstrings_fi, &len);
    DBGERR(err, "Error receiving message over urpc\n");

    struct capref l1c = {
        .cnode = cnode_task,
        .slot = TASKCN_SLOT_ROOTCN
    };

    err = cnode_create_foreign_l2(l1c, ROOTCN_SLOT_MODULECN, &cnode_module);
    DBGERR(err, "Creating l2cnode");

    struct capref module_cap = {
        .cnode = cnode_module,
        .slot = 0,
    };

    err = frame_forge(module_cap, modstrings_fi->base, modstrings_fi->bytes, my_core_id);
    DBGERR(err, "Error forging mod cap\n");
    module_cap.slot++;

    for (int i = 0; i < bi->regions_length; ++i) {
        if (bi->regions[i].mr_type == RegionType_Module) {
            err = frame_forge(module_cap, bi->regions[i].mr_base, bi->regions[i].mrmod_size, my_core_id);
            DBGERR(err, "Error forging frame cap\n");
            bi->regions[i].mrmod_slot = module_cap.slot++;
        }
    }
    return SYS_ERR_OK;
}

static errval_t receive_bi(struct bootinfo * _bi, urpc_s * urpc) {
    errval_t err;
    size_t len;
    err = urpc_read_msg(urpc, (void**) &bi, &len);
    DBGERR(err, "Error receiving message over urpc\n");

    return SYS_ERR_OK;
}

static errval_t map_ram(struct bootinfo * _bi, urpc_s * urpc) {
    errval_t err;

    struct capref mem_cap = {
        .cnode = cnode_super,
        .slot = 0,
    };

    bi->regions[bi->regions_length - 2] = bi->regions[bi->regions_length - 1];
    bi->regions_length = bi->regions_length - 1;

    for (int i = 0; i < bi->regions_length; ++i) {
        if (bi->regions[i].mr_type == RegionType_Empty && !bi->regions[i].mr_consumed) {
            err = ram_forge(mem_cap, bi->regions[i].mr_base, bi->regions[i].mr_bytes, my_core_id);
            DBGERR(err, "Error forging ram cap\n");
            mem_cap.slot++;
        }
    }

    return SYS_ERR_OK;
}

static errval_t dummy_recv_raw(struct aos_rpc *chan, char **buf, size_t* len, struct capref *cap) {
    return SYS_ERR_OK;
}

static errval_t dummy_recv(struct aos_rpc *chan, rpc_req **buf, size_t* len, struct capref *cap) {
    errval_t err;
    rpc_req * req;
    size_t len2;
    err = urpc_read_msg((urpc_s *) aos_rpc_get_a(chan), (void **) &req, &len2);
    err = urpc_read_msg((urpc_s *) aos_rpc_get_a(chan), (void **) buf, len);

    return SYS_ERR_OK;
}

static errval_t dummy_send_raw(struct aos_rpc *chan, const char *buf, size_t len, struct capref cap) {
    errval_t err;
    err = urpc_write_msg((urpc_s *) aos_rpc_get_a(chan), (char *) buf, len);

    return SYS_ERR_OK;
}

static errval_t dummy_send(uint8_t type, struct aos_rpc *chan, const char *buf, size_t len, struct capref cap) {
    errval_t err;
    rpc_req req;

    req.type = type;
    err = urpc_write_msg((urpc_s *) aos_rpc_get_a(chan), req.bytes, sizeof(rpc_req));
    err = urpc_write_msg((urpc_s *) aos_rpc_get_a(chan), (char *) buf, len);
    // FIXME: ADD LOCK

    return SYS_ERR_OK;
}

__unused static void urpc_handler(void* args) {
    urpc_s * urpc = (urpc_s*) args;
    struct aos_rpc * rpc = _aos_rpc_chan_alloc();

    aos_rpc_init_f(rpc, dummy_send_raw, dummy_send, dummy_recv_raw, dummy_recv);
    aos_rpc_init_a(rpc, urpc);

    rpc_req * req;


    debug_printf("BLOCKING ON READ\n");
    size_t len;
    urpc_read_msg(urpc, (void **) &req, &len);
    debug_printf("UNBLOCKED\n");

    ipc_request_handler(req->type, req->buf, rpc, NULL_CAP);
    debug_printf("REQ HANDLED\n");
}

int main(int argc, char *argv[])
{
    errval_t err;
    urpc_s urpc;

    /* Set the core id in the disp_priv struct */
    err = invoke_kernel_get_core_id(cap_kernel, &my_core_id);
    assert(err_is_ok(err));
    disp_set_core_id(my_core_id);

    for (int i = 0; i < argc; i++) {
       printf(" %s", argv[i]);
    }
    printf("\n");

    unit_test_ctx ut_ctx = { 
        .mm   = &aos_mm,
        .bi   = bi,
        .urpc = &urpc
    };

    /* First argument contains the bootinfo location, if it's not set */
    bi = (struct bootinfo*)strtol(argv[1], NULL, 10);
    if (!bi) {
        assert(my_core_id > 0);

        urpc_map(cap_urpc, &urpc);

        receive_bi(bi, &urpc);
        assert(bi);
    }

    uint32_t regstart = 0 + my_core_id,
             regend  = 1 + my_core_id;
    if (argc > 2) {
    //    regstart = strtol(argv[2], NULL, 10),
    //    regend = strtol(argv[3], NULL, 10);
    //    // Obtain a range from command line arguments

    }

    // Mark all the memory regions outside of the range as
    // consumed (and the ones inside the range as not consumed)
    for (int i = 0; i < bi->regions_length; i++) {
        if (bi->regions[i].mr_type == RegionType_Empty) {
            if (regstart == 0 && regend > 0) {
                bi->regions[i].mr_consumed = false;
                regend--;
            } else {
                if (regstart > 0) {
                    regstart--;
                }

                bi->regions[i].mr_consumed = true;
            }
        }
    }

    if (my_core_id != 0 ) {
        map_ram(bi, &urpc);
    }
    // for (int i = 0; i < bi->regions_length; i++) {
    //     debug_printf("[0x%"PRIxGENPADDR" ~> 0x%"PRIxGENPADDR"] mrmod_size: 0x%zx %d\n", bi->regions[i].mr_base, bi->regions[i].mr_base + bi->regions[i].mr_bytes, bi->regions[i].mrmod_size, bi->regions[i].mr_type);
    // }

    err = initialize_ram_alloc();
    if(err_is_fail(err)){
        DEBUG_ERR(err, "initialize_ram_alloc");
    }
    
    if (my_core_id != 0 ) {
        map_regions(bi, &urpc);
    }
    // Init the spawned process list for the toy process manager
    dlist_init(&spawned_proc_list);

    err = cap_retype(cap_selfep, cap_dispatcher, 0, ObjType_EndPoint, 0, 1); 
    DBGERR(err, "Error retyping dispatcher to selfep\n");
    _aos_rpc_initialize_init();

    err = inthandler_setup_arm(handle_uart_getchar_interrupt, NULL, 106);
    DBGERR(err, "Failed to set handler for reading uart\n");

    err = _aos_rpc_set_closure(get_init_rpc(), MKCLOSURE(handshake_handler, get_init_rpc()));
    DBGERR(err, "Error setting handshake handler closure\n");

    // Only if we are the main core we spawn other cores
    if (my_core_id == 0) {
        for (int i = 1; i < CORES_NO; i++) {
            err = spawn_core(my_core_id, i, &urpc);
            DBGERR(err, "Error spawning a core\n");
        }

        urpc_write_msg(&urpc, bi, sizeof(struct bootinfo) + sizeof(struct mem_region)*(bi->regions_length));

        struct capref module_cap = {
            .cnode = cnode_module,
            .slot = 0,
        };
        struct frame_identity mod_fi;
        err = frame_identify(module_cap, &mod_fi);
        DBGERR(err, "Error identifying module cap\n");
        urpc_write_msg(&urpc, &mod_fi, sizeof(struct frame_identity));
    }

    // Test for both cores
    unit_test_start(&ut_ctx);

    // Spawn a thread 
    struct thread* urpc_thread;
    urpc_thread = thread_create((thread_func_t) urpc_handler, (void *) &urpc);
    if (!urpc_thread) debug_printf("Error starting URPC thread\n");

    if (my_core_id == 0) {
        rpc_req req;
        req.type = RPC_REQ_PROC_SPAWN;
        rpc_req_proc_spawn sreq;
        sreq.core_id = 1;
        memcpy(req.buf, &sreq, 20);
        strncpy( (char *) req.buf + offsetof(rpc_req_proc_spawn, name), "order", 20);
        urpc_write_msg(&urpc, &req, sizeof(rpc_req));
    } else {
        rpc_req req;
        req.type = RPC_REQ_PROC_SPAWN;
        rpc_req_proc_spawn sreq;
        sreq.core_id = 0;
        memcpy(req.buf, &sreq, 20);
        strncpy( (char *) req.buf + offsetof(rpc_req_proc_spawn, name), "order", 20);
        urpc_write_msg(&urpc, &req, sizeof(rpc_req));
    }

    // Hang around
    struct waitset *default_ws = get_default_waitset();
    while (true) {
        err = event_dispatch(default_ws);
        if (err_is_fail(err)) {
            DEBUG_ERR(err, "in event_dispatch");
            abort();
        }
    }

    return EXIT_SUCCESS;
}
