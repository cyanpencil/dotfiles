#include <test/test.h>

#include <test/test_mm.h>
#include <test/test_paging.h>

void unit_test_start(unit_test_ctx * ut_ctx) {
    unit_test_mem(ut_ctx);
    unit_test_paging(ut_ctx);
}
