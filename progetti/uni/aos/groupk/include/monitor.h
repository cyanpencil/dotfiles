#ifndef __MONITOR_HDR_
#define __MONITOR_HDR_

enum URPC_CB_REQ {
    URPC_CB_SPAWN_PROC = 0,
    URPC_CB_BINDING,
    URPC_CB_REQS_NO // This is the count of the previous types
};
typedef struct urpc_cb_s {
    dlinked_node_t node;
    //       (arg  , chan           , res );
    void (*f)(void*, struct aos_rpc*, rpc_res*);
    void* args; // Caller arguments
    struct aos_rpc *chan;
} urpc_cb_t;
dlinked_list_t urpc_cb_queues[URPC_CB_REQS_NO];

void default_urpc_cb(void* arg, struct aos_rpc* chan, rpc_res* res);
void cross_core_fs_urpc_cb(void* arg, struct aos_rpc* chan, rpc_res* res);

void handshake_handler (void *args);
void ipc_request_handler(int type, char * buf, struct aos_rpc* rpc, struct capref received_cap);
#endif
