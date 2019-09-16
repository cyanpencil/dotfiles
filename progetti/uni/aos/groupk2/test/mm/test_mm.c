/**
 * \file
 * \brief Tests for the physical memory management (i.e., caps)
 */

#include <mm/mm.h>
#include <aos/debug.h>
#include <aos/slot_alloc.h>
#include <aos/dlinked_list.h>

#include <test/test_mm.h>

#define RED_COLOR "\x1b[31;1m"
#define GREEN_COLOR "\x1b[32;1m"
#define YELLOW_COLOR "\x1b[33;1m"
#define CYAN_COLOR "\x1b[36;1m"
#define red_COLOR "\x1b[31m"
#define green_COLOR "\x1b[32m"
#define yellow_COLOR "\x1b[33m"
#define cyan_COLOR "\x1b[36m"
#define END_COLOR   "\x1b[0m"

size_t count_alloc_size(struct mmnode* node, enum nodetype type, size_t size);
size_t count_alloc_size(struct mmnode* node, enum nodetype type, size_t size) {
    size_t count = 0;
    while(node) {
        if (node->type == type && 
                (!size || node->size == size)) {
            count++;
        }
        node = node->next;
    }
    return count;
}

size_t count_alloc(struct mmnode* node, enum nodetype type);
size_t count_alloc(struct mmnode* node, enum nodetype type) {
    return count_alloc_size(node, type, 0);
}

void alloc_free(struct mm* mm);
void alloc_free(struct mm* mm) { const uint32_t NODE_NO      = 2;
    const uint32_t NODE_SIZE    = 0x1000;

    /*debug_printf("PRE\n");*/
    /*print_list(mm);*/
    /*Allocation of NODE_NO nodes of size NODE_SIZE*/
    uint32_t old_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    struct capref cap[NODE_NO]; 
    for (int i = 0; i < NODE_NO; i++) { mm_alloc (mm, NODE_SIZE, &cap[i]); }
    uint32_t new_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist), NodeType_Allocated);
    assert(new_alloc_nodes_no - old_alloc_nodes_no == NODE_NO);

    /*Free of the previously NODE_NO allocated nodes*/
    uint32_t old_free_nodes_no  = count_alloc(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free);
    for (int i = 0; i < NODE_NO; i++) {
        /*Get the base and the size of the node corresponding to the*/
        /*capability we want to free*/
        struct frame_identity fi;
        frame_identify(cap[i], &fi);

        mm_free (mm, cap[i], fi.base, fi.bytes);
    }
    uint32_t new_free_nodes_no = count_alloc(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free);
    /*debug_printf("POST\n");*/
    /*print_list(mm);*/
    /*print_list_node(mm->free_buckets[0]);*/
    /*print_list_node(mm->free_buckets[1]);*/
    /*print_list_node(mm->free_buckets[2]);*/
    assert(new_free_nodes_no - old_free_nodes_no == NODE_NO);
}

void alloc_free_16K(struct mm* mm);
void alloc_free_16K(struct mm* mm) {
    const uint32_t NODE_NO      = 2;
    const uint32_t NODE_SIZE    = 0x1000;
    const uint32_t NODE_ALIGN   = 0x4000;

    uint32_t old_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    struct capref cap[NODE_NO]; 
    for (int i = 0; i < NODE_NO; i++) { 
        mm_alloc_aligned(mm, NODE_SIZE, NODE_ALIGN, &cap[i]);
    }
    uint32_t new_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist), NodeType_Allocated);
    assert(new_alloc_nodes_no - old_alloc_nodes_no == NODE_NO);

    /*Free of the previously NODE_NO allocated nodes*/
    uint32_t old_free_nodes_no  = count_alloc(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free);
    for (int i = 0; i < NODE_NO; i++) {
        /*Get the base and the size of the node corresponding to the*/
        /*capability we want to free*/
        struct frame_identity fi;
        frame_identify(cap[i], &fi);

        mm_free (mm, cap[i], fi.base, fi.bytes);
    } uint32_t new_free_nodes_no = count_alloc(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free);
    assert(new_free_nodes_no - old_free_nodes_no == NODE_NO);
}

void double_free(struct mm* mm);
void double_free(struct mm* mm) {
    struct capref cap; 
    struct frame_identity fi;
    errval_t err;
    mm_alloc_aligned(mm, 0x1000, 1024, &cap);

    frame_identify(cap, &fi);
    err = mm_free(mm, cap, fi.base, fi.bytes);
    err = mm_free(mm, cap, fi.base, fi.bytes);
    assert(err == MM_ERR_NOT_FOUND);
}

#define setw(v,a)   (*((volatile unsigned int *) (a)) |= (0x1 << v))

void free_wrong_size(struct mm* mm);
void free_wrong_size(struct mm* mm) {
    struct capref cap; 
    struct frame_identity fi;
    errval_t err;
    mm_alloc_aligned(mm, 0x1000, 1024, &cap);

    frame_identify(cap, &fi);
    err = mm_free(mm, cap, fi.base, fi.bytes * 40);
    assert(err == MM_ERR_MM_FREE);
}

