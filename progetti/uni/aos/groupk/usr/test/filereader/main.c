/**
 * \file
 * \brief Hello world application
 */

/*
 * Copyright (c) 2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, CAB F.78, Universitaetstr. 6, CH-8092 Zurich,
 * Attn: Systems Group.
 */ 
#include <stdio.h>
#include <ctype.h>

#include <aos/aos.h>
#include <aos/aos_rpc.h>
#include <fs/fs.h>
#include <fs/dirent.h>
#include <aos/deferred.h>
#include <omap_timer/timer.h> 
#include <spawn/spawn.h>
#include <spawn/multiboot.h>
#include <spawncore.h>

#define ENABLE_LONG_FILENAME_TEST 1
/* reading */
#define MOUNTPOINT     "/sdcard"
#define SUBDIR         "/parent"
#define SUBDIR_LONG    "/parent-directory"
#define DIR_NOT_EXIST  "/not-exist"
#define FILENAME       "/myfile.txt"
#define LONGFILENAME   "/mylongfilenamefile.txt"
#define LONGFILENAME2  "/mylongfilenamefilesecond.txt"
#define FILE_NOT_EXIST "/not-exist.txt"
#define HELLO_BIN   "hello"

#define TEST_PREAMBLE(arg) \
    debug_printf("\n"); \
    debug_printf("-------------------------------\n"); \
    debug_printf("%s(%s)\n", __FUNCTION__, arg);

#define TEST_END \
    debug_printf("-------------------------------\n");

#define EXPECT_SUCCESS(err, test, _time) \
    do{ \
        if(err_is_fail(err)){ \
            DEBUG_ERR(err, test); \
        } else { \
            debug_printf("~> \x1b[33mSUCCESS\x1b[0m: " test " took %" PRIu64 "ms\n", _time);\
        }\
   } while(0);\

#define EXPECT_FAILURE(err, _test, _time) \
    do{ \
        if(err_is_fail(err)){ \
            debug_printf("~> \x1b[33mSUCCESS\x1b[0m: failure expected " _test " took %" PRIu64 "ms\n", _time);\
        } else { \
            DEBUG_ERR(err, "~> \x1b[31mFAILURE\x1b[0m: failure expected, but test succeeded" _test); \
        }\
   } while(0);\

