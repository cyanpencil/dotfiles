// NetworkManager
// Userspace Network Management process

#include <aos/aos_rpc.h>
#include <aos/deferred.h>
#include <aos/domain.h>
#include <aos/dlinked_list.h>
#include <urpc.h>

#include <netutil/checksum.h>
#include <arpa/inet.h>
#include <netinet/ip.h>
#include <netinet/in.h>

#include "nm.h"
#include "inet.h"

errval_t net_rx(sk_buff * sb);
dlinked_list_t * globa_queue;

struct nm * netman;

errval_t net_rx(sk_buff * sb) {
    //printf("Enqueued %d @ %p\n", sb->len, sb);

#ifdef NO_QUEUES
    net_handle(sb);
#else
    thread_mutex_lock(&netman->lock);
    dlist_tail_insert(globa_queue, sb);
    thread_mutex_unlock(&netman->lock);
#endif

    return SYS_ERR_OK;
}

static inline void net_handle(sk_buff * sb) {
    switch (sb->protocol) {
        case INET_IP4:
            ip4_rx_handle(sb);
            break;

        default:
            debug_printf("UNHANDLED PROTO\n");
    }
}

__unused static void net_queue(void * arg) {
    dlinked_list_t * queue = (dlinked_list_t *) arg;
    sk_buff * sb;

    while (1) {
        while (! (sb = (sk_buff *) dlist_head(queue))) {
            barrelfish_usleep(1);
        }
        net_handle(sb);

        thread_mutex_lock(&netman->lock);
        dlist_remove(queue, (void *) sb);
        thread_mutex_unlock(&netman->lock);
    }
}

#include "../../lib/aos/include/threads_priv.h"
static inline void udp_user_write(sock_state * sstate) {
    size_t len;
    sock_res * msg;

    while (1) {
        wait_for_new_message(sstate->urpc);
        urpc_read_msg(sstate->urpc, (void **) &msg, &len);
        
        struct sopt so = {
            .src.s_addr = sstate->src,
            .default_netdev = netman->default_netdev,
            .sport = sstate->sport,

            .dst.s_addr = msg->src,
            .dport = ntohs(msg->sport),
        };


        udp_tx_handle(&so, (unsigned char *) msg->buf, msg->len);

        barrelfish_usleep(1);
        free(msg);
    }
}

static inline void udp_echo(sock_state * sstate) {
    size_t len;
    sock_res * msg;

    urpc_s bad;
    urpc_s_init(sstate->urpc->buf, &bad, false);
    while (1) {
        urpc_read_msg(&bad, (void **) &msg, &len);
        
        struct sopt so = {
            .src.s_addr = sstate->src,
            .default_netdev = netman->default_netdev,
            .sport = sstate->sport,

            .dst.s_addr = msg->src,
            .dport = ntohs(msg->sport),
        };

        udp_tx_handle(&so, (unsigned char *) msg->buf, msg->len);

        barrelfish_usleep(1);
        free(msg);
    }
}

static inline void icmp_ping(sock_state * sstate) {
    size_t len;
    sock_res * msg;

    while (1) {
        urpc_read_msg(sstate->urpc, (void **) &msg, &len);
        
        sopt opt = {
            .src.s_addr = sstate->src,
            .dst.s_addr = msg->src,
            .default_netdev = netman->default_netdev,
        };

        char * body = "AAAAAAAAAAAAAAAAAAAAAAAA";

        icmp_echo_req(&opt, (uint8_t *) body, sizeof(body), msg->seq);

        free(msg);
        barrelfish_usleep(1);
    }
}

