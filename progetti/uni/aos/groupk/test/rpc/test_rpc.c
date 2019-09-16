/**
 * \file
 * \brief Tests for the physical memory management (i.e., caps)
 */

#include <mm/mm.h>
#include <aos/debug.h>
#include <aos/slot_alloc.h>
#include <aos/domain.h>
#include <aos/aos_rpc.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>
#include <test/test_rpc.h>
#include <test/test.h>


void unit_test_rpc(unit_test_ctx * utctx) {
    struct capref frame; 
    frame_alloc(&frame, sizeof(struct spawninfo), NULL);
    struct spawninfo *si;
    paging_map_frame_attr(get_current_paging_state(), (void**) &si, sizeof(*si), frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    errval_t err = spawn_load_by_name("test_rpc", si);
    DBGERRC(err, "Error spawning test_rpc\n");
    assert(err_is_ok(err));
}
