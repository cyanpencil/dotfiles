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
#include <spawn/multiboot.h>
#include <aos/benchmark.h>

int main(int argc, char *argv[]) {
    errval_t err = SYS_ERR_OK;

    if (argc < 2) {
        debug_printf("OOOORDEEEEEEEER !!!\n");
    }
    debug_printf("Hello form core %d, received %s!\n", disp_get_current_core_id(), argv[1]);

    //bench_startsession(false);
    //bench_t assign, loop;
    //bench_initmeasure(&assign);
    //bench_initmeasure(&loop);

    //volatile int x = 0;
    //bench_startmeasure(&assign);
    //x = 1;
    //unsigned int small = bench_endmeasure(&assign);
    //[> Measure ticks for some operation <]
    //volatile int n = 100000000;
    //bench_startmeasure(&loop);
    //while (n) n--;
    //unsigned int t = bench_endmeasure(&loop);

    //debug_printf("Los numeros ===========> Assignment cycles: %d , While cycles: %d\n",
            //small, t);
    //bench_endsession();

    return err;
}
