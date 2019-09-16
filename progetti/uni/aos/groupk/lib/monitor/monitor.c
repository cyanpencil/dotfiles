#include <stdio.h>
#include <stdlib.h>

#include <aos/aos.h>
#include <aos/inthandler.h>
#include <fs/fs.h>
#include <aos/deferred.h>
#include <aos/waitset.h>
#include <aos/aos_rpc.h>
#include <aos/kernel_cap_invocations.h>
#include <target/arm/barrelfish_kpi/arm_core_data.h>
#include <spawn/spawn.h>
#include <urpc.h>

#include <monitor.h>
#include <urpc.h>

#include <test/test.h>
#include <test/test_mm.h>
#include <test/test_paging.h>
#include <test/test_proc.h>
#include <test/test_rpc.h>
#include <test/test_cores.h>
#include <test/test_filesystem.h>
#include <test/test_nm.h>

extern urpc_s urpc;
extern unit_test_ctx utctx;
extern volatile bool fs_setup_complete;

// static struct aos_rpc *binding_pending_chan = NULL;

//ghetto name server
#define KNOWN_SERVICES_MAX 20
#define KNOWN_CHAN_MAX 20
char* known_services[KNOWN_SERVICES_MAX];
struct aos_rpc* known_services_chans[KNOWN_SERVICES_MAX];
int num_known_services = 0;

void default_urpc_cb(void* arg, struct aos_rpc* chan, rpc_res* res) {
    DBGERRC(
     aos_rpc_send_capbuf(chan, res->bytes, sizeof(*res), NULL_CAP),
     "Error forwarding response over LMP"
    );
}

void cross_core_fs_urpc_cb(void* arg, struct aos_rpc* chan, rpc_res* res) {
    errval_t err;
    struct capref *cap = (struct capref*) arg;
    // Try again
    if (res->args.err == LIB_ERR_MONITOR_RPC_SERVICE_NOT_FOUND) {
        barrelfish_usleep(1000000);
        struct frame_identity cap_fi;
        err = frame_identify(*cap, &cap_fi);
        DBGERRV(err, "Error identifying module cap\n");

        rpc_req req;
        req.type = RPC_REMOTE_BIND_FRAME;
        rpc_remote_bind_req sreq = {0};
        sreq.cap_fi = cap_fi;
        strncpy(sreq.service_name, "mmchs_service", SERVICE_NAME_LEN);
        memcpy(req.buf, &sreq, sizeof(rpc_remote_bind_req));
        urpc_write_msg(&urpc, &req, sizeof(rpc_req));
        debug_printf("Sent remote bind request for service %s, from %d to other core\n", "mmchs_service", disp_get_core_id());

        urpc_cb_t *cb = malloc(sizeof(urpc_cb_t));
        if (cb == NULL) { USER_PANIC("MALLOC FAILED\n"); }
        cb->f = cross_core_fs_urpc_cb;
        cb->args = cap;
        cb->chan = chan;
        dlist_tail_insert(&urpc_cb_queues[URPC_CB_BINDING], cb);
    } else {
        filesystem_init_from_init_cross(*cap);
        filesystem_mount("/sdcard", "mmchs://fat32/0");
        fs_setup_complete = true;
    }

}

static void handle_getchar_req(struct aos_rpc* chan, int count) {
    char *ret = consume_readterm_buf(count);
    errval_t err = aos_rpc_send_capbuf(chan, ret, count, NULL_CAP);
    DBGERRV(err, "Error sending response to getchar\n");
}

typedef void (*callable)(char*);
typedef struct {
    const char *name;
    callable func;
} builtin;

static void handle_rpc_run_test(struct aos_rpc* chan, char *buf) {
    builtin tests[] = {
         {"mem", (callable) unit_test_mem},
         {"paging", (callable) unit_test_paging},
         {"proc", (callable) unit_test_proc},
         {"rpc", (callable) unit_test_rpc},
         {"cores", (callable) unit_test_cores},
         {"filesystem", (callable) unit_test_filesystem},
    };
    int test_num = (sizeof (tests) / sizeof (builtin));

    if (!buf || !strcmp(buf, "help") || *buf == '\0') {
        for (int i = 0; i < test_num; i++) {
            printf("run_test %s\n", tests[i].name);
        }
        return;
    }

    for (int i = 0; i < test_num; i++) {
        if (!strcmp(buf, tests[i].name)){
            tests[i].func((void*) &utctx);
            return;
        }
    }

}

