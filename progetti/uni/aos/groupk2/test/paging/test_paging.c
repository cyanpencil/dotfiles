/**
 * \file
 * \brief Tests for the physical memory management (i.e., caps)
 */

#include <mm/mm.h>
#include <aos/debug.h>
#include <aos/slot_alloc.h>
#include <aos/domain.h>
#include <test/test_mm.h>
#include <test/test_paging.h>

#define RED_COLOR "\x1b[31;1m"
#define GREEN_COLOR "\x1b[32;1m"
#define YELLOW_COLOR "\x1b[33;1m"
#define CYAN_COLOR "\x1b[36;1m"
#define red_COLOR "\x1b[31m"
#define green_COLOR "\x1b[32m"
#define yellow_COLOR "\x1b[33m"
#define cyan_COLOR "\x1b[36m"
#define END_COLOR   "\x1b[0m"

static lvaddr_t base_vaddr = 0x6000000;
static int rw_flags = VREGION_FLAGS_READ_WRITE;
static struct paging_state* st;

static void paging_test(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;

    frame_alloc(&frame, bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame, bytes, rw_flags);

    *(lvaddr_t *) base_vaddr = 0x1337;
    assert (*(lvaddr_t *) base_vaddr == 0x1337);
    base_vaddr += BASE_PAGE_SIZE;
}

static void strange_align_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;

    frame_alloc(&frame, bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr + 50, frame, bytes, rw_flags);

    *(lvaddr_t *) (base_vaddr+0x1010) = 0x1337;
    assert (*(lvaddr_t *) (base_vaddr+0x1010) == 0x1337);
    base_vaddr += 2*BASE_PAGE_SIZE;
}

static void paging_test_many(struct mm* mm) {
    const uint32_t PAGE_NO = 300;
    size_t bytes = 0x1000;
    struct capref frame[PAGE_NO];

    for (int i = 0; i < PAGE_NO; i++) {
        frame_alloc(&frame[i], bytes, NULL);
        paging_map_fixed_attr(st, base_vaddr+i*bytes, frame[i], bytes, rw_flags);
        *(lvaddr_t *) (base_vaddr+i*bytes) = 0x1337 + i;
    }

    for (int i = 0; i < PAGE_NO; i++) {
        assert (*(lvaddr_t *) (base_vaddr+i*bytes) == 0x1337 + i);
    }
    base_vaddr += BASE_PAGE_SIZE*PAGE_NO;
}

static void already_mapped_page(struct mm* mm) {
    struct capref frame[2];
    size_t bytes = 0x1000;
    frame_alloc(&frame[0], bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame[0], bytes, rw_flags);
    frame_alloc(&frame[1], bytes, NULL);
    errval_t err = paging_map_fixed_attr(st, base_vaddr, frame[1], bytes, rw_flags);
    base_vaddr += BASE_PAGE_SIZE*2;
    assert(err == MM_ERR_ALREADY_PRESENT);
}

static void double_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x2000;
    frame_alloc(&frame, bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame, bytes, rw_flags);
    *(lvaddr_t *) (base_vaddr) = 0x1337;
    *(lvaddr_t *) (base_vaddr+0x1000) = 0x1337;
    assert (*(lvaddr_t *) (base_vaddr) == 0x1337);
    assert (*(lvaddr_t *) (base_vaddr+0x1000) == 0x1337 );
    base_vaddr += 2*BASE_PAGE_SIZE;
}

static void huge_page(struct mm* mm) {
    const uint32_t PAGE_NO = 20;
    struct capref frame;
    size_t bytes = 0x1000*PAGE_NO;
    frame_alloc(&frame, bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame, bytes, rw_flags);
    for (int i = 0; i < PAGE_NO; i++) {
        *(lvaddr_t *) (base_vaddr+i*0x1000) = 0x1337 + i;
        assert (*(lvaddr_t *) (base_vaddr+i*0x1000) == 0x1337 + i);
    }
    base_vaddr += BASE_PAGE_SIZE*PAGE_NO;
}

static void page_alloc(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    *(lvaddr_t *) buf = 0x1337;
    assert (*(lvaddr_t *) buf == 0x1337);
}

static void large_page_alloc(struct mm* mm) {
    const uint32_t PAGE_NO = 20;
    struct capref frame;
    size_t bytes = 0x1000*PAGE_NO;
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    for (int i = 0; i < PAGE_NO; i++) {
        *(lvaddr_t *) (buf+i*0x1000) = 0x1337 + i;
        assert (*(lvaddr_t *) (buf+i*0x1000) == 0x1337 + i);
    }
}

typedef void test_func(struct mm* mm);

static void run_test(struct mm* mm, char * name, test_func func);
static void run_test(struct mm* mm, char * name, test_func func) {
    printf("[TEST] Testing "yellow_COLOR"%-30.30s "END_COLOR"... ", name);
    func(mm);
    printf(GREEN_COLOR"Success"END_COLOR"\n");
}

void page_test(struct mm* mm);
void page_test(struct mm* mm) {
    printf("=== Testing "YELLOW_COLOR"paging.c"END_COLOR" ===\n");

    run_test(mm, "PAGING_TEST", paging_test);
    run_test(mm, "STRANGE_ALIGN_PAGE", strange_align_page);
    run_test(mm, "PAGING_TEST_MANY", paging_test_many);
    run_test(mm, "ALREADY_MAPPED_PAGE", already_mapped_page);
    run_test(mm, "DOUBLE_PAGE", double_page);
    run_test(mm, "HUGE_PAGE", huge_page);
    run_test(mm, "PAGE_ALLOC", page_alloc);
    run_test(mm, "LARGE_PAGE_ALLOC", large_page_alloc);
}

void unit_test_paging(unit_test_ctx * utctx) {
    st = get_current_paging_state();
    page_test(utctx->mm);
}
