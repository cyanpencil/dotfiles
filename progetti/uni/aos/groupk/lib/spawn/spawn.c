#include <aos/aos.h>
#include <spawn/spawn.h>

#include <test/test.h> //FIXME: Remove this, is juts to get the color define
#include <elf/elf.h>
#include <aos/dispatcher_arch.h>
#include <barrelfish_kpi/paging_arm_v7.h>
#include <barrelfish_kpi/domain_params.h>
#include <spawn/multiboot.h>
#include <spawn/spawn.h>
#include <aos/paging.h>
#include <aos/aos_rpc.h>

extern struct bootinfo *bi;

void print_spawned_proc(void* _node) {
    spawned_proc_t* proc = (spawned_proc_t*) _node;
    debug_printf("| "cyan_COLOR"%-12s"END_COLOR" | %s |\n",
            proc->name,
            proc->disp_s_g->disabled ? RED_COLOR"dead      "END_COLOR : GREEN_COLOR"alive     "END_COLOR);
}

/**
 * \brief Mapping capability to child 
 *
 * A callback that copies the capability from 
 * our CSpace to the child's CSpace
 *
 */
static errval_t child_mapping_cb(void* _si, struct capref cap) {
    struct spawninfo* si = (struct spawninfo*) _si;
    struct capref cap_child = {
        .cnode = si->pagecnode,
        .slot = si->next_page_slot++
    };
    return cap_copy(cap_child, cap);
}

/**
 * \brief Setup an initial cspace
 *
 * Create an initial cspace layout
 */
static errval_t spawn_setup_cspace(struct spawninfo *si) {
    errval_t err;

    /* Create root CNode */
    err = cnode_create_l1(&si->croot_cap, &si->rootcnode);
    DBGERR(err, "Error creating root_cnode\n");

    /* Create taskcn */
    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_TASKCN, &si->taskcnode);
    DBGERR(err, "Error creating taskcnode_slots\n");

    /* Create slot_alloc_cnode */
    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_SLOT_ALLOC0, NULL);
    DBGERR(err, "Error creating slot_alloc_0\n");

    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_SLOT_ALLOC1, NULL);
    DBGERR(err, "Error creating slot_alloc_1\n");

    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_SLOT_ALLOC2, NULL);
    DBGERR(err, "Error creating slot_alloc_2\n");

    // Create DCB: make si->dcb invokable
    err = slot_alloc(&si->disp_cap);
    DBGERR(err, "Error allocating disp_cap\n");
    err = dispatcher_create(si->disp_cap);
    DBGERR(err, "Error allocating disp_cap\n");

    // Copy DCB to new taskcn
    struct capref disp_own_cap = {
        .cnode = si->taskcnode,
        .slot  = TASKCN_SLOT_DISPATCHER,
    };
    err = cap_copy(disp_own_cap, si->disp_cap);
    DBGERR(err, "Error copying disp_cap in its own CSPACE\n");

    // Map root CNode (in taskcn)
    struct capref rootcnode = {
        .cnode = si->taskcnode,
        .slot  = TASKCN_SLOT_ROOTCN
    };
    err = cap_copy(rootcnode, si->croot_cap);
    DBGERR(err, "Error copying rootcnode in disp space\n");

    /* Fill up basecn */
    struct cnoderef base_page_cnode;

    // Create basecn in our rootcn so we can copy stuff in there
    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_BASE_PAGE_CN, &base_page_cnode);
    DBGERR(err, "Error creating base_page_cnode\n");
    
    // get big RAM cap for L2_CNODE_SLOTS BASE_PAGE_SIZEd caps
    struct capref ram;
    // get big RAM cap for L2_CNODE_SLOTS BASE_PAGE_SIZEd caps struct capref ram;
    err = ram_alloc(&ram, L2_CNODE_SLOTS*BASE_PAGE_SIZE);
    DBGERR(err, "Error allocating RAM cap\n");

    // retype big RAM cap into small caps in new basecn
    struct capref ram_ref_start = {
        .cnode = base_page_cnode,
        .slot = 0,
    };
    err = cap_retype(ram_ref_start, ram, 0, ObjType_RAM, BASE_PAGE_SIZE, L2_CNODE_SLOTS);
    DBGERR(err, "Error retyping RAM\n");

    // delete big RAM cap
    err = cap_destroy(ram);
    DBGERR(err, "Error destroying RAM cap\n");

    return SYS_ERR_OK;

}

