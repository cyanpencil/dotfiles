/**
 * \file
 * \brief Implementation of AOS rpc-like messaging
 */

/*
 * Copyright (c) 2013 - 2016, ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, Universitaetstr. 6, CH-8092 Zurich. Attn: Systems Group.
 */


#include <aos/aos_rpc.h>
#include <aos/waitset.h>
#include <aos/deferred.h>
#include <aos/aos.h>
#include <stdio.h>

struct aos_rpc {
    struct lmp_chan lc;
    struct waitset* ws;
    struct thread_mutex mutex;
    errval_t (*send_raw_f)(struct aos_rpc*, const char *,
            size_t, struct capref);
    errval_t (*send_req_f)(uint8_t type, struct aos_rpc*, const char *,
            size_t, struct capref);
    errval_t (*rec_raw_f)(struct aos_rpc*, char **,
            size_t*, struct capref*);
    errval_t (*rec_req_f)(struct aos_rpc*, rpc_req **,
            size_t*, struct capref*);
    void * ancillary;
};

struct aos_rpc rpc_with_init;

/**
 * CALLBACKS
 */
void lmp_chan_send_cb(struct aos_rpc *chan, void* _args) {
    lmp_chan_send_cb_t* args = (lmp_chan_send_cb_t*) _args;
    errval_t err = lmp_chan_send(
            &chan->lc, args->flags, args->cap, args->len,
            (args->words)[0],
            (args->words)[1],
            (args->words)[2],
            (args->words)[3],
            (args->words)[4],
            (args->words)[5],
            (args->words)[6],
            (args->words)[7],
            (args->words)[8]
    );
    if (err_no(err) == SYS_ERR_LMP_BUF_OVERFLOW) {
        // Reregister and wait
        // FIXME where is the reregistration?
        // FIXME where is the reregistration?
        // FIXME where is the reregistration?
        err = event_dispatch(get_default_waitset());
        if (err_is_fail(err)) {
            DEBUG_ERR(err, "Waiting for re-registration of send callback");
            abort();
        }
    } else {
        DEBUG_ERR(err, "Error sending self endpoint to other endpoint\n");
    }
}

static errval_t send_block(struct aos_rpc* chan, void *_args) {
    errval_t err;
    lmp_chan_send_cb_t* args = (lmp_chan_send_cb_t*) _args;

    for (;;) {
        err = lmp_chan_send(
                &chan->lc, args->flags, args->cap, args->len,
                (args->words)[0],
                (args->words)[1],
                (args->words)[2],
                (args->words)[3],
                (args->words)[4],
                (args->words)[5],
                (args->words)[6],
                (args->words)[7],
                (args->words)[8]
                );
        if (lmp_err_is_transient(err)) {
            //DEBUG_ERR(err, "Error while sending lmp_chan_send\n");
            continue;
        }
        if (err_is_fail(err)) {
            DEBUG_ERR(err, "Error while sending lmp_chan_send\n");
            return err;
        }
        break;
    }


    return SYS_ERR_OK;
    /*lmp_chan_send_cb_t args = {*/
        /*.lc = &rpc->lc,*/
        /*.flags = 0,*/
        /*.len = 3,*/
        /*.cap = rpc->lc.local_cap,*/
        /*.words[0] = 'S', .words[1] = 'Y', .words[2] = 'N'*/
    /*};*/
    /*err = lmp_chan_register_send(&rpc->lc, get_default_waitset(), MKCLOSURE(lmp_chan_send_cb, &args));*/
    /*DBGERR(err, "Error registrating callback for send\n");*/
    /*debug_printf("Waiting on event dispatch\n");*/
    /*err = event_dispatch(get_default_waitset());*/
    /*if (err_is_fail(err)) {*/
        /*DEBUG_ERR(err, "Waiting for re-registration of send callback");*/
        /*abort();*/
    /*}*/
}

