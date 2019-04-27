#ifndef __SPAWNCORE_HDR_
#define __SPAWNCORE_HDR_

#ifndef __SC_INT
typedef union _cbuf_u urpc_cbuf_u;
#endif

typedef struct _urpc_s {
    void *        buf;
    urpc_cbuf_u * rbuf;
    size_t        rcursor;
    urpc_cbuf_u * wbuf;
    size_t        wcursor;
} urpc_s;

size_t read(urpc_s * buf, char * dest, size_t n);
size_t write(urpc_s * buf, char * src, size_t n);

void b_read(urpc_s * buf, char * dest, size_t n);
void b_write(urpc_s * buf, char * src, size_t n);

errval_t urpc_map(struct capref _cap_urpc, urpc_s * urpc);
errval_t spawn_core(int my_core_id, int core_id, urpc_s * urpc);
#endif
