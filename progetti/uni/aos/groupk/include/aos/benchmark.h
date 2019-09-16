/**
 * \file
 * \brief A library to carry out benchmarks
 */
#ifndef AOS_BENCHMARK_H
#define AOS_BENCHMARK_H

#include <aos/debug.h>

typedef struct bench_s{
    unsigned int overhead;
    unsigned int start;
    unsigned int end;
} bench_t;

void bench_startsession(bool long_session);
void bench_endsession(void);
void bench_initmeasure(bench_t* measure);
inline void bench_startmeasure(bench_t* measure) {
    __asm__ volatile ("mrc p15, 0, %0, c9, c13, 0\t\n": "=r"(measure->start));
}

inline unsigned int bench_endmeasure(bench_t* measure) {
    __asm__ volatile ("mrc p15, 0, %0, c9, c13, 0\t\n": "=r"(measure->end));
    return (measure->end - measure->start);
}

#endif /* AOS_BENCHMARK_H */

