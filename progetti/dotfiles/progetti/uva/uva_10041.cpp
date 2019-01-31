#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;



int main() {
    int t, n;
    int c[501];
    scanf("%d", &t);
    while(t--) {
        scanf("%d", &n);
        for (int i = 0; i < n; i++) scanf("%d", &c[i]);
        sort(c, c + n);
        int s = c[n/2];
        int k = 0;
        for (int i = 0; i < n; i++) k += abs(c[i] - s);
        printf("%d\n", k);
        continue;
    }
}
