#include <stdio.h>
#include <aos/debug.h>
#include <test/test.h>
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>
#include <spawn/spawn.h>
#include <spawn/multiboot.h>


int main(int argc, char *argv[]) {
    errval_t err;
    coreid_t my_core_id = disp_get_current_core_id();

    if (my_core_id == 0) {
        aos_rpc_register_service(get_init_rpc(), "test_binding"); // advertise to init that I'm called "test_binding"
        debug_printf("Waiting for request ...\n");
        char *buf = aos_rpc_receive_bind(); // blocking until someone gives me a shared frame
        debug_printf("Processing request ...\n");
        if (buf != NULL) {
            debug_printf("Surprisingly, I actually received a capability - answering with ACK\n");
            strcpy(buf, "ACK");
        } else {
            debug_printf("Something went wrong...\n");
        }
    } else {
        char* buf;
        err = aos_rpc_new_remote_bind_4ever("test_binding", BASE_PAGE_SIZE, (void**) &buf);
        DBGERR(err, "Error binding frame\n");
        while (true) {
            if (buf[0] == 'A' && buf[1] == 'C' && buf[2] == 'K') {
                debug_printf("Got ack!!!\n");
                break;
            }
        }
        debug_printf("SUCCESS!!!!\n");
    }

    return 0;
}
