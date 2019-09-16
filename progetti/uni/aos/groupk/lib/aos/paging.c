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
#include <spawn/spawn.h>
#include <math.h>
#include "threads_priv.h"

#include <stdio.h>
#include <string.h>
#include <stdarg.h>

static struct paging_state current;

bool paging_hide_critical;

typedef void *(*morecore_alloc_func_t)(size_t bytes, size_t *retbytes);
extern morecore_alloc_func_t sys_morecore_alloc;

static void debug_critical(char *str, ...) {
    if (paging_hide_critical) return;
    va_list argptr;
    printf("\x1b[31;1mCRITICAL: ");

    va_start(argptr, str);
    vprintf(str, argptr);
    va_end(argptr);

    printf("\x1b[0m\n");
}

void _print_chunk(void* _node);
void _print_chunk(void* _node) { 
    struct pgchunk *chunk = (struct pgchunk*)_node;
    debug_printf("Base: 0x%lx, End: 0x%lx\n", chunk->base, chunk->end); 
}

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

errval_t paging_init_state(struct paging_state *st, lvaddr_t start_vaddr,
        struct capref pdir, struct slot_allocator * ca)
{
    //static bool has_been_called = false;
    //debug_printf("paging_init_state\n");
    // TODO (M2): implement state struct initialization
    // TODO (M4): Implement page fault handler that installs frames when a page fault
    // occurs and keeps track of the virtual address space.

    for (int i = 0; i < ARM_L1_MAX_ENTRIES; i++) {
        st->l2_mapped[i] = false;
    }

    dlist_init(&st->chunklist);
    dlist_init(&st->chunk_reserved_list);
    dlist_init(&st->pgmapping_list);
    st->start_vaddr = start_vaddr;
    st->l1page_cap = pdir;
    st->slot_alloc = ca;
    slab_init(&st->pgchunk_alloc, sizeof (struct pgchunk), slab_default_refill);
    slab_init(&st->pgmapping_alloc, sizeof (struct pgmapping), slab_default_refill);

    if (sys_morecore_alloc) { // is morecore initialized ? 

        // FIXME: this can cause pagefaults... are we sure of using malloc?
        size_t pgchunk_bytes = ROUND_UP(sizeof (struct pgchunk)*30, BASE_PAGE_SIZE);
        void* buf = malloc(pgchunk_bytes);
        if (!buf) USER_PANIC("[x] Malloc failed\n");
        slab_grow(&st->pgchunk_alloc, buf, pgchunk_bytes);
        size_t pgmapping_bytes = ROUND_UP(sizeof (struct pgmapping)*30, BASE_PAGE_SIZE);
        buf = malloc(pgmapping_bytes);
        if (!buf) USER_PANIC("[x] Malloc failed\n");
        slab_grow(&st->pgmapping_alloc, buf, pgmapping_bytes);

        //static char nodebuff[sizeof(struct pgchunk)*20]; 
        //slab_grow(&st->pgchunk_alloc, nodebuff, sizeof(nodebuff));
        //static char nodebuff2[sizeof(struct pgmapping)*20];
        //slab_grow(&st->pgmapping_alloc, nodebuff2, sizeof(nodebuff2));
    }
    else { // if not, use static buffer (warning! can only use one time)
        //assert(!has_been_called); // This branch should be taken only once
        //has_been_called = true;
        static char nodebuf[sizeof(struct pgchunk)*20]; 
        slab_grow(&st->pgchunk_alloc, nodebuf, sizeof(nodebuf));
        static char nodebuf2[sizeof(struct pgmapping)*20]; 
        slab_grow(&st->pgmapping_alloc, nodebuf2, sizeof(nodebuf2));
    }

    // We add an initial dummy chunk to the list
    // 1. This way we never return 0x0 as valid address
    // 2. Regardless, there should always be at least one chunk in the list

    struct pgchunk* first_chunk = slab_alloc(&st->pgchunk_alloc);
    first_chunk->base = 0x0; // FIXME this is really a horrible hack to have at least one node
    first_chunk->end = 0x0;
    dlist_tail_insert(&st->chunklist, first_chunk);

    struct pgchunk* second_chunk = slab_alloc(&st->pgchunk_alloc);
    second_chunk->base = 0x0;
    second_chunk->end = start_vaddr;
    dlist_tail_insert(&st->chunk_reserved_list, second_chunk);

