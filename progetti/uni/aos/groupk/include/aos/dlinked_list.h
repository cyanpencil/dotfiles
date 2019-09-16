/**
 * \file
 * \brief A library for managing doubly-linked lists
 */

/*
 * Copyright (c) 2008, 2011, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Haldeneggsteig 4, CH-8092 Zurich. Attn: Systems Group.
 */

#ifndef AOS_DLINKED_LIST_H
#define AOS_DLINKED_LIST_H

typedef struct dlinked_list_s dlinked_list_t;
typedef struct dlinked_node_s dlinked_node_t;

struct dlinked_list_s {
    dlinked_node_t* head;
    dlinked_node_t* tail;
};

struct dlinked_node_s {
    dlinked_node_t* prev;
    dlinked_node_t* next;
};

void dlist_init(dlinked_list_t* list);
void* dlist_head(dlinked_list_t* list);
void* dlist_tail(dlinked_list_t* list);
void dlist_insert(dlinked_list_t* list, void* _node, void* _prev, void* _next);
void dlist_head_insert(dlinked_list_t* list, void* _node);
void dlist_tail_insert(dlinked_list_t* list, void* _node);
void* dlist_next(void* node);
dlinked_node_t* dlist_find(dlinked_list_t* list, bool (*filter)(dlinked_node_t*));
void dlist_remove(dlinked_list_t* list, void* _node);
void dlist_print(dlinked_list_t* list, void (*print_node)(void*));
void dlist_print_reverse(dlinked_list_t* list, void (*print_node)(void*));

#endif /* AOS_DLINKED_LIST_H */
