/**
 * \file
 * \brief UDP echo server 
 */


#include <stdio.h>
#include <aos/debug.h>
#include <aos/threads.h>
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>

#include <net/sock.h>
#include <urpc.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]) {
    errval_t err = SYS_ERR_OK;

    barrelfish_usleep(2000000);
    char* buf;
    err = aos_rpc_new_remote_bind_4ever("nm", BASE_PAGE_SIZE, (void**) &buf);
    DBGERR(err, "Error binding to nm\n");

    urpc_s sock_urpc;
    urpc_s_init((char*) buf, &sock_urpc, false);

    sock_req sr = {
        .magic = SR_MAGIC,
        .proto = SR_UDP,
        .sport = 7
    };

    err = urpc_write_msg(&sock_urpc, (void**) &sr, sizeof(sock_req));
    DBGERR(err, "Error writing req\n");

    while (1) {
        sock_res* msg;
        size_t len;
        err = urpc_read_msg(&sock_urpc, (void**) &msg, &len);
        DBGERR(err, "Error receiving message\n");

        msg->src = msg->src;
        msg->sport = msg->sport;

        err = urpc_write_msg(&sock_urpc, (void**) msg, len); //sizeof(sock_res) + msg->len);
        DBGERR(err, "Error echoing back\n");

        free(msg);
        barrelfish_usleep(1);
    }


    return err;
}
