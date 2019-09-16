#include <test/test.h>

#include <test/test_mm.h>
#include <test/test_paging.h>
#include <test/test_proc.h>
#include <test/test_rpc.h>
#include <test/test_cores.h>
#include <test/test_filesystem.h>
#include <test/test_nm.h>

void unit_test_start(unit_test_ctx * ut_ctx) {
    // unit_test_mem(ut_ctx);
    // unit_test_paging(ut_ctx);
    //unit_test_filesystem(ut_ctx);
    unit_test_proc(ut_ctx);
    // unit_test_rpc(ut_ctx);
    // unit_test_cores(ut_ctx);
     //unit_test_nm(ut_ctx);
}
