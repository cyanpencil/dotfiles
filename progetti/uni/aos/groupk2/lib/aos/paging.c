/**
 * \file
 * \brief AOS paging helpers.
 */

/*
 * Copyright (c) 2012, 2013, 2016, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetstr. 6, CH-8092 Zurich. Attn: Systems Group.
 */

#include <aos/aos.h>
#include <aos/paging.h>
#include <aos/except.h>
#include <aos/slab.h>
#include "threads_priv.h"

#include <stdio.h>
#include <string.h>

#define DBGERR(e,s) if (err_is_fail(e)) {DEBUG_ERR(e, s); return e;}

static struct paging_state current;

/**
 * \brief Helper function that allocates a slot and
 *        creates a ARM l2 page table capability
 */
__attribute__((unused))
static errval_t arml2_alloc(struct paging_state * st, struct capref *ret)
{
    errval_t err;
    err = st->slot_alloc->alloc(st->slot_alloc, ret);
    if (err_is_fail(err)) {
        debug_printf("slot_alloc failed: %s\n", err_getstring(err));
        return err;
    }
    err = vnode_create(*ret, ObjType_VNode_ARM_l2);
    if (err_is_fail(err)) {
        debug_printf("vnode_create failed: %s\n", err_getstring(err));
        return err;
    }
    return SYS_ERR_OK;
}

static lvaddr_t slab_pglist_space_addr = 0x1000000;

errval_t add_pglist_slab(void);
errval_t add_pglist_slab(void) {
    struct capref frame;

    errval_t err = frame_alloc(&frame, BASE_PAGE_SIZE, NULL);
    DBGERR(err, "frame_alloc");

    // FIXME do not use default paging_state
    paging_map_fixed_attr(NULL, slab_pglist_space_addr, frame, 
                        BASE_PAGE_SIZE, VREGION_FLAGS_READ_WRITE);
    DBGERR(err, "paging_map");

    return SYS_ERR_OK;
}

errval_t ghetto_refill_pages(struct slab_allocator *slabs) {
    debug_printf("ghetto_refill_pages_called\n");
    errval_t err = add_pglist_slab();
    slab_grow(slabs, (lvaddr_t*)slab_pglist_space_addr,
                (0x1000 / sizeof(struct pglist))*sizeof (struct pglist));
    slab_pglist_space_addr += BASE_PAGE_SIZE;
    DBGERR(err, "slab_grow_pages");
    debug_printf("ghetto_refill_pages_finished\n");

    return SYS_ERR_OK;
}

errval_t paging_init_state(struct paging_state *st, lvaddr_t start_vaddr,
        struct capref pdir, struct slot_allocator * ca)
{
    debug_printf("paging_init_state\n");
    // TODO (M2): implement state struct initialization
    // TODO (M4): Implement page fault handler that installs frames when a page fault
    // occurs and keeps track of the virtual address space.

    st->slot_alloc = get_default_slot_allocator(); // FIXME are we sure of this?
    slab_init(&st->pglist_alloc, sizeof (struct pglist), ghetto_refill_pages);

    static char nodebuf[sizeof(struct pglist)*8];
    slab_grow(&st->pglist_alloc, nodebuf, sizeof(nodebuf));

    return SYS_ERR_OK;
}

/**
 * \brief This function initializes the paging for this domain
 * It is called once before main.
 */
errval_t paging_init(void)
{
    debug_printf("paging_init\n");
    // TODO (M2): Call paging_init_state for &current
    // TODO (M4): initialize self-paging handler
    // TIP: use thread_set_exception_handler() to setup a page fault handler
    // TIP: Think about the fact that later on, you'll have to make sure that
    // you can handle page faults in any thread of a domain.
    // TIP: it might be a good idea to call paging_init_state() from here to
    // avoid code duplication.
    set_current_paging_state(&current);
    /*current.slot_alloc = get_default_slot_allocator(); // FIXME are we sure of this?*/
    struct capref _pdir;
    paging_init_state(&current, 0, _pdir, NULL);
    return SYS_ERR_OK;
}


/**
 * \brief Initialize per-thread paging state
 */
void paging_init_onthread(struct thread *t)
{
    // TODO (M4): setup exception handler for thread `t'.
}