static void handle_ram_alloc_req(struct aos_rpc* chan, rpc_req_ram_cap* req) {
    //debug_printf("Trying to allocate size:%zx, align:%zx\n", req->size, req->align);
    rpc_res_ram_cap res = {};

    struct capref ram;
    errval_t err;
    //debug_printf("alllllocatting %d\n", req->size);
    // Get memory for child
    err = ram_alloc_aligned(&ram, req->size, req->align);
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Getting ram for memserver");
    }
    res.args.err        = err;
    // Send back the outcome and the ram capability
    err = aos_rpc_send_capbuf(chan, res.bytes, 36, ram);

    DBGERRV(err, "Error sending cap request to init\n");
}

static void handle_rpc_dev_cap_req(struct aos_rpc* chan, rpc_req_dev_cap* req) {
    errval_t err;
    rpc_res_dev_cap res = {};
    struct capref dev_cap;
    err = slot_alloc(&dev_cap);
    res.args.err = err;
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Allocating frame cap for device");
        err = aos_rpc_send_capbuf(chan, res.bytes, 36, dev_cap);
    }

    err = frame_forge(dev_cap, req->paddr, req->bytes, req->coreid);
    res.args.err = err;
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Forging frame cap for device");
    }
    err = aos_rpc_send_capbuf(chan, res.bytes, 36, dev_cap);
    DBGERRV(err, "Error sending back forged frame for device\n");
}

extern coreid_t my_core_id;

static void handle_rpc_req_proc_spawn(struct aos_rpc* chan, rpc_req_proc_spawn* req) {
    errval_t err;
    domainid_t pid;
    struct spawninfo *si = NULL;

    if (my_core_id != req->core_id) {
        debug_printf("Calling URPC %d\n", req->core_id);

        // Writing message to other core (only two cores for now, no need to specify destination)
        size_t req_size = sizeof(rpc_req) + sizeof(rpc_req_proc_spawn) + strlen(req->name) + 1;
        rpc_req * reqq = malloc(req_size);
        if (reqq == NULL) {
            USER_PANIC("RPC spawn: Malloc failed");
        }

        reqq->type = RPC_REQ_PROC_SPAWN;
        rpc_req_proc_spawn * sreq = (rpc_req_proc_spawn *) reqq->buf;
        sreq->core_id = req->core_id;
        strncpy( sreq->name, req->name, strlen(req->name)+1);

        urpc_cb_t *cb = malloc(sizeof(urpc_cb_t));
        if (cb == NULL) { USER_PANIC("MALLOC FAILED\n"); }
        cb->f = default_urpc_cb;
        cb->args = NULL;
        cb->chan = chan;
        dlist_tail_insert(&urpc_cb_queues[URPC_CB_SPAWN_PROC], cb);

        // urpc_lock(&urpc);
        urpc_write_msg(&urpc, reqq, req_size);
        debug_printf("CALLED URPC\n");

        // rpc_res_proc_spawn * urpc_res;
        // size_t urpc_res_len;
        // urpc_read_msg(&urpc, (void *) &urpc_res, &urpc_res_len);
        // urpc_unlock(&urpc);

        // pid = urpc_res->pid;
        // err = urpc_res->err;
        // free(urpc_res);
        // free(reqq);
    } else {
        si = malloc(sizeof(struct spawninfo));
        if (!si) {
            USER_PANIC("RPC spawn: Malloc failed");
        }
        si->core_id = req->core_id;

        err = spawn_load_by_name(req->name, si);
        pid = si->disp_g->domain_id;

        if (err_is_fail(err)) {
            DEBUG_ERR(err, "Error spawning child\n");
        }

        rpc_res_proc_spawn * res = malloc(sizeof(rpc_res_proc_spawn));
        if (!res) {
            USER_PANIC("RPC spawn: Malloc failed");
        }

        res->args.res_type = URPC_CB_SPAWN_PROC;
        res->args.err = err;
        if (!err_is_fail(err)) {
            res->args.pid = pid;
        } else {
            DEBUG_ERR(err, "Error sending cap request to init\n");
        }


        debug_printf("Size %d, Pid: %d\n", sizeof(*res), res->args.pid);
        err = aos_rpc_send_capbuf(chan, res->bytes, sizeof(*res), NULL_CAP);


        free(si);
        free(res);
    }

}

