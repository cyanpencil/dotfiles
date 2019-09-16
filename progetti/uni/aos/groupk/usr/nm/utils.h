#ifndef __NM_UTILS_H
#define __NM_UTILS_H

#include <ctype.h>

static inline void hexdump(size_t len, char * buf) {
    printf("HEXDUMP @ %p, %dB\n", buf, len);
    const int line_s = 4;
    size_t tot = 0;
    for (int i = 0; i < len; i+=line_s) {
        for (int k = 0; k < line_s; k++) {
            tot++;
            for (int j = 0; j < 8; j++) {
                if (tot > len) {
                    printf("X");
                } else if (buf[i+k] & 1<<(7-j)) {
                    printf("1");
                } else {
                    printf("0");
                }
            }
            printf(" ");
        }
        printf("\t");

        for (int k = 0; k < line_s; k++) {
            printf("%c ", isprint(buf[i+k]) ? buf[i+k]  : '.');
        }

        printf("\n");
    }
    printf("\n");
}

#endif
