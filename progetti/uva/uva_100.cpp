#include <iostream>
#include <sstream>
#include <fstream>
#include <cstring>
using namespace std;

int dp[400005];

int calc(int n) {
    if (n < 400005) {
        if (dp[n] != -1) return dp[n];
        if (n == 1) return 1;
        if (n % 2 == 1) return dp[n] = calc(3*n + 1) + 1;
        else return dp[n] = calc(n/2) + 1;
    }
    else {
        if (n % 2 == 1) return calc(3*n + 1) + 1;
        else return calc(n / 2) + 1;
    }
}

int main() {
    memset(dp, -1, sizeof dp);
    int i, j;
    string s;
    while (getline(cin, s)) {
        istringstream iss(s);
        iss >> i >> j;
        int M = 0;
        for (int k = min(i, j); k <= max(i, j); k++) {
            M = max(M, calc(k));
        }
        printf("%d %d %d\n", i, j, M);
    }
}