static void handle_service_registration(struct aos_rpc* chan, char* buf) {
    if (*buf != '\0') {
        if (!strcmp(buf, "mmchs_service")) {
            debug_printf("Registering to mmchs from init\n");
            filesystem_init_from_init(chan);
            filesystem_mount("/sdcard", "mmchs://fat32/0");
            fs_setup_complete = true;
        }
        assert(num_known_services < KNOWN_SERVICES_MAX);
        known_services[num_known_services] = malloc(64);
        strncpy (known_services[num_known_services], buf, 63);
        known_services_chans[num_known_services] = chan;
        debug_printf("Registered service with name %s\n", known_services[num_known_services]);
        num_known_services++;
    }
}

static errval_t handle_binding_frame(struct aos_rpc* chan, char* buf, struct capref cap, bool recurse) {
    errval_t err = SYS_ERR_OK;

    // debug_printf("Servicing remote bind request for service %s, core %d, recurse %d, known %d \n", buf, my_core_id, recurse, num_known_services);

    assert(strlen(buf) <= SERVICE_NAME_LEN);
    if (*buf != '\0') {
            // check if service is available on current core
        for (int i = 0; i < num_known_services; i++) {
            if (!strncmp(known_services[i], buf, 63))  {
                debug_printf("FOUND THE SERVICE\n");
                err = aos_rpc_send_req(RPC_BIND_FRAME, known_services_chans[i], "AAAAAAAA", 8, cap);
                DBGERR(err, "Error binding frame to service\n");

                if (recurse) {
                    rpc_remote_bind_res res = {
                        .args = {
                            .err = SYS_ERR_OK
                        }
                    };
                    err = aos_rpc_send_capbuf(chan, res.bytes, 36, NULL_CAP);
                } else {
                    rpc_remote_bind_res res = {
                        .args = {
                            .res_type = URPC_CB_BINDING,
                            .err = SYS_ERR_OK
                        }
                    };
                    debug_printf("NOT FOUND, recurse\n");
                    err = aos_rpc_send_req(RPC_REMOTE_RES, chan, (void*) &res, sizeof(rpc_remote_bind_res), NULL_CAP);
                }
                DBGERR(err, "Error sending message back\n");

                return SYS_ERR_OK;
            }
        }

        if (!recurse) {
            rpc_remote_bind_res res = {
                .args = {
                    .res_type = URPC_CB_BINDING,
                    .err = LIB_ERR_MONITOR_RPC_SERVICE_NOT_FOUND
                }
            };
            debug_printf("NOT FOUND, no recurse\n");
            err = aos_rpc_send_req(RPC_REMOTE_RES, chan, (void*) &res, sizeof(rpc_remote_bind_res), NULL_CAP);

            DBGERR(err, "Error sending message back\n");
            return LIB_ERR_MONITOR_RPC_SERVICE_NOT_FOUND;
        }

        // otherwise send cap to other core

        struct frame_identity cap_fi;
        err = frame_identify(cap, &cap_fi);
        DBGERR(err, "Error identifying module cap\n");

        rpc_req req;
        req.type = RPC_REMOTE_BIND_FRAME;
        rpc_remote_bind_req sreq = {0};
        sreq.cap_fi = cap_fi;
        strncpy(sreq.service_name, buf, SERVICE_NAME_LEN);
        memcpy(req.buf, &sreq, sizeof(rpc_remote_bind_req));
        urpc_write_msg(&urpc, &req, sizeof(rpc_req));
        // debug_printf("Sent remote bind request for service %s, from %d to other core\n", buf, my_core_id);

        urpc_cb_t *cb = malloc(sizeof(urpc_cb_t));
        if (cb == NULL) { USER_PANIC("MALLOC FAILED\n"); }
        cb->f = default_urpc_cb;
        cb->args = NULL;
        cb->chan = chan;
        dlist_tail_insert(&urpc_cb_queues[URPC_CB_BINDING], cb);

        //debug_printf("Sent remote bind request for service %s, from %d to other core\n", buf, my_core_id);
        return LIB_ERR_MONITOR_RPC_SERVICE_RETRY_OTHER_CORE;
    }
    return LIB_ERR_MONITOR_RPC_MALFORMED_SERVICE_NAME;
}

