/**
 * \file
 * \brief UDP netcat
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

    assert(argc > 3);

    barrelfish_usleep(2000000);
    char* buf;
    err = aos_rpc_new_remote_bind_4ever("nm", BASE_PAGE_SIZE, (void**) &buf);
    DBGERR(err, "Error binding to nm\n");

    urpc_s sock_urpc;
    urpc_s_init((char*) buf, &sock_urpc, false);

    uint16_t sport = atoi(argv[2]);
    uint16_t dport = atoi(argv[3]);

    sock_req sr = {
        .magic = SR_MAGIC,
        .proto = SR_UDP,
        .sport = sport,
        .dport = dport,
        .dst = inet_addr(argv[1]),
    };

        debug_printf("MEEEEEEEEEEEESGGGGGGGGGGGGGGGG \n");
    err = urpc_write_msg(&sock_urpc, (void**) &sr, sizeof(sock_req));
    DBGERR(err, "Error writing req\n");

    while (1) {
        char cmmsg[1024 + sizeof(sock_res)];
        size_t mlen;

        sock_res* mmsg = (sock_res *) cmmsg;

        mmsg->src = sr.dst;
        mmsg->sport = sr.dport;
        
        debug_printf("READING\n");

    //char *input = (char *) mmsg->buf;
    //size_t i = 0;
    //char* c = input++;
    //while ((*(c-1) != '\r' && *(c-1) != '\n') && i++ < 1023) {
    //    *c = getchar();
    //    c++;
    //}

    //char *newline = MAX(strchr(input, '\n'), strchr(input, '\r'));
    //if (newline) *newline = '\0';


        debug_printf("read %c\n", getchar());
        //mlen = fread( (char *) mmsg + offsetof(sock_res, buf), 1024, 1, stdin);
        //scanf("%1024s", (char *) mmsg + offsetof(sock_res, buf));
        mlen = strlen((char *) mmsg + offsetof(sock_res, buf)) + 1;

        debug_printf("writing\n");
        err = urpc_write_msg(&sock_urpc, (void*) mmsg, sizeof(sock_res) + mlen);
        DBGERR(err, "Error sending\n");

        sock_res* msg;
        size_t len;
        debug_printf("reading response\n");
        err = urpc_read_msg(&sock_urpc, (void**) &msg, &len);
        DBGERR(err, "Error receiving message\n");

        fwrite(msg->buf, msg->len, 1, stdout);

        free(msg);
        barrelfish_usleep(1);
    }


    return err;
}
