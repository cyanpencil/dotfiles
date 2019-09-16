/**
 * \file
 * \brief A library for managing doubly-linked lists
 */

#include <printf.h>
#include <aos/debug.h>
#include <aos/dlinked_list.h>

void dlist_init(dlinked_list_t* list) {
    assert(list != NULL);
    list->head = NULL;
    list->tail = NULL;
}

void* dlist_head(dlinked_list_t* list) { return list->head; }

void* dlist_tail(dlinked_list_t* list) { return list->tail; }

void dlist_insert(dlinked_list_t* list, void* _node, void* _prev, void* _next) {
    assert(_node != NULL);
    if (_prev == NULL || list->head == _next) { 
        return dlist_head_insert(list, _node);
    } else if (_next == NULL || list->tail  == _prev) {
        return dlist_tail_insert(list, _node);
    }
    dlinked_node_t* node_prev = (dlinked_node_t*) _prev;
    dlinked_node_t* node_next = (dlinked_node_t*) _next;
    dlinked_node_t* node      = (dlinked_node_t*) _node;

    node->prev = node_prev;
    node->next = node_next;

    node_prev->next = node;
    node_next->prev = node;
}

void dlist_head_insert(dlinked_list_t* list, void* _node) {
    assert(_node != NULL);
    dlinked_node_t* node = (dlinked_node_t*) _node;
    // No node is prev of head
    node->prev = NULL;
    // Set old head to be next of new head
    node->next = list->head;
    // If list had a head, prev of old head is new head
    if (list->head != NULL) list->head->prev = node;
    // Actually set head to be new node
    list->head = node;

    // If list was empty, tail is new head
    if (list->tail == NULL) list->tail = node;
}

void dlist_tail_insert(dlinked_list_t* list, void* _node) {
    assert(_node != NULL);
    dlinked_node_t* node = (dlinked_node_t*) _node;
    // No node is next of tail
    node->next = NULL;
    // Set old tail to be prev of new tail
    node->prev = list->tail;
    // If list had a tail, next of old tail is new tail
    if (list->tail != NULL) list->tail->next = node;
    // Actually set tail to be new node
    list->tail = node;

    // If list was empty, head is new tail
    if (list->head == NULL) list->head = node;
}

void* dlist_next(void* node) {
    return ((dlinked_node_t*) node)->next;
}

dlinked_node_t* dlist_find(dlinked_list_t* list, bool (*filter)(dlinked_node_t*)) {
    dlinked_node_t* node = list->head;
    while (node != NULL) {
        if (filter(node)) return node;
        else node = node->next;
    }
    return node;
}

void dlist_remove(dlinked_list_t* list, void* _node) {
    assert(_node != NULL);
    dlinked_node_t* node = (dlinked_node_t*) _node;
    // If prev is not NULL set its next to be the next
    // of the node to be removed
    if (node->prev) node->prev->next = node->next;
    // If next node is not NULL set its prev to be prev
    // of the node to be removed
    if (node->next) node->next->prev = node->prev;

    // If node to be removed was the head set the next 
    // to be the new head
    if (list->head == node) list->head = node->next;
    // If node to be removed was the tail set the prev 
    // to be the new head
    if (list->tail == node) list->tail = node->prev;
}

void dlist_print(dlinked_list_t* list, void (*print_node)(void*)) {
    dlinked_node_t* node = list->head;
    while (node != NULL) {
        print_node(node);
        node = node->next;
    }
}

void dlist_print_reverse(dlinked_list_t* list, void (*print_node)(void*)) {
    dlinked_node_t* node = list->tail;
    while (node != NULL) {
        print_node(node);
        node = node->prev;
        if (node != NULL) {
            printf(" => ");
        }
    }
    printf("\n");
}
