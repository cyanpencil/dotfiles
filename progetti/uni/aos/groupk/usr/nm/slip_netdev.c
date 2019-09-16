#include <aos/aos_rpc.h>
#include <netutil/user_serial.h>
#include <maps/omap44xx_map.h>
#include <driverkit/driverkit.h>
#include <urpc.h>

#include "slip_netdev.h"
#include "utils.h"

#define MAX_PACKET_SIZE 1800

/**
 * Internal data structures
 *
 */
typedef struct _slip_stat {
    sk_buff *sb;
    uint16_t curr;
    bool escape;
    struct slip_netdev * nd;
    char * buf;
} slip_stat;

typedef struct slip_netdev {
    struct netdev * next;
    struct netdev * prev;
    struct netdev_pub p;
    urpc_s urpc;
    slip_stat slip_stat_r;
    slip_stat slip_stat_w;
    struct thread_mutex wlock;
    struct capref slip_frame;
    rx_cb rx_cb;
    char scrap[2*MAX_PACKET_SIZE];
} slip_netdev;


enum SLIP_ESC {
    SLIP_END     = 0300,
    SLIP_ESC     = 0333,
    SLIP_ESC_END = 0334,
    SLIP_ESC_ESC = 0335,
    SLIP_ESC_NUL = 0336,
};

int SLIP_SYM[3] = {SLIP_END, SLIP_ESC, 0};

typedef void (*packet_cb)(slip_stat * slip);

/**
 * Global state - needed by the serial driver. Not my fault.
 *
 */
slip_netdev * __slip_netdev;

/**
 * Forward declarations
 *
 */
void packet_input(slip_stat * slip);
void packet_output(netdev * self, sk_buff * sb);

/**
 * Utils
 *
 */

static sk_buff * sk_alloc(void) {
    sk_buff * tmp = malloc(sizeof(sk_buff) + MAX_PACKET_SIZE);
    if (! tmp ) {
        USER_PANIC("netdev malloc failed");
    }

    tmp->head = tmp->data = tmp->tail = (unsigned char *) tmp  + sizeof(sk_buff);
    tmp->end = (unsigned char *) tmp + sizeof(sk_buff) + MAX_PACKET_SIZE;
    tmp->len = 0;
    return tmp;
}


/**
 * Slip primitives
 *
 */
static inline void slip_serialize(uint8_t *buf, size_t len, slip_stat * slip, void (*wcallback)(uint8_t *, size_t)) {
    for (int i = 0; i < len; i++) {
        uint8_t c = buf[i];
        switch (c) {
            case SLIP_END:
                slip->buf[slip->curr++] = SLIP_ESC;
                slip->buf[slip->curr++] = SLIP_ESC_END;
                break;

            case SLIP_ESC:
                slip->buf[slip->curr++] = SLIP_ESC;
                slip->buf[slip->curr++] = SLIP_ESC_ESC;
                break;

            case 0:
                slip->buf[slip->curr++] = SLIP_ESC;
                slip->buf[slip->curr++] = SLIP_ESC_NUL;
                break;

            default:
                slip->buf[slip->curr++] = c;
        }
    }
    slip->buf[slip->curr++] = SLIP_END;
    //debug_printf("SENDDDDDDDDDDDDDDD %d\n", slip->curr);
    //hexdump(slip->curr, slip->buf);
    wcallback((uint8_t *) slip->buf, slip->curr);
    slip->curr = 0;
}

static inline void slip_deserialize_c(uint8_t c, slip_stat * slip, packet_cb cb) {
    switch (c) {
        case SLIP_END:
            cb(slip);
            slip->curr = 0;
            break;

        case SLIP_ESC:
            slip->escape = true;
            break;

        default:
            if (slip->escape) {
                // Check escape code valid. If not, drop character
                if (c-SLIP_ESC_END >= 0 && c-SLIP_ESC_END < 3) {
                    slip->sb->head[slip->curr] = SLIP_SYM[c-SLIP_ESC_END];
                } else {
                    debug_printf("[slip] Warning: invalid escape, dropping char\n");
                }
                slip->escape = false;

            } else {
                slip->sb->head[slip->curr] = c;
            }
    
            slip->curr++;
            if (slip->curr == MAX_PACKET_SIZE) {
                debug_printf("[slip] Warning: packet too big\n");
                slip->curr = 0;
                slip->escape = false;
            }

    }
}