void aligned_test(struct mm* mm);
void aligned_test(struct mm* mm) {
    struct capref cap[3]; 
    struct frame_identity fi;
    mm_alloc_aligned(mm, 0x1000, 1024,   &cap[0]);
    mm_alloc_aligned(mm, 0x1000, 0x1000, &cap[1]);
    mm_alloc_aligned(mm, 0x1000, 0x4000, &cap[2]);

    frame_identify(cap[0], &fi);
    assert (fi.base % 1024 == 0);
    mm_free (mm, cap[0], fi.base, fi.bytes);

    frame_identify(cap[1], &fi);
    assert (fi.base % 0x1000 == 0);
    mm_free (mm, cap[1], fi.base, fi.bytes);

    frame_identify(cap[2], &fi);
    assert (fi.base % 0x4000 == 0);
    mm_free (mm, cap[2], fi.base, fi.bytes);
}

void _print_mmnode(void* _node);
void _print_mmnode(void* _node) {
    struct mmnode* node = (struct mmnode*) _node;
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

void aligned_test_256 (struct mm * mm);
void aligned_test_256 (struct mm * mm) {
    const uint32_t NODE_NO      = 2;
    const uint32_t NODE_SIZE    = 0x100000;

    /*dlist_print(&mm->head_dlist, _print_mmnode);*/
    uint32_t old_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    struct capref cap[NODE_NO]; 
    for (int i = 0; i < NODE_NO; i++) {
        if (err_is_fail(mm_alloc_aligned(mm, NODE_SIZE, 0x1000, &cap[i]))) {
            assert(false);
        }
    }
    /*dlist_print(&mm->head_dlist, _print_mmnode);*/
    uint32_t new_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    assert(new_alloc_nodes_no - old_alloc_nodes_no == NODE_NO);


    uint32_t old_free_nodes_no  = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, NODE_SIZE);
    for (int i = 0; i < NODE_NO; i++) {
        struct frame_identity fi;
        frame_identify(cap[i], &fi);
        mm_free (mm, cap[i], fi.base, fi.bytes);
    } 
    uint32_t new_free_nodes_no = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, NODE_SIZE);
    assert(new_free_nodes_no - old_free_nodes_no == NODE_NO);
}

void aligned_strange_size (struct mm * mm);
void aligned_strange_size (struct mm * mm) {
    const uint32_t NODE_NO      = 10;
    const uint32_t NODE_SIZE    = 0xfff;

    uint32_t old_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    struct capref cap[NODE_NO]; 
    for (int i = 0; i < NODE_NO; i++) {
	if (err_is_fail(mm_alloc_aligned(mm, NODE_SIZE, 0x1000, &cap[i]))) {
	    assert(false);
	}
    }
    uint32_t new_alloc_nodes_no = count_alloc((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated);
    assert(new_alloc_nodes_no - old_alloc_nodes_no == NODE_NO);


    uint32_t old_free_nodes_no  = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, page_round_up(NODE_SIZE));
    for (int i = 0; i < NODE_NO; i++) {
        struct frame_identity fi;
        frame_identify(cap[i], &fi);
        mm_free (mm, cap[i], fi.base, fi.bytes);
    } 
    uint32_t new_free_nodes_no = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, page_round_up(NODE_SIZE));
    assert(new_free_nodes_no - old_free_nodes_no == NODE_NO);
}

void stress_alloc_aligned (struct mm * mm);
void stress_alloc_aligned (struct mm * mm) {
    const uint32_t ITER_NO  = 10;
    const uint32_t NODE_NO      = 400;
    // Allocate the capability array
    lvaddr_t vaddr = 0x5f00000;
    struct capref * cap = (void *) vaddr; 
    

    size_t bytes = NODE_NO * sizeof(struct capref);
	int flags = VREGION_FLAGS_READ_WRITE;

    const int32_t FRAME_NO = (bytes / 0x1000) + (bytes % 0x1000 != 0);
    struct capref frame[FRAME_NO];
    for (int i = 0; i < FRAME_NO; i++) {
        frame_alloc(&frame[i], bytes, NULL);
        paging_map_fixed_attr(NULL, vaddr+i*0x1000, frame[i], 0x1000, flags);
    }

    for (uint32_t j = 0; j < ITER_NO; ++j) {
        /*printf("%lu/%lu\n", j, ITER_NO);*/
        const uint32_t NODE_SIZE    = 0x100000;
        const uint32_t NODE_ALIGN   = 0x100000;

        /*[>Allocation of NODE_NO nodes of size NODE_SIZE<]*/
        /*dlist_print(&mm->head_dlist, &print_node);*/
        uint32_t old_alloc_nodes_no = count_alloc_size((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated, page_round_up(NODE_SIZE));
        for (int i = 0; i < NODE_NO; i++) { mm_alloc_aligned(mm, NODE_SIZE, NODE_ALIGN, &cap[i]); }
        /*dlist_print(&mm->head_dlist, &print_node);*/
        uint32_t new_alloc_nodes_no = count_alloc_size((struct mmnode*) dlist_head(&mm->head_dlist),  NodeType_Allocated, page_round_up(NODE_SIZE));
        assert(new_alloc_nodes_no - old_alloc_nodes_no == NODE_NO);

        /*[>Free of the previously NODE_NO allocated nodes<]*/
        uint32_t old_free_nodes_no  = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, page_round_up(NODE_SIZE));
        for (int i = 0; i < NODE_NO; i++) {
            /*[>Get the base and the size of the node corresponding to the<]*/
            /*[>capability we want to free<]*/
            struct frame_identity fi;
            frame_identify(cap[i], &fi);

            mm_free (mm, cap[i], fi.base, fi.bytes);
        } uint32_t new_free_nodes_no = count_alloc_size(mm->free_buckets[bucket_type(NODE_SIZE)], NodeType_Free, page_round_up(NODE_SIZE));
        assert(new_free_nodes_no - old_free_nodes_no == NODE_NO);
    }
}