    thread_mutex_init(&st->mutex);
    thread_mutex_init(&st->frame_mutex);

    return SYS_ERR_OK;
}




// We assing 16K to the stack for the excpetion handler
static char stack_base[0x8000]; 
static char* stack_top = stack_base + 0x8000;
/**
 * \brief This function handle exceptions for the thread
 */
void exception_handler(enum exception_type type, int subtype,
                       void *addr, arch_registers_state_t *regs);
void exception_handler(enum exception_type type, int subtype,
                       void *addr, arch_registers_state_t *regs) {

    switch (type) {
        case EXCEPT_PAGEFAULT: {

            if ((lvaddr_t) addr <= 0x1000) {
                lvaddr_t ip = (lvaddr_t) registers_get_ip(regs);
                debug_printf("PAGEFAULT ON NULL ADDR 0x%x, @%p, ABORTING!!", addr, ip);
                thread_exit(1);
            }

            // Handle the pagefault
            //struct morecore_state* mst = get_morecore_state();
            lvaddr_t base = ROUND_DOWN((lvaddr_t) addr, BASE_PAGE_SIZE);

            lvaddr_t sp = (lvaddr_t) registers_get_sp(regs);
            struct thread* t = thread_self();

            // debug_printf("[\x1b[32mPage fault handler\x1b[0m] Allocating [0x%zx, 0x%zx] for (%p)\n", base, base + BASE_PAGE_SIZE - 1, addr);

            if ((base <= (lvaddr_t) t->stack + 4*BASE_PAGE_SIZE && base >= (lvaddr_t) t->stack)
              || sp > (lvaddr_t) t->stack_top) {
                debug_printf("Critical Error: STACK OVERFLOW at %p (stack [%p - %p]) Quitting\n", addr, t->stack, t->stack_top);
                thread_exit(1);
            }


            struct capref frame;
            errval_t err = frame_alloc(&frame, BASE_PAGE_SIZE, NULL);
            if (err_is_fail(err)) {
                debug_printf("Unable to alloc frame\n");
                DEBUG_ERR(err, "ciao");
                USER_PANIC("UNABLE TO FIX PAGE FAULT inside exception_handler\n");
            }
            err = paging_map_fixed_attr(NULL, base, frame, BASE_PAGE_SIZE, VREGION_FLAGS_READ_WRITE);
            if (err_is_fail(err)) { 
                debug_critical("Unable to map while trying to fix page fault!!!!\n");
                USER_PANIC("UNABLE TO FIX PAGE FAULT inside exception_handler\n");
            }
            break;
           }
        case EXCEPT_NULL:
            debug_printf("Occhio zio, NULL_POINTER dereference\n");
            break;
        default:
            debug_printf("[x]Unhandled exception\n");
            thread_exit(0x1);
            break;
    }
}

/**
 * \brief This function initializes the paging for this domain
 * It is called once before main.
 */
errval_t paging_init(void)
{
    errval_t err;
    debug_printf("paging_init\n");
    // TODO (M2): Call paging_init_state for &current
    // TODO (M4): initialize self-paging handler
    // TIP: use thread_set_exception_handler() to setup a page fault handler
    // TIP: Think about the fact that later on, you'll have to make sure that
    // you can handle page faults in any thread of a domain.
    // TIP: it might be a good idea to call paging_init_state() from here to
    // avoid code duplication.
    /*if (current) return SYS_ERR_OK;*/
    set_current_paging_state(&current);
    struct capref current_l1page_cap = {.cnode = cnode_page, .slot = 0};
    paging_init_state(&current, (lvaddr_t) 0x4000000, current_l1page_cap, get_default_slot_allocator());

    // Setting thread exception handler
    debug_printf("Setting exception handler for main thread\n");
    err = thread_set_exception_handler(
            exception_handler, NULL,
            (void*) stack_base, (void*) stack_top,
            NULL, NULL);
    DBGERR(err, "Error setting exception handler for main thread\n");
    return SYS_ERR_OK;
}


/**
 * \brief Initialize per-thread paging state
 */
