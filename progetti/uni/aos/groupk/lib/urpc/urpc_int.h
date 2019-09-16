#ifndef __AOS_INTERNAL_USE_ONLY_URPC_H
#define __AOS_INTERNAL_USE_ONLY_URPC_H

#define ROUND_UP(n, size)           ((((n) + (size) - 1)) & (~((size) - 1)))
#define URPC_FRAME_SIZE             sizeof(urpc_rwbuf_s)
#define URPC_STRUCT_SIZE            sizeof(urpc_cbuf_u)
#define URPC_BUF_SIZE               (URPC_STRUCT_SIZE - sizeof(struct cbuf_s_hdr))
#define MCACHE_LINE_SIZE            32

extern struct bootinfo* bi;

struct cbuf_s_hdr {
    char pad[28];
    uint32_t to_read;
    char pad2[28];
    uint32_t to_write;
};

struct cbuf_s {
    char pad[28];
    uint32_t to_read;
    char pad2[28];
    uint32_t to_write;
    char buf[];
};

typedef union _cbuf_u {
    struct cbuf_s s;
    char bytes[BASE_PAGE_SIZE/2]; // 128 KB
} urpc_cbuf_u;

typedef struct _urpc_rwbuf_s {
    urpc_cbuf_u r;
    urpc_cbuf_u w;
} urpc_rwbuf_s;

struct urpc_msg {
    uint32_t len;
    char buf[];
};

#endif 