typedef struct test_node {
    dlinked_node_t* prev;
    dlinked_node_t* node;
    char c;
} test_node;
void _print_node(void* _node);
void _print_node(void* _node) { printf("Node: %c", ((test_node*)_node)->c); }
bool _find_node_A(dlinked_node_t* node);
bool _find_node_A(dlinked_node_t* node) {
    return ((test_node*) node)->c == 'A';
}
bool _find_node_B(dlinked_node_t* node);
bool _find_node_B(dlinked_node_t* node) {
    return ((test_node*) node)->c == 'A';
}
bool _find_node_C(dlinked_node_t* node);
bool _find_node_C(dlinked_node_t* node) {
    return ((test_node*) node)->c == 'C';
}
bool _find_node_Z(dlinked_node_t* node);
bool _find_node_Z(dlinked_node_t* node) {
    return ((test_node*) node)->c == 'Z';
}
void dlinked_list_test(struct mm* mm);
void dlinked_list_test(struct mm* mm) {
    dlinked_list_t list; dlist_init(&list);
    test_node* node_a = slab_alloc(&mm->slabs);
    node_a->c = 'A';
    test_node* node_b = slab_alloc(&mm->slabs);
    node_b->c = 'B';
    test_node* node_c = slab_alloc(&mm->slabs);
    node_c->c = 'C';

    dlist_head_insert(&list, (dlinked_node_t*) node_a);
    assert(list.head == (dlinked_node_t*)node_a);
    assert(list.tail == (dlinked_node_t*)node_a);

    dlist_head_insert(&list, (dlinked_node_t*) node_b);
    assert(list.head == (dlinked_node_t*)node_b);
    assert(list.tail == (dlinked_node_t*)node_a);

    dlist_tail_insert(&list, (dlinked_node_t*) node_c);
    assert(list.head == (dlinked_node_t*)node_b);
    assert(list.tail == (dlinked_node_t*)node_c);

    test_node* node = (test_node*) dlist_find(&list, &_find_node_A);
    assert(node == node_a);

    node = (test_node*) dlist_find(&list, &_find_node_Z);
    assert(node == NULL);

    dlist_remove_node(&list, (dlinked_node_t*)node_a);
    node = (test_node*) dlist_find(&list, &_find_node_A);
    assert(node == NULL);

    dlist_remove_node(&list, (dlinked_node_t*)node_c);
    node = (test_node*) dlist_find(&list, &_find_node_C);
    assert(node == NULL);

    dlist_remove_node(&list, (dlinked_node_t*)node_b);
    node = (test_node*) dlist_find(&list, &_find_node_B);
    assert(node == NULL);
}

typedef void test_func(struct mm* mm);

static void run_test(struct mm* mm, char * name, test_func func);
static void run_test(struct mm* mm, char * name, test_func func) {
    printf("[TEST] Testing "yellow_COLOR"%-30.30s "END_COLOR"... ", name);
    func(mm);
    printf(GREEN_COLOR"Success"END_COLOR"\n");
}


void memory_test(struct mm* mm);
void memory_test(struct mm* mm) {
    printf("=== Testing "YELLOW_COLOR"mm.c"END_COLOR" ===\n");

#if 0
#endif
    run_test(mm, "ALLOC_FREE", alloc_free);
    run_test(mm, "ALLOC_FREE_TEST_16K", alloc_free_16K);
    run_test(mm, "ALLOC_FREE_TEST_16K (2nd time)", alloc_free_16K);
    run_test(mm, "ALLOC_ALIGNED", aligned_test);
    run_test(mm, "DOUBLE_FREE", double_free);
    run_test(mm, "FREE_WRONG_SIZE", free_wrong_size);

    run_test(mm, "ALIGNED_STRANGE_SIZE", aligned_strange_size);
    run_test(mm, "256_ALLOC_ALIGNED", aligned_test_256);
    run_test(mm, "DLINKED_LIST_TEST", dlinked_list_test);

    run_test(mm, "STRESS", stress_alloc_aligned);
}

void unit_test_mem(unit_test_ctx * utctx) {
    memory_test(utctx->mm);
}