errval_t aos_rpc_send_number(struct aos_rpc *chan, uintptr_t val) {
    lmp_chan_send_cb_t args = {
        .flags = 0,
        .len = 1,
        .cap = NULL_CAP,
        .words[0] = val,
    };
    errval_t err = send_block(chan, &args);
    DBGERR(err, "Error sending number to other endpoint\n");
    return SYS_ERR_OK;
}

struct rec_capbuf {
    struct aos_rpc *chan;
    char *buf;
    size_t* len;
    struct capref* cap;
    struct waitset *ws;
    bool done;
};

static void callback_rec_capbuf (void* args) {
    struct rec_capbuf *result = (struct rec_capbuf*) args;
    struct lmp_recv_msg msg = LMP_RECV_MSG_INIT;
    result->done = true;

    errval_t err = lmp_chan_recv(&result->chan->lc, &msg, result->cap);
    if (err_no(err) == LIB_ERR_NO_LMP_MSG) {
        // do nothing, wait for next msg
        err = lmp_chan_register_recv(&result->chan->lc, result->ws, 
                                MKCLOSURE(callback_rec_capbuf, args));
        DBGERRV(err, "Error registering rec_capbuf\n");
        result->done = false;
        return;
    } else if (err_is_fail(err)) {
        // return and stop
        DBGERRV(err, "Error receiving rec_capbuf\n");
    }

    if (result->len)
        *(result->len) = 4 * msg.buf.msglen;
    for (uint8_t i = 0; i < MIN(4 * msg.buf.msglen, RPC_MAX_LEN); ++i) {
        result->buf[i] = ((char *) msg.words)[i];
    }


    if (result->cap != NULL && !capcmp(*result->cap, NULL_CAP)) {
        err = lmp_chan_alloc_recv_slot(&result->chan->lc);
        DBGERRV(err, "Error allocating new space for cap\n");
    }
}


static errval_t _aos_rpc_rec_capbuf(struct aos_rpc *chan, char **buf, size_t* len, struct capref *cap) {
    struct rec_capbuf *result = malloc(sizeof(struct rec_capbuf)); // FIXME do not use malloc
    if (!result) USER_PANIC("[x] Malloc failed\n");
    result->chan = chan;
    result->cap = cap;
    result->len = len;
    result->ws = malloc(sizeof(struct waitset));
    if (!result->ws) USER_PANIC("[x] Malloc failed\n");
    result->done = false;
    result->buf = malloc(RPC_MAX_LEN);
    if (!result->buf) USER_PANIC("[x] Malloc failed\n");
    *buf = result->buf;

    waitset_init(result->ws);

    errval_t err = lmp_chan_register_recv(&chan->lc, result->ws, 
                            MKCLOSURE(callback_rec_capbuf, result));
    DBGERR(err, "Error registering callback for rec_capbuf\n");

    while (!result->done) {
        event_dispatch(result->ws);
    }

    free(result); //FIXME debug this
    free(result->ws); //FIXME debug this
    return SYS_ERR_OK;
}

errval_t aos_rpc_rec_capbuf(struct aos_rpc *chan, char **buf, size_t* len, struct capref *cap) {
    if (!chan) {
        USER_PANIC("Null channel!");
    }

    return chan->rec_raw_f(chan, buf, len, cap);
}

static errval_t aos_rpc_rec_capbuf_static(struct aos_rpc *chan, char *buf, size_t* len, struct capref *cap) {
    struct rec_capbuf result;
    struct waitset ws;
    result.chan = chan;
    result.cap = cap;
    result.len = len;
    result.ws = &ws;
    result.done = false;
    result.buf = buf;

    waitset_init(result.ws);

    errval_t err = lmp_chan_register_recv(&chan->lc, result.ws, 
                            MKCLOSURE(callback_rec_capbuf, &result));
    DBGERR(err, "Error registering callback for rec_capbuf\n");

    while (!result.done) {
        event_dispatch(result.ws);
    }

    return SYS_ERR_OK;
}

