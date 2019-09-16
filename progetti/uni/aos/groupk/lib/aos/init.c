/**
 * \file
 * \brief Barrelfish library initialization.
 */

/*
 * Copyright (c) 2007-2019, ETH Zurich.
 * Copyright (c) 2014, HP Labs.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, CAB F.78, Universitaetstr. 6, CH-8092 Zurich,
 * Attn: Systems Group.
 */

#include <stdio.h>
#include <aos/aos.h> 
#include <aos/dispatch.h> 
#include <aos/curdispatcher_arch.h>
#include <aos/dispatcher_arch.h>
#include <aos/deferred.h>
#include <barrelfish_kpi/dispatcher_shared.h>
#include <aos/morecore.h>
#include <aos/paging.h>
#include <aos/systime.h>
#include <aos/aos_rpc.h>
#include <aos/inthandler.h>
#include <barrelfish_kpi/domain_params.h>
#include <fs/fs.h>
#include <spawn/spawn.h>
#include "threads_priv.h"
#include "init.h"

/// Are we the init domain (and thus need to take some special paths)?
static bool init_domain;

extern size_t (*_libc_terminal_read_func)(char *, size_t);
extern size_t (*_libc_terminal_write_func)(const char *, size_t);
extern void (*_libc_exit_func)(int);
extern void (*_libc_assert_func)(const char *, const char *, const char *, int);

int HARDWARE = 1;
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
        // if (HARDWARE) {
            event_dispatch(get_default_waitset());
        // } else {
            // sys_getchar(&readterm_buf[readterm_idx++]);
        // }
    }
    readterm_idx -= count;
    return &readterm_buf[readterm_idx];
}

void libc_exit(int);

extern struct aos_rpc rpc_with_init;
extern struct aos_rpc rpc_with_mem_svr;

__weak_reference(libc_exit, _exit);
void libc_exit(int status)
{
    //TODO: exit terminal services

    debug_printf("libc_exit NYI!\n");
    thread_exit(status);
    // If we're not dead by now, we wait
    while (1) {}
}

static void libc_assert(const char *expression, const char *file,
                        const char *function, int line)
{
    char buf[512];
    size_t len;

    /* Formatting as per suggestion in C99 spec 7.2.1.1 */
    len = snprintf(buf, sizeof(buf), "Assertion failed on core %d in %.*s: %s,"
                   " function %s, file %s, line %d.\n",
                   disp_get_core_id(), DISP_NAME_LEN,
                   disp_name(), expression, function, file, line);
    sys_print(buf, len < sizeof(buf) ? len : sizeof(buf));
}

static size_t syscall_terminal_write(const char *buf, size_t len)
{
    if (!len) return 0;
    if (init_domain) {
        return sys_print(buf, len);
    } else {
        aos_rpc_serial_puts(get_init_rpc(), buf, len);
        return len;
    }
}

static size_t write_into_file(const char *buff, size_t len) {
    struct dispatcher_shared_generic * disp = get_dispatcher_shared_generic(curdispatcher());

    FILE *f = fopen(disp->filename, "w");
    if (f == NULL) {
        return 0;
    }

    const char *blueprint = buff;
    size_t blueprint_len = len;
    char *buf = calloc(sizeof(char), blueprint_len);
    for (int i = 0; i < blueprint_len; i++) {
        buf[i] = blueprint[i % blueprint_len];
    }
    size_t written = fwrite(buf, sizeof(char), blueprint_len, f);

    if (written != blueprint_len) {
        return written;
    }

    int res = fclose(f);
    if (res) {
        return written;
    }

    return written;
}

static size_t dummy_terminal_read(char *buf, size_t len)
{
    if (init_domain) {
        if (HARDWARE) {
            consume_readterm_buf(1);
        } else {
            sys_getchar(buf);
        }
    } else {
        aos_rpc_serial_getchar(get_init_rpc(), buf);
    }
    return 1;
}


