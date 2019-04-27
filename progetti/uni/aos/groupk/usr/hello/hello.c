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
#include <aos/debug.h>
#include <aos/aos.h>
#include <aos/aos_rpc.h>
#include <aos/deferred.h>
#include <aos/dispatcher_arch.h>

int main(int argc, char *argv[]) {
    debug_printf("I've received %d arguments\n", argc);
    debug_printf("Arguments: [ \n");
    for (int i = 0; i < argc; ++i) {
      debug_printf("\t%s\n", argv[i]);
    }

    if (get_dispatcher_generic(curdispatcher())->core_id!= 0) return 0;

    debug_printf("Requesting spawn on core 1\n");
    aos_rpc_process_spawn(get_init_rpc(), "hello", 1, NULL);

    return 0;
}
