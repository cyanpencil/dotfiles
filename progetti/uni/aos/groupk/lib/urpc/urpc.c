/**
 * \file
 * \brief init user-space domain special structures.
 */

#include <aos/aos.h>
#include <spawncore.h>
#include <urpc.h>

errval_t urpc_read_msg(urpc_s* urpc, void** buf, size_t* len) {
    errval_t err = SYS_ERR_OK;

    b_read(urpc, (void*) len, sizeof(size_t));
    *buf = malloc(*len);
    if (!buf) {
        USER_PANIC("Malloc failed\n");
        return SYS_ERR_OK;
    }
    b_read(urpc, *buf, *len);

    return err;
}

errval_t urpc_write_msg(urpc_s* urpc, void* buf, size_t len) {
    errval_t err = SYS_ERR_OK;

    b_write(urpc, (void*) &len, sizeof(size_t));
    b_write(urpc, buf, len);

    return err;
}
