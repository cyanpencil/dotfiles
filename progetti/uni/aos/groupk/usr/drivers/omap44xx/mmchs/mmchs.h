/*
 * Copyright (c) 2013, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#ifndef MMCHS2_H
#define MMCHS2_H

#include <aos/aos.h>

#include "mmchs_debug.h"
#include "omap44xx_cm2.h"
#include "omap44xx_ctrlmod.h"
#include "i2c.h"
#include "twl6030.h"

#define MMCHS_DBG(msg, ...) ;
/* \
    // do { \
        // debug_printf("[\x1b[36m mmchs\x1b[0m ] "msg, ##__VA_ARGS__); \
    // } while (0);*/


void mmchs_init(void);
errval_t mmchs_read_block(size_t block_nr, void *buffer);
errval_t mmchs_write_block(size_t block_nr, void *buffer);

void init_service(void);

#endif // MMCHS2_H
