#include <aos/aos_rpc.h>
#include <netutil/user_serial.h>
#include <maps/omap44xx_map.h>
#include <driverkit/driverkit.h>
#include <urpc.h>
#include <aos/deferred.h>

#include "lo_netdev.h"
#include "utils.h"

#define MAX_PACKET_SIZE 1500

typedef struct lo_netdev {
    struct netdev * next;
    struct netdev * prev;
    struct netdev_pub p;
    rx_cb rx_cb;
} lo_netdev;

static void packet_output(netdev * self, sk_buff * sb) {
    lo_netdev * lo_nd = (lo_netdev *) self;
    sb->idev = self;
    sb->protocol = INET_IP4;
    lo_nd->rx_cb(sb);
}

static void evdispatch_loop(void* args) {
    return;
}

static errval_t lo_init(netdev * self, rx_cb cb) {
    lo_netdev * lo_nd = (lo_netdev *) self;

    lo_nd->rx_cb = cb;

    return SYS_ERR_OK;
}

errval_t netdev_new_lo(netdev ** nd) {
    errval_t err = SYS_ERR_OK;

    lo_netdev * lo_nd;
    lo_nd = malloc(sizeof(lo_netdev));
    if (!lo_nd) {
        USER_PANIC("netdev malloc failed");
    }

    lo_nd->p.init = lo_init;
    lo_nd->p.loop = evdispatch_loop;
    lo_nd->p.hw_send = packet_output;

    *nd = (netdev *) lo_nd;

    return err;
}

