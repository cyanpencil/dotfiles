#ifndef __MONITOR_HDR_
#define __MONITOR_HDR_

void handshake_handler (void *args);
void handle_uart_getchar_interrupt(void *args);
void ipc_request_handler(int type, char * buf, struct aos_rpc* rpc, struct capref received_cap);
#endif
