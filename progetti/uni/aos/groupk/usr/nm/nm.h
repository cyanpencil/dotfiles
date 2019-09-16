#ifndef __NM_H
#define __NM_h

#define UDP_PORTS 65535

#include <aos/threads.h>
#include <net/sock.h>
#include "slip_netdev.h"
#include "lo_netdev.h"

#pragma pack(push, 1)
typedef struct sock_state {
    urpc_s * urpc;
    uint16_t sport;
    uint16_t dport;
    in_addr_t dst;
    in_addr_t src;
} sock_state;

struct nm {
    netdev * default_netdev;
    dlinked_list_t netdevs;
    dlinked_list_t bound_udp;
    sock_state * udp_port[UDP_PORTS];
    struct thread_mutex lock;
};

#pragma pack(pop)
#endif
