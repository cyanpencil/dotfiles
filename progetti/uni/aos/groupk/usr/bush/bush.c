/**

 * Copyright (c) 2016 ETH Zurich.
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached LICENSE file.
 * If you do not find this file, copies can be found by writing to:
 * ETH Zurich D-INFK, CAB F.78, Universitaetstr. 6, CH-8092 Zurich,
 * Attn: Systems Group.
 */


#include <stdio.h>
#include <aos/debug.h>
#include <ctype.h>
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>
#include <aos/benchmark.h>

#include <test/test.h>
#include <test/test_mm.h>
#include <test/test_paging.h>
#include <test/test_proc.h>
#include <test/test_rpc.h>
#include <test/test_cores.h>
#include <test/test_filesystem.h>
#include "../lib/aos/include/init.h"

#include <fs/fs.h>
#include <fs/dirent.h>
#include "../lib/fs/fs_internal.h"

#include <net/sock.h>
#include <arpa/inet.h>

static char* fish= "\n\n\t\t\t                        _.'.__\n\
\t\t\t                      _.'      .\n\
\t\t\t':'.               .''   __ __  .\n\
\t\t\t  '.:._          ./  _ ''     \"-'.__\n\
\t\t\t.'''-: \"\"\"-._    | .                \"-\"._\n\
\t\t\t '.     .    \"._.'                       \"\n\
\t\t\t    '.   \"-.___ .        .'          .  :o'.\n\
\t\t\t      |   .----  .      .           .'     (\n\
\t\t\t       '|  ----. '   ,.._                _-'\n\
\t\t\t        .' .---  |.\"\"  .-:;.. _____.----'\n\
\t\t\t        |   .-\"\"\"\"    |      '\n\
\t\t\t      .'  _'  CORE%d  .'    _'\n\
\t\t\t     |_.-' Barrelfish '-.'\n\n\n";

static void eval_str(char *input);
static void blink_leds(char* args);
static void calculator(char* args);
static void cat(char* args);
static void echo(char* args);
static void help(char* args);
static void time_cmd(char* args);
static void oncore(char* args);
static void run_test(char* args);
static void ls(char* args);
static void pwd(char* args);
static void cd(char* args);
static void nc(char* args);
static void write_file(char* args);
static void make_dir(char* args);
static void rm_dir(char* args);
static void rm_file(char* args);


typedef void (*callable)(char*);
typedef struct {
    const char *name;
    callable func;
} builtin;

const builtin builtins[] = {
     {"blink_leds", blink_leds},
     {"calculator", calculator},
     {"cat", cat},
     {"echo", echo},
     {"help", help},
     {"time", time_cmd},
     {"oncore", oncore},
     {"run_test", run_test},
     {"ls", ls},
     {"cd", cd},
     {"pwd", pwd},
     {"nc", nc},
     {"write", write_file},
     {"mkdir", make_dir},
     {"rmdir", rm_dir},
     {"remove", rm_file},
};
#define builtin_num (sizeof (builtins) / sizeof (builtin))

char* session_path;

__unused
static void spawn_service(char* args) {
    printf("Input name of service to spawn: ");
    fflush(stdout);
    char name[1024];
    scanf("%s", name);

    printf("\nYou selected service ");
    fflush(stdout);
    printf("\x1b[33m%s\x1b[0m\n", name);
    printf("Launching it up...\n");

    domainid_t pid;
    coreid_t core = 0;
    aos_rpc_process_spawn(get_init_rpc(), name, core, &pid);
    debug_printf("SPAWNED CHILD WITH PID: %d\n", pid);
}

struct la_madonna
{
    dlinked_node_t node;
    char *name;                     ///< name of the file or directory
    uint32_t size;                    ///< the size of the direntry in bytes or files
    size_t refcount;                ///< reference count for open handles
    struct mmchsfs_dirent *parent;    ///< parent directory
    // XXX: This speeds up reverse lookup
    size_t parent_sector; ///< sector relative to the parent first sector in which the DirEntry is located
    size_t parent_in_sec_off; ///< offset in bytes inside sector where stored in parent

