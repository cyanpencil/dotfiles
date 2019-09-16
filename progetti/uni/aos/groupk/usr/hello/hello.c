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
    // debug_printf("I've received %d arguments\n", argc);
    // debug_printf("Arguments: [ \n");
    // for (int i = 0; i < argc; ++i) {
      // debug_printf("\t%s\n", argv[i]);
    // }

    coreid_t core = 0;
    if (disp_get_current_core_id() == 0) {
        core = 1;
    }

    domainid_t pid;

    size_t SIZE = 0x1000;
    char * test = malloc(SIZE);
    memset(test, 'a', SIZE);
    memcpy(test, "order ", 6);
    test[SIZE-1] = 0;

    debug_printf("Requesting spawn on core %d\n", core);
    errval_t err = aos_rpc_process_spawn(get_init_rpc(), test, core, &pid);
    debug_printf("Spawn on core %d returned %d, pid %d\n", core, err, pid);

    return 0;
}
