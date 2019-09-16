/**
 * \file
 * \brief A library for managing physical memory (i.e., caps)
 */

#include <mm/mm.h>
#include <aos/debug.h>

#define DBGERR(e,s) if (err_is_fail(e)) {DEBUG_ERR(e, s); return e;}

#define KEEP_SLOTS 8
#define KEEP_SLABS 8
#define KB (0x400)
#define MB (KB * 1024)
#define IN_RANGE(a, x, y) (a >= x && a <= y)

/* Utils functions */

errval_t alloc_slot(struct mm* mm, int count, struct capref * cap) {
    struct slot_prealloc * sa = (struct slot_prealloc *)mm->slot_alloc_inst;
    if (sa->meta[sa->current].free < KEEP_SLOTS) {
        if (err_is_fail(mm->slot_refill(sa))) {
            return MM_ERR_SLOT_NOSLOTS;
        }
    }
    return mm->slot_alloc(sa, 1, cap);
}

// TODO: Make this a define
gensize_t page_round_up(gensize_t size){
    if          (IN_RANGE(size, 1, 4*KB)) {
        return 4*KB;
    } else if   (IN_RANGE(size, 4*KB + 1, 64*KB)) {
        return 64*KB;
    } else if   (IN_RANGE(size, 64*KB + 1, 1*MB)) {
        return 1*MB;
    } else {
        USER_PANIC("Page round up failed! (COJONE)");
    }
}

enum bucket_size bucket_type(gensize_t size) {
    if          (IN_RANGE(size, 1, 4*KB)) {
        return BucketSize_4K;
    } else if   (IN_RANGE(size, 4*KB + 1, 64*KB)) {
        return BucketSize_64K;
    } else if   (IN_RANGE(size, 64*KB + 1, 1*MB)) {
        return BucketSize_1M;
    } else {
        return BucketSize_Err;
    }
}

void print_node(void* _node) {
    struct mmnode* node = (struct mmnode*) _node;
    if (node == NULL) {
        printf("[NULL]");
        return;
    }
    struct frame_identity fi;
    frame_identify(node->cap.cap, &fi);
    printf(
        "[Base: 0x%"PRIxGENPADDR", Size: 0x%"PRIxGENSIZE", Type: %s] - Cap: [0x%"PRIxGENPADDR", Size: 0x%"PRIxGENSIZE"] \n",
        node->base,
        node->size,
        node->type == NodeType_Free ? "Free  " : "Alloc.",
        fi.base,
        fi.bytes
    );
}

void print_list(struct mm *mm) {
    return;
    /*debug_printf("\x1b[33;1m ----> PRINTING LIST OF MMNODES <---- \x1b[0m\n");*/
    /*struct mmnode *node = mm->head;*/
    /*while (node) {*/
            /*print_node(node);*/
            /*node = node->next;*/
    /*}*/
} 
void print_list_node(struct mmnode* node) {
    debug_printf("\x1b[33;1m ----> PRINTING LIST OF MMNODES <---- \x1b[0m\n");
    while (node) {
            print_node(node);
            node = node->next;
    }
} 

static lvaddr_t slab_page_space_addr = 0x2200000;

errval_t add_vpage_slab(void) {
    struct capref frame;

    errval_t err = frame_alloc(&frame, BASE_PAGE_SIZE, NULL);
    DBGERR(err, "frame_alloc");

    // FIXME do not use default paging_state
    paging_map_fixed_attr(NULL, slab_page_space_addr, frame, 
                        BASE_PAGE_SIZE, VREGION_FLAGS_READ_WRITE);
    DBGERR(err, "paging_map");

    return SYS_ERR_OK;
}


errval_t ghetto_refill(struct slab_allocator *slabs);
errval_t ghetto_refill(struct slab_allocator *slabs) {

    //FIXME: use slab_default_refill instead of this function
    // when paging_map_frame will be implemented

    errval_t err = add_vpage_slab();
    slab_grow(slabs, (lvaddr_t*)slab_page_space_addr,
                (0x1000 / sizeof(struct mmnode))*sizeof (struct mmnode));
    slab_page_space_addr += BASE_PAGE_SIZE;
    DBGERR(err, "slab_grow");

    return SYS_ERR_OK;
}

/**
 * Initialize the memory manager.
 *
 * \param  mm               The mm struct to initialize.
 * \param  objtype          The cap type this manager should deal with.
 * \param  slab_refill_func Custom function for refilling the slab allocator.
 * \param  slot_alloc_func  Function for allocating capability slots.
 * \param  slot_refill_func Function for refilling (making) new slots.
 * \param  slot_alloc_inst  Pointer to a slot allocator instance (typically passed to the alloc and refill functions).
 */
