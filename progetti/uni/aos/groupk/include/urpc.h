/**
 * \file
 * \brief init user-space domain special structures.
 */

#ifndef AOS_URPC_H
#define AOS_URPC_H

#include <spawncore.h>

errval_t urpc_read_msg(urpc_s*, void** buf, size_t* len);

errval_t urpc_write_msg(urpc_s*, void* buf, size_t len);

//errval_t urpc_free_msg(void* buf);

#endif 
