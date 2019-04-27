#include <stdio.h>
#include <stdlib.h>

#include <aos/aos.h>
#include <aos/inthandler.h>
#include <aos/waitset.h>
#include <aos/aos_rpc.h>
#include <aos/kernel_cap_invocations.h>
#include <target/arm/barrelfish_kpi/arm_core_data.h>
#include <spawn/spawn.h>

#include <monitor.h>

volatile uint16_t readterm_idx = 0;
char readterm_buf[READTERM_BUFSIZE];


void handle_uart_getchar_interrupt(void *args) {
    if (readterm_idx == READTERM_BUFSIZE) return;
    sys_getchar(&readterm_buf[readterm_idx++]);
}

char* consume_readterm_buf(int count) {
    while (readterm_idx < count) {
        // wait that the user writes something
        // FIXME this blocking wait will later be offloaded to terminal process
        // FIXME really not sure about this event_dispatch()
        event_dispatch(get_default_waitset());
    }
    readterm_idx -= count;
    return &readterm_buf[readterm_idx];
}

static void handle_getchar_req(struct aos_rpc* chan, int count) {
    char *ret = consume_readterm_buf(count);
    errval_t err = aos_rpc_send_capbuf(chan, ret, count, NULL_CAP);
    DBGERRV(err, "Error sending response to getchar\n");
}

static void handle_ram_alloc_req(struct aos_rpc* chan, rpc_req_ram_cap* req) {
    //debug_printf("Trying to allocate size:%zx, align:%zx\n", req->size, req->align);
    rpc_res_ram_cap res = {};

    struct capref ram;
    errval_t err;
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

extern coreid_t my_core_id;

static void handle_rpc_req_proc_spawn(struct aos_rpc* chan, rpc_req_proc_spawn* req) {
    errval_t err;

    struct spawninfo *si = malloc(sizeof(struct spawninfo));
    si->core_id = req->core_id;

    if (my_core_id != si->core_id) {
        debug_printf("Call URPC %d\n", si->core_id);
        return;
    }

    err = spawn_load_by_name(req->name, si);
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Error spawning child\n");
    }

    rpc_res_proc_spawn * res = malloc(sizeof(res));
    res->err = err;
    if (!err_is_fail(err)) {
        res->pid = si->disp_g->domain_id;
    }

    err = aos_rpc_send_capbuf(chan, res->bytes, sizeof(res), si->disp_cap);
    free(si);
    free(res);
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Error sending cap request to init\n");
    }
}

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
            sys_print(puts_req->buf, puts_req->len);
            break;
           }
        case RPC_REQ_PROC_SPAWN:
            handle_rpc_req_proc_spawn(rpc, (rpc_req_proc_spawn*) buf);
            break;;
        default:
            debug_printf("Unknown command: raw: __NOTE_NO_ACCESS_TO_RAW_HERE, content: %s\n", buf);
            //for (int i = 0; i < 36; i++) {
                //debug_printf("%x %c\n", *(req + i));
            //}
    }
}
    

// TODO: change name to this function
static void confidential_channel_handler (void *args) { 
    struct aos_rpc* rpc = (struct aos_rpc*) args;
    errval_t err;

    rpc_req * req;
    size_t len;
    struct capref received_cap = NULL_CAP;
    err = aos_rpc_rec_req(rpc, &req, &len, &received_cap);  // this waits until there is something to read
    DBGERRV(err, "Error during handling of a request to init!\n");
    /*debug_printf("Serving request type: %d\n", req->type);*/

    ipc_request_handler(req->type, req->buf, rpc, received_cap);

    free(req);

    err = _aos_rpc_set_closure(rpc, MKCLOSURE(confidential_channel_handler, rpc));
}


void handshake_handler (void *args) {
    struct aos_rpc* init_rpc = (struct aos_rpc*) args;
    errval_t err;
    struct lmp_chan chan_init_to_child;

    char *buf;
    size_t len;
    struct capref child_endpoint;
    err = aos_rpc_rec_capbuf(init_rpc, &buf, &len, &child_endpoint);
    DBGERRV(err, "Error receiving handshake message from child\n");

    if (!strncmp(buf, "SYN", 3)) {
        debug_printf("Received \x1b[1mSYN\x1b[0m request from a child!\n");

        err = lmp_chan_accept(&chan_init_to_child, DEFAULT_LMP_BUF_WORDS, child_endpoint);
        err = lmp_chan_alloc_recv_slot(&chan_init_to_child);  


        struct aos_rpc* rpc_init_to_child = _aos_rpc_chan_alloc();
        _aos_rpc_set_chan(rpc_init_to_child, chan_init_to_child);

        err = lmp_chan_register_recv(&chan_init_to_child, get_default_waitset(), 
                                MKCLOSURE(confidential_channel_handler, rpc_init_to_child));

        aos_rpc_send_capbuf(rpc_init_to_child, "ACK", 4, chan_init_to_child.local_cap);
    }

    // Reregister handshake_handler
    err = _aos_rpc_set_closure(init_rpc, MKCLOSURE(handshake_handler, init_rpc));
}
