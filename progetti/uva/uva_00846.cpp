#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int dp [100];

int calc(int n, int l) {
    float s = sqrt(n);
    int base = floor(s);
    int remainder = n - base*base;
    return ((base - 1) * 2 + 1) + ceil((float)remainder / base);
}

/*
1: 1             int= 1
2: 1 2 1         int= 4
3: 1 2 3 2 1     int= 9
4: 1 2 3 4 3 2 1 int= 16
 */

int main() {
    int Z;
    memset(dp, -1, sizeof dp);
    scanf("%d", &Z);
    while(Z--) {
        int a, b;
        scanf("%d %d", &a, &b);
        int n = abs(b-a);
        if (n == 0) {printf("0\n"); continue;}
        printf("%d\n", calc(n, 1));
    }

}
