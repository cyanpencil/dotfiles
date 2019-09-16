/**
 * \file
 * \brief Tests for the multicore protocol
 */

#include <aos/debug.h>
#include <aos/domain.h>
#include <test/test_filesystem.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>
#include <aos/aos_rpc.h>
#include <aos/deferred.h>
#include <aos/dispatch.h>
#include <aos/aos.h>
#include <aos/curdispatcher_arch.h>
#include <aos/dispatcher_arch.h>

#define run_test(ARG, NAME, FUN)\
    do { \
        print_test(NAME);\
        FUN(ARG); \
        printf(GREEN_COLOR"Success"END_COLOR"\n"); \
    } while (0); 

void unit_test_filesystem(unit_test_ctx * utctx) {
    print_test_summary("filesystem.c");
    // errval_t err;
    // domainid_t pid;
    // err = aos_rpc_process_spawn(get_init_rpc(), "test_terminal", my_core_id, &pid);
    // DBGERRV(err, "Error spawning fileread test process\n");
    // if (my_core_id == 0) {
        // paging_map_frame_attr(get_current_paging_state(), (void**) &si, sizeof(*si), frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
        // assert(spawn_load_by_name("mmchs", si, false) == SYS_ERR_OK);
    // }

    if (!disp_get_current_core_id()) {
        struct spawninfo si;
        assert(spawn_load_by_name("filereader", &si) == SYS_ERR_OK);
    }
}
