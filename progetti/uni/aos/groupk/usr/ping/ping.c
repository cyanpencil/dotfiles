/**
 * \file
 * \brief ping tool
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

    assert(argc > 2);

    char* buf;
    barrelfish_usleep(1000000);
    err = aos_rpc_new_remote_bind_4ever("nm", BASE_PAGE_SIZE, (void**) &buf);
    DBGERR(err, "Error binding to nm\n");

    urpc_s sock_urpc;
    urpc_s_init((char*) buf, &sock_urpc, false);

    sock_req sr = {
        .magic = SR_MAGIC,
        .proto = SR_ICMP,
        .src = 0,
        .dst = inet_addr(argv[1]),
    };

    err = urpc_write_msg(&sock_urpc, (void**) &sr, sizeof(sock_req));
    DBGERR(err, "Error writing req\n");


    size_t seq = 0,
           count = atoi(argv[2]);
    while (seq < count) {
        sock_res msg;

        msg.src = inet_addr(argv[1]);
        msg.seq = seq++;

        printf("Pinging %s\n", argv[1]);

        err = urpc_write_msg(&sock_urpc, (void**) &msg, sizeof(sock_res)); //sizeof(sock_res) + msg->len);
        DBGERR(err, "Error echoing back\n");

        barrelfish_usleep(300000);
    }


    return err;
}
