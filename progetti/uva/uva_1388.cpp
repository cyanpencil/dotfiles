
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


int rint() {
    int a;
    scanf(" %d", &a);
    return a;
}

int main() {
    int n, m;
    while (scanf("%d %d", &n, &m) == 2) {
        double A[1001];
        double B[11001];
        double sol = 0;
        double nstep = (10000.0 / (double)n);
        double mstep = (10000.0 / (double)(n + m));
        A[0] = 0;
        B[0] = 0;
        for (int i = 0; i < n; i++) {
            A[i + 1] = A[i] + nstep;
        }
        for (int i = 0; i < (n + m); i++) {
            B[i + 1] = B[i] + mstep;
        }

        for (int i = 1; i < n; i++) {
            double minmin = 9999999999;
            for (int l = 1; l < (n + m); l++) {
                minmin = min(abs(B[l] - A[i]), minmin);
            }
            sol += minmin;
        }

        printf("%.4lf\n", sol);
    }

}
