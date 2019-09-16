/*
 * Reference implementation of AOS milestone 0, on the Pandaboard.
 */

/*
 * Copyright (c) 2009-2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#include <kernel.h>

#include <assert.h>
#include <bitmacros.h>
#include <maps/omap44xx_map.h>
#include <paging_kernel_arch.h>
#include <platform.h>
#include <serial.h>

#define THR_OFF 0x00
#define RHR_OFF 0x00
#define LSR_OFF 0x14
#define UART_THR  (OMAP44XX_MAP_L4_PER_UART3 + THR_OFF)
#define UART_RHR  (OMAP44XX_MAP_L4_PER_UART3 + RHR_OFF)
#define UART_LSR  (OMAP44XX_MAP_L4_PER_UART3 + LSR_OFF)

#define OE_OFF 0x134
#define DO_OFF 0x13c
#define GPIO1_OE (OMAP44XX_MAP_L4_WKUP_GPIO1 + OE_OFF)
#define GPIO1_DO (OMAP44XX_MAP_L4_WKUP_GPIO1 + DO_OFF)

#define GPIO4_OE (OMAP44XX_MAP_L4_PER_GPIO4 + OE_OFF)
#define GPIO4_DO (OMAP44XX_MAP_L4_PER_GPIO4 + DO_OFF)

#define PAD_OFF 0xf4
#define MUX110 (OMAP44XX_MAP_L4_CFG_SYSCTRL_PADCONF_CORE + PAD_OFF)

#define writeb(v,a) (*((volatile unsigned char *) (a)) = (v))
#define readb(a)    (*((volatile unsigned char *) (a)))
#define writeh(v,a) (*((volatile unsigned short int *) (a)) = (v))
#define readh(a)    (*((volatile unsigned short int *) (a)))
#define writew(v,a) (*((volatile unsigned int *) (a)) = (v))
#define readw(a)    (*((volatile unsigned int *) (a)))

#define setw(v,a)   (*((volatile unsigned int *) (a)) |= (0x1 << v))
#define unsetw(v,a) (*((volatile unsigned int *) (a)) &= !(0x1 << v))

#define MSG(format, ...) printk( LOG_NOTE, "OMAP44xx: "format, ## __VA_ARGS__ )

void blink_leds(void);

/* RAM starts at 2G (2 ** 31) on the Pandaboard */
lpaddr_t phys_memory_start= GEN_ADDR(31);

/*** Serial port ***/

unsigned serial_console_port= 2;

errval_t
serial_init(unsigned port, bool initialize_hw) {
    /* XXX - You'll need to implement this, but it's safe to ignore the
     * parameters. */

    return SYS_ERR_OK;
};

void
serial_putchar(unsigned port, char c) {
    while ( ! (readb(UART_LSR) & (0x1 << 5)) )
        ;

    writeb(c, UART_THR);
}

char
serial_getchar(unsigned port) {
    while ( ! (readb(UART_LSR) & (0x1)) )
        ;

    return readb(UART_RHR);
}

/*** LED flashing ***/


void sleep(int);
void sleep(int t) {
    for (volatile unsigned int i = 0; i < t * 50000; ++i) {
        ;
    }
}

void blonk_leds(int i);
void
blonk_leds(int i) {
    writeh((0x3 | (readh(MUX110 + 2) >> 3) << 3), MUX110 + 2);

    unsetw(8, GPIO1_OE);
    unsetw(14, GPIO4_OE);

    setw(8, GPIO1_DO);
    setw(14, GPIO4_DO);
    sleep(i);
    unsetw(8, GPIO1_DO);
    unsetw(14, GPIO4_DO);
}

__attribute__((noreturn))
void
blink_leds(void) {
    writeh((0x3 | (readh(MUX110 + 2) >> 3) << 3), MUX110 + 2);

    unsetw(8, GPIO1_OE);
    unsetw(14, GPIO4_OE);
    while (1) {
        setw(8, GPIO1_DO);
        sleep(1000);
        unsetw(8, GPIO1_DO);
        setw(14, GPIO4_DO);
        sleep(1000);
        unsetw(14, GPIO4_DO);
    }
    while(1);
}