static errval_t spawn_setup_vspace(struct spawninfo *si) {
    errval_t err;

    err = cnode_create_foreign_l2(si->croot_cap, ROOTCN_SLOT_PAGECN, &si->pagecnode);
    DBGERR(err, "Error creating pagecnode\n");

    si->next_page_slot = 0;
    struct capref l1_page_table_own_cap = {
        .cnode = si->pagecnode,
        .slot = si->next_page_slot++
    };
    si->vroot_cap = l1_page_table_own_cap; // FIXME: refactor
    struct capref l1_page_table_cap;
    err = slot_alloc(&l1_page_table_cap);
    DBGERR(err, "Error allocating slot\n");

    err = vnode_create(l1_page_table_cap, ObjType_VNode_ARM_l1);
    DBGERR(err, "Error creating vnode\n");

    // Copying capatiblity to ROOT_VSPACE_CNODE in child
    err = cap_copy(l1_page_table_own_cap, l1_page_table_cap);
    DBGERR(err, "Error copying l1_page_table_cap in new dispatcher CSPACE\n");

    paging_init_state(&si->st, 0x1000, l1_page_table_cap, get_default_slot_allocator());

    // Setup the callback to copy the capability in the child's CSpace
    si->st.si = si;
    si->st.mapping_cb = &child_mapping_cb; 
    return SYS_ERR_OK;
}

/**
 * \brief Setup the dispatcher frame
 */
static errval_t spawn_setup_dispatcher(struct spawninfo *si,
                                        coreid_t core_id,
                                        const char *name,
                                        genvaddr_t entry,
                                        void* arch_info) {
    errval_t err;

    /* Create dispatcher frame (in taskcn) */
    /*err = slot_alloc(&si->disp_frame_cap);*/
    /*DBGERR(err, "Error creating a slot for the disp_frame_cap\n");*/
    /*err = frame_create(si->disp_frame_cap, (1 << DISPATCHER_FRAME_BITS), &ret_bytes);*/
    size_t ret_bytes;
    err = frame_alloc(&si->disp_frame_cap, (1 << DISPATCHER_FRAME_BITS), &ret_bytes);
    assert(ret_bytes == (1 << DISPATCHER_FRAME_BITS));
    DBGERR(err, "Error creating a disp_frame_cap\n");
    struct capref disp_frame_own_cap = {
        .cnode = si->taskcnode,
        .slot  = TASKCN_SLOT_DISPFRAME
    };
    err = cap_copy(disp_frame_own_cap, si->disp_frame_cap);
    DBGERR(err, "Error copying disp_frame_cap to own disp_frame_cap\n");

    /* Map in dispatcher frame */
    err = paging_map_frame_attr(get_current_paging_state(),
            (void**) &si->handle,
            1 << DISPATCHER_FRAME_BITS,
            si->disp_frame_cap,
            VREGION_FLAGS_READ_WRITE,
            NULL, NULL);
    DBGERR(err, "Error creating a page to write dispatcher's own info\n");

    genvaddr_t spawn_dispatcher_base;
    err = paging_map_frame_attr(&si->st,
            (void**) &spawn_dispatcher_base,
            1 << DISPATCHER_FRAME_BITS,
            si->disp_frame_cap,
            VREGION_FLAGS_READ_WRITE,
            NULL, NULL);
    DBGERR(err, "Failed mapping page in child space\n");

     /*Set initial state */
    si->disp_s_g = get_dispatcher_shared_generic(si->handle);
    struct dispatcher_shared_generic* disp = si->disp_s_g;
    si->disp_g = get_dispatcher_generic(si->handle);
    struct dispatcher_generic *disp_gen = si->disp_g;
    struct dispatcher_shared_arm *disp_arm = get_dispatcher_shared_arm(si->handle);
    si->enabled_area = dispatcher_get_enabled_save_area(si->handle);
    arch_registers_state_t *disabled_area =
        dispatcher_get_disabled_save_area(si->handle);

    /* Setup dispatcher and make it runnable */
    disp_gen->core_id   = core_id;
    disp->udisp         = spawn_dispatcher_base;
    disp->disabled      = 1;
    /*disp->fpu_trap      = 1;*/ //FIXME: Wait for instructions from the TAs
    /*Copy the name for debugging*/
    const char *copy_name = strrchr(name, '/');
    if (copy_name == NULL) {
        copy_name = name;
    } else {
        copy_name++;
    }
    strncpy(disp->name, copy_name, DISP_NAME_LEN);

    //debug_printf("Program counter: 0x%"PRIxGENPADDR"\n", si->entry_point);
    disabled_area->named.pc = si->entry_point; // FIXME: Here we set the program counter where it should start to execute

    //Initialize offset registers
    disp_arm->got_base = si->got_base; // FIXME: Address of .got in child VSPace
    si->enabled_area->regs[REG_OFFSET(PIC_REGISTER)] = si->got_base; // FIXME: ?
    disabled_area->regs[REG_OFFSET(PIC_REGISTER)] = si->got_base; // FIXME: ?

    si->enabled_area->named.cpsr= CPSR_F_MASK | ARM_MODE_USR;
    disabled_area->named.cpsr= CPSR_F_MASK | ARM_MODE_USR;

    disp_gen->eh_frame = 0;
    disp_gen->eh_frame_size = 0;
    disp_gen->eh_frame_hdr = 0;
    disp_gen->eh_frame_hdr_size = 0;

    return SYS_ERR_OK;
}

