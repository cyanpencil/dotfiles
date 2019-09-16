/**
 * \file
 * \brief init user-space domain special structures.
 */
#include <aos/aos.h>
#include <aos/deferred.h>
#include <spawncore.h>
#include <machine/atomic.h>
#include <urpc.h>

#include "urpc_int.h"

#define max(x, y) (x > y ? x : y)

errval_t urpc_read_msg(urpc_s* urpc, void** buf, size_t* len) {
    errval_t err = SYS_ERR_OK;
    thread_mutex_lock(&urpc->lock);

    /*
     * Since we read at least MCACHE_LINE_SIZE bytes,
     * if the MCACHE_LINE_SIZE is smaller than size, we
     * guarantee that we have a buffer big enough
     */
    char len_buf[max(MCACHE_LINE_SIZE, sizeof(size_t))];
    b_read(urpc, (void*) len_buf, max(MCACHE_LINE_SIZE, sizeof(size_t)));
    memcpy(len, len_buf, sizeof(size_t));
    /*
     * Same reason as before, we have a guarantee 
     * that we will never exceed the size of the 
     * malloced buffer
     */
    size_t buf_size = ROUND_UP(*len, MCACHE_LINE_SIZE);
    *buf = malloc(buf_size);
    if (!buf) {
        thread_mutex_unlock(&urpc->lock);
        USER_PANIC("Malloc failed\n");
        return SYS_ERR_OK;
    }
    b_read(urpc, *buf, buf_size);

    thread_mutex_unlock(&urpc->lock);
    return err;
}

errval_t urpc_write_msg(urpc_s* urpc, void* buf, size_t len) {
    errval_t err = SYS_ERR_OK;
    thread_mutex_lock(&urpc->lock);
    // assert(len % MCACHE_LINE_SIZE == 0);

    b_write(urpc, (void*) &len, sizeof(size_t));
    b_write(urpc, buf, len);

    thread_mutex_unlock(&urpc->lock);
    return err;
}

errval_t urpc_lock(urpc_s* urpc) {
    thread_mutex_lock(&urpc->lock);
    return SYS_ERR_OK;
}

errval_t urpc_unlock(urpc_s* urpc) {
    thread_mutex_unlock(&urpc->lock);
    return SYS_ERR_OK;
}

size_t read(urpc_s * src_buf, char * dest, size_t n) {
    size_t read = 0;
    n = ROUND_UP(n, MCACHE_LINE_SIZE);
    urpc_cbuf_u * r_buf = src_buf->rbuf;
    size_t * cursor = &src_buf->rcursor;
    while (read < n && *cursor != r_buf->s.to_read) {

        // Memory barrier for reading cursor to_read
        dmb();

        size_t min_read = MCACHE_LINE_SIZE;
        size_t pad = 0;
        if (n - read < MCACHE_LINE_SIZE) {
            min_read = n - read;
            pad = MCACHE_LINE_SIZE - min_read;
        }
        // Read data bytes
        for (int i = 0; i < min_read; i++) {
            dest[read++] = r_buf->s.buf[(*cursor)++];
        }
        for (int i = 0; i < pad; i++) {
            (*cursor)++;
            read++;
        }

        // Memory barrier for setting cursor to_write
        dmb();

        // Advance shared cursor
        size_t tmp = (r_buf->s.to_write + MCACHE_LINE_SIZE) % URPC_BUF_SIZE;
        r_buf->s.to_write  = tmp; // Atomic if 32bit in armv7

        // Advance own cursor
        *cursor %= URPC_BUF_SIZE;
    }

    return read;
}

