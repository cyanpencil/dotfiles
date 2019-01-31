#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int dp[300][300];

int calc_free(int n) {
    if (n == 0) return 1;
}

int calc(int n, int d) {
    if (d == 0) return 1;
    if (d < 2*n) return 0;
    if (n == 0) return 0;
    if (dp[n][d] != -1) return dp[n][d];
    int max_sol = 0;
    for (int i = 0; i < n; i++) {
        int sol = 1;
        sol *= calc(i, d - 1);
        sol *= calc(n - 2 - i, d);
        max_sol += sol;
    }
    return dp[n][d] = max_sol;
}


int main() {
    memset(dp, -1, sizeof dp);
    int n, d;
    while(scanf("%d %d", &n, &d) == 2) {
        cout << calc(n, d) << endl;
    }
}
