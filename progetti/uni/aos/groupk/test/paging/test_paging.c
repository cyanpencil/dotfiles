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
#include <test/test.h>


static lvaddr_t base_vaddr = 0x6000000;
static int rw_flags = VREGION_FLAGS_READ_WRITE;
static struct paging_state* st;
extern bool paging_hide_critical;

void paging_test(struct mm* mm);
void paging_test(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;

    frame_alloc(&frame, bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame, bytes, rw_flags);

    *(lvaddr_t *) base_vaddr = 0x1337;
    assert (*(lvaddr_t *) base_vaddr == 0x1337);
    base_vaddr += BASE_PAGE_SIZE;
}

void strange_align_page(struct mm* mm);
void strange_align_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;

    frame_alloc(&frame, bytes, NULL);
    errval_t err = paging_map_fixed_attr(st, base_vaddr + 50, frame, bytes, rw_flags);
    assert(err = SYS_ERR_VM_FRAME_UNALIGNED);

    base_vaddr += 2*BASE_PAGE_SIZE;
}

void paging_test_many(struct mm* mm);
void paging_test_many(struct mm* mm) {
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

void paging_test_big(struct mm* mm);
void paging_test_big(struct mm* mm) {
    const uint32_t PAGE_NO = 0x100;
    size_t bytes = 0x1000;
    struct capref frame;

    frame_alloc(&frame, bytes*PAGE_NO, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame, bytes*PAGE_NO, rw_flags);

    for (int i = 0; i < PAGE_NO; i++) {
        *(lvaddr_t *) (base_vaddr+i*bytes) = 0x1337 + i;
    }

    for (int i = 0; i < PAGE_NO; i++) {
        assert (*(lvaddr_t *) (base_vaddr+i*bytes) == 0x1337 + i);
    }
    base_vaddr += bytes*PAGE_NO;
}

void already_mapped_page(struct mm* mm);
void already_mapped_page(struct mm* mm) {
    struct capref frame[2];
    size_t bytes = 0x1000;
    frame_alloc(&frame[0], bytes, NULL);
    paging_map_fixed_attr(st, base_vaddr, frame[0], bytes, rw_flags);
    frame_alloc(&frame[1], bytes, NULL);
    errval_t err = paging_map_fixed_attr(st, base_vaddr, frame[1], bytes, rw_flags);
    base_vaddr += BASE_PAGE_SIZE*2;
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
}

void double_page(struct mm* mm);
void double_page(struct mm* mm) {
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

void huge_page(struct mm* mm);
void huge_page(struct mm* mm) {
    const uint32_t PAGE_NO = 0x100;
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

void page_alloc(struct mm* mm);
void page_alloc(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    *(lvaddr_t *) buf = 0x1337;
    assert (*(lvaddr_t *) buf == 0x1337);
}

void large_page_alloc(struct mm* mm);
void large_page_alloc(struct mm* mm) {
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

void consecutive_pages(struct mm* mm);
void consecutive_pages(struct mm* mm) {
    const uint32_t PAGE_NO = 2;
    size_t bytes = 0x1000;
    struct capref frame[PAGE_NO];
    void * buf[2];

    assert(PAGE_NO >= 2);

    for (int i = 0; i < PAGE_NO; i++) {
        frame_alloc(&frame[i], bytes, NULL);
        paging_map_frame_attr(st, &buf[i], bytes, frame[i], rw_flags, NULL, NULL);
    }

    for (int i = 0; i < PAGE_NO - 1; i++) {
        if (!(buf[i] + bytes == buf[i+1])) {
            // this CAN fail
            printf(RED_COLOR"WARNWARN"END_COLOR"\n");
        }
    }
}

void consecutive_pages_big(struct mm* mm);
void consecutive_pages_big(struct mm* mm) {
    const uint32_t PAGE_NO = 0x5;
    size_t bytes = 0x1000 * 0x100;
    struct capref frame_m;
    struct capref * frame;

    frame_alloc(&frame_m, PAGE_NO*sizeof(struct capref), NULL);
    paging_map_frame_attr(st, (void *) &frame, PAGE_NO*sizeof(struct capref), frame_m, rw_flags, NULL, NULL);

    void * buf[PAGE_NO];

    //assert(PAGE_NO >= 2);

    for (int i = 0; i < PAGE_NO; i++) {
        frame_alloc(&frame[i], bytes, NULL);
        paging_map_frame_attr(st, &buf[i], bytes, frame[i], rw_flags, NULL, NULL);
    }

    //for (int i = 0; i < PAGE_NO - 1; i++) {
    //    assert (buf[i] + bytes == buf[i+1]);
    //}
}

void unmap_page(struct mm* mm);
void unmap_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    *(lvaddr_t *) buf = 0x1337;
    assert (*(lvaddr_t *) buf == 0x1337);

    errval_t err = paging_unmap(st, buf);
    assert(err == SYS_ERR_OK);
}

void unmap_huge_page(struct mm* mm);
void unmap_huge_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x400000; // 4 MB
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    *(lvaddr_t *) buf = 0x1337;
    assert (*(lvaddr_t *) buf == 0x1337);

    errval_t err = paging_unmap(st, buf);
    assert(err == SYS_ERR_OK);

    err = mark_chunk_free_mapped(st, (lvaddr_t)buf, 0x1000);
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
    err = mark_chunk_free_mapped(st, (lvaddr_t)buf + 0x6000, 0x1000);
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
    err = mark_chunk_free_mapped(st,(lvaddr_t) buf + 0x3ff000, 0x1000); 
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
}

void unmap_invalid_page(struct mm* mm);
void unmap_invalid_page(struct mm* mm) {
    errval_t err = paging_unmap(st, (void*) 0x0);
    assert(err == SYS_ERR_VNODE_NOT_INSTALLED);
}

void unmap_remap_page(struct mm* mm);
void unmap_remap_page(struct mm* mm) {
    struct capref frame;
    size_t bytes = 0x1000;
    void* buf;

    frame_alloc(&frame, bytes, NULL);
    paging_map_frame_attr(st, &buf, bytes, frame, rw_flags, NULL, NULL);

    errval_t err = paging_unmap(st, buf);
    assert(err == SYS_ERR_OK);

    paging_map_fixed_attr(st, (lvaddr_t) buf, frame, bytes, rw_flags);

    *(lvaddr_t *) buf = 0x1337;
    assert (*(lvaddr_t *) buf == 0x1337);
}

void paging_region(struct mm* mm);
void paging_region(struct mm* mm) {
    size_t bytes = 0x100000; // 1 MB
    struct paging_region pr;
    paging_region_init(st, &pr, bytes);

    // now let's check that the region was actually marked as used
    errval_t err = mark_chunk_reserved(st, pr.base_addr, pr.region_size);
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
}

void unmap_paging_region(struct mm* mm);
void unmap_paging_region(struct mm* mm) {
    size_t bytes = 0x100000; // 1 MB
    struct paging_region pr;
    paging_region_init(st, &pr, bytes);

    paging_region_unmap(&pr, pr.base_addr, bytes/2);

    errval_t err = mark_chunk_reserved(st, pr.base_addr + bytes/2, pr.region_size);
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);

    err = mark_chunk_free_reserved(st, pr.base_addr, bytes/2);
    assert(err == SYS_ERR_VM_ALREADY_MAPPED);
}

__unused static void huge_malloc(struct mm* mm) {
    size_t bytes = 0x100000 * 100; // 100 MB

    int *c = malloc(bytes);

    *(c + bytes/8) = 0xcafebabe;
    assert(*(c + bytes/8) == 0xcafebabe);
    free(c);
}

typedef void test_func(struct mm* mm);

static void run_test(struct mm* mm, char * name, test_func func);
static void run_test(struct mm* mm, char * name, test_func func) {
    print_test(name);
    func(mm);
    printf(GREEN_COLOR"Success"END_COLOR"\n");
}

void page_test(struct mm* mm);
void page_test(struct mm* mm) {
    print_test_summary("paging.c");

    run_test(mm, "PAGING_TEST", paging_test);
    //run_test(mm, "STRANGE_ALIGN_PAGE", strange_align_page);  // temp disabled because of too many warning prints
    run_test(mm, "PAGING_TEST_MANY", paging_test_many);
    run_test(mm, "PAGING_TEST_BIG", paging_test_big);
    //run_test(mm, "ALREADY_MAPPED_PAGE", already_mapped_page); // temp disabled because of too many warning prints
    run_test(mm, "DOUBLE_PAGE", double_page);
    run_test(mm, "HUGE_PAGE", huge_page);
    run_test(mm, "PAGE_ALLOC", page_alloc);
    run_test(mm, "LARGE_PAGE_ALLOC", large_page_alloc);
    run_test(mm, "CONSECUTIVE_PAGES", consecutive_pages);
    run_test(mm, "CONSECUTIVE_PAGES_BIG", consecutive_pages_big);

    paging_hide_critical = true;
    run_test(mm, "UNMAP_PAGE", unmap_page);
    run_test(mm, "UNMAP_INVALID_PAGE", unmap_invalid_page);
    run_test(mm, "UNMAP_HUGE_PAGE", unmap_huge_page);
    run_test(mm, "UNMAP_REMAP_PAGE", unmap_remap_page);

    run_test(mm, "PAGING_REGION", paging_region);
    //run_test(mm, "PAGING_REGION_UNMAP", unmap_paging_region);
    //
    paging_hide_critical = false;

    run_test(mm, "HUGE MALLOC 100MB", huge_malloc);
}

void unit_test_paging(unit_test_ctx * utctx) {
    st = get_current_paging_state();
    page_test(utctx->mm);
}