static errval_t _aos_rpc_send_capbuf(struct aos_rpc *chan, const char *str, size_t str_len, struct capref cap) {
    lmp_chan_send_cb_t args = {
        .flags = 0,
        .len = MIN(str_len / 4 + 1, 9),
        .cap = cap,
        .words = {0}
    };
    for (uint8_t i = 0; i < MIN(str_len, RPC_MAX_LEN); ++i) {
        ((char *) &args.words[i / 4])[i % 4] = str[i];
    }
    errval_t err = send_block(chan, &args);
    DBGERR(err, "Error sending fast string to the other endpoint\n");
    return SYS_ERR_OK;
}

errval_t aos_rpc_send_capbuf(struct aos_rpc *chan, const char *str, size_t str_len, struct capref cap) {
    if (!chan) {
        USER_PANIC("Null channel!");
    }

    return chan->send_raw_f(chan, str, str_len, cap);
}

//errval_t aos_rpc_send(struct aos_rpc *chan, const char *buf, size_t len, struct capref cap) {

/**
 * Send typed request of arbitrary length.
 *
 */
static errval_t _aos_rpc_send_req(uint8_t type, struct aos_rpc *chan, const char *buf, size_t len, struct capref cap) {
    errval_t err;
    rpc_req req;

    req.type = type;

    if ( len <= RPC_MAX_LEN - RPC_RESERVED ) {
        req.longreq = false;
        memcpy(req.buf, buf, len);
        err = aos_rpc_send_capbuf(chan, req.bytes, sizeof(rpc_req)+len, cap);
    } else {
        req.longreq = true;
        req.len = len;

        struct capref frame;
        void* sbuf;
        frame_alloc(&frame, len, NULL);
        err = paging_map_frame_attr(get_current_paging_state(), &sbuf, len, frame, VREGION_FLAGS_READ_WRITE, NULL, NULL);
        DBGERR(err, "Send large: mapping failed\n");

        memcpy(sbuf, buf, len);

        err = aos_rpc_send_capbuf(chan, req.bytes, RPC_MAX_LEN, frame);
        paging_unmap(get_current_paging_state(), sbuf);
    }

    return err;
}

errval_t aos_rpc_send_req(uint8_t type, struct aos_rpc *chan, const char *buf, size_t len, struct capref cap) {
    if (!chan) {
        USER_PANIC("Null channel!");
    }

    return chan->send_req_f(type, chan, buf, len, cap);
}