void paging_init_onthread(struct thread *t) {
    // TODO (M4): setup exception handler for thread `t'.
    t->exception_handler = &exception_handler;
    //FIXME: Check if multicore is fine same stack for everyone
    void *stack = calloc(0x10000, 1);
    t->exception_stack = (void*) stack;
    t->exception_stack_top = (void*) stack + 0x10000;
    /*DBGERRV(err, "Error setting exception handler for thread\n");*/
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
    pr->region_size  = ROUND_UP(size, BASE_PAGE_SIZE);
    pr->st = st;
    thread_mutex_init(&pr->mutex);

    //debug_printf("Reserved region %lx-%lx\n", pr->base_addr, pr->base_addr + pr->region_size);
    mark_chunk_reserved(st, pr->base_addr, pr->region_size);
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
    thread_mutex_lock(&pr->mutex);
    lvaddr_t end_addr = pr->base_addr + pr->region_size;
    ssize_t rem = end_addr - pr->current_addr;
    if (rem > req_size) { // FIXME: Should this be greater or equal
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
        thread_mutex_unlock(&pr->mutex);
        debug_printf("OUT OF SPACE\n");
        return LIB_ERR_VSPACE_MMU_AWARE_NO_SPACE;
    }
    thread_mutex_unlock(&pr->mutex);
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
    USER_PANIC("ok.");
    //struct pgmapping* cur = dlist_head(&pr->st->pgmapping_list);
    //while (cur) {
        //if (cur->base >= base && cur->base < base + bytes) {
            //debug_printf("Found pgmapping at 0x%lx inside paging region\n", base);
            //dlist_remove(&pr->st->pgmapping_list, cur);

            //errval_t err = vnode_unmap(cur->table_cap, cur->mapping_cap);
            //DBGERR(err, "Failed unmapping page!\n");

            ////slab_free(&pr->st->pgmapping_alloc, cur);
        //}
        //cur = cur->next;
    //}
    //mark_chunk_free(pr->st, base, bytes);
    return SYS_ERR_OK;
}

/**
 *
 * \brief Find a bit of free virtual address space that is large enough to
 *        accomodate a buffer of size `bytes`.
 */
errval_t paging_alloc(struct paging_state *st, void **buf, size_t bytes)
{
    //debug_printf("Called paging_alloc with 0x%zx bytes\n", bytes);
    *buf = NULL;
    struct pgchunk* chunk = (struct pgchunk*) st->chunk_reserved_list.head;
    if (!chunk) {
        debug_critical("Page chunklist should _never_ be empty!");
        return SYS_ERR_VM_RETRY_SINGLE;
    }
    while (chunk) {
        if (chunk->next) {
            if (bytes <= (chunk->next->base - chunk->end)) {
                *buf = (void*)chunk->end;
                break;
            }
        } else {
            if (bytes <= (2*VADDR_OFFSET - chunk->end)) {
                *buf = (void*)chunk->end;
                break;
            }
        }
        chunk = chunk->next;
    }
    if (*buf == NULL) {
        debug_critical("Space for %zx bytes not found in virtual memory", bytes);
        return SYS_ERR_PERFMON_NOT_AVAILABLE;
    }
    //debug_printf("Paging_alloc: Return valid addr 0x%lx\n", *buf);

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
    thread_mutex_lock_nested(&st->frame_mutex);
    errval_t err = paging_alloc(st, buf, bytes);
    if (err_is_fail(err)) {
        return err;
    }
    err = paging_map_fixed_attr(st, (lvaddr_t)(*buf), frame, bytes, flags);
    thread_mutex_unlock(&st->frame_mutex);
    return err;
}

#define BUFFERONE_SIZE 0x10000
char bufferone[BUFFERONE_SIZE];
char *bufferone_p = bufferone;

errval_t
slab_refill_no_pagefault(struct slab_allocator *slabs, struct capref frame, size_t minbytes)
{
    // Refill the two-level slot allocator without causing a page-fault
    minbytes *= SLAB_KEEP;  // horrible hack to avoid slab_alloc getting stuck at KEEP_SLABS
    size_t bytes = ROUND_UP(minbytes, BASE_PAGE_SIZE);
    slab_grow(slabs, bufferone_p, bytes);
    bufferone_p += bytes;
    assert(bufferone_p < bufferone + BUFFERONE_SIZE); // FIXME make this dynamically increase
    return SYS_ERR_OK;
}