static void handle_bind(void * urpc_p) {
    size_t len;
    char * msg;
    urpc_s * urpc = (urpc_s *) urpc_p;
        //char * bind_buf = malloc(BASE_PAGE_SIZE);
        //urpc_s * urpc = malloc(sizeof(urpc_s));

        //urpc_s_init(&bind_buf, urpc, true);
        //debug_printf("handle bind %p\n", urpc);
        //sock_req testsr = {
        //    .magic = SR_MAGIC,
        //    .sport = 66,
        //    .src = 0,
        //    .dst = inet_addr("10.0.2.2"),
        //};
    
    debug_printf("Netman: waiting\n");
    urpc_read_msg(urpc, (void **) &msg, &len);
    debug_printf("Netman: unblocked\n");

    sock_req * sr = (sock_req *) msg;
    len = sizeof(sock_req);

    if (!sr->src) {
        sopt opt = {
            .default_netdev = netman->default_netdev,
        };
        sr->src = ip_route(sr->dst, &opt)->p.ip;
    }

    debug_printf("Netman: binding\n");
    // lock nm
    thread_mutex_lock(&netman->lock);
    if (sr->magic == SR_MAGIC) {
        switch (sr->proto) {
            case SR_UDP:
                debug_printf("Netman: binding %p\n", sr->sport);
                if (netman->udp_port[sr->sport] == NULL) {
                    sock_state * sstate = malloc(sizeof(sock_state));
                    sstate->dport = sr->dport;
                    sstate->sport = sr->sport;
                    sstate->urpc = urpc;
                    sstate->src = sr->src;
                    sstate->dst = sr->dst;

                    netman->udp_port[sr->sport] = sstate;
                    thread_mutex_unlock(&netman->lock);

                    //udp_echo(sstate);
                    udp_user_write(sstate); // should never return
                    goto end;
                } else {
                    debug_printf("Netman: Bind: Address already in use\n");
                }

                break;

            case SR_ICMP:
                thread_mutex_unlock(&netman->lock);

                sock_state sstate;
                sstate.urpc = urpc;
                sstate.src = sr->src;
                sstate.dst = sr->dst;

                icmp_ping(&sstate);
                goto end;

                break;

            case SR_TCP:
            default:
                debug_printf("Netman: NYI\n");
        }
    } else {
        debug_printf("Netman: invalid magic\n");
    }
    // unlock
    thread_mutex_unlock(&netman->lock);

end:
    free(msg);
    return;
}

int main(int argc, char ** argv) {
    errval_t err;
    if (disp_get_current_core_id() != 0) {
        return 0;
    }

    // register service
    err = aos_rpc_register_service(get_init_rpc(), "nm");

    netman = malloc(sizeof(struct nm));
    thread_mutex_init(&netman->lock);

    dlinked_list_t queue;
    globa_queue = &queue;
    dlist_init(&queue);

    dlist_init(&netman->netdevs);

    netdev * s_nd;
    netdev_new_slip(&s_nd);

    s_nd->p.ip = inet_addr("10.0.0.1");
    s_nd->p.mask = inet_addr("255.0.0.0");
    s_nd->p.init(s_nd, net_rx);
    netman->default_netdev = s_nd;
    dlist_tail_insert(&netman->netdevs, s_nd);

    netdev * lo_nd;
    netdev_new_lo(&lo_nd);
    lo_nd->p.ip = inet_addr("127.0.0.1");
    lo_nd->p.mask = inet_addr("255.0.0.0");
    lo_nd->p.init(lo_nd, net_rx);
    dlist_tail_insert(&netman->netdevs, lo_nd);

    struct thread* recv_thread;
    recv_thread = thread_create((thread_func_t) s_nd->p.loop, NULL);
    if (!recv_thread) {
        debug_printf("Error starting recv thread\n");
        return -1;
    }

    struct thread* queue_thread;
    queue_thread = thread_create((thread_func_t) net_queue, &queue);
    if (!queue_thread) {
        debug_printf("Error starting queue thread\n");
        return -1;
    }
    //char * buf;
    //aos_rpc_new_remote_bind_4ever("acaso", 0x1000, (void **) &buf);
    //debug_printf("No way\n");

    while (1) {
        debug_printf("loopin\n");
        char * bind_buf = aos_rpc_receive_bind();

        urpc_s * urpc = malloc(sizeof(urpc_s));
        urpc_s_init(bind_buf, urpc, true);

        struct thread* bind_thread = thread_create((thread_func_t) handle_bind, urpc);
        if (!bind_thread) {
            debug_printf("Error starting bind thread\n");
        } else {
            // save thread for future join
        }
        
        barrelfish_usleep(10);
    }

    return 0;
}