errval_t mm_init(struct mm *mm, enum objtype objtype,
                 slab_refill_func_t slab_refill_func,
                 slot_alloc_t slot_alloc_func,
                 slot_refill_t slot_refill_func,
                 void *slot_alloc_inst)
{
        assert(mm != NULL);

        /* Initialize the slabs struct */
        slab_init(&mm->slabs, 
                  sizeof (struct mmnode),
                  slab_refill_func); 

        /* Assign slot alloc/refill */
        mm->slot_alloc        = slot_alloc_func;
        mm->slot_refill       = slot_refill_func;
        mm->slot_alloc_inst   = slot_alloc_inst;
        mm->slabs.refill_func = ghetto_refill;

        mm->masters_head = NULL;
        mm->masters_tail = NULL;
        /*mm->head = NULL;*/
        dlist_init(&mm->head_dlist);
        for (uint8_t i = 0; i < BUCKETS_NO; ++i) {
            mm->free_buckets[i] = NULL;
        }

        /* Setting the type of capabilities the instance is to deal with */
        mm->objtype = objtype;

        return SYS_ERR_OK;
}

/**
 * Destroys the memory allocator.
 */
void mm_destroy(struct mm *mm)
{
}

/**
 * Adds a capability to the memory manager.
 *
 * \param  cap  Capability to add
 * \param  base Physical base address of the capability
 * \param  size Size of the capability (in bytes)
 */
errval_t mm_add(struct mm *mm, struct capref cap, genpaddr_t base, size_t size)
{
    /**
     * Here the init will give us the capability for a given region of 
     * memory. We just create a new corresponding mmnode and add it to the
     * list of mmnodes
     */
    struct mmnode *master_node = slab_alloc(& mm->slabs);
    master_node->base = 0;
    master_node->size = size;
    master_node->type = NodeType_Free;


    // set the master_node->capinfo struct
    master_node->cap.cap = cap;
    master_node->cap.base = base;
    master_node->cap.size = size;

    // Append at the end of the mm->masters_tail
    master_node->next = NULL; 
    master_node->prev = mm->masters_tail;
    if (mm->masters_tail) mm->masters_tail->next = master_node;
    mm->masters_tail = master_node;

    // If mm->masters_head was empty, the current master_node becomes the masters_head
    if(!mm->masters_head) mm->masters_head = master_node;

    return SYS_ERR_OK;
}

/**
 * Find a suitable node for the mm_alloc_aligned
 */
struct mmnode* mm_bucket_find(struct mm *mm, size_t size, size_t alignment, struct capref *retcap) {
    struct mmnode* node = mm->free_buckets[bucket_type(size)];
    while(node) {
        /**
         * If `node->base` is aligned to `alignment` we use it
         */
        if (node->base % alignment == 0) {
            break; 
        } else {
            node = node->next;
        }
    }
    return node;
}

/**
 * Create and return a new suitable node from the masters node
 */
errval_t mm_alloc_from_master(struct mm *mm, size_t size, size_t alignment, struct capref *retcap,
        struct mmnode** node_p) {
    /**
     * If we cannot find a suitable node amongst the free ones
     * in the buckets, we look inside one of the free masters
     */
    errval_t err;
    size = page_round_up(size);
    struct mmnode* master = mm->masters_head;

    if (!master) return MM_ERR_NOT_FOUND;

    while (master) {
        /**
         * If `node->base` is aligned to `alignment` we check that:
         * - The node->size is >= than the requested size
         */
        if ((master->base + master->cap.base) % alignment == 0 && master->size >= size) { /*debug_printf("Alloc_ master 1--");*/
            struct capref node_cap;
            if (err_is_fail(alloc_slot(mm, 1, &node_cap))) {
                return MM_ERR_SLOT_NOSLOTS;
            }

            err = cap_retype(node_cap, master->cap.cap, master->base, mm->objtype, size, 1);
            DBGERR(err, "failed cap retype");
            // TODO: If master size == 0 remove from list

            // Update the master
            // WARNING! XXX WARNING!
            // THIS FUNCTION CAN BE CALLED BY THE SLAB_ALLOC
            // XXX XXX XXX XXX
            //
            master->base += size;
            master->size -= size;


            *node_p = slab_alloc(&mm->slabs);
            struct mmnode* node = *node_p;
            if (node == NULL) {
                DEBUG_ERR(MM_ERR_SLOT_NOSLOTS, "Node null when requesting slab");
                return MM_ERR_SLOT_NOSLOTS;
            }
            node->type = NodeType_Allocated;
            node->cap.cap= node_cap;
            node->prev = node->next = NULL;
            node->base = node->cap.base = master->cap.base + master->base - size;
            node->size = node->cap.size = size;
            node->bucket = bucket_type(size);

            return SYS_ERR_OK;
        }
        /**
         * If `node->base` is not aligned to `alignment` we check that:
         * - The node->size is >= than the sum of:
         *   -- Requested `size`
         *   -- ROUND_UP(node->base, alignment) - node->base
         */
        else if ((master->base + master->cap.base) % alignment != 0 &&
                 master->size >= (ROUND_UP(master->base + master->cap.base, alignment) - master->base - master->cap.base) + size) {
            size_t pad = ROUND_UP(master->base + master->cap.base, alignment) - master->cap.base - master->base;
            while(pad > 0) {
                size_t pag_size;
                //TODO: Should check 1MB?
                if (pad >= 64*KB) pag_size = 64*KB;
                else pag_size = 4*KB;

                struct capref pad_cap;
                if (err_is_fail(alloc_slot(mm, 1, &pad_cap))) {
                    return MM_ERR_SLOT_NOSLOTS;
                }
                err = cap_retype(pad_cap, master->cap.cap, master->base, mm->objtype, pag_size, 1);
                DBGERR(err, "failed cap retype 2");

                master->base += pag_size;
                master->size -= pag_size;

                // TODO: If size == 0 remove from list
                struct mmnode* pad_node = slab_alloc(&mm->slabs);
                if (pad_node == NULL) {
                    DEBUG_ERR(MM_ERR_SLOT_NOSLOTS, "Node null porca troia");
                    return MM_ERR_SLOT_NOSLOTS;
                }

                pad_node->type = NodeType_Free;
                pad_node->cap.cap = pad_cap;
                pad_node->base = pad_node->cap.base = master->cap.base + master->base - pag_size;
                pad_node->size = pad_node->cap.size = pag_size;
                pad_node->bucket = bucket_type(pag_size);
                // Insert the free node in the according bucket
                pad_node->prev = NULL;
                pad_node->next = mm->free_buckets[pad_node->bucket];
                if (mm->free_buckets[pad_node->bucket]) 
                    mm->free_buckets[pad_node->bucket]->prev = pad_node;
                mm->free_buckets[pad_node->bucket] = pad_node;


                // Update the master
                pad -= pag_size;
            }
            continue;
        } else {
            master = master->next;
        }
    }
    return MM_ERR_NOT_FOUND;
}

