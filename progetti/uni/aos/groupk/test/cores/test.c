/**
 * \file
 * \brief Tests for the multicore protocol
 */

#include <mm/mm.h>
#include <aos/debug.h>
#include <aos/slot_alloc.h>
#include <aos/domain.h>
#include <test/test_cores.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>
#include <spawncore.h>
#include <aos/aos_rpc.h>
#include <aos/deferred.h>
#include <aos/dispatch.h>
#include <aos/aos.h>
#include <aos/curdispatcher_arch.h>
#include <aos/dispatcher_arch.h>

coreid_t my_core_id;


#define run_test(ARG, NAME, FUN)\
    do { \
        print_test(NAME);\
        FUN(ARG); \
        printf(GREEN_COLOR"Success"END_COLOR"\n"); \
    } while (0); 
                

__unused static void basic_handshake(void * ctx) {
    unit_test_ctx* ut_ctx = (unit_test_ctx*) ctx;
    char *handshake_syn = "HANDSHAKE SYN";
    char *handshake_ack = "HANDSHAKE ACK";
    char buf[100];

    b_write(ut_ctx->urpc, handshake_syn, strlen(handshake_syn)+1);
    b_read(ut_ctx->urpc, buf, strlen(handshake_syn)+1);

    assert(!strcmp(buf, handshake_syn));

    b_write(ut_ctx->urpc, handshake_ack, strlen(handshake_ack)+1);
    b_read(ut_ctx->urpc, buf, strlen(handshake_ack)+1);

    assert(!strcmp(buf, handshake_ack));
}


__unused static void big_payload_64k(void *ctx) {
    unit_test_ctx* ut_ctx = (unit_test_ctx*) ctx;

    size_t size = 0x10000;  // 64 KB
    char handshake[size];
    char buf[size];

    for (int i = 0; i < size - 1; i++) {
        handshake[i] = 0x61 + (i % 25); // all letters of alphabet
    }
    handshake[size - 1] = '\0';

    if (my_core_id == 0) {
        b_write(ut_ctx->urpc, handshake, size);
    } else {
        b_read(ut_ctx->urpc, buf, size);
        assert(!strncmp(buf, handshake, 10));
    }
}

__unused static void big_malloc(void *ctx){
    if (my_core_id == 0) return;

    size_t size = 1 << 20;
    char* buf = malloc(size); // 1 MB
    for (int i = 0; i < size; i++) buf[i] = 97 + (i%25);

    assert(buf[size-1] == 97 + ((size-1) % 25));
}

void unit_test_cores(unit_test_ctx * utctx) {
    dispatcher_handle_t handle = curdispatcher();
    struct dispatcher_generic* disp = get_dispatcher_generic(handle);
    my_core_id = disp->core_id;

    print_test_summary("spawncore.c");

    run_test(utctx, "BASIC_HANDSHAKE", basic_handshake);
    run_test(utctx, "BIG_PAYLOAD_64K", big_payload_64k);
    run_test(utctx, "BIG_BUF_2ND_COR", big_malloc);
    return;
}
