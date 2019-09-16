/**
 * \file
 * \brief Memory manager header
 */

/*
 * Copyright (c) 2008, 2011, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#ifndef AOS_MM_H
#define AOS_MM_H

#include <sys/cdefs.h>
#include <errors/errno.h>
#include <aos/types.h>
#include <aos/capabilities.h>
#include <aos/slab.h>
#include <aos/dlinked_list.h>
#include "slot_alloc.h"

__BEGIN_DECLS

enum nodetype {
    NodeType_Free,      ///< This region exists and is free
    NodeType_Allocated  ///< This region exists and is allocated
};

struct capinfo {
    struct capref cap;
    genpaddr_t base;
    gensize_t size;
};

#define BUCKETS_NO 5
enum  bucket_size {
    BucketSize_4K   = 0,
    BucketSize_64K  = 1,
    BucketSize_1M   = 2,
    BucketSize_16M  = 3,
    BucketSize_64M  = 4,
    BucketSize_Err  = -1
};

/** * \brief Node in Memory manager
 */
struct mmnode {
    struct mmnode *prev;   ///< Previous node in the list.
    struct mmnode *next;   ///< Next node in the list.
    enum nodetype type;    ///< Type of `this` node.
    struct capinfo cap;    ///< Cap in which this region exists
    genpaddr_t base;       ///< Base address of this region
    gensize_t size;        ///< Size of this free region in cap
    enum bucket_size bucket;
};

/**
 * \brief Memory manager instance data
 *
 * This should be opaque from the perspective of the client, but to allow
 * them to allocate its memory, we declare it in the public header.
 */
struct mm {
    struct slab_allocator slabs; ///< Slab allocator used for allocating nodes
    slot_alloc_t slot_alloc;     ///< Slot allocator for allocating cspace
    slot_refill_t slot_refill;   ///< Slot allocator refill function
    void *slot_alloc_inst;       ///< Opaque instance pointer for slot allocator
    enum objtype objtype;        ///< Type of capabilities stored
    dlinked_list_t head_dlist; ///< Doubly linked list of allocated mmnode*
    dlinked_list_t masters_dlist; ///< Doubly linked list of allocated mmnode*
    dlinked_list_t free_buckets[BUCKETS_NO];
    struct thread_mutex big_mm_lock;
};

enum bucket_size bucket_type(gensize_t size);
gensize_t page_round_up(gensize_t size);
struct mmnode* mm_bucket_find(struct mm *mm, size_t size, size_t alignment, struct capref *retcap);
errval_t mm_alloc_from_master(struct mm *mm, size_t size, size_t alignment, struct capref *retcap, struct mmnode** node);

errval_t mm_init(struct mm *mm, enum objtype objtype,
                     slab_refill_func_t slab_refill_func,
                     slot_alloc_t slot_alloc_func,
                     slot_refill_t slot_refill_func,
                     void *slot_alloc_inst);
errval_t mm_add(struct mm *mm, struct capref cap, genpaddr_t base, size_t size);
errval_t mm_alloc_aligned(struct mm *mm, size_t size, size_t alignment,
                              struct capref *retcap);
errval_t mm_alloc(struct mm *mm, size_t size, struct capref *retcap);
errval_t mm_free(struct mm *mm, struct capref cap, genpaddr_t base, gensize_t size);
void mm_dump_mmnodes(struct mm *mm);
void mm_destroy(struct mm *mm);

// Utils
errval_t alloc_slot(struct mm* mm, int count, struct capref * cap);
void print_node(void* node);
void print_list(struct mm *mm);
void print_list_node(struct mmnode* node);

errval_t add_vpage_slab(void);

__END_DECLS

#endif /* AOS_MM_H */
