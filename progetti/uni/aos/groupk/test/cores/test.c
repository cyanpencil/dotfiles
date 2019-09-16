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
#include <aos/benchmark.h>

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
    char buf[0x100];

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
        bench_t measure; bench_initmeasure(&measure);
        bench_startsession(false);
        bench_startmeasure(&measure);
        b_write(ut_ctx->urpc, handshake, size);
        unsigned int cycles = bench_endmeasure(&measure);
        debug_printf("Sending took: %u cycles\n", cycles);
    } else {
        bench_t measure; bench_initmeasure(&measure);
        bench_startsession(false);
        bench_startmeasure(&measure);
        b_read(ut_ctx->urpc, buf, size);
        unsigned int cycles = bench_endmeasure(&measure);
        debug_printf("Receiving took: %u cycles\n", cycles);
        assert(!strncmp(buf, handshake, size - 1));
    }
}

__unused static void big_payload_performance(void *ctx) {
    unit_test_ctx* ut_ctx = (unit_test_ctx*) ctx;

    const int TEST_NO = 10;
    for (int k = 0; k < TEST_NO; ++k) {
        size_t size = 0x10000;  // 64 KB
        char handshake[size];
        char buf[size];

        for (int i = 0; i < size - 1; i++) {
            handshake[i] = 0x61 + (i % 25); // all letters of alphabet
        }
        handshake[size - 1] = '\0';

        if (my_core_id == 0) {
            bench_t measure; bench_initmeasure(&measure);
            bench_startsession(false);
            bench_startmeasure(&measure);
            b_write(ut_ctx->urpc, handshake, size);
            unsigned int cycles = bench_endmeasure(&measure);
            printf("Tx: %u\n", cycles);
            bench_endsession();
        } else {
            bench_t measure; bench_initmeasure(&measure);
            bench_startsession(false);
            bench_startmeasure(&measure);
            b_read(ut_ctx->urpc, buf, size);
            unsigned int cycles = bench_endmeasure(&measure);
            printf("Rx: %u\n", cycles);
            assert(!strncmp(buf, handshake, size - 1));
            bench_endsession();
        }
    }
}
__unused static void big_malloc(void *ctx){
    if (my_core_id == 0) return;

    size_t size = 1 << 20;
    char* buf = malloc(size); // 1 MB
    for (int i = 0; i < size; i++) buf[i] = 97 + (i%25);

    assert(buf[size-1] == 97 + ((size-1) % 25));
}

__unused static void binding_test(void *ctx) {
    struct capref frame2; 
    frame_alloc(&frame2, sizeof(struct spawninfo), NULL);
    struct spawninfo *si2;
    paging_map_frame_attr(get_current_paging_state(), (void**) &si2, sizeof(*si2), frame2, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    errval_t err = spawn_load_by_name("test_binding", si2);
    DBGERRC(err, "Error spawning test_binding");
    assert(err_is_ok(err));
}

void unit_test_cores(unit_test_ctx * utctx) {
    dispatcher_handle_t handle = curdispatcher();
    struct dispatcher_generic* disp = get_dispatcher_generic(handle);
    my_core_id = disp->core_id;

    print_test_summary("spawncore.c");

    // run_test(utctx, "BASIC_HANDSHAKE", basic_handshake);
    // run_test(utctx, "BIG_PAYLOAD_64K", big_payload_64k);
    //run_test(utctx, "BIG_PAYLOAD_PERFORMANCE", big_payload_performance);
    // run_test(utctx, "BIG_BUF_2ND_COR", big_malloc);
    // run_test(utctx, "BINDING_TEST", binding_test);
    return;
}
