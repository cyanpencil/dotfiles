/**
 * \file
 * \brief Tests for network manager
 */

#include <test/test_nm.h>

#include <aos/aos.h>
#include <aos/debug.h>
#include <aos/aos_rpc.h>
#include <spawn/spawn.h>

coreid_t my_core_id;

#define run_test(ARG, NAME, FUN)\
    do { \
        print_test(NAME);\
        FUN(ARG); \
        printf(GREEN_COLOR"Success"END_COLOR"\n"); \
    } while (0); 
                

__unused static void spawn_nm(unit_test_ctx * utctx) {
    if (my_core_id == 0) {
        struct spawninfo *si = malloc(sizeof(struct spawninfo));
        assert(spawn_load_by_name("network_manager", si) == SYS_ERR_OK);
    }

    if (my_core_id == 0) {
        struct spawninfo *si2 = malloc(sizeof(struct spawninfo));
        assert(spawn_load_by_name("uecho", si2) == SYS_ERR_OK);
    }


    //if (my_core_id == 0) {
    //    struct spawninfo *si2 = malloc(sizeof(struct spawninfo));
    //    assert(spawn_load_by_name("ping 10.0.2.2 100", si2) == SYS_ERR_OK);
    //}

    if (my_core_id == 1) {
        struct spawninfo *si2 = malloc(sizeof(struct spawninfo));
        assert(spawn_load_by_name_fd("bush", si2, 1) == SYS_ERR_OK);
    }
}

void unit_test_nm(unit_test_ctx * utctx) {
    my_core_id = disp_get_current_core_id();

    print_test_summary("nm.c");

    run_test(utctx, "SPAWN_NM", spawn_nm);
    return;
}
