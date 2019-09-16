/**
 * \file
 * \brief A library to carry out benchmarks
 */
#include <aos/benchmark.h>

static bool session_running;

void bench_startsession(bool long_session) {
    if (session_running) {
        debug_printf("A session restart has been requested while another session was running\n");
    }
    int32_t config = 0x0;

    /* Enable all counters (including cycle's) */
    config |= 0x1;

    /* Reset counters */
    config |= 0x2; /* reset all counters to zero */
    config |= 0x4; /* reset cycle counter to zero */

    if (long_session)
        config |= 0x8; /* enable divider factor for CCNT (64 times) */

    /* Enable export for events */
    config |= 16;

    /* Program the PMCR */
    __asm__ volatile ("mcr p15, 0, %0, c9, c12, 0\t\n" :: "r"(config));

    /* Enable all counters */
    __asm__ volatile ("mcr p15, 0, %0, c9, c12, 1\t\n" :: "r"(0x8000000f));

    /* Clear overflows */
    __asm__ volatile ("mcr p15, 0, %0, c9, c12, 3\t\n" :: "r"(0x8000000f));
    session_running = true;
}

void bench_endsession(void) {
    session_running = false;
}

void bench_initmeasure(bench_t* measure) {
    measure->start = 0;
    measure->end = 0;
    // Calculate a new overhead according to different system's loads
    unsigned int _overhead;
    __asm__ volatile ("mrc p15, 0, %0, c9, c13, 0\t\n": "=r"(_overhead));
    __asm__ volatile ("mrc p15, 0, %0, c9, c13, 0\t\n": "=r"(measure->overhead));
    measure->overhead = measure->overhead - _overhead;
    //debug_printf("Over: %d\n", measure->overhead);
}
