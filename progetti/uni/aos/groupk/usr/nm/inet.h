#ifndef __NM_IP_H
#define __NM_IP_H

#include "sk_buf.h"
#include "utils.h"
#include "inet_icmp.h"
#include "inet_udp.h"

static inline void ip4_rx_handle(sk_buff * sb) {
    struct ip * iph = (struct ip *) sb->data;
    skb_pull(sb, sizeof(struct ip));

    sb->nh.iph = iph;

    // Check header len for options
    uint8_t hl = iph->ip_hl - 5;
    if (hl*4 + ntohs(iph->ip_len) - sizeof(struct ip) > sb->len) {
        //debug_printf("Dropping invalid len IP packet %hd %d\n", 
        //        ntohs(iph->ip_len), sb->len);
        goto drop;
    } else if (hl > 0) {
        skb_pull(sb, hl*4);
    }


    //debug_printf("IP INFOS: %hhu, %hu, %hhu\n", iph->ip_p, ntohs(iph->ip_len), iph->ip_hl);
    //printf("IP DUMP:\n");
    //hexdump(sizeof(struct ip), (char *) iph);

    // Verify checksum and header fields
    uint8_t csum = inet_checksum(iph, sizeof(struct ip));
    if ( csum != 0       ||
         iph->ip_v != 4  ||
         iph->ip_hl < 5  ||
         iph->ip_ttl == 0
         ) {
        debug_printf("Dropping invalid IP packet\n");
        goto drop;
    }
    
    if ((iph->ip_off & ~IP_OFFMASK) == IP_MF) {
        debug_printf("Dropping fragmented IP packet\n");
        goto drop;
    }

    // Call L3 handlers
    switch (iph->ip_p) {
        case IPPROTO_ICMP:
            icmp_rx_handle(sb);
            break;

        case IPPROTO_UDP:
            udp_rx_handle(sb);
            break;

        case IPPROTO_TCP:
        default:
            debug_printf("UNHANDLED IP PROTO\n");
            goto drop;
    };

    return;

drop:
    skb_free(sb);
    return;
}

static bool prefix_match(in_addr_t net, in_addr_t ip, in_addr_t mask) {
    if ((net & mask) == (ip & mask))
        return true;
    else
        return false;
}
    

static inline netdev * ip_route(in_addr_t addr, sopt * opt) {
    dlinked_node_t* node = netman->netdevs.head;
    while (node) {
        netdev * nd = (netdev *) node;
        if (prefix_match(nd->p.ip, addr, nd->p.mask))
            return nd;
        else 
            node = node->next;
    }

    return opt->default_netdev;
}

static inline void ip4_tx_prefill(sk_buff * skb, sopt * opt) {
    assert(skb->data - skb->tail >= sizeof(struct ip));

    struct ip * iph = (struct ip *) skb_push(skb, sizeof(struct ip));

    memset(iph, 0, sizeof(struct ip));
    iph->ip_v = 4;
    iph->ip_hl = 0x05;
    iph->ip_ttl = 0xff;
    iph->ip_len = htons(skb->len);
    iph->ip_tos = 0;
    iph->ip_id = 0; // ??
    iph->ip_off = htons(IP_DF);

    skb->odev = ip_route(opt->dst.s_addr, opt);

    iph->ip_p = opt->proto; // l4 proto
    
    iph->ip_src.s_addr = (opt->src.s_addr); // check interface ip?
    iph->ip_dst.s_addr = (opt->dst.s_addr);

    // prefill only - set the skb data pointer back
    skb_pull(skb, sizeof(struct ip));

    // dispatch skb
    ip4_tx_handle(skb);
}

static inline void ip4_tx_handle(sk_buff * skb) {
    struct ip * iph = (struct ip *) skb_push(skb, sizeof(struct ip));
    
    skb->nh.iph = iph;
    skb->protocol = INET_IP4;

    if (!skb->odev) {
        skb->odev = skb->idev; // TODO: needs proper routing
    }

    iph->ip_sum = 0;
    iph->ip_sum = inet_checksum(iph, sizeof(struct ip));

    //debug_printf("IP INFOS: l3p %hhu, len %hu, hlen %hhu\n",
    //        iph->ip_p, 
    //        ntohs(iph->ip_len), 
    //        iph->ip_hl);
    skb->odev->p.hw_send(skb->odev, skb);
}

#endif
