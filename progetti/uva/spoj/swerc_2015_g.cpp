#include <iostream>
#include <fstream>
#include <cstring>
#include <math.h>
#include <vector>
using namespace std;

vector< vector<int> > piles;
int dp [100][1000];
int n, k, nn, t, sol;

void calc(int r) {
    for (int i = piles[r].size() - 1; i >= 0; i--) {
        bool won = false;
        for (int m = 0; m <= k; m++) {
            if (i + m >= piles[r].size()) continue;
            if (piles[r][i + m] + i + m >= piles[r].size()) continue;
            if (dp[r][piles[r][i + m] + i + m] == 0) { won = true; break; }
        }
        dp[r][i] = won;
    }
}

int main() {
    freopen("input.txt", "r", stdin);
    scanf("%d %d", &n, &k);
    piles.clear();
    for (int i = 0; i < n; i++) {
        memset(dp, -1, sizeof dp);
        vector<int> row;
        scanf(" %d", &nn);
        while(nn--) scanf(" %d", &t), row.push_back(t);
        piles.push_back(row);
    }
    for (int m = 0; m < piles.size(); m++) calc(m);
    for (int m = 0; m < piles.size(); m++) sol = sol ^ dp[m][0];
    if (sol) cout << "Alice can win.\n";
    else cout << "Bob will win.\n";
}