static errval_t elf_allocator_section(void* state, genvaddr_t base, size_t bytes, uint32_t flags, void **ret)
{
    errval_t err;

    debug_printf("Mapping a region at base %"PRIxGENVADDR" size %d with flags R:%d W:%d X:%d\n", base, bytes, (flags & VREGION_FLAGS_READ) > 0, (flags & VREGION_FLAGS_WRITE) > 0, (flags & VREGION_FLAGS_EXECUTE) > 0);

    size_t off = base % BASE_PAGE_SIZE;
    base -= off;
    bytes = ROUND_UP(bytes + off, BASE_PAGE_SIZE);

    struct capref frame;
    err = frame_alloc(&frame, bytes, NULL);
    DBGERR(err, "Error allocating frame for elf section");

    struct spawninfo *si = (struct spawninfo*) state;
    err = paging_map_fixed_attr(&si->st, base, frame, bytes, flags);
    DBGERR(err, "Error mapping elf section");

    err = paging_map_frame_attr(get_current_paging_state(), ret, bytes, frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Error mapping elf section");

    *ret += off;
    return SYS_ERR_OK;
}

static errval_t spawn_load_elf(struct spawninfo *si, lvaddr_t elf_vaddr, size_t bytes)
{
    errval_t err;
    
    err = elf_load(EM_ARM, elf_allocator_section, (void*) si, elf_vaddr,
                    bytes, &si->entry_point);
    DBGERR(err, "Loading elf");

    struct Elf32_Shdr *got_section = elf32_find_section_header_name(elf_vaddr, bytes, ".got");
    if (!got_section) {
        return SPAWN_ERR_ELF_MAP;
    }
    si->got_base = got_section->sh_addr;

    return SYS_ERR_OK;
}

/**
 * \brief Setup arguments and environment
 *
 * \param argv   Command-line arguments, NULL-terminated
 * \param envp   Environment, NULL-terminated
 */
static errval_t spawn_setup_env(struct spawninfo *si, struct mem_region* mr) {
    errval_t err;
    // 1. Get args (as string).
    const char*     args       = multiboot_module_opts(mr);
    const size_t    args_len   = strlen(args);
    /*Here we copy the arguments in the child vspace and we setup its VSpace*/

    // (1) First of all create a frame to hold the `spawn_domain_params` + 
    // NULL-terminated (+1) `args` 
    const size_t frame_size = ROUND_UP(
            sizeof(struct spawn_domain_params) + args_len + 1,
            BASE_PAGE_SIZE);
    struct capref frame;
    size_t retsize;
    err = frame_alloc(&frame, frame_size, &retsize);
    assert(retsize == frame_size);
    DBGERR(err, "Error allocating capref for frame for argumengts\n");

    // (2a) Map the args frame in our VSpace
    void *params_vaddr;
    err = paging_map_frame_attr(get_current_paging_state(),
                                &params_vaddr,
                                frame_size,
                                frame,
                                VREGION_FLAGS_READ_WRITE,
                                NULL, NULL);
    DBGERR(err, "Error mapping memory in parent for arguments\n");

    // (2b) Map the args frame in the child's VSpace through the
    // callback set before -- in si->st
    lvaddr_t params_child_vaddr;
    // We disable the callback because the argsp capability
    // need to reside in a specific place
    err = paging_map_frame_attr(&si->st,
                                (void*) &params_child_vaddr,
                                frame_size,
                                frame,
                                VREGION_FLAGS_READ_WRITE,
                                NULL, NULL);
    DBGERR(err, "Error mapping memory in children for arguments\n");

    // (2c) Give the child the capability for the frame used to map
    // the pages needed for the arguments
    struct capref child_frame = {
        .cnode = si->taskcnode,
        .slot = TASKCN_SLOT_ARGSPAGE
    };
    err = cap_copy(child_frame, frame);
    DBGERR(err, "Error copying arguments capability into child CSpace\n");

    // (3) Fill in the spawn_domain_params
    struct spawn_domain_params *params = params_vaddr;
    memset(params->argv, 0, sizeof(*params->argv));
    memset(params->envp, 0, sizeof(*params->envp));

    // (3a) Add argv 
    char* argv_base = (void*) params + sizeof(struct spawn_domain_params);
    strcpy(argv_base, args);
    char* argv_child_base = (void*) params_child_vaddr + sizeof(struct spawn_domain_params);

    size_t argc = 0;
    char* it = argv_base;
    char* prev = argv_base;
    // We scroll the entire string until we reach the zero terminator
    // Here we remove spaces from the beginning
    while (*it) {
        while (*(prev = it++) == ' ') ;
        while (*it != ' ' && *it != 0 && it++) ;
        *it++ = 0;
        params->argv[argc++] = (void*) argv_child_base + (prev - argv_base);
    }
    params->argv[argc] = NULL; // We NULL-terminate the argv list
    params->argc = argc;

    // Other settings set to 0/NULL
    // FIXME: Should we set some of this?
    params->vspace_buf = NULL;
    params->vspace_buf_len = 0;
    params->tls_init_base = NULL;
    params->tls_init_len = 0;
    params->tls_total_len = 0;
    params->pagesize = 0;

    si->enabled_area->named.r0 = params_child_vaddr;

    return SYS_ERR_OK;
}

static errval_t spawn_setup_lmp(struct spawninfo* si) {
    errval_t err;

    // Allocate a slot to accomodate the ep of the child
    err = slot_alloc(&si->selfep);
    DBGERR(err, "Error allocating slot for child selfep\n");
    // Retype child disp_cap into an endoint in the previously allocated slot
    err = cap_retype(si->selfep, si->disp_cap, 0, ObjType_EndPoint, 0, 1); 
    DBGERR(err, "Error creating self endpoint capability for child\n");
    // Move the created endpoint in the child's CSpace
    struct capref child_selfep = {
        .cnode = si->taskcnode,
        .slot = TASKCN_SLOT_SELFEP,
    };
    err = cap_copy(child_selfep, si->selfep);
    DBGERR(err, "Error copying child's own endpoint into its CSpace\n");

    struct aos_rpc* init_rpc = get_init_rpc();

    // Place parent's endpoint into child's CSpace
    struct capref child_initep = {
        .cnode = si->taskcnode,
        .slot = TASKCN_SLOT_INITEP
    };
    err = cap_copy(child_initep, aos_rpc_local_cap(init_rpc));
    DBGERR(err, "Error copying parent's endpoint into child's CSpace");
    
    //FIXME: Remove all this below, testing only
    struct capref some_ram;
    DBGERR(slot_alloc(&some_ram), "FAILED SLOT ALLOC");
    DBGERR(ram_alloc(&some_ram, 0x100000), "FAILED RAM ALLOC");
    struct capref child_some_ram = {
        .cnode = si->taskcnode,
        .slot = TASKCN_SLOTS_FREE,
    };
    DBGERR(cap_copy(child_some_ram, some_ram), "Erroc copying");

    return SYS_ERR_OK;

}

errval_t register_to_process_manager(struct spawninfo* si, char* binary_name);
errval_t register_to_process_manager(struct spawninfo* si, char* binary_name) {
    // Add the dcb of the child to the list of spawned children
    struct capref frame; 
    errval_t err = frame_alloc(&frame, sizeof(spawned_proc_t), NULL);
    DBGERR(err, "Error allocating a node for the spawned_proc_list\n");
    spawned_proc_t* proc_node;
    err = paging_map_frame_attr(get_current_paging_state(), (void**) &proc_node, 
                                sizeof(spawned_proc_t), frame, 
                                VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Error mapping a node for the spawned_proc_list\n");

    proc_node->disp_capref = si->disp_cap;
    proc_node->name = binary_name;
    proc_node->disp_s_g = si->disp_s_g;
    proc_node->chan = si->chan;
    proc_node->capep = si->capep;
    dlist_tail_insert(&spawned_proc_list, proc_node);

    // Mapping the kill switch in the child memory
    struct capref kill_frame;
    err = frame_alloc(&kill_frame, 0x1, NULL);
    DBGERR(err, "Error allocating frame for kill switch");

    err = paging_map_fixed_attr(&si->st, 0x50800000, kill_frame, 1, VREGION_FLAGS_READ);
    DBGERR(err, "Error mapping elf section");
    // Mapping the kill switch in our memory as well
    err = paging_map_frame_attr(get_current_paging_state(),
            (void **)&proc_node->kill_switch, 1, kill_frame,
            VREGION_FLAGS_READ_WRITE, NULL, NULL);

    *(proc_node->kill_switch) = 0;

    return SYS_ERR_OK;
}


// TODO(M2): Implement this function such that it starts a new process
// TODO(M4): Build and pass a messaging channel to your child process
errval_t spawn_load_by_name(void * binary_name, struct spawninfo * si) {
    debug_printf("spawn start_child: starting: %s\n", binary_name);

    if (!binary_name || ! ((char *) binary_name)[0] ) {
        debug_printf("Waring: refuseing to spawn, no name\n");
        return SPAWN_ERR_FIND_MODULE;
    }
        

    errval_t err = SYS_ERR_OK;

    // Init spawninfo
    memset(si, 0, sizeof(*si));
    si->binary_name = binary_name;

    // - Get the binary from multiboot image
    struct mem_region * mb;
    mb = multiboot_find_module(bi, binary_name);
    if (!mb) {
        return SPAWN_ERR_LOAD;
    }

    struct capref child_frame = {
        .cnode = cnode_module,
        .slot = mb->mrmod_slot,
    };

    // - Map multiboot module in your address space
    lvaddr_t elf_file;
    //gensize_t elf_size = mb->mr_bytes;
    err = paging_map_frame_attr(get_current_paging_state(),
            (void **)&elf_file, mb->mrmod_size, child_frame,
            VREGION_FLAGS_READ, NULL, NULL);

    if (strncmp("\x7f""ELF", (char *) elf_file, 4)) {
        return SPAWN_ERR_ELF_MAP;
    }

    // - Setup childs cspace
    err = spawn_setup_cspace(si);
    DBGERR(err, "Error setting up cspace\n");

    // - Setup childs vspace
    err = spawn_setup_vspace(si);
    DBGERR(err, "Error setting up vspace\n");

    // - Load the ELF binary
    err = spawn_load_elf(si, (lvaddr_t) elf_file, mb->mrmod_size);
    DBGERR(err, "Error loading elf\n");

    // - Setup dispatcher
    spawn_setup_dispatcher(si, si->core_id, binary_name, si->entry_point, NULL);
    // - Setup environment
    spawn_setup_env(si, mb);
    // - Setup LMP
    spawn_setup_lmp(si);

    // - Make dispatcher runnable
    struct capref l1_pagetable_child = {
            .cnode = si->pagecnode,
            .slot = PAGECN_SLOT_VROOT
    };

    struct capref dispatcher_frame_child = {
            .cnode = si->taskcnode,
            .slot = TASKCN_SLOT_DISPFRAME
    };

    register_to_process_manager(si, binary_name);

    err = invoke_dispatcher(si->disp_cap, cap_dispatcher,
            si->croot_cap, l1_pagetable_child,
            dispatcher_frame_child, true);
    DBGERR(err, "Error invoking dispatcher\n");


    return err;
}
