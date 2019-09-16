#ifndef __ICMP_H
#define __ICMP_H

#include "icmp.h"
#include "sk_buf.h"
#include "utils.h"
#include "sopt.h"

#include "inet_fw.h"


static inline void icmp_echo_reply(sk_buff * skb) {
    struct icmphdr * icmp = (struct icmphdr *) 
        skb_push(skb, sizeof(struct icmphdr));

    icmp->type = ICMP_ECHOREPLY;
    icmp->checksum = 0;
    icmp->checksum = inet_checksum(icmp, skb->len);

    struct ip * iphdr = skb->nh.iph;
    struct in_addr temp = iphdr->ip_src;
    iphdr->ip_src = iphdr->ip_dst;
    iphdr->ip_dst = temp;
    iphdr->ip_ttl = 255;

    skb->h.icmp = icmp; 
    skb->odev = NULL;

    ip4_tx_handle(skb);
}

static inline void icmp_echo_req(sopt * opt, uint8_t * buf, size_t size, size_t seq) {
    const size_t headers_size = sizeof(struct ip) + 
            sizeof(struct icmphdr) + SCRAP_SPACE;

    sk_buff * skb = skb_alloc(headers_size + size);

    skb_reserve(skb, headers_size);

    skb_put(skb, size);
    memcpy(skb->data, buf, size);
    struct icmphdr * icmp = (struct icmphdr *) 
        skb_push(skb, sizeof(struct icmphdr));

    memset(skb->data, 0, sizeof(struct icmphdr));
    icmp->type = ICMP_ECHO;

    icmp->un.echo.id = 0xfaba;
    icmp->un.echo.sequence = htons(seq);

    icmp->checksum = 0;
    icmp->checksum = inet_checksum(icmp, skb->len);

    skb->h.icmp = icmp; 
    skb->odev = NULL;
    skb->idev = NULL; // more correct: route(src);

    opt->proto = IPPROTO_ICMP;

    ip4_tx_prefill(skb, opt);
}


static inline void icmp_rx_handle(sk_buff * sb) {
    struct icmphdr * icmp = (struct icmphdr *) sb->data;

    sb->h.icmp = icmp;
    skb_pull(sb, sizeof(struct icmphdr));

    switch (icmp->type) {
        case ICMP_ECHO:
            //debug_printf("ICMP info: seqno %d, type %d:%d\n", 
            //        ntohs(icmp->un.echo.sequence), 
            //        icmp->type, icmp->code);
            icmp_echo_reply(sb);
            break;

        case ICMP_ECHOREPLY:
            debug_printf("ICMP reply received: from %d, seqno %d, type %d:%d\n", 
                    sb->nh.iph->ip_src.s_addr,
                    ntohs(icmp->un.echo.sequence), 
                    icmp->type, icmp->code);
            goto drop;
            break;

        case ICMP_DEST_UNREACH:
            debug_printf("ICMP dest unreachable");
            goto drop;

        default:
            //hexdump(sizeof(struct icmphdr), (char *) sb->h.icmp);
            goto drop;
    }

    return;

drop:
    skb_free(sb);
    return;
}
#endif
