#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>
using namespace std;

int moneyz[] = {1, 5, 10, 25, 50};
long long dp[5][30001];

long long solve(int n, int mi) {
    if (n == 0) return 1;
    if (dp[mi][n] != -1) return dp[mi][n];
    long long sol = 0;
    for (int i = 0; i <= mi; i++) {
        if (n - moneyz[i] >= 0) sol += solve(n - moneyz[i], i);
    }
    return dp[mi][n] = sol;
}

//long long dp[30001];
//long long solve(int n, int k) {
    //return dp[n];
//}

int main() {
    memset(dp, -1, sizeof dp);
    //dp[0] = 1;
    //for (int k = 0; k < 5; k++) {
        //int m = moneyz[k];
        //for (int i = 0; i + m < 30001; i++) {
            //dp[i + m] += dp[i];
        //}
    //}
    int n;
    while(scanf(" %d", &n) == 1) {
        long long sol = solve(n, 4);
        if (sol > 1) printf("There are %lld ways to produce %d cents change.\n", sol, n);
        else printf("There is only 1 way to produce %d cents change.\n", n);
    }
}
