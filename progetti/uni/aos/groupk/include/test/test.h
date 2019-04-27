#ifndef AOS_TEST_H
#define AOS_TEST_H

#include <mm/mm.h>
#include <aos/paging.h>
#include <spawn/multiboot.h>
#include <spawncore.h>

#define RED_COLOR "\x1b[31;1m"
#define GREEN_COLOR "\x1b[32;1m"
#define YELLOW_COLOR "\x1b[33;1m"
#define CYAN_COLOR "\x1b[36;1m"
#define red_COLOR "\x1b[31m"
#define green_COLOR "\x1b[32m"
#define yellow_COLOR "\x1b[33m"
#define cyan_COLOR "\x1b[36m"
#define magenta_COLOR "\x1b[35m"
#define gray_COLOR  "\x1b[90m"
#define END_COLOR   "\x1b[0m"

typedef struct unit_test_ctx {
    struct mm * mm;
    struct bootinfo *bi;
    urpc_s *urpc;
} unit_test_ctx;


void unit_test_start(unit_test_ctx *);


#define print_test_summary(NAME)\
do { \
    int id = disp_get_current_core_id();\
    printf("=== Testing "YELLOW_COLOR"%s"END_COLOR" from core %s%d"END_COLOR" ===\n", NAME, id ? magenta_COLOR : cyan_COLOR, id);\
} while (0); 


#define print_test(NAME)\
do { \
    int id = disp_get_current_core_id();\
    printf("[TEST %s%d"END_COLOR".%d] Testing "yellow_COLOR"%-30.30s "END_COLOR"... ", id ? magenta_COLOR : cyan_COLOR, id, thread_get_id(thread_self()), NAME); \
} while (0); 

#endif /* AOS_TEST_H */