static void handle_remote_binding_frame(struct aos_rpc* chan, char* buf) {
    errval_t err;
    struct capref mem_cap;
    err = slot_alloc(&mem_cap);
    DBGERRV(err, "Error allocating mem_cap slot\n");
    
    /*
     * Kernel might not clean correctly the page table mappings after
     * modifying them, thus making it inconsistent with what's inside the TLB.
     * MMU considers L2 cache, while the kernel considers L1 cache
     */
    //rpc_remote_bind_req* req = (rpc_remote_bind_req*) buf; // FIXME: How could this break everything?
    rpc_remote_bind_req stack_req;
    rpc_remote_bind_req* req = &stack_req;
    memcpy(req, buf, sizeof(rpc_remote_bind_req));
    err = frame_forge(mem_cap, req->cap_fi.base, req->cap_fi.bytes, my_core_id);
    DBGERRV(err, "Error forging mem_cap\n");
    err = handle_binding_frame(chan, req->service_name, mem_cap, false);
}

// static void handle_remote_binding_response(struct aos_rpc* chan, rpc_remote_bind_res_req *ires) {
    // errval_t err = SYS_ERR_OK;
    // assert(chan != NULL);
    // // Send back the outcome
    // rpc_remote_bind_res res = {
        // .args = {
            // .err = ires->err
        // }
    // };
    // err = aos_rpc_send_capbuf(chan, res.bytes, 36, NULL_CAP);
    // DBGERRV(err, "Error sending cap request to init\n");
    // binding_pending_chan = NULL;
// }

void ipc_request_handler(int type, char * buf, struct aos_rpc* rpc, struct capref received_cap) {
    switch (type) {
        case RPC_RAM_CAP_REQ:
            handle_ram_alloc_req(rpc, (rpc_req_ram_cap*) buf);
            break;
        case RPC_PUTCHAR_REQ:
            sys_print(buf + 1, 1);
            break;
        case RPC_GETCHAR_REQ:
            handle_getchar_req(rpc, 1);
            break;
        case RPC_READ_REQ:
            handle_getchar_req(rpc, ((rpc_req_read*) buf)->len);
            break;
        case RPC_PUTS_REQ: {
            rpc_req_puts* puts_req = (rpc_req_puts*) buf;
            if (false && my_core_id != 0) {
                size_t req_size = sizeof(rpc_req) + sizeof(rpc_req_puts) + puts_req->len;
                rpc_req * reqq = malloc(req_size);
                if (reqq == NULL) {
                    USER_PANIC("RPC puts: Malloc failed");
                }
                reqq->type = RPC_PUTS_REQ;
                rpc_req_puts * sreq = (rpc_req_puts *) reqq->buf;
                sreq->len = puts_req->len;
                strncpy( sreq->buf, puts_req->buf, puts_req->len);

                urpc_write_msg(&urpc, reqq, req_size);

                free(reqq);
            } else {
                sys_print(puts_req->buf, puts_req->len);
            }
            break;
           }
        case RPC_REQ_PROC_SPAWN:
            handle_rpc_req_proc_spawn(rpc, (rpc_req_proc_spawn*) buf);
            break;
        case RPC_REG_SERVICE:
            handle_service_registration(rpc, buf);
            break;
        case RPC_BIND_FRAME:
            handle_binding_frame(rpc, buf, received_cap, true);
            break;
        case RPC_REMOTE_BIND_FRAME:
            handle_remote_binding_frame(rpc, buf);
            break;
        case RPC_DEV_CAP_REQ:
            handle_rpc_dev_cap_req(rpc, (rpc_req_dev_cap*) buf);
            break;
        case RPC_RUN_TEST:
            handle_rpc_run_test(rpc, buf);
            break;
        case RPC_IRQ_CAP_REQ:
            aos_rpc_send_capbuf(rpc, "IRQ", 4, cap_irq);
            break;

        // Here URPC response
        case RPC_REMOTE_RES: {
            // handle_remote_binding_response(binding_pending_chan, (rpc_remote_bind_res_req*) buf);
            rpc_res *res = (rpc_res*) buf;
            size_t res_type = res->args.res_type;
            struct urpc_cb_s *cb = dlist_head(&urpc_cb_queues[res_type]);
            if (cb == NULL) {
                debug_printf("\x1b[1mNo callback was set for this response!\x1b[0m - %d\n", res->args.res_type);
                break;
            }
            cb->f(cb->args, cb->chan, (rpc_res*) buf);
            dlist_remove(&urpc_cb_queues[res_type], cb);
            // free cb
            break;
        }
        default:
            debug_printf("Unknown command: raw: __NOTE_NO_ACCESS_TO_RAW_HERE, content: %s\n", buf);
            for (int i = 0; i < 36; i++) {
                debug_printf("%x \n", buf[i]);
            }
    }
}
    

