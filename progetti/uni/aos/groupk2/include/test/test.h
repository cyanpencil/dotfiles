#ifndef AOS_TEST_H
#define AOS_TEST_H

#include <mm/mm.h>
#include <aos/paging.h>

typedef struct unit_test_ctx {
    struct mm * mm;
} unit_test_ctx;


void unit_test_start(unit_test_ctx *);

#endif /* AOS_TEST_H */