    dlinked_list_t *children; ///< a dlinked_list of children

    bool is_root;               ///< flag indicating whether this is root
    bool is_dir;                    ///< flag indicationg this is a dir
    bool is_long_dir;               ///< flag indicationg this is a long dir section
    char* long_name;               ///< long version of the name
    bool was_explored;           ///< flag indicating whether the content of this directory has been explored

    size_t cluster; ///< first sector of cluster of this dirent
    char attr;       ///< attributes of the dir

    union {
        void *data;                 ///< file data pointer
        struct mmchsfs_dirent *dir;   ///< directory pointer
    };
};

struct porcodio
{
    struct fs_handle common;
    char *path;
    bool isdir;
    struct mmchsfs_dirent *dirent;
    union {
        off_t file_pos;
        struct mmchsfs_dirent *dir_pos;
    };
    uint32_t last_cluster;
};

static void ls(char* args) {
    char* mystr = calloc(1024, 1);
    strcpy(mystr, session_path);
    strcat(mystr, args);

    fs_dirhandle_t dh;
    errval_t err = opendir(mystr, &dh);
    if (err_is_fail(err)) { return; }

    do {
        char *name;
        err = readdir(dh, &name);
        if (err_no(err) == FS_ERR_INDEX_BOUNDS) {
            break;
        } 
        printf("%s\n", name);
    } while(err_is_ok(err));

    closedir(dh);
}

static void pwd(char* args) {
    printf("%s\n", session_path);
}

static void cd(char* args) {
    int idx = strlen(session_path);
    strcpy(session_path + idx, args);
    idx = strlen(session_path);
    session_path[idx] = '/';
    session_path[idx+1] = '\0';
}

static void cat(char* args) {
    if (*args == '\0') {
        while (true) {
            printf("Please input 10 chars:\n");
            fflush(stdout);
            char *str = malloc(10);
            scanf("%10s", str);
            //aos_rpc_serial_read(get_init_rpc(), &str, 10);

            printf("Thanks! This is the ");
            fflush(stdout);
            printf("string you sent me: %s\n\n", str);
        }
    } else {
        char* mystr = calloc(1024, 1);
        strcpy(mystr, session_path);
        strcat(mystr, args);
        FILE *f = fopen(mystr, "r");
        if (f == NULL) {
            return;
        }

        /* obtain the file size */
        int res = fseek (f , 0 , SEEK_END);
        if (res) {
            return ;
        }

        size_t filesize = ftell (f);
        rewind (f);

        debug_printf("File size is %zu\n", filesize);

        char *buf = calloc(filesize + 2, sizeof(char));
        if (buf == NULL) {
            return ;
        }

        size_t read = fread(buf, sizeof(char), filesize, f);

        for (int i = 0; i < read; i++) {
            printf("%c", isprint(buf[i]) ? buf[i]  : '.');
            if (!((i+1) % 32))
                printf("\n");
        }
        printf("\n");
        debug_printf("Read %zu / %zu\n", read, filesize);
        if (read != filesize) {
            return ;
        }

        free(buf);
        res = fclose(f);
        if (res) {
            return ;
        }
    }
}

__unused static void make_dir(char *args)
{
    char* mystr = calloc(1024, 1);
    strcpy(mystr, session_path);
    strcat(mystr, args);
    mkdir(mystr);
}

__unused static void rm_dir(char *args)
{
    char* mystr = calloc(1024, 1);
    strcpy(mystr, session_path);
    strcat(mystr, args);
    rmdir(mystr);
}

__unused static void rm_file(char *args)
{
    char* mystr = calloc(1024, 1);
    strcpy(mystr, session_path);
    strcat(mystr, args);
    debug_printf("%s\n", mystr);
    rm(mystr);
}


