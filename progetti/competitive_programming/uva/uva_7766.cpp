
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

unsigned long long reverse(unsigned long long s, unsigned long long e, unsigned long long c, bool right) {
    if (s == e)
        if (c == s) return c;
    unsigned long long m = (s + e) / 2;
    if (right) {
        if (c <= m) return reverse(s, m, c, right);
        //c = (m + e) - (c - (m + e)/2);
        c = (m+1) + (e - c);
        return reverse(m+1, e, c, !right);
    }
    else {
        if (c > m) return reverse(m+1, e, c, right);
        c = s + (m - c);
        return reverse(s, m, c, !right);
    }
}


int main() {
    int n;
    char aa[61], bb[61];
    while (scanf(" %d %s %s", &n, &aa, &bb) == 3) {
        unsigned long long a = (unsigned long long) strtoll(aa, nullptr, 2);
        unsigned long long b = (unsigned long long) strtoll(bb, nullptr, 2);
        //cout << reverse(0, (1 << n) - 1, b, 1) - reverse(0, (1 << n) - 1, a, 1) - 1 << endl;

        cout << reverse(0, (1 << 4) - 1, 9, 1) << endl;

        //n = 4;
        //for (int i = 0; i < (1 << n); i++) {
            //cout << reverse(0, (1 << n) - 1, i, 1) << endl;
        //}
    }
}