size_t write(urpc_s * dest_buf, char * src, size_t n) {
    urpc_cbuf_u * s_buf = dest_buf->wbuf;
    n = ROUND_UP(n, MCACHE_LINE_SIZE);
    size_t * cursor = &dest_buf->wcursor;
    size_t wrote = 0;
    while (wrote < n && *cursor != s_buf->s.to_write) {

        // Memory barrier for reading cursor to_write
        dmb();

        // Here we write always MCACHE_LINE_SIZE, but if 
        // remaining data is less than MCACHE_LINE_SIZE, we pad it
        size_t min_write = MCACHE_LINE_SIZE;
        size_t pad = 0;
        if (n - wrote < MCACHE_LINE_SIZE) {
            min_write = n - wrote;
            pad = MCACHE_LINE_SIZE - min_write;
        }

        //debug_printf("write cursor: buf: %p, shared: %p, cur: %p, %p\n", 
        //        dest_buf, s_buf->s.buf, *cursor, s_buf->s.to_write);
        // Write data byte
        for (int i = 0; i < min_write; i++) {
            s_buf->s.buf[(*cursor)++] = src[wrote++];
        }
        for (int i = 0; i < pad; i++) {
            s_buf->s.buf[(*cursor)++] = 0;
            wrote++;
        }

        // Memory barrier for setting cursor to_read
        dmb();

        // Advance shared cursor
        size_t tmp = (s_buf->s.to_read + MCACHE_LINE_SIZE ) % URPC_BUF_SIZE;
        s_buf->s.to_read = tmp; // Atomic if 32bit in armv7

        // Advance own cursor
        *cursor %= URPC_BUF_SIZE;
    }

    return wrote;
}

void wait_for_new_message(urpc_s * urpc) {
    volatile urpc_cbuf_u * r_buf = urpc->rbuf;
    volatile size_t * cursor = &urpc->rcursor;
    while ( *cursor == r_buf->s.to_read) {
        //FIXME: yield processor here
        barrelfish_usleep(1);
    }
}

bool urpc_message_avail(urpc_s * urpc) {
    volatile urpc_cbuf_u * r_buf = urpc->rbuf;
    volatile size_t * cursor = &urpc->rcursor;
    return *cursor != r_buf->s.to_read;
}

void b_read(urpc_s * src_buf, char * dest, size_t n) {
    size_t recv = 0;
    size_t count = 0;
    while (recv < n) {
        recv += read(src_buf, dest + recv, n - recv);
        if (count++ % 50000)
            barrelfish_usleep(1);
    }
}

void b_write(urpc_s * dest_buf, char * src, size_t n) {
    size_t recv = 0;
    size_t count = 0;
    while (recv < n) {
        recv += write(dest_buf, src + recv, n - recv);
        if (count++ % 50000)
            barrelfish_usleep(1);
    }
}

void urpc_s_init(void * _urpcrw, urpc_s * urpc, bool reversed) {
    urpc_rwbuf_s * urpcrw = (urpc_rwbuf_s *) _urpcrw;
    urpc->buf = (void *) urpcrw;
    urpc->rbuf = reversed ? &((urpc_rwbuf_s *) urpcrw)->w : &((urpc_rwbuf_s *) urpcrw)->r;
    urpc->wbuf = reversed ? &((urpc_rwbuf_s *) urpcrw)->r : &((urpc_rwbuf_s *) urpcrw)->w;
    urpc->rcursor = 0;
    urpc->wcursor = 0;

    // XXX: Only one side of the urpc should initialize those
    //      shared cursors
    if (!reversed) {
        urpcrw->r.s.to_write = URPC_BUF_SIZE - MCACHE_LINE_SIZE;
        urpcrw->w.s.to_write = URPC_BUF_SIZE - MCACHE_LINE_SIZE;
    }

    thread_mutex_init(&urpc->lock);
}

errval_t urpc_map(struct capref _cap_urpc, urpc_s * urpc) {
    errval_t err;
    urpc_rwbuf_s * urpcrw;
    err = paging_map_frame_attr(get_current_paging_state(), (void *) &urpcrw, URPC_FRAME_SIZE,
            _cap_urpc, VREGION_FLAGS_READ_WRITE, NULL, NULL);
    DBGERR(err, "Mapping urpc failed");

    urpc_s_init(urpcrw, urpc, true);
    return err;
}