// TODO: change name to this function
static void confidential_channel_handler (void *args) { 
    struct aos_rpc* rpc = (struct aos_rpc*) args;
    errval_t err;

    char * req;
    size_t len;
    uint8_t type;
    struct capref received_cap = NULL_CAP;
    err = aos_rpc_rec_req(&type, rpc, &req, &len, &received_cap);  // this waits until there is something to read
    DBGERRV(err, "Error during handling of a request to init!\n");
    /*debug_printf("Serving request type: %d\n", req->type);*/

    ipc_request_handler(type, req, rpc, received_cap);

    free(req);

    err = _aos_rpc_set_closure(rpc, MKCLOSURE(confidential_channel_handler, rpc));
}


void handshake_handler (void *args) {
    struct aos_rpc* init_rpc = (struct aos_rpc*) args;
    errval_t err;
    struct lmp_chan chan_init_to_child;

    char buf[RPC_MAX_LEN];
    size_t len;
    struct capref child_endpoint;
    err = aos_rpc_rec_capbuf(init_rpc, buf, &len, &child_endpoint);
    DBGERRV(err, "Error receiving handshake message from child\n");

    if (!strncmp(buf, "SYN", 3)) {
        //debug_printf("Received \x1b[1mSYN\x1b[0m request from a child!\n");

        err = lmp_chan_accept(&chan_init_to_child, DEFAULT_LMP_BUF_WORDS, child_endpoint);
        err = lmp_chan_alloc_recv_slot(&chan_init_to_child);  


        struct aos_rpc* rpc_init_to_child = _aos_rpc_chan_alloc();
        _aos_rpc_set_chan(rpc_init_to_child, chan_init_to_child);

        err = lmp_chan_register_recv(&chan_init_to_child, get_default_waitset(), 
                                MKCLOSURE(confidential_channel_handler, rpc_init_to_child));

        if (buf[4] != '\0') {
            known_services[num_known_services] = malloc(64);
            strncpy (known_services[num_known_services], buf + 4, 63);
            known_services_chans[num_known_services] = rpc_init_to_child;
            debug_printf("Registered service with name %s\n", known_services[num_known_services]);
            num_known_services++;
        }

        aos_rpc_send_capbuf(rpc_init_to_child, "ACK", 4, chan_init_to_child.local_cap);
    }

    // Reregister handshake_handler
    err = _aos_rpc_set_closure(init_rpc, MKCLOSURE(handshake_handler, init_rpc));
}
