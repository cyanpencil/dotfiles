#ifndef __SOCK_RQ_H
#define __SOCK_RQ_H

#define SR_MAGIC 0xc0febabe 

enum sock_req_t {
    SR_UDP,
    SR_TCP,
    SR_ICMP,
};

typedef struct sock_req {
    uint32_t magic;
    enum sock_req_t proto;
    uint16_t sport;
    uint16_t dport;
    in_addr_t dst;
    in_addr_t src;
} sock_req;

typedef struct sock_res {
    in_addr_t src;
    uint16_t sport;
    uint16_t len;
    uint16_t seq;
    uint16_t zeros;
    unsigned char buf[]; // NOTE: KEEP ALIGNED
} __attribute__((packed)) sock_res;

#endif
