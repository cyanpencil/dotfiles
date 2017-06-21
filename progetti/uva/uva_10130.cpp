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
    int T;
    scanf(" %d", &T);
    while(T--) {
        int N, G;
        scanf(" %d", &N);
        int p [N], w [N];
        for (int i = 0; i < N; i++) scanf(" %d %d", &p[i], &w[i]);
        scanf(" %d", &G);
        int m [G];
        for (int i = 0; i < G; i++) scanf(" %d", &m[i]);
        
        
        int dp [1000];
        memset(dp, 0, sizeof dp);
        for (int j = 0; j < N; j++) {
            for (int k = 50; k >= 0; k--) {
                if (k == 0 || dp[k] != 0) {
                    if (dp[k + w[j]] == 0 || dp [k + w[j]] < dp[k] + p[j]) {
                        dp[k + w[j]] = dp[k] + p[j];
                    }
                }
            }
        }

        int ssol = 0;
        for (int i = 0; i < G; i++) {
            int sol = 0;
            for (int j = 0; j <= m[i]; j++) {
                sol = max(sol, dp[j]);
            }
            ssol += sol;
        }

        cout << ssol << endl;
    }
}
