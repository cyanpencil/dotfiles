#ifndef __NM_SKB_H
#define __NM_SKB_H

#include <arpa/inet.h>
#include "icmp.h"
#include "udp.h"

#define SCRAP_SPACE 50 // Space to allocate for unknown headers

enum protocols {
    INET_IP4
};

typedef struct sk_buff {
    struct sk_buff  * next;
    struct sk_buff  * prev;

    union {
        struct udphdr *  udph;
        struct ip *      ipiph;
        struct icmphdr * icmp;
        unsigned char *  raw;
    } h;    // L4

    union {
        struct ip *      iph;
        unsigned char *  raw;
    } nh;   // L3

    union {
        unsigned char*  raw;
    } mac;  // L2

    struct netdev * idev;
    struct netdev * odev;

    uint32_t protocol;

    size_t len;

    unsigned char *  head,
                  *  data,
                  *  tail,
                  *  end;
} sk_buff;

static inline void skb_reserve(struct sk_buff * skb, int len) {
    skb->data += len;
    skb->tail += len;
    assert(skb->data + skb->len <= skb->end && skb->tail <= skb->end);
}

static inline unsigned char * skb_put(struct sk_buff * skb, int len) {
    unsigned char * tmp = skb->tail;
    skb->tail += len;
    skb->len  += len;
    assert(skb->data + skb->len <= skb->end && skb->tail <= skb->end);
    //debug_printf("LEN: %d data: %d, len: %d, end: %d, tail: %d\n", 
    //        len,
    //        skb->data,
    //        skb->len,
    //        skb->end,
    //        skb->tail);
    return tmp;
}

static inline unsigned char * skb_push(struct sk_buff * skb, int len) {
    skb->data -= len;
    skb->len  += len;
    assert(skb->data >= skb->head);
    assert(skb->data + skb->len <= skb->end);
    return skb->data;
}

static inline unsigned char * skb_pull(struct sk_buff * skb, int len) {
    assert(skb->len >= len && skb->data + len <= skb->tail);
    skb->len  -= len;
    return skb->data += len;
}

static inline void skb_trim(struct sk_buff * skb, int len) {
    assert(skb->data + len <= skb->end);
    skb->len = len;
    skb->tail = skb->data + len;
}

static inline void skb_free(struct sk_buff * skb) {
    free(skb);
}

static inline sk_buff * skb_alloc(size_t size) {
    sk_buff * tmp = malloc(sizeof(sk_buff) + size);
    if (! tmp ) {
        USER_PANIC("sk_buf malloc failed");
    }

    tmp->head = tmp->data = tmp->tail = (unsigned char *) tmp  + sizeof(sk_buff);
    tmp->end = (unsigned char *) tmp + sizeof(sk_buff) + size;
    tmp->len = 0;
    return tmp;
}

#endif