/**
 * Allocate aligned physical memory.
 *
 * \param       mm        The memory manager.
 * \param       size      How much memory to allocate.
 * \param       alignment The alignment requirement of the base address for your memory.
 * \param[out]  retcap    Capability for the allocated region.
 */
errval_t mm_alloc_aligned(struct mm *mm, size_t size, size_t alignment, struct capref *retcap)
{
    /**
     * Here we look through the list of mmnodes, trying to find one
     * that is large enough (>= size). If the found node is exactly
     * as large as requested (== size), we just return that; otherwise 
     * if it's larger we need to split it up into two
     */
    assert(alignment % 1024 == 0);
    assert(size > 0); assert(size <= 1*MB);

    errval_t err;
    struct mmnode* node;
    node = mm_bucket_find(mm, size, alignment, retcap);
    if (!node) {
        err = mm_alloc_from_master(mm, size, alignment, retcap, &node);
        DBGERR(err, "Failed alloc from master");
    } else {
        if (node->prev) node->prev->next = node->next;
        if (node->next) node->next->prev = node->prev;
        if (mm->free_buckets[node->bucket] == node) mm->free_buckets[node->bucket] = node->next;
        node->type = NodeType_Allocated;
    }

    if (!node) {
        debug_printf("Ma che cazzo");
        USER_PANIC("----");
    }
    
    /*node->prev = NULL;*/
    /*node->next = mm->head;*/
    /*if (mm->head) mm->head->prev= node;*/
    /*mm->head  = node;*/
    // TODO: NEW
    dlist_head_insert(&mm->head_dlist, (dlinked_node_t*) node);
    *retcap = node->cap.cap;

    return SYS_ERR_OK;
}

/**
 * Allocate physical memory.
 *
 * \param       mm        The memory manager.
 * \param       size      How much memory to allocate.
 * \param[out]  retcap    Capability for the allocated region.
 */
errval_t mm_alloc(struct mm *mm, size_t size, struct capref *retcap)
{
    return mm_alloc_aligned(mm, size, BASE_PAGE_SIZE, retcap);
}

/**
 * Free a certain region (for later re-use).
 *
 * \param       mm        The memory manager.
 * \param       cap       The capability to free.
 * \param       base      The physical base address of the region.
 * \param       size      The size of the region.
 */
errval_t mm_free(struct mm *mm, struct capref cap, genpaddr_t base, gensize_t size)
{
    /** 
     * OK so what happens here, pay attention:
     * 1 - we go through the list to find the node with that capability
     * 2 - we free it, even if base/size is wrong
     * 3 - like a boss
     */
    struct mmnode *node = (struct mmnode*) dlist_head(&mm->head_dlist);
    while (node && (node->type == NodeType_Free || !capcmp(node->cap.cap, cap))) {
        node = node->next; 
    }

    if (!node) {
        return MM_ERR_NOT_FOUND;
    }

    // Remove the node from the allocated list
    /*if (node->prev) node->prev->next = node->next;*/
    /*if (node->next) node->next->prev = node->prev;*/
    /*if (mm->head == node) mm->head = node->next;*/
    dlist_remove_node(&mm->head_dlist, (dlinked_node_t*) node);
    node->type = NodeType_Free;

    // Insert the free node in the according bucket
    node->prev = NULL;
    node->next = mm->free_buckets[node->bucket];
    if (mm->free_buckets[node->bucket]) mm->free_buckets[node->bucket]->prev = node;
    mm->free_buckets[node->bucket] = node;

    if (node->base != base || node->size != size) {
        // if the argumens are wrong, we just report the mismatch
        // but still free the whole block
        return MM_ERR_MM_FREE;
    }

    return SYS_ERR_OK;
}
