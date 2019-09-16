#ifndef __NETDEV_H
#define __NETDEV_H

#include "sk_buf.h"

typedef errval_t (*rx_cb)(sk_buff * sb);

typedef struct netdev {
    struct netdev * next;
    struct netdev * prev;

    struct netdev_pub {
        void (*hw_send)(struct netdev * self, sk_buff *);
        errval_t (*init)(struct netdev * self, rx_cb);
        void (*loop)(void * arg);
        errval_t (*destroy)(struct netdev * self);

        uint32_t ip;
        uint32_t mask;
    } p;
    char netdev_pvt[];
} netdev;

#endif