__unused static void write_file(char *args)
{
    int res = 0;


    char *tok, *rest;
    tok = strtok_r(args, " \n", &rest);

    char* mystr = calloc(1024, 1);
    strcpy(mystr, session_path);
    strcat(mystr, tok);


    FILE *f = fopen(mystr, "w");
    if (f == NULL) {
        return ;
    }

    tok = strtok_r(NULL, " \n", &rest);

    char *blueprint = tok;
    size_t blueprint_len = strlen(blueprint);
    char *buf = calloc(sizeof(char), blueprint_len);
    for (int i = 0; i < blueprint_len; i++) {
        buf[i] = blueprint[i % blueprint_len];
    }
    size_t written = fwrite(buf, sizeof(char), blueprint_len, f);

    if (written != blueprint_len) {
        return ;
    }

    res = fclose(f);
    if (res) {
        return ;
    }

    return ;
}

static void calculator(char* args) {
    printf("Welcome to the ghost calc");
    fflush(stdout);
    printf(". Enjoy your stay :)\n");
    while (true) {
        int a, b, res = 0;
        char expr;
        scanf("%d %c %d", &a, &expr, &b);

        switch (expr) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                res = a / b;
                break;
        }

        printf("%d %c %d = %d\n", a, expr, b, res);
    }
}

static void echo(char* args) {
    if (!args) return;
    char *ohm = strchr(args, '>');
    char *due = ohm;
    if (ohm) {
        ohm++;
        while (*ohm == ' ') ohm++;
        struct dispatcher_shared_generic * disp = get_dispatcher_shared_generic(curdispatcher());
        disp->fd = 2;
        char* mystr = calloc(1024, 1);
        strcpy(mystr, session_path);
        strcat(mystr, ohm);
        debug_printf("%s\n", mystr);
        strncpy(disp->filename, mystr, 31);
        barrelfish_libc_glue_init();
        *due = '\0';
        *ohm = '\0';
        printf("%s\n", args);
        disp->fd = 0;
        barrelfish_libc_glue_init();
    } else {
        printf("%s\n", args);
    }
}

static void time_cmd(char *args) {
    bench_t measure; bench_initmeasure(&measure);
    bench_startsession(false);
    bench_startmeasure(&measure);
    eval_str(args);
    unsigned int cycles = bench_endmeasure(&measure);
    printf("The command \"%s\" took %u cycles\n", args, cycles);
    bench_endsession();
}

static void oncore(char *args) {
    domainid_t pid;

    char *tok, *rest;
    tok = strtok_r(args, " \n", &rest);

    int id = strtol(tok, NULL, 10);

    tok = strtok_r(NULL, " \n", &rest);
    if (tok) aos_rpc_process_spawn(get_init_rpc(), tok, id, &pid);
}

static void blink_leds(char *args) {
    printf("TODO\n");
}

static void nc(char * args) {
    char * argv[4];
    char *rest;
    errval_t err;
    argv[1] = strtok_r(args, " ", &rest);
    argv[2] = strtok_r(NULL, " ", &rest);
    argv[3] = strtok_r(NULL, " \n", &rest);

    debug_printf("ARGS: %s %s %s\n", argv[1], argv[2], argv[3]);

    barrelfish_usleep(2000000);
    char* buf;
    err = aos_rpc_new_remote_bind_4ever("nm", BASE_PAGE_SIZE, (void**) &buf);
    DBGERRV(err, "Error binding to nm\n");

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
    DBGERRV(err, "Error writing req\n");

    while (1) {
        char cmmsg[1024 + sizeof(sock_res)];
        size_t mlen;

        sock_res* mmsg = (sock_res *) cmmsg;

        mmsg->src = sr.dst;
        mmsg->sport = sr.dport;
        
        debug_printf("READING\n");

    char *input = (char *) mmsg->buf;
    size_t i = 0;
    char* c = input++;
    while ((*(c-1) != '\r' && *(c-1) != '\n') && i++ < 1023) {
        *c = getchar();
        c++;
    }

    char *newline = MAX(strchr(input, '\n'), strchr(input, '\r'));
    if (newline) *newline = '\0';


        //debug_printf("read %c\n", getchar());
        //mlen = fread( (char *) mmsg + offsetof(sock_res, buf), 1024, 1, stdin);
        //scanf("%1024s", (char *) mmsg + offsetof(sock_res, buf));
        mlen = strlen((char *) mmsg->buf) + 1;

        debug_printf("writing\n");
        err = urpc_write_msg(&sock_urpc, (void*) mmsg, sizeof(sock_res) + mlen);
        DBGERRV(err, "Error sending\n");

        sock_res* msg;
        size_t len;
        debug_printf("reading response\n");
        err = urpc_read_msg(&sock_urpc, (void**) &msg, &len);
        DBGERRV(err, "Error receiving message\n");

        debug_printf("printing response\n");
        printf("%s", msg->buf);
        //fwrite(msg->buf, msg->len, 1, stdout);

        //free(msg);
        barrelfish_usleep(1);
    }
}


