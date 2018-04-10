#include <stdio.h>

int mystrlen(char*);

int main() {
    char s[] = "hellohello";
    printf("%d\n", mystrlen(s));
    return 0;
}
