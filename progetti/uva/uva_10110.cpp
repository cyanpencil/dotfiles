#include <iostream>
#include <fstream>
#include <math.h>

using namespace std;

int main() {
    long long n;
    while(scanf(" %lld", &n) == 1 && n != 0) {
        long long s = (long long) sqrt(n);
        if (s * s == n) printf("yes\n");
        else printf("no\n");
    }
}
