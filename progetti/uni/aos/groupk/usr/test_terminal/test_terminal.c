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
#include <aos/aos.h>
#include <aos/deferred.h>
#include <aos/aos_rpc.h>

void spawn_service(coreid_t core);
void spawn_service(coreid_t core) {
    printf("Input name of service to spawn: ");
    fflush(stdout);
    char name[1024];
    scanf("%s", name);

    printf("\nYou selected service ");
    fflush(stdout);
    printf("\x1b[33m%s\x1b[0m\n", name);
    printf("Launching it up...\n");

    domainid_t pid;
    aos_rpc_process_spawn(get_init_rpc(), name, core, &pid);
    debug_printf("SPAWNED CHILD WITH PID: %d\n", pid);
}

void parrot(void);
void parrot(void) {
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
}

void calculator(void);
void calculator(void) {
    printf("Welcome to the ghost calc");
    fflush(stdout);
    printf(". Enjoy your stay :)\n");
    while (true) {
        int a, b, res = 0;
        char expr;
        int ret = scanf("%d %c %d", &a, &expr, &b);
        if (ret < 3) {
            scanf("%c");
            printf("Enter a valid expression\n");
            continue;
        }

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

#include "../../lib/aos/include/threads_priv.h"

int main(int argc, char *argv[]) {

    aos_rpc_register_service(get_init_rpc(), "terminal");


    if (false && disp_get_current_core_id() != 1) {
        printf("\n\n\t\t\t                        _.'.__\n\
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
\t\t\t      .'  _'  CORE0  .'    _'\n\
\t\t\t     |_.-' Barrelfish '-.'\n\n\n");
        debug_printf("Not running terminal interactively on core 0...\n");
        return 0;
    }


    while (true) {
        printf("\n\n\t\t\t                        _.'.__\n\
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
\t\t\t      .'  _'  CORE%.1d  .'    _'\n\
\t\t\t     |_.-' Barrelfish '-.'\n\n\n", disp_get_current_core_id());
        /*printf("> Barrelfish OS admin menu \n");*/
        printf("> Please select your choice: \n");
        printf("1. Parrot\n");
        printf("2. Ghost calculator\n");
        printf("3. Spawn a process (on core 0)\n");
        printf("4. Quit\n");


        //barrelfish_usleep(20);

        char c;
        scanf("%c", &c);

        switch (c) {
            case '1':
                parrot();
                break;
            case '2':
                calculator();
                break;
            case '3':
                spawn_service(!disp_get_current_core_id());
                break;
            case '4':
                goto exit;
            default:
                printf("Wrong command, try again!\n");
        }
    }

exit:
    while (true) {
        printf("\x1b[?25l\x1b[23;65H ");
        printf("\x1b[25;65Ho");
        fflush(stdout);
        barrelfish_usleep(400000);
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