static inline void slip_deserialize(uint8_t *buf, size_t len, slip_stat * slip, packet_cb cb) {
    for (int i = 0; i < len; i++) {
        slip_deserialize_c(buf[i], slip, cb);
    }
}

void serial_input(uint8_t *buf, size_t len) {
    slip_deserialize(buf, len, &__slip_netdev->slip_stat_r, packet_input);
}

void packet_input(slip_stat * slip) {
    // data already filled in
    // by the driver
    skb_put(slip->sb, slip->curr);

    // SLIP encapsulates IP
    slip->sb->protocol = INET_IP4;
    slip->sb->idev = (netdev *) slip->nd;

    // Callback to network stack
    // (must be fast)
    slip->nd->rx_cb(slip->sb);

    // Allocate new socket buffer for next packet
    slip->sb = sk_alloc();
}

void packet_output(netdev * self, sk_buff * sb) {
    slip_netdev * s_nd = (slip_netdev *) self;
    
    thread_mutex_lock(&s_nd->wlock);
    slip_serialize(sb->data, sb->len, &s_nd->slip_stat_w, serial_write);
    thread_mutex_unlock(&s_nd->wlock);

    skb_free(sb);
}

static errval_t nm_serial_init(void) {
    errval_t err;

    // get IRQ cap
    err = aos_rpc_get_irq_cap(
            get_init_rpc(), 
            &cap_irq);
    DBGERR(err, "Failed to get IRQ capability");

    lvaddr_t uart_region;
    err = map_device_register(
            OMAP44XX_MAP_L4_PER_UART4, 
            OMAP44XX_MAP_L4_PER_UART4_SIZE, 
            &uart_region);
    DBGERR(err, "Failed to map frame");


    err = serial_init((lvaddr_t) uart_region, UART4_IRQ);
    DBGERR(err, "Failed to initialise uart");

    return SYS_ERR_OK;
}


#include <aos/deferred.h>
static void evdispatch_loop(void* args) {
    while (1) {
        barrelfish_usleep(1);
        event_dispatch(get_default_waitset());
    }
}

static errval_t slip_init(netdev * self, rx_cb cb) {
    slip_netdev * s_nd = (slip_netdev *) self;

    s_nd->slip_stat_r.sb = sk_alloc();
    s_nd->rx_cb = cb;

    s_nd->slip_stat_w.buf = s_nd->scrap;

    s_nd->slip_stat_r.nd = s_nd;
    s_nd->slip_stat_w.nd = s_nd;

    return SYS_ERR_OK;
}

errval_t netdev_new_slip(netdev ** nd) {
    errval_t err;

    slip_netdev * s_nd;
    s_nd = malloc(sizeof(slip_netdev));

    if (!s_nd) {
        USER_PANIC("netdev malloc failed");
    }

    err = frame_alloc(&s_nd->slip_frame, BASE_PAGE_SIZE, NULL);
    DBGERR(err, "Failed to allocate slip frame");

    err = urpc_map(s_nd->slip_frame, &s_nd->urpc);
    DBGERR(err, "Failed to map slip frame");

    // Initialize hardware
    err = nm_serial_init();
    DBGERR(err, "Failed to initialise hw");

    debug_printf("[slip] Slip serial init done\n");

    s_nd->p.init = slip_init;
    s_nd->p.loop = evdispatch_loop;
    s_nd->p.hw_send = packet_output;

    thread_mutex_init(&s_nd->wlock);

    __slip_netdev = s_nd;
    *nd = (netdev *) s_nd;

    return err;
}