#if 0
void* aos_rpc_parse_lreq(rpc_req_puts* req, struct capref frame_cap) {
    char *buf;
    errval_t err = paging_map_frame_attr(get_current_paging_state(), (void **) &buf, req->len, frame_cap, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    if (err_is_fail(err)) {
        DEBUG_ERR(err, "Error parsing lreq\n");
    }
    return buf;
}
#endif

static errval_t _aos_rpc_rec_req(struct aos_rpc *chan, rpc_req **buf, size_t* len, struct capref *cap) {
    errval_t err;

    rpc_req * req;
    size_t rlen;
    struct capref frame_cap = NULL_CAP;
    err = aos_rpc_rec_capbuf(chan, (char**) &req, &rlen, &frame_cap);


    if (!req->longreq) {
        *buf = req; 
        *cap = frame_cap;
    } else {
        assert(!capcmp(frame_cap, NULL_CAP));
        *cap = NULL_CAP;

        if (len) *len = req->len;

        void * mapped_buf;
        err = paging_map_frame_attr(get_current_paging_state(), &mapped_buf, req->len, frame_cap, VREGION_FLAGS_READ_WRITE, NULL, NULL);
        DBGERR(err, "Receive large: mapping failed\n");
        /* Extremely horryble. Our caller cannot distinguish between 
         * - this function returning a malloced area
         * - this function returning a mapped area
         *
         * Handled by the aos_rpc_req_free function.
         */
        rpc_req * req2 = malloc(sizeof(rpc_req) + req->len);
        if (!req2) USER_PANIC("[x] Malloc failed\n");

        // FIXME PAGING_UNMAP HERE

        memcpy(req2, req, sizeof(rpc_req));
        memcpy(req2->buf, mapped_buf, req->len);
        *buf = req2;
    }

    return err;
}

errval_t aos_rpc_rec_req(struct aos_rpc *chan, rpc_req **buf, size_t* len, struct capref *cap) {
    if (!chan) {
        USER_PANIC("Null channel!");
    }

    return chan->rec_req_f(chan, buf, len, cap);
}

errval_t aos_rpc_req_free(rpc_req *req);
errval_t aos_rpc_req_free(rpc_req *req) {
    errval_t err = SYS_ERR_OK;
    if (req->longreq) {
    } else {
        //err = paging_unmap(get_current_paging_state(), req);
    }
    free(req);

    return err;
}

errval_t aos_rpc_send_string(struct aos_rpc *chan, const char *string) {
    size_t str_len = strlen(string) + 1;
    return aos_rpc_send_req(0, chan, string, str_len, NULL_CAP);
}

errval_t aos_rpc_get_ram_cap(struct aos_rpc *chan, size_t size, size_t align,
                             struct capref *retcap, size_t *ret_size) {
    errval_t err;
    /* Ask init for some RAM */
    rpc_req_ram_cap ram_req = {
        .size = size,
        .align = align,
    };
    rpc_req req = {
        .type = RPC_RAM_CAP_REQ,
        .longreq = false,
    };
    memcpy(req.buf, &ram_req, sizeof ram_req);
    
    // Mutex
    //debug_printf("[Thread %d] Trying to acquire lock for get_ram_cap ... \n", thread_get_id(thread_self()));
    thread_mutex_lock_nested(&chan->mutex);
    //debug_printf("[Thread \x1b[33m%d\x1b[0m] \x1b[31;1mAcquired\x1b[0m lock, getting ram cap ... \n", thread_get_id(thread_self()));

    err = aos_rpc_send_capbuf(chan, req.bytes, sizeof(req), NULL_CAP);
    DBGERR(err, "Error sending cap request to init\n");

    /* Wait for the RAM from init*/
    rpc_res_ram_cap res_args;
    size_t len;
    err = aos_rpc_rec_capbuf_static(chan, (char*) &res_args, &len, retcap);
    DBGERR(err, "Error receiving response from init\n");

    if (ret_size != NULL) {
        /*To get the real allocated RAM we use the frame identify*/
        struct frame_identity fi; frame_identify(*retcap, &fi);
        *ret_size = fi.bytes;
    }

    // Mutex
    //debug_printf("[Thread \x1b[33;1m%d\x1b[0m] \x1b[32;1mReleased\x1b[0m the lock for get_ram_cap ... \n", thread_get_id(thread_self()));
    thread_mutex_unlock(&chan->mutex);

    return res_args.args.err;
}

errval_t aos_rpc_serial_getchar(struct aos_rpc *chan, char *retc)
{
    char req;
    aos_rpc_send_req(RPC_GETCHAR_REQ, chan, &req, 1, NULL_CAP);

    char *buf;
    size_t len;
    aos_rpc_rec_capbuf(chan, &buf, &len, NULL);

    *retc = buf[0];
    return SYS_ERR_OK;
}

errval_t aos_rpc_serial_read(struct aos_rpc *chan, char **retc, int len)
{
    // unused

    assert(len <= RPC_MAX_LEN);

    rpc_req_read req = {
        .len = len
    };
    aos_rpc_send_req(RPC_READ_REQ, chan, (char *) &req, sizeof(req), NULL_CAP);

    char *buf;
    size_t retlen;
    aos_rpc_rec_capbuf(chan, &buf, &retlen, NULL);

    *retc = buf;
    return SYS_ERR_OK;
}


errval_t aos_rpc_serial_putchar(struct aos_rpc *chan, char c)
{
    char buf[2] = {RPC_PUTCHAR_REQ, c}; // Type not really used here, refactor it out
    aos_rpc_send_req(RPC_PUTCHAR_REQ, chan, buf, 2, NULL_CAP);
    return SYS_ERR_OK;
}

errval_t aos_rpc_serial_puts(struct aos_rpc *chan, const char* c, int len)
{
    size_t size = sizeof(rpc_req_puts) + len + 1;

    char buf[size];
    rpc_req_puts* req = (rpc_req_puts*) buf;
    req->len = len;
    memcpy(req->buf, c, len);
    *(req->buf + len) = '\0';

    aos_rpc_send_req(RPC_PUTS_REQ, chan, (char *) req, size, NULL_CAP);

    return SYS_ERR_OK;
}

errval_t aos_rpc_process_spawn(struct aos_rpc *chan, char *name,
                               coreid_t core, domainid_t *newpid)
{
    assert(strlen(name) <= RPC_REQ_PROC_SPAWN_NAME);
    errval_t err;

    const size_t nlen = strlen(name) + 1; //note: unsafe
    const size_t size = sizeof(rpc_req_proc_spawn) + nlen; //note: unsafe
    rpc_req_proc_spawn * req = malloc(size);
    if (!req) USER_PANIC("[x] Malloc failed\n");
    req->core_id = core;
    strncpy(req->name, name, nlen);

    /*debug_printf("Asking init to spawn: %s (proc_type: %d)\n", req.name, req.req_type);*/
    err = aos_rpc_send_req(RPC_REQ_PROC_SPAWN, chan, (char *) req, size, NULL_CAP);
    DBGERR(err, "Error sending cap request to init\n");

    free(req);

    rpc_res_proc_spawn * res_args;

    struct capref disp_cap; // NOTE: NOT PASSED TO CHILD
    err = aos_rpc_rec_capbuf(chan, (char**) &res_args, NULL, &disp_cap);
    DBGERR(err, "Error receiving response from init\n");

    if (newpid != NULL) {
        *newpid = res_args->pid;
    }
    err = res_args->err;
    free(res_args);

    return err;
}

errval_t aos_rpc_process_get_name(struct aos_rpc *chan, domainid_t pid,
                                  char **name)
{
    // TODO (milestone 5): implement name lookup for process given a process
    // id
    return SYS_ERR_OK;
}

errval_t aos_rpc_process_get_all_pids(struct aos_rpc *chan,
                                      domainid_t **pids, size_t *pid_count)
{
    // TODO (milestone 5): implement process id discovery
    return SYS_ERR_OK;
}

errval_t aos_rpc_get_device_cap(struct aos_rpc *rpc,
                                lpaddr_t paddr, size_t bytes,
                                struct capref *frame)
{
    return LIB_ERR_NOT_IMPLEMENTED;
}

void aos_rpc_init_f(struct aos_rpc *rpc, 
        errval_t (*send_raw_f)(struct aos_rpc*, const char *,
            size_t, struct capref),
        errval_t (*send_req_f)(uint8_t type, struct aos_rpc*, const char *,
            size_t, struct capref),
        errval_t (*rec_raw_f)(struct aos_rpc*, char **,
            size_t*, struct capref*),
        errval_t (*rec_req_f)(struct aos_rpc*, rpc_req **,
            size_t*, struct capref*)
        ) {
    rpc->send_raw_f = send_raw_f;
    rpc->send_req_f = send_req_f;
    rpc->rec_raw_f = rec_raw_f;
    rpc->rec_req_f = rec_req_f;
}

void aos_rpc_init_a(struct aos_rpc *rpc, void * ancillary) {
    rpc->ancillary = ancillary;
}

void * aos_rpc_get_a(struct aos_rpc *rpc) {
    return rpc->ancillary;
}

// Please please refactor me out
static void _aos_rpc_init(struct aos_rpc *rpc) {
    rpc->send_raw_f = _aos_rpc_send_capbuf;
    rpc->send_req_f = _aos_rpc_send_req;
    rpc->rec_raw_f = _aos_rpc_rec_capbuf;
    rpc->rec_req_f = _aos_rpc_rec_req;
}

errval_t aos_rpc_init(struct aos_rpc *rpc, struct capref endpoint) {
    errval_t err;
    err = lmp_chan_accept(&rpc->lc, DEFAULT_LMP_BUF_WORDS, endpoint);
    DBGERR(err, "Error creating channel in aos_rpc_init");
    err = lmp_chan_alloc_recv_slot(&rpc->lc); 
    DBGERR(err, "Error allocating slot for new channel");

    _aos_rpc_send_capbuf(rpc, "SYN", 4, rpc->lc.local_cap);
    DBGERR(err, "Error sending self endpoint to init\n");

    char buf[RPC_MAX_LEN];
    size_t len;
    struct capref custom_channel_endpoint;
    //debug_printf("[RPC GET RAM CAP]Waiting for handshake cap request answer\n");
    err = aos_rpc_rec_capbuf_static(rpc, (char*) buf, &len, &custom_channel_endpoint);
    DBGERR(err, "Error receiving response from init\n");

    if (strncmp(buf, "ACK", 3)) {
        /*debug_printf("WTF received something unintelligible from init: %s\n", buf);*/
        return SYS_ERR_LMP_EP_STATE_INVALID;
    }
    /*debug_printf("Succesfully received ACK from init\n");*/

    rpc->lc.remote_cap = custom_channel_endpoint;
    DBGERR(err, "Error allocating slot for recv");

    /*Setup the mutex structure*/
    thread_mutex_init(&rpc->mutex);

    _aos_rpc_init(rpc);

    return SYS_ERR_OK;
}

/**
 * \brief Returns the RPC channel to init.
 */
struct aos_rpc *aos_rpc_get_init_channel(void)
{
    return get_init_rpc();
}

/**
 * \brief Returns the channel to the memory server
 */
struct aos_rpc *aos_rpc_get_memory_channel(void)
{
    return get_init_rpc();
}

/**
 * \brief Returns the channel to the process manager
 */
struct aos_rpc *aos_rpc_get_process_channel(void)
{
    return get_init_rpc();
}

/**
 * \brief Returns the channel to the serial console
 */
struct aos_rpc *aos_rpc_get_serial_channel(void)
{
    return get_init_rpc();
}

/**
 * Utils functioncs
 */
struct capref aos_rpc_local_cap(struct aos_rpc *chan) {
    return chan->lc.local_cap;
}

void _aos_rpc_initialize_init(void) {
    errval_t err = lmp_chan_accept(&rpc_with_init.lc, DEFAULT_LMP_BUF_WORDS, NULL_CAP);
    DBGERRV(err, "Error creating init's channel\n");

    err = lmp_chan_alloc_recv_slot(&rpc_with_init.lc); 
    DBGERRV(err, "Error allocating new slot to be received");

    _aos_rpc_init(&rpc_with_init);

    set_init_rpc(&rpc_with_init);
}

errval_t _aos_rpc_set_closure(struct aos_rpc* rpc, struct event_closure closure) {
    errval_t err = lmp_chan_register_recv(&rpc->lc, get_default_waitset(), closure);
    return err;
}

struct aos_rpc* _aos_rpc_chan_alloc(void) {
    struct aos_rpc* rpc = malloc(sizeof(struct aos_rpc));
    if (!rpc) USER_PANIC("[x] Malloc failed\n");
    return rpc;
}

void _aos_rpc_set_chan(struct aos_rpc* rpc, struct lmp_chan lc) {
    rpc->lc = lc;
    _aos_rpc_init(rpc);
}
