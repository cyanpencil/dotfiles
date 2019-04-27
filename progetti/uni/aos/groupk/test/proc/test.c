/**
 * \file
 * \brief Tests for the processes
 */

#include <mm/mm.h>
#include <aos/debug.h>
#include <aos/slot_alloc.h>
#include <aos/domain.h>
#include <test/test_proc.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>
#include <aos/aos_rpc.h>

#define run_test(ARG, NAME, FUN)\
    do { \
        print_test(NAME);\
        FUN(ARG); \
        printf(GREEN_COLOR"Success"END_COLOR"\n"); \
    } while (0); 
                

void spawn_hello(void * useless);
void spawn_hello(void * useless) {
    struct capref frame2; 
    frame_alloc(&frame2, sizeof(struct spawninfo), NULL);
    struct spawninfo *si2;
    paging_map_frame_attr(get_current_paging_state(), (void**) &si2, sizeof(*si2), frame2, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    assert(spawn_load_by_name("order", si2) == SYS_ERR_OK);

    //struct capref frame; 
    //frame_alloc(&frame, sizeof(struct spawninfo), NULL);
    //struct spawninfo *si;
    //paging_map_frame_attr(get_current_paging_state(), (void**) &si, sizeof(*si), frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    //assert(spawn_load_by_name("hello", si) == SYS_ERR_OK);
}


//typedef void test_func(struct mm* mm);
//static void run_test(struct mm* mm, char * name, test_func func);
//static void run_test(struct mm* mm, char * name, test_func func) {
//    printf("[TEST] Testing "yellow_COLOR"%-30.30s "END_COLOR"... ", name);
//    func(mm);
//    printf(GREEN_COLOR"Success"END_COLOR"\n");
//}

void print_proc_list(void);
void print_proc_list(void) {
    debug_printf("+=""ProcMan""===================+\n");
    debug_printf("+ "gray_COLOR"NAME"END_COLOR"         | "gray_COLOR"STATUS"END_COLOR"     +\n");
    debug_printf("+---------------------------+\n");
    dlist_print(&spawned_proc_list, &print_spawned_proc);
    debug_printf("+===========================+\n");
}


void unit_test_proc(unit_test_ctx * utctx) {
    // Add init to the list of spawned processes 
    struct capref frame; 
    frame_alloc(&frame, sizeof(spawned_proc_t), NULL);
    spawned_proc_t* proc_node;
    paging_map_frame_attr(get_current_paging_state(), (void**) &proc_node, 
                                sizeof(spawned_proc_t), frame, 
                                VREGION_FLAGS_READ_WRITE, NULL, NULL);
    proc_node->disp_capref = cap_dispatcher;
    proc_node->name = "init";
    /*dlist_tail_insert(&spawned_proc_list, proc_node);*/

    print_test_summary("spawn.c");

    run_test(NULL, "SPAWN_HELLO", spawn_hello);
    /*run_test(NULL, "SPAWN_HELLO", spawn_hello);*/
    return;
}
