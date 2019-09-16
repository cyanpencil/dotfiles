#ifndef __SO_OPT_H
#define __SO_OPT_H

#include <arpa/inet.h>
#include <aos/dlinked_list.h>

#include "netdev.h"

typedef struct sopt {
    struct in_addr src;
    struct in_addr dst;
    uint8_t proto;
    uint16_t sport;
    uint16_t dport;
    netdev * default_netdev;
    dlinked_list_t netdevs;
} sopt;

#endif
