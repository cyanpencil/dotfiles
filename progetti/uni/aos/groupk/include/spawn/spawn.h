/**
 * \file
 * \brief create child process library
 */

/*
 * Copyright (c) 2016, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetsstrasse 6, CH-8092 Zurich. Attn: Systems Group.
 */

#ifndef _INIT_SPAWN_H_
#define _INIT_SPAWN_H_

#include "aos/slot_alloc.h"
#include "aos/paging.h"

#include <aos/dispatcher.h>

struct spawninfo {

    // Information about the binary
    char * binary_name;     // Name of the binary

    // TODO: Use this structure to keep track
    // of information you need for building/starting
    // your new process!
    struct capref croot_cap;
    struct cnoderef rootcnode;
    struct cnoderef taskcnode;
    struct capref disp_cap;
    struct capref initep;
    struct capref selfep;
    struct capref disp_frame_cap;
    struct dispatcher_shared_generic* disp_s_g;
    struct dispatcher_generic* disp_g;
    dispatcher_handle_t handle;
    arch_registers_state_t* enabled_area;

    lvaddr_t got_base;
    genvaddr_t entry_point;

    struct capref vroot_cap;
    struct cnoderef pagecnode;
    cslot_t next_page_slot;
    struct paging_state st;

    struct lmp_chan chan;
    struct capref capep;

    coreid_t core_id;
};

// Structures to keep the spawned processes
typedef struct spawned_proc_s {
    dlinked_node_t* prev;
    dlinked_node_t* next;
    char* name;
    char* kill_switch;
    struct dispatcher_shared_generic* disp_s_g;
    struct capref disp_capref;
    struct lmp_chan chan;
    struct capref capep;
} spawned_proc_t;

void print_spawned_proc(void* _node);

dlinked_list_t spawned_proc_list;
// Start a child process by binary name. Fills in si
errval_t spawn_load_by_name(void * binary_name, struct spawninfo * si);

#endif /* _INIT_SPAWN_H_ */