errval_t mark_chunk_reserved(struct paging_state *st, lvaddr_t vaddr, size_t bytes) {
    bool previous_paging = paging_hide_critical;
    paging_hide_critical = true;
    errval_t err = mark_chunk_used(st, vaddr, bytes, &st->chunk_reserved_list);
    paging_hide_critical = previous_paging;
    return err;
}

errval_t mark_chunk_mapped(struct paging_state *st, lvaddr_t vaddr, size_t bytes) {
    return mark_chunk_used(st, vaddr, bytes, &st->chunklist);
}

errval_t mark_chunk_used(struct paging_state *st, lvaddr_t vaddr, size_t bytes, dlinked_list_t *list) {
    //debug_printf("HEAD: %p LIST: %p Marking %p (%p) as used\n", list->head, list, vaddr, vaddr + bytes);
    bool add_tail = true;
    struct pgchunk* chunk = (struct pgchunk*) list->head;
    if (!chunk) {
        debug_critical("Page chunklist should _never_ be empty!");
        return SYS_ERR_VM_RETRY_SINGLE;
    }
    while (chunk) {
        if (chunk->base <= vaddr && chunk->end > vaddr) {
            debug_critical("(1) Trying to map already mapped page at 0x%zx!", vaddr);
            return SYS_ERR_VM_ALREADY_MAPPED;
        }
        if (chunk->base >= vaddr && chunk->base < vaddr + bytes) {
            debug_critical("(2) Trying to map already mapped page at 0x%zx!", vaddr);
            return SYS_ERR_VM_ALREADY_MAPPED;
        }
        if (chunk->base == vaddr + bytes) {
            chunk->base = vaddr;
            add_tail = false;
            break;
        }
        if (chunk->base > vaddr + bytes) {
            lvaddr_t oldbase = chunk->base;
            chunk->base = vaddr; // mark this place as reserved to avoid recursion
            struct pgchunk* newchunk = slab_alloc(&st->pgchunk_alloc);
            newchunk->base = vaddr;
            newchunk->end = vaddr + bytes;
            add_tail = false;
            chunk->base = oldbase; // shrink the previous chunk back to original size
            dlist_insert(list, newchunk, chunk->prev, chunk);
            break;
        }
        if (chunk->end == vaddr) {
            if (chunk->next) {
                if (chunk->next->base >= vaddr + bytes) {
                    chunk->end = vaddr + bytes;
                    add_tail = false;
                    break;
                } else {
                    debug_critical("(3) Trying to map already mapped page at 0x%zx!", vaddr);
                    return SYS_ERR_VM_ALREADY_MAPPED;
                }
            } else {
                chunk->end = vaddr + bytes;
                add_tail = false;
                break;
            }
        }
        chunk = chunk->next;
    }
    if (add_tail) {
        struct pgchunk* tail = (struct pgchunk*) list->tail;
        lvaddr_t oldend = tail->end;
        tail->end = vaddr + bytes; // mark space as reserved to avoid recursion
        struct pgchunk* newchunk = slab_alloc(&st->pgchunk_alloc);
        newchunk->base = vaddr;
        newchunk->end = vaddr + bytes;
        tail->end = oldend; // shrink the previous chunk back to original size
        dlist_tail_insert(list, newchunk);
    }
    //debug_printf("Start list\n");
    //dlist_print(list, _print_chunk);
    return SYS_ERR_OK;
}

errval_t mark_chunk_free_reserved(struct paging_state *st, lvaddr_t vaddr, size_t bytes) {
    return mark_chunk_free(st, vaddr, bytes, &st->chunk_reserved_list);
}

errval_t mark_chunk_free_mapped(struct paging_state *st, lvaddr_t vaddr, size_t bytes) {
    return mark_chunk_free(st, vaddr, bytes, &st->chunklist);
}

