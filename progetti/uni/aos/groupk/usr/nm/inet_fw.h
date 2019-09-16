#ifndef __NM_IP_FWH
#define __NM_IP_FWH

#include "sk_buf.h"
#include "sopt.h"

static inline void ip4_tx_prefill(sk_buff * skb, sopt * opt);
static inline void ip4_tx_handle(sk_buff * sb);
static inline void ip4_rx_handle(sk_buff * sb);

#endif
