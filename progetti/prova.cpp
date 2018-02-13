#include <stdio.h> 

int main() {
    int value, *pv, **ppv;

    value = 971;
    pv = (int*)&ppv;
    ppv = &pv;

    printf("%d %d %d\n", value, *pv, **ppv);
    return 0;
}