/**
 * \brief return a pointer to a bit of the paging region `pr`.
 * This function gets used in some of the code that is responsible
 * for allocating Frame (and other) capabilities.
 */
errval_t paging_region_init(struct paging_state *st, struct paging_region *pr, size_t size)
{
    void *base;
    errval_t err = paging_alloc(st, &base, size);
    if (err_is_fail(err)) {
        debug_printf("paging_region_init: paging_alloc failed\n");
        return err_push(err, LIB_ERR_VSPACE_MMU_AWARE_INIT);
    }
    pr->base_addr    = (lvaddr_t)base;
    pr->current_addr = pr->base_addr;
    pr->region_size  = size;
    // TODO: maybe add paging regions to paging state?
    return SYS_ERR_OK;
}

/**
 * \brief return a pointer to a bit of the paging region `pr`.
 * This function gets used in some of the code that is responsible
 * for allocating Frame (and other) capabilities.
 */
errval_t paging_region_map(struct paging_region *pr, size_t req_size,
                           void **retbuf, size_t *ret_size)
{
    lvaddr_t end_addr = pr->base_addr + pr->region_size;
    ssize_t rem = end_addr - pr->current_addr;
    if (rem > req_size) {
        // ok
        *retbuf = (void*)pr->current_addr;
        *ret_size = req_size;
        pr->current_addr += req_size;
    } else if (rem > 0) {
        *retbuf = (void*)pr->current_addr;
        *ret_size = rem;
        pr->current_addr += rem;
        debug_printf("exhausted paging region, "
                "expect badness on next allocation\n");
    } else {
        return LIB_ERR_VSPACE_MMU_AWARE_NO_SPACE;
    }
    return SYS_ERR_OK;
}

/**
 * \brief free a bit of the paging region `pr`.
 * This function gets used in some of the code that is responsible
 * for allocating Frame (and other) capabilities.
 * NOTE: Implementing this function is optional.
 */
errval_t paging_region_unmap(struct paging_region *pr, lvaddr_t base, size_t bytes)
{
    // XXX: should free up some space in paging region, however need to track
    //      holes for non-trivial case
    return SYS_ERR_OK;
}

/**
 * TODO(M2): Implement this function
 * \brief Find a bit of free virtual address space that is large enough to
 *        accomodate a buffer of size `bytes`.
 */
errval_t paging_alloc(struct paging_state *st, void **buf, size_t bytes)
{
    *buf = NULL;
    size_t total = 0;
    int l1 = 0;
    while (total < bytes && l1 < ARM_L1_MAX_ENTRIES) {
        if (st->pg_offset_l1[l1]) {
            for (int l2 = 0; l2 < ARM_L2_MAX_ENTRIES && total < bytes; l2++) {
                if (st->pg_offset_l1[l1]->pg_offset_l2[l2]) {
                    *buf = NULL;
                    total = 0;
                }
                else {
                    if (!*buf) {
                        *buf = (void*) (l1*BASE_PAGE_SIZE*ARM_L2_MAX_ENTRIES + l2*BASE_PAGE_SIZE);
                    }
                    total += BASE_PAGE_SIZE;
                }
            }
        }
        else {
            if (!*buf) {
                *buf = *buf ? *buf : (void*) (l1*BASE_PAGE_SIZE*ARM_L2_MAX_ENTRIES);
            }
            total += BASE_PAGE_SIZE*ARM_L2_MAX_ENTRIES;
        }
        l1++;
    }
    if (total < bytes) return MM_ERR_NOT_FOUND;
    return SYS_ERR_OK;
}

/**
 * \brief map a user provided frame, and return the VA of the mapped
 *        frame in `buf`.
 */
errval_t paging_map_frame_attr(struct paging_state *st, void **buf,
                               size_t bytes, struct capref frame,
                               int flags, void *arg1, void *arg2)
{
    errval_t err = paging_alloc(st, buf, bytes);
    if (err_is_fail(err)) {
        return err;
    }
    return paging_map_fixed_attr(st, (lvaddr_t)(*buf), frame, bytes, flags);
}