errval_t mark_chunk_free(struct paging_state *st, lvaddr_t vaddr, size_t bytes, dlinked_list_t* list) {
    //debug_printf("Marking chunk at 0x%lx, size 0x%zx as free\n", vaddr, bytes);
    if (vaddr < st->start_vaddr) {
        debug_critical("Trying to free reserved space!");
        return SYS_ERR_VM_RETRY_SINGLE;
    }
    //dlist_print(&st->chunklist, _print_chunk);
    struct pgchunk* chunk = (struct pgchunk*) list->head; 
    if (!chunk) {
        debug_critical("Page chunklist should _never_ be empty!");
        return SYS_ERR_VM_RETRY_SINGLE;
    }
    while (chunk) {
        if (chunk->base < vaddr) {
            if (chunk->end > vaddr + bytes) {
                //split 
                struct pgchunk* newchunk = slab_alloc(&st->pgchunk_alloc);
                newchunk->base = vaddr + bytes;
                newchunk->end = chunk->end;
                dlist_insert(list, newchunk, chunk, chunk->next);
                chunk->end = vaddr;
                //dlist_print(&st->chunklist, _print_chunk);
                return SYS_ERR_OK;
            }
            else if (chunk->end == vaddr + bytes) {
                // resize
                chunk->end = vaddr;
                //dlist_print(&st->chunklist, _print_chunk);
                return SYS_ERR_OK;
            } else {
                // resize and continue
                if (chunk->end > vaddr) {
                    chunk->end = vaddr;
                }
                chunk = chunk->next;
                continue;
            }
        }
        if (chunk->base == vaddr) {
            if (chunk->end > vaddr + bytes) {
                //resize
                chunk->base = vaddr + bytes;
                //dlist_print(&st->chunklist, _print_chunk);
                return SYS_ERR_OK;
            }
            else if (chunk->end == vaddr + bytes) {
                // delete
                dlist_remove(list, chunk);
                //dlist_print(&st->chunklist, _print_chunk);
                return SYS_ERR_OK;
            } else {
                // delete and continue
                vaddr = chunk->end;
                chunk = chunk->next;
                dlist_remove(list, chunk);
                continue;
            }
        }
        if (chunk->base > vaddr && chunk->base < vaddr + bytes) {
            debug_critical("Trying to free unmapped page at 0x%lx!", vaddr);
            return SYS_ERR_VM_ALREADY_MAPPED;
        }
        chunk = chunk->next;
    }
    debug_critical("Unable to mark chunk at 0x%lx as free!", vaddr);
    return SYS_ERR_VM_ALREADY_MAPPED;
}

#define DBGERRM(lock, err, str) do {\
    if (err_is_fail(err)) {\
    thread_mutex_unlock(lock);\
    DEBUG_ERR(err, str);\
    debug_printf(str);\
    return err;\
    }\
} while(0);

/**
 * \brief map a user provided frame at user provided VA.
 * TODO(M1): Map a frame assuming all mappings will fit into one L2 pt
 * TODO(M2): General case 
 */
errval_t paging_map_fixed_attr(struct paging_state *st, lvaddr_t vaddr,
        struct capref frame, size_t bytes, int flags)
{
    // debug_printf("[%lx, %lx]\n", vaddr, vaddr+bytes);
    if (!st) {
        st = get_current_paging_state(); // FIXME: Shouldn't it be better to let the setting of the paging state explicit and add an assertion to check that is not NULL?
    }
    thread_mutex_lock_nested(&st->mutex);
    if (vaddr % BASE_PAGE_SIZE != 0) {
        thread_mutex_unlock(&st->mutex);
        debug_critical("Cannot map unaligned virtual address 0x%lx!", vaddr);
        return SYS_ERR_VM_FRAME_UNALIGNED;
    }
    errval_t err = SYS_ERR_OK;
    struct capref l2_to_l1, frame_to_l2;
    struct capref l1_pt = st->l1page_cap;
    size_t already_mapped = 0;
    bytes = ROUND_UP(bytes, BASE_PAGE_SIZE);


    // don't care if fails
    mark_chunk_reserved(st, vaddr, bytes);

    err = mark_chunk_mapped(st, vaddr, bytes);
    DBGERRM(&st->mutex, err, "Error while marking paging chunk used\n");


    // Add mapping to paging_state list for unmapping 
    struct pgmapping *new_pgmapping = slab_alloc(&st->pgmapping_alloc);
    new_pgmapping->base = vaddr;
    new_pgmapping->linked_next = NULL;
    dlist_tail_insert(&st->pgmapping_list, new_pgmapping);

