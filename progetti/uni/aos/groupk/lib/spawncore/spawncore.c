#include <aos/aos.h>
#include <aos/coreboot.h>
#include <aos/kernel_cap_invocations.h>
#include <target/arm/barrelfish_kpi/arm_core_data.h>
#include <spawn/multiboot.h>

#include "../urpc/urpc_int.h"
#define __SC_INT
#include <spawncore.h>
#include <urpc.h>

extern int HARDWARE;

static errval_t setup_core_urpc(struct arm_core_data* core_data_s, urpc_rwbuf_s ** urpc_buffer) {
    // Allocating frame for URPC of other core
    errval_t err;
    //struct capref urpc_frame; 
    //size_t retbytes;
    //err = frame_alloc(&urpc_frame, MON_URPC_SIZE, &retbytes);
    //DBGERR(err, "Error allocating frame for core data on core boot\n");

    // Setup core data URPC fields
    err = frame_alloc(&cap_urpc, BASE_PAGE_SIZE, NULL);
    DBGERR(err, "Error allocating frame for cap urpc\n");
    struct frame_identity urpc_fi;
    err = frame_identify(cap_urpc, &urpc_fi);
    DBGERR(err, "Error identifying core_data\n");
    core_data_s->urpc_frame_base    = urpc_fi.base;
    core_data_s->urpc_frame_size    = urpc_fi.bytes;

    err = paging_map_frame_attr(get_current_paging_state(), (void **) urpc_buffer, 0x1000,
            cap_urpc, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Urpc frame mapping failed");

    return SYS_ERR_OK;
}

static errval_t setup_core_data(void** core_data_buf, struct arm_core_data** core_data_s,
        struct capref* kcb,
        struct frame_identity* core_data_fi) {
    // Allocating frame for core_data of core
    errval_t err;
    size_t core_data_size;
    struct capref core_data_frame; 
    err = frame_alloc(&core_data_frame, sizeof(struct arm_core_data), &core_data_size);
    DBGERR(err, "Error allocating frame for core data on core boot\n");

    // Mapping the frame
    err = paging_map_frame_attr(get_current_paging_state(), 
                                core_data_buf, core_data_size, core_data_frame, VREGION_FLAGS_READ_WRITE,
                                NULL, NULL);
    DBGERR(err, "Error mapping core data struct frame\n");

    memset(*core_data_buf, 0, core_data_size);

    // Getting the physical addresses
    err = frame_identify(core_data_frame, core_data_fi);
    DBGERR(err, "Error identifying core_data\n");

    // Structure view into the mapped buffer
    *core_data_s = (struct arm_core_data*) *core_data_buf;

    // Getting a KCB
    struct capref ram_cap; 
    err = ram_alloc(&ram_cap, OBJSIZE_KCB);
    DBGERR(err, "Error getting ram for KCB\n");

    err = slot_alloc(kcb);
    err = cap_retype(*kcb, ram_cap, 0, ObjType_KernelControlBlock, OBJSIZE_KCB, 1);
    DBGERR(err, "Error retyping ram cap to KCB\n");
    invoke_kcb_clone(*kcb, core_data_frame);

    struct frame_identity fi;
    err = frame_identify(*kcb, &fi);
    DBGERR(err, "Error identifying kcb\n");
    (*core_data_s)->kcb = fi.base;


    return SYS_ERR_OK;
}

static errval_t setup_core_monitor(struct arm_core_data* core_data_s,
        struct frame_identity core_data_fi, char * monitor_name) {
    errval_t err;
    size_t retbytes;
    // Setting up core data monitor fields
    struct mem_region* init_mm_reg = multiboot_find_module(bi, monitor_name);
    struct multiboot_modinfo init_mod = {
        .reserved   = 0,
        .mod_start  = init_mm_reg->mr_base, //FIXME: are those supposed to be virtual addresses?
        .mod_end    = init_mm_reg->mr_base + init_mm_reg->mrmod_size,
        .string     = init_mm_reg->mrmod_data,
    };
    core_data_s->monitor_module     = init_mod;

    // Allocating frame for init on other core
    struct capref init_frame; 
    err = frame_alloc(&init_frame, 
            2*ARM_CORE_DATA_PAGES*BASE_PAGE_SIZE, 
            &retbytes);
    DBGERR(err, "Error allocating frame for init on core boot\n");
    //debug_printf("Here it is: 0x%zx\n", retbytes);

    //Setup core data init fields
    struct frame_identity init_fi;
    err = frame_identify(init_frame, &init_fi);
    DBGERR(err, "Error identifying init frame\n");
    strncpy(core_data_s->init_name, monitor_name, DISP_NAME_LEN + 1);
    //debug_printf("[Mem] 0x%llx/0x%llx \n", init_fi.bytes, 2*ARM_CORE_DATA_PAGES*BASE_PAGE_SIZE);
    core_data_s->memory_base_start  = init_fi.base;
    core_data_s->memory_bytes       = init_fi.bytes;

    // Set the cmdline to the cmdline_buf 
    core_data_s->cmdline            = core_data_fi.base + offsetof(struct arm_core_data, cmdline_buf);

    return SYS_ERR_OK;
}

static errval_t setup_cpu_driver(void** cpu_driver_addr, void** reloc_seg_addr, struct arm_core_data* core_data_s) {
    errval_t err;
    struct mem_region* cpu_driver_mm_reg;
    
    if ((cpu_driver_mm_reg = multiboot_find_module(bi, "cpu_omap44xx")) != NULL) {
        HARDWARE = 1;
    } else if ((cpu_driver_mm_reg = multiboot_find_module(bi, "cpu_a15ve")) != NULL) {
        HARDWARE = 0;
    } else {
        USER_PANIC("Unknown hardware\n");
    }

    struct capref cpu_driver_frame = {
        .cnode  = cnode_module,
        .slot   = cpu_driver_mm_reg->mrmod_slot,
    };
    struct frame_identity cpu_driver_fi;
    err = frame_identify(cpu_driver_frame, &cpu_driver_fi);
    DBGERR(err, "Error identifying core_data\n");
    err = paging_map_frame_attr(get_current_paging_state(),
            cpu_driver_addr, cpu_driver_fi.bytes, cpu_driver_frame, VREGION_FLAGS_READ_WRITE,
            NULL, NULL);
    DBGERR(err, "Error mapping page for cpu driver ELF\n");


    // Allocate space for relocation of the cpu driver ELF
    struct capref reloc_seg_frame;
    size_t reloc_seg_size;
    err = frame_alloc(&reloc_seg_frame, cpu_driver_fi.bytes, &reloc_seg_size);
    DBGERR(err, "Error allocating frame for reloc segment\n");

    assert(cpu_driver_fi.bytes == reloc_seg_size);


    err = paging_map_frame_attr(get_current_paging_state(),
            reloc_seg_addr, reloc_seg_size, reloc_seg_frame, VREGION_FLAGS_READ_WRITE,
            NULL, NULL);
    DBGERR(err, "Error mapping relocation segment frame\n"); 

    struct frame_identity reloc_fi;
    err = frame_identify(reloc_seg_frame, &reloc_fi);
    // Load relocatable segment of the cpu driver
    err = load_cpu_relocatable_segment(*cpu_driver_addr, *reloc_seg_addr, reloc_fi.base,
                                       core_data_s->kernel_load_base,
                                       &core_data_s->got_base);
    printf("CPUD %"PRIxLVADDR"\n", core_data_s->kernel_load_base);
    return SYS_ERR_OK;
}

//char urpc_
//
//    if ( my_core_id == 0 ) {
//        void * buffo;
//        err = paging_map_frame_attr(get_current_paging_state(), &buffo, 0x1000,
//                cap_urpc, VREGION_FLAGS_READ_WRITE, NULL, NULL);
//        DEBUG_ERR(err, "bpp");
//        *((char *) buffo) = 'a';
//    }

errval_t spawn_core(int my_core_id, int core_id, urpc_s * urpc) {
    errval_t err;
    debug_printf("Spawning core #\x1b[33m%d\x1b[0m\n", core_id);

    // Setup core data and kcb
    void* core_data_buf;
    struct arm_core_data* core_data_s;
    struct capref kcb;
    struct frame_identity core_data_fi;
    err = setup_core_data(&core_data_buf, &core_data_s, &kcb, &core_data_fi);
    DBGERR(err, "Core data setup failed\n");

    core_data_s->src_core_id        = my_core_id;
    core_data_s->dst_core_id        = core_id;

    // Setup init
    err = setup_core_monitor(core_data_s, core_data_fi, "armv7/sbin/init");
    DBGERR(err, "Init setup failed\n");

    // Load the cpu driver
    void* cpu_driver_addr = NULL; 
    void* reloc_seg_addr = NULL;
    setup_cpu_driver(&cpu_driver_addr, &reloc_seg_addr, core_data_s);

    // Setup URPC
    urpc_rwbuf_s * urpc_rw;
    setup_core_urpc(core_data_s, &urpc_rw);
    urpc_s_init(urpc_rw, urpc, my_core_id != 0);

    // Clean cache to point of coherency
    // Syscall: priviledged ARM instruction
    sys_armv7_cache_clean_poc((void*)(uint32_t) core_data_fi.base, 
            (void*)(uint32_t) (core_data_fi.base + core_data_fi.bytes - 1));

    err = invoke_monitor_spawn_core(core_id, CPU_ARM7, core_data_fi.base);
    DBGERR(err, "Error invoking monitor_spawn_core\n");

    // Cleaning up
    err = paging_unmap(get_current_paging_state(), core_data_buf);
    DBGERR(err, "Error unmapping core_data_buf\n");
    err = paging_unmap(get_current_paging_state(), cpu_driver_addr);
    DBGERR(err, "Error unmapping cpu_driver_addr\n");
    err = paging_unmap(get_current_paging_state(), reloc_seg_addr);
    DBGERR(err, "Error unmapping reloc_seg_addr\n");

    return SYS_ERR_OK;
}