#include <net/sock.h>
#include <urpc.h>
#include <arpa/inet.h>
#include <aos/deferred.h>

urpc_s sock_urpc;
in_addr_t dst;
volatile uint16_t port = 0;

void network_recv(void);
static inline void init_network(void) {
    errval_t err = SYS_ERR_OK;

    char* buf;
    barrelfish_usleep(2000000);
    err = aos_rpc_new_remote_bind_4ever("nm", BASE_PAGE_SIZE, (void**) &buf);
    DBGERRV(err, "Error binding to nm\n");

    urpc_s_init((char*) buf, &sock_urpc, false);

    sock_req sr = {
        .magic = SR_MAGIC,
        .proto = SR_UDP,
        .sport = 22
    };

    err = urpc_write_msg(&sock_urpc, (void**) &sr, sizeof(sock_req));
    DBGERRV(err, "Error writing req\n");

    network_recv();
    //debug_printf("POOOOOOOOOOOOOORT %d\n", port);
}



static inline size_t network_write(const char *buf, size_t len) {
    while (port == 0) {
        barrelfish_usleep(1);
    }

    errval_t err;
    sock_res* msg = malloc(len + sizeof(sock_res));

    msg->src = dst; 
    msg->sport = port;
    msg->len = len;


    memcpy(msg->buf, buf, len);
    err = urpc_write_msg(&sock_urpc, (void**) msg, len + sizeof(sock_res));
    if (err_is_fail(err)) DEBUG_ERR(err, "Error sending char\n");

    free(msg);
    return len;
}


//static size_t network_read(size_t len) {
//    urpc_lock(urpc);
//
//    size_t len;
//    char len_buf[max(MCACHE_LINE_SIZE, sizeof(size_t))];
//    b_read(urpc, (void*) len_buf, max(MCACHE_LINE_SIZE, sizeof(size_t)));
//    memcpy(&len, len_buf, sizeof(size_t));
//
//    size_t buf_size = ROUND_UP(*len, MCACHE_LINE_SIZE);
//    b_read(urpc, readterm_buf + readterm_idx, buf_size);
//
//    readterm_idx += len;
//
//    urpc_unlock(urpc);
//}

sock_res * net_msg = NULL;
size_t msg_avail = 0;
size_t msg_curr  = 0;

void network_recv(void) {
    if (net_msg) {
        free(net_msg);
    }

    errval_t err;
    size_t len;
    err = urpc_read_msg(&sock_urpc, (void**) &net_msg, &len);
    DBGERRV(err, "Error receiving message\n");

    dst = net_msg->src;
    port = net_msg->sport;

    msg_avail = net_msg->len;
    msg_curr = 0;
}

static size_t network_consume_buf(char * buf, size_t count) {
    size_t cons = 0;

    if (msg_avail == 0) {
        network_recv();
        assert(msg_avail > 0);
    }

    while (cons < count && msg_avail) {
        msg_avail--;
        buf[cons++] = net_msg->buf[msg_curr++];

    }

    return cons;
}

/* Set libc function pointers */
void barrelfish_libc_glue_init(void)
{
    // XXX: FIXME: Check whether we can use the proper kernel serial, and
    // what we need for that
    // TODO: change these to use the user-space serial driver if possible

    struct dispatcher_shared_generic * disp = get_dispatcher_shared_generic(curdispatcher());

    switch (disp->fd) {
        case 0:
            _libc_terminal_read_func = dummy_terminal_read;
            _libc_terminal_write_func = syscall_terminal_write;
            break;

        case 1:

            _libc_terminal_read_func = network_consume_buf;
            _libc_terminal_write_func = network_write;
            break;

        case 2:
            _libc_terminal_read_func = dummy_terminal_read;
            _libc_terminal_write_func = write_into_file;
            break;

        default:
            USER_PANIC("BUG");

    }

    _libc_exit_func = libc_exit;
    _libc_assert_func = libc_assert;

    /* morecore func is setup by morecore_init() */

    // XXX: set a static buffer for stdout
    // this avoids an implicit call to malloc() on the first printf
    static char buf[BUFSIZ];
    setvbuf(stdout, buf, _IOLBF, sizeof(buf));
}


