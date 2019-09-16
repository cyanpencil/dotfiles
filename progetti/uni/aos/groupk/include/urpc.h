/**
 * \file
 * \brief init user-space domain special structures.
 */
#ifndef AOS_URPC_H
#define AOS_URPC_H
#include <aos/thread_sync.h>


#ifndef __SC_INT
typedef union _cbuf_u urpc_cbuf_u;
#endif

typedef struct _urpc_s {
    void *              buf;
    urpc_cbuf_u *       rbuf;
    size_t              rcursor;
    urpc_cbuf_u *       wbuf;
    size_t              wcursor;
    struct thread_mutex lock;
} urpc_s;

/**
 * Read a message from URPC frame.
 *
 * The message length is set by the sender, this function
 * will allocate a buffer of the right size and block 
 * until the full message is read.
 */
errval_t urpc_read_msg(urpc_s*, void** buf, size_t* len);

/**
 * Write a message in URPC frame.
 *
 * Reads len bytes from buf, and writes them in the shared frame.
 * This function will block until the full message is sent.
 */
errval_t urpc_write_msg(urpc_s*, void* buf, size_t len);

/**
 * Exclusively lock the frame.
 */
errval_t urpc_lock(urpc_s*);

/**
 * Exclusively unlock the frame.
 */
errval_t urpc_unlock(urpc_s*);

/**
 * Busy waiting without holding a lock
 */
void wait_for_new_message(urpc_s * urpc);

/*
 * Return whether there are messages to be read
 */
bool urpc_message_avail(urpc_s * urpc);

/*
 * Utility functions
 *
 */
size_t read(urpc_s * buf, char * dest, size_t n);
size_t write(urpc_s * buf, char * src, size_t n);

void b_read(urpc_s * buf, char * dest, size_t n);
void b_write(urpc_s * buf, char * src, size_t n);

errval_t urpc_map(struct capref _cap_urpc, urpc_s * urpc);
void urpc_s_init(void * urpcrw, urpc_s * urpc, bool reversed);

#endif 
