#include <stdio.h>
#include <aos/debug.h>
#include <test/test.h>
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>

__unused static void alloc_rpc_mem(void) {
    struct capref frame;
    frame_alloc(&frame, BASE_PAGE_SIZE, NULL);
    struct frame_identity fi;
    frame_identify(frame, &fi);
    assert(fi.bytes >= BASE_PAGE_SIZE);
}

__unused static void paging_remote_alloced(void) {
    struct capref frame;
    frame_alloc(&frame, BASE_PAGE_SIZE, NULL);
    char *buf;
    paging_map_frame_attr(get_current_paging_state(), (void**) &buf, BASE_PAGE_SIZE, frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    *((size_t*)buf) = 0xdeadbeef;
    assert(*((size_t*)buf) == 0xdeadbeef);
}

__unused static void remote_spawn(void) {
    domainid_t pid;
    int id = disp_get_current_core_id();
    errval_t err = aos_rpc_process_spawn(get_init_rpc(), "hello", id, &pid);
    assert(err == SYS_ERR_OK);
}

__unused static void remote_spawn_memeater(void) {
    domainid_t pid;
    int id = disp_get_current_core_id();
    errval_t err = aos_rpc_process_spawn(get_init_rpc(), "memeater", id, &pid);
    assert(err == SYS_ERR_OK);
}

__unused static void remote_spawn_terminal(void) {
    domainid_t pid;
    int id = disp_get_current_core_id();
    errval_t err = aos_rpc_process_spawn(get_init_rpc(), "test_terminal", id, &pid);
    assert(err == SYS_ERR_OK);
}

typedef void test_func(void);

static void run_test(char * name, test_func func);
static void run_test(char * name, test_func func) {
    //printf("[TEST] Testing "yellow_COLOR"%-30.30s "END_COLOR"... ", name);
    func();
    //printf(GREEN_COLOR"Success"END_COLOR"\n");
}

void rpc_test(void);
void rpc_test(void) {
    printf("=== (Remotely) Testing "yellow_COLOR"aos_rpc.c"END_COLOR" ===\n");
    fflush(stdout);

    //for (int i = 0; i < 2560; i++) {
    //    debug_printf("Test: %d\n", i);
    //    run_test("REMOTE MM ALLOC", alloc_rpc_mem);
    //}
    /*run_test("PAGING REMOTE ALLOCED", paging_remote_alloced);*/
    /*run_test("REMOTE SPAWN HELLO", remote_spawn);*/
    /*run_test("MEMEATER", remote_spawn_memeater);*/
    //barrelfish_usleep(800000);
    run_test("TERMINAL TEST", remote_spawn_terminal);
}


int main(int argc, char *argv[]) {
    rpc_test();
    fflush(stdout);
    return 0;
}