typedef void *(*morecore_alloc_func_t)(size_t bytes, size_t *retbytes);
extern morecore_alloc_func_t sys_morecore_alloc;
errval_t
slab_refill_no_pagefault(struct slab_allocator *slabs, struct capref frame, size_t minbytes)
{
    // Refill the two-level slot allocator without causing a page-fault
    minbytes *= SLAB_KEEP;  // horrible hack to avoid slab_alloc getting stuck at KEEP_SLABS
    size_t ret_bytes;
    void * buf = sys_morecore_alloc(ROUND_UP(minbytes, BASE_PAGE_SIZE), &ret_bytes);
    slab_grow(slabs, buf, ROUND_UP(minbytes, BASE_PAGE_SIZE));
    return SYS_ERR_OK;
}

static bool check_page_already_mapped(struct paging_state *st, size_t arm_l1_offset, size_t arm_l2_offset) {
    if (st->pg_offset_l1[arm_l1_offset]) {
        if (st->pg_offset_l1[arm_l1_offset]->pg_offset_l2[arm_l2_offset]) {
            return true;
        }
    }
    else {
        st->pg_offset_l1[arm_l1_offset] = slab_alloc(& st->pglist_alloc);
        for (int i = 0; i < ARM_L2_MAX_ENTRIES; i++) {
            st->pg_offset_l1[arm_l1_offset]->pg_offset_l2[i] = false;
        }
    }
    st->pg_offset_l1[arm_l1_offset]->pg_offset_l2[arm_l2_offset] = true;
    return false;
}

/**
 * \brief map a user provided frame at user provided VA.
 * TODO(M1): Map a frame assuming all mappings will fit into one L2 pt
 * TODO(M2): General case 
 */
errval_t paging_map_fixed_attr(struct paging_state *st, lvaddr_t vaddr,
        struct capref frame, size_t bytes, int flags)
{
    if (!st) {
        st = get_current_paging_state();
    }
    errval_t err;
    struct capref l2_to_l1, frame_to_l2;
    struct capref l1_pt = {
            .cnode = cnode_page,
            .slot = 0,
    };
    bytes += (vaddr % BASE_PAGE_SIZE);
    bytes = ROUND_UP(bytes, BASE_PAGE_SIZE);
    vaddr = ROUND_DOWN(vaddr, BASE_PAGE_SIZE);

    while (bytes > 0) {
        size_t arm_l1_offset = ARM_L1_OFFSET(vaddr);
        size_t arm_l2_offset = ARM_L2_OFFSET(vaddr);

        //debug_printf("Mapping page at l1 off: %d l2 off: %d\n", arm_l1_offset, ARM_L2_OFFSET(vaddr));

        if (check_page_already_mapped(st, arm_l1_offset, arm_l2_offset)) {
            return MM_ERR_ALREADY_PRESENT;
        }

        if (!st->l2_mapped[arm_l1_offset]) {
            // create an l2 page table
            err = arml2_alloc(st, &(st->l2_caps[arm_l1_offset]));
            DBGERR(err, "creating l2 frame");

            // map l2 to l1
            err = st->slot_alloc->alloc(st->slot_alloc, &l2_to_l1);
            DBGERR(err, "alloc slot L2 to L1");
            err = vnode_map(l1_pt, st->l2_caps[arm_l1_offset],
                            arm_l1_offset, flags, 0, 1, l2_to_l1);
            DBGERR(err, "mapping L2 to L1");

            st->l2_mapped[arm_l1_offset] = true;
        }

        // map frame to l2
        err = st->slot_alloc->alloc(st->slot_alloc, &frame_to_l2);
        DBGERR(err, "alloc slot frame to L2");
        err = vnode_map(st->l2_caps[arm_l1_offset], frame,
                        arm_l2_offset, flags, 0, 1,frame_to_l2);
        DBGERR(err, "mapping frame to L2");
        
        bytes -= BASE_PAGE_SIZE;
        vaddr += BASE_PAGE_SIZE;
    }
    return SYS_ERR_OK;
}

/**
 * \brief unmap a user provided frame, and return the VA of the mapped
 *        frame in `buf`.
 * NOTE: Implementing this function is optional.
 */
errval_t paging_unmap(struct paging_state *st, const void *region)
{
    return SYS_ERR_OK;
}
