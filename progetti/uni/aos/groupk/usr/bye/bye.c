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
#include <aos/threads.h>
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>

#define THREADS_NO 2000
#define MALLOCS_NO 20
static volatile bool threads_started = false;

#include "../../lib/aos/include/threads_priv.h"
__unused static void malloc_and_write(void* args) {
    //debug_printf("Before writing\n");
    //char c[5] = {0};
    //c[0] = 0;
    //for (int i = 0; i < 0x1000; i++) {
        //*((char *) thread_self()->stack + i) = 0;
    //}
    //debug_printf("Writing at %p, stack [%p - %p]\n", 0x0, thread_self()->stack, thread_self()->stack_top);

}

__unused static void waste_slots(void) {
    for (int i = 0; i < 1024; i++) {
            if (! (i % 50)) debug_printf("Wasting slot %d\n", i);
            struct capref ram;
            frame_alloc(&ram, BASE_PAGE_SIZE, NULL);
            //aos_rpc_get_ram_cap(get_init_rpc(), BASE_PAGE_SIZE, BASE_PAGE_SIZE, &ram, NULL);
    }
}


int main(int argc, char *argv[]) {
    errval_t err = SYS_ERR_OK;
    debug_printf("Hello, world! from userspace \n");

    debug_printf("[\x1b[33;1mMain thread\x1b[0m] Sleeping before spawning threads\n");
    struct thread *thread_list[THREADS_NO];
    for (int i = 0; i < THREADS_NO; ++i) {
        debug_printf("[Main thread] Starting thread #%d ... at ip %p\n", i, &malloc_and_write);
        thread_list[i] = thread_create((thread_func_t) malloc_and_write, NULL);
        if (!thread_list[i]) debug_printf("Error starting thread #1\n");
    }
    threads_started = true;
    for (int i = 0; i < THREADS_NO; i++) {
        err = thread_join(thread_list[i], NULL);
        if (err_is_fail(err))
            debug_printf("Error joining with thread #%d\n", i);
    }

    waste_slots();

    debug_printf("Bye finished\n");
    return err;
}