/** \brief Initialise libbarrelfish.
 *
 * This runs on a thread in every domain, after the dispatcher is setup but
 * before main() runs.
 */
errval_t barrelfish_init_onthread(struct spawn_domain_params *params)
{
    errval_t err;

    // do we have an environment?
    if (params != NULL && params->envp[0] != NULL) {
        extern char **environ;
        environ = params->envp;
    }

    // Init default waitset for this dispatcher
    struct waitset *default_ws = get_default_waitset();
    waitset_init(default_ws);

    // Initialize ram_alloc state
    ram_alloc_init();
    /* All domains use smallcn to initialize */
    err = ram_alloc_set(ram_alloc_fixed);
    if (err_is_fail(err)) {
        return err_push(err, LIB_ERR_RAM_ALLOC_SET);
    }

    err = paging_init();
    if (err_is_fail(err)) {
        return err_push(err, LIB_ERR_VSPACE_INIT);
    }

    err = slot_alloc_init();
    if (err_is_fail(err)) {
        return err_push(err, LIB_ERR_SLOT_ALLOC_INIT);
    }

    err = morecore_init(BASE_PAGE_SIZE);
    if (err_is_fail(err)) {
        return err_push(err, LIB_ERR_MORECORE_INIT);
    }

    lmp_endpoint_init();

    // init domains only get partial init
    if (init_domain) {
        return SYS_ERR_OK;
    }

    // TODO MILESTONE 3: register ourselves with init
    err = ram_alloc_set(NULL);
    if (err_is_fail(err)) {
        return err_push(err, LIB_ERR_RAM_ALLOC_SET);
    }
    /* allocate lmp channel structure */
    /* create local endpoint */
    /* set remote endpoint to init's endpoint */
    /* set receive handler */
    /* send local ep to init */
    /* wait for init to acknowledge receiving the endpoint */
    /* initialize init RPC client with lmp channel */
    /* set init RPC client in our program state */

    //debug_printf("Rpc init with init\n");
    err = aos_rpc_init(&rpc_with_init, cap_initep);
    DBGERR(err, "Failed to initialize channel with init\n");
    //debug_printf("Rpc init with mem svr\n");
    err = aos_rpc_init(&rpc_with_mem_svr, cap_initep);
    DBGERR(err, "Failed to initialize channel with mem server\n");
    set_init_rpc(&rpc_with_init);

    struct dispatcher_shared_generic * disp = get_dispatcher_shared_generic(curdispatcher());
    if (disp->fd){
        init_network();
    }

    if (strcmp("mmchs", disp_name())) {
        /*
         * Initializing Filesystem
         */

        // THERE IS NO SDCARD !!!

        //debug_printf("Initializing fs\n");
        err = filesystem_init();
        DBGERR(err, "failure during fs init");

        //debug_printf("Mounting fs\n");
        err = filesystem_mount("/sdcard", "mmchs://fat32/0");
        DBGERR(err, "failure during fs mount");
    }

    // right now we don't have the nameservice & don't need the terminal
    // and domain spanning, so we return here
    return SYS_ERR_OK;
}


/**
 *  \brief Initialise libbarrelfish, while disabled.
 *
 * This runs on the dispatcher's stack, while disabled, before the dispatcher is
 * setup. We can't call anything that needs to be enabled (ie. cap invocations)
 * or uses threads. This is called from crt0.
 */
void barrelfish_init_disabled(dispatcher_handle_t handle, bool init_dom_arg);
void barrelfish_init_disabled(dispatcher_handle_t handle, bool init_dom_arg)
{
    init_domain = init_dom_arg;
    disp_init_disabled(handle);
    thread_init_disabled(handle, init_dom_arg);
}
