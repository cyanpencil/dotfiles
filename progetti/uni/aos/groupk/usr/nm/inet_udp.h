#ifndef __INET_UDP_H
#define __INET_UDP_H

#include "udp.h"

extern struct nm * netman;

static inline void udp_tx_handle(sopt * opt, unsigned char * buf, size_t len) {
    const size_t headers_size = sizeof(struct ip) + 
            sizeof(struct udphdr) + SCRAP_SPACE;

    sk_buff * skb = skb_alloc(headers_size + len);

    skb_reserve(skb, headers_size);

    skb_put(skb, len);
    memcpy(skb->data, buf, len);
    struct udphdr * udp = (struct udphdr *) 
        skb_push(skb, sizeof(struct udphdr));

    memset(skb->data, 0, sizeof(struct udphdr));

    udp->uh_ulen = htons(len + sizeof(struct udphdr));
    udp->uh_sport = htons(opt->sport);
    udp->uh_dport = htons(opt->dport);

    struct udp_pseudohdr * ups = (struct udp_pseudohdr *)
        skb_push(skb, sizeof(struct udp_pseudohdr));
    udp->uh_sum = 0;
    ups->src = opt->src;
    ups->dst = opt->dst;
    ups->zero = 0;
    ups->proto = IPPROTO_UDP;
    ups->len = udp->uh_ulen;
    udp->uh_sum = inet_checksum(ups, skb->len);
    if (udp->uh_sum == 0) {
        udp->uh_sum = 0xffff;
    }

    skb_pull(skb, sizeof(struct udp_pseudohdr));

    opt->proto = IPPROTO_UDP;
    skb->h.udph = udp;
    skb->odev = NULL;
    skb->idev = NULL; // more correct: route(src)?;

    //debug_printf("DATA: %p len: %d port %hd %hd\n", skb->data, skb->len,
    //        opt->sport, opt->dport);
    ip4_tx_prefill(skb, opt);
}

static inline void udp_rx_handle(sk_buff * skb) {
    struct udphdr * udp = (struct udphdr *) skb->data;
    skb->h.udph = udp;

    skb_pull(skb, sizeof(struct udphdr));

    sock_state * ss;
    if (!(ss = netman->udp_port[ntohs(udp->uh_dport)])) {
        debug_printf("UDP: noone listening\n");
        goto drop;        
    }

    if (udp->uh_sum != 0) {
        // checksum
    }

    size_t len = ntohs(udp->uh_ulen) - sizeof(struct udphdr);
    if (len > skb->len) {
        debug_printf("UDP: Illegal len\n");
        goto drop;
    }

    in_addr_t src = skb->nh.iph->ip_src.s_addr;
    uint16_t port = udp->uh_sport;

    // XXX: destroys UDP header
    sock_res * sr = (struct sock_res *)
        skb_push(skb, sizeof(sock_res));
    sr->src = src;
    sr->sport = port;
    sr->len = len;

    urpc_write_msg(ss->urpc, skb->data, skb->len);

drop:
    skb_free(skb);
    return;
}

#endif