static void run_test(char *args) {
    aos_rpc_send_req(RPC_RUN_TEST, get_init_rpc(), args, strlen(args) + 1, NULL_CAP);
}


static void help(char* args) {
    printf("Available commands:\n");
    for (int i = 0; i < builtin_num; i++) {
        printf("%s\n", builtins[i].name);
    }
}

static void eval_str(char *input) {
    char* space = strpbrk(input, " \n");
    int cmd_len = space - input;

    for (int i = 0; i < builtin_num; i++) {
        if (!strncmp(input, builtins[i].name, cmd_len)){
            builtins[i].func(cmd_len > 0 ? space + 1 : "");
            return;
        }
    }

    if (!input) return;

    char* from_sd = strchr(input, '/');

    char *mystr = calloc(2048, 1);
    if (from_sd - input >= 2) {
        strcpy(mystr, session_path);
        strcpy(mystr + strlen(session_path), input);
    } else if (from_sd - input == 1) {
        strcpy(mystr, session_path);
        strcpy(mystr + strlen(session_path), input + 2);
    } else {
        strcpy(mystr, input);
    }

    
    domainid_t pid;
    debug_printf("%s\n", mystr);
    errval_t err = aos_rpc_process_spawn(get_init_rpc(), mystr, 0, &pid);
    if (err_is_fail(err)) {
        printf("Invalid command: %s\n", input);
    }
}


static void shell(void) {
    session_path = calloc(1024, 1);
    strcpy(session_path, "/sdcard/");
    printf("Welcome to Bush - the Barely Usable Shell!\n");
    char *input = calloc(1024, 1);
    while (true) {
        printf("\x1b[1m>>\x1b[0m ");
        fflush(stdout);
        size_t i = 0;
        char* c = input++;
        while ((*(c-1) != '\r' && *(c-1) != '\n') && i++ < 1023) {
            *c = getchar();
            c++;
        }

        char *newline = MAX(strchr(input, '\n'), strchr(input, '\r'));
        if (newline) *newline = '\0';

        eval_str(input-1);
    }
}

#include "../../lib/aos/include/threads_priv.h"

int main(int argc, char *argv[]) {

    aos_rpc_register_service(get_init_rpc(), "terminal");


    if (disp_get_current_core_id() != 1) {
        printf(fish, disp_get_current_core_id());
        debug_printf("Not running terminal interactively on core 0...\n");
        return 0;
    }


    while (true) {
        printf(fish, disp_get_current_core_id());

        shell();

        char c;
        scanf("%c", &c);

    }

    while (true) {
        printf("\x1b[?25l\x1b[23;65H ");
        printf("\x1b[25;65Ho");
        fflush(stdout);
        barrelfish_usleep(399999);
        printf("\x1b[25;65H ");
        printf("\x1b[24;66Ho");
        fflush(stdout);
        barrelfish_usleep(400000);
        printf("\x1b[23;65Ho");
        printf("\x1b[24;66H ");
        fflush(stdout);
        barrelfish_usleep(400000);
    }

    return 0;
}
