#ifndef __UDP_H
#define __UDP_H

struct udphdr {
    uint16_t uh_sport;      /* source port */
    uint16_t uh_dport;      /* destination port */
    uint16_t uh_ulen;       /* udp length */
    uint16_t uh_sum;        /* udp checksum */
};

struct udp_pseudohdr {
    struct in_addr src;
    struct in_addr dst;
    uint8_t zero;
    uint8_t proto;
    uint16_t len;
};

#endif