#define run_test(fn, arg) \
    do { \
        tstart = omap_timer_read(); \
        err = fn(arg); \
        tend = omap_timer_read(); \
        EXPECT_SUCCESS(err, #fn, omap_timer_to_ms(tend-tstart)); \
        TEST_END \
    } while(0); \

#define run_test_fail(fn, arg) \
    do { \
        tstart = omap_timer_read(); \
        err = fn(arg); \
        tend = omap_timer_read(); \
        EXPECT_FAILURE(err, #fn, omap_timer_to_ms(tend-tstart)); \
        TEST_END \
    } while(0); \

__unused static errval_t test_read_dir(char *dir)
{
    errval_t err;

    // TEST_PREAMBLE(dir)

    fs_dirhandle_t dh;
    err = opendir(dir, &dh);
    if (err_is_fail(err)) {
        return err;
    }

    assert(dh);

    do {
        char *name;
        err = readdir(dh, &name);
        if (err_no(err) == FS_ERR_INDEX_BOUNDS) {
            break;
        } else if (err_is_fail(err)) {
            goto err_out;
        }
        // debug_printf("%s\n", name);
    } while(err_is_ok(err));

    return closedir(dh);
    err_out:
    return err;
}

__unused static errval_t test_mkdir(char *dir) {
    TEST_PREAMBLE(dir)
    return mkdir(dir);
}

__unused static errval_t test_rmdir(char *dir) {
    TEST_PREAMBLE(dir)
    return rmdir(dir);
}

__unused static errval_t spawn_from_sd(char *file) {
    TEST_PREAMBLE(file);

    domainid_t pid;
    return aos_rpc_process_spawn(get_init_rpc(), MOUNTPOINT "/" "hello", 0, &pid);
}

__unused static errval_t test_fread_only(char *file)
{
    int res = 0;

    TEST_PREAMBLE(file)

    FILE *f = fopen(file, "r");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    /* obtain the file size */
    res = fseek (f , 0 , SEEK_END);
    if (res) {
        return FS_ERR_INVALID_FH;
    }

    size_t filesize = ftell (f);
    rewind (f);

    debug_printf("File size is %zu\n", filesize);

    char *buf = calloc(filesize + 2, sizeof(char));
    if (buf == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    size_t read = fread(buf, sizeof(char), filesize, f);
    if (read != filesize) {
        return FS_ERR_READ;
    }

    free(buf);
    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}

__unused static errval_t test_fread(char *file)
{
    int res = 0;

    TEST_PREAMBLE(file)

    FILE *f = fopen(file, "r");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    /* obtain the file size */
    res = fseek (f , 0 , SEEK_END);
    if (res) {
        return FS_ERR_INVALID_FH;
    }

    size_t filesize = ftell (f);
    rewind (f);

    debug_printf("File size is %zu\n", filesize);

    char *buf = calloc(filesize + 2, sizeof(char));
    if (buf == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    size_t read = fread(buf, sizeof(char), filesize, f);

    for (int i = 0; i < read; i++) {
        printf("%c ", isprint(buf[i]) ? buf[i]  : '.');
        if (!((i+1) % 52))
            printf("\n");
    }
    printf("\n");
    debug_printf("Read %zu / %zu\n", read, filesize);
    if (read != filesize) {
        return FS_ERR_READ;
    }

    free(buf);
    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}
__unused static errval_t test_fread_bench(char *file) {
    int res = 0;

    // TEST_PREAMBLE(file)

    FILE *f = fopen(file, "r");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    /* obtain the file size */
    res = fseek (f , 0 , SEEK_END);
    if (res) {
        return FS_ERR_INVALID_FH;
    }

    size_t filesize = ftell (f);
    rewind (f);

    // debug_printf("File size is %zu\n", filesize);

    char *buf = calloc(filesize + 2, sizeof(char));
    if (buf == NULL) {
        return LIB_ERR_MALLOC_FAIL;
    }

    uint64_t bench_start, bench_end;
    bench_start = omap_timer_read();
    size_t read = fread(buf, sizeof(char), filesize, f);
    bench_end = omap_timer_read();
    debug_printf("%s, %zu\n", file, omap_timer_to_ms(bench_end - bench_start));
    if (read != filesize) {
        return FS_ERR_READ;
    }

    free(buf);
    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}

__unused static errval_t test_fwrite(char *file)
{
    int res = 0;

    TEST_PREAMBLE(file)

    FILE *f = fopen(file, "w");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    /* obtain the file size */
    // res = fseek (f , 0 , SEEK_END);
    // if (res) {
        // return FS_ERR_INVALID_FH;
    // }

    // size_t filesize = ftell (f);
    // rewind (f);

    // debug_printf("File size is %zu\n", filesize);

    char *blueprint = "HelloThere!";
    size_t blueprint_len = strlen(blueprint);
    char *buf = calloc(sizeof(char), blueprint_len);
    for (int i = 0; i < blueprint_len; i++) {
        buf[i] = blueprint[i % blueprint_len];
    }
    size_t written = fwrite(buf, sizeof(char), blueprint_len, f);

    if (written != blueprint_len) {
        return FS_ERR_READ;
    }

    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}

__unused static errval_t test_fwrite_long(char *file)
{
    int res = 0;

    TEST_PREAMBLE(file)

    FILE *f = fopen(file, "w");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    /* obtain the file size */
    res = fseek (f , 0 , SEEK_END);
    if (res) {
        return FS_ERR_INVALID_FH;
    }

    size_t filesize = ftell (f);
    rewind (f);

    debug_printf("File size is %zu\n", filesize);

    filesize = 5000;
    char *buf = calloc(sizeof(char), filesize);
    char *blueprint = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    size_t blueprint_len = strlen(blueprint);
    for (int i = 0; i < filesize; i++) {
        buf[i] = blueprint[i % blueprint_len];
    }
    buf[filesize - 1] = 0x0;
    size_t written = fwrite(buf, sizeof(char), filesize, f);

    if (written != filesize) {
        return FS_ERR_READ;
    }

    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}

__unused static errval_t test_fcreate(char *file)
{
    int res = 0;

    TEST_PREAMBLE(file)

    FILE *f = fopen(file, "w");
    if (f == NULL) {
        return FS_ERR_OPEN;
    }

    res = fclose(f);
    if (res) {
        return FS_ERR_CLOSE;
    }

    return SYS_ERR_OK;
}

__unused static errval_t test_file_remove(char *file)
{
    TEST_PREAMBLE(file)
    return rm(file);
}

int main(int argc, char *argv[]) {
    errval_t err;
    __unused uint64_t tstart, tend;

    debug_printf("Filereader test\n");

    err = omap_timer_init();
    assert(err_is_ok(err));
    omap_timer_ctrl(true);

    // debug_printf("============================================\n");
    // debug_printf("             Dir Create Tests              \n");
    // debug_printf("============================================\n");
    // err = test_rmdir(MOUNTPOINT "/" "dir");
    // if (!err_is_ok(err) && err != FS_ERR_NOTFOUND)
        // DEBUG_ERR(err, "Error removing create\n");
    // run_test(test_mkdir, MOUNTPOINT "/" "dir");
    // run_test(test_read_dir, MOUNTPOINT "/" "dir");

    // debug_printf("============================================\n");
    // debug_printf("             Dir Delete Tests              \n");
    // debug_printf("============================================\n");
     //err = mkdir(MOUNTPOINT "/" "dir");
     //test_fwrite(MOUNTPOINT "/" "dir" "/" "create");
    // if (!err_is_ok(err) && err != FS_ERR_EXISTS)
        // DEBUG_ERR(err, "Error creating dir\n");
    // run_test(test_rmdir, MOUNTPOINT "/" "dir");
    // run_test_fail(test_read_dir, MOUNTPOINT "/" "dir");

    // if (disp_get_current_core_id()) {
        // debug_printf("Core %d listing dir\n", disp_get_current_core_id());
        // while(true) {
            // run_test(test_read_dir, MOUNTPOINT "/" "white");
            // barrelfish_usleep(1000000);
        // }
    // }
    // barrelfish_usleep(1000000);

    //debug_printf("============================================\n");
    //debug_printf("               Dir Read Tests               \n");
    //debug_printf("============================================\n");
    // for (int i = 0; i < 5; i++) {
        // uint64_t bench_s, bench_e;
        // bench_s = omap_timer_read();
        // for (int c = 0; c < 10; c++) {
            // test_read_dir(MOUNTPOINT "/");
            // // run_test_fail(test_read_dir, DIR_NOT_EXIST);
            //run_test(test_read_dir,MOUNTPOINT "/" "white");
            //run_test(test_read_dir,MOUNTPOINT "/" "white" "/"  ".");
            // test_read_dir(MOUNTPOINT "/" "white" "/" "rabbit" "/" "..");
            // test_read_dir(MOUNTPOINT "/" "white" "/" "rabbit");
            // test_read_dir(MOUNTPOINT "/" "white" "/" "rabbit" "/" "." "/" ".." "/" "." "/" "rabbit");
        // }
        // bench_e = omap_timer_read();
        // debug_printf("OUTTTTTTT %lld\n", omap_timer_to_ms(bench_e- bench_s));
    // }

    // debug_printf("============================================\n");
    // debug_printf("             File Create Tests              \n");
    // debug_printf("============================================\n");
    // err = test_file_remove(MOUNTPOINT "/" "create");
    // if (!err_is_ok(err) && err != FS_ERR_NOTFOUND)
        // DEBUG_ERR(err, "Error removing create\n");
    // run_test(test_fcreate, MOUNTPOINT "/" "create");
    // run_test(test_fread, MOUNTPOINT "/" "create");

    // err = test_file_remove(MOUNTPOINT "/" "white" "/" "create");
    // if (!err_is_ok(err) && err != FS_ERR_NOTFOUND)
        // DEBUG_ERR(err, "Error removing create\n");
    // run_test(test_fread, MOUNTPOINT "/" "white" "/" "create");

    // debug_printf("============================================\n");
    // debug_printf("             File Delete Tests              \n");
    // debug_printf("============================================\n");
    // err = test_fcreate(MOUNTPOINT "/" "delete");
    // if (!err_is_ok(err) || err != FS_ERR_EXISTS) // DBGERR(err, "Error deleting delete\n"); // run_test(test_file_remove, MOUNTPOINT "/" "delete");

    // debug_printf("============================================\n");
    // debug_printf("               File Read Tests              \n");
    // debug_printf("============================================\n");
    // run_test(test_fread, MOUNTPOINT FILENAME);
    // run_test(test_fread, MOUNTPOINT "/" "averylongfoldernamewhichmakesnosenseatall.txt");
    // run_test(test_fread, MOUNTPOINT "/" "long.txt");
    // run_test(test_fread, MOUNTPOINT "/" "white" "/" "rabbit" "/" "matrix.txt");

    // debug_printf("============================================\n");
    // debug_printf("              File Write Tests              \n");
    // debug_printf("============================================\n");
    // run_test(test_fwrite, MOUNTPOINT FILENAME);
    // run_test(test_fread, MOUNTPOINT FILENAME);
    // run_test(test_fwrite_long, MOUNTPOINT FILENAME);
    // run_test(test_fread, MOUNTPOINT FILENAME);

    // if (disp_get_current_core_id() == 0) {
        // debug_printf("============================================\n");
        // debug_printf("              Proc Spawn Tests              \n");
        // debug_printf("============================================\n");
        // run_test(spawn_from_sd, MOUNTPOINT "/" "hello");
    // }


    // debug_printf("============================================\n");
    // debug_printf("               Benchmark Tests              \n");
    // debug_printf("============================================\n");
    // for (int i = 0; i < 5; i++) {
        // // test_fread_bench(MOUNTPOINT "/" "bench512");
        // // test_fread_bench(MOUNTPOINT "/" "bench1K");
        // test_fread_bench(MOUNTPOINT "/" "bench4K");
        // test_fread_bench(MOUNTPOINT "/" "bench16K");
        // test_fread_bench(MOUNTPOINT "/" "bench32K");
        // test_fread_bench(MOUNTPOINT "/" "bench64K");
        // // test_fread_bench(MOUNTPOINT "/" "bench1M");
    // }

    return EXIT_SUCCESS;
}