    while (bytes > 0) {
        size_t arm_l1_offset = ARM_L1_OFFSET(vaddr);
        size_t arm_l2_offset = ARM_L2_OFFSET(vaddr);

        int count = MIN(ceil((float)bytes / BASE_PAGE_SIZE), ARM_L2_MAX_ENTRIES - arm_l2_offset);

        if (!st->l2_mapped[arm_l1_offset]) {
            // create an l2 page table
            err = arml2_alloc(st, &(st->l2_caps[arm_l1_offset]));
            DBGERRM(&st->mutex, err, "creating l2 frame");

            // map l2 to l1
            err = st->slot_alloc->alloc(st->slot_alloc, &l2_to_l1);
            DBGERRM(&st->mutex, err, "alloc slot L2 to L1");
            err = vnode_map(l1_pt, st->l2_caps[arm_l1_offset],
                            arm_l1_offset, flags, 0, 1, l2_to_l1);
            DBGERRM(&st->mutex, err, "mapping L2 to L1");

            // If a callback is set we call it with the given spawn info and the new capref
            if (st->mapping_cb) {
                err = st->mapping_cb(st->si, l2_to_l1);
                DBGERRM(&st->mutex, err, "Error when calling mapping_cb after mapping l2_to_l1\n");
            }

            st->l2_mapped[arm_l1_offset] = true;
        }

        // map frame to l2
        err = st->slot_alloc->alloc(st->slot_alloc, &frame_to_l2);
        DBGERRM(&st->mutex, err, "alloc slot frame to L2");
        err = vnode_map(st->l2_caps[arm_l1_offset], frame,
                        arm_l2_offset, flags, already_mapped, count,frame_to_l2); 
        DBGERRM(&st->mutex, err, "mapping frame to L2");
        
        // If a callback is set we call it with the given spawn info and the new capref
        if (st->mapping_cb) {
            err = st->mapping_cb(st->si, frame_to_l2);
            DBGERRM(&st->mutex, err, "Error when calling mapping_cb after mapping frame_to_l2\n");
        }


        new_pgmapping->table_cap = st->l2_caps[arm_l1_offset];
        new_pgmapping->mapping_cap = frame_to_l2;
        new_pgmapping->end = new_pgmapping->base + BASE_PAGE_SIZE * count;

        already_mapped += BASE_PAGE_SIZE * count;
        bytes -= BASE_PAGE_SIZE * count;
        vaddr += BASE_PAGE_SIZE * count;

        if (bytes > 0) {
            struct pgmapping* following_pgmapping = slab_alloc(&st->pgmapping_alloc);
            following_pgmapping->base = vaddr;
            following_pgmapping->linked_next = NULL;
            dlist_head_insert(&st->pgmapping_list, following_pgmapping);
            new_pgmapping->linked_next = following_pgmapping;
            new_pgmapping = following_pgmapping;
        }
    }
    thread_mutex_unlock(&st->mutex);
    return SYS_ERR_OK;
}

/**
 * \brief unmap region starting at address `region`.
 * NOTE: Implementing this function is optional.
 */
errval_t paging_unmap(struct paging_state *st, const void *region)
{
    lvaddr_t address = (lvaddr_t) region;
    struct pgmapping* cur = dlist_tail(&st->pgmapping_list);

    while (cur && cur->base != address) {
        cur = cur->prev;
    }
    if (!cur) {
        debug_critical("pgmapping not found!!!\n");
        return SYS_ERR_VNODE_NOT_INSTALLED;
    }


    errval_t err = vnode_unmap(cur->table_cap, cur->mapping_cap);
    DBGERR(err, "Failed unmapping page!\n");
    err = mark_chunk_free_mapped(st, cur->base, cur->end - cur->base);
    DBGERR(err, "Failed marking chunk as free!\n");
    err = mark_chunk_free_reserved(st, cur->base, cur->end - cur->base);
    DBGERR(err, "Failed marking chunk as free!\n");

    dlist_remove(&st->pgmapping_list, cur);

    while (cur && cur->linked_next) {
        cur = cur->linked_next;

        err = vnode_unmap(cur->table_cap, cur->mapping_cap);
        DBGERR(err, "Failed unmapping page!\n");
        err = mark_chunk_free_mapped(st, cur->base, cur->end - cur->base);
        DBGERR(err, "Failed marking chunk as free");
        err = mark_chunk_free_reserved(st, cur->base, cur->end - cur->base);
        DBGERR(err, "Failed marking chunk as free!\n");

        dlist_remove(&st->pgmapping_list, cur);
    }

    return SYS_ERR_OK;
}
