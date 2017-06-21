#include <iostream>
#include <fstream>
#include <vector>
#include <cstring>
#include <math.h>
using namespace std;
typedef long long ll;
#define mod 2147483647

int n,a,s;
ll dp[2002][2002];
vector<int> p;

ll solve(int height, int base) {
    if (height >= n) return 1;
    if (dp[height][base] != -1) return dp[height][base];
    if (p[height] == base) return 0;
    ll sol = 0;
    bool left = p[height] < base;
    if (!(left && p[height + 1] < p[height]) && !(!left && p[height + 1] > p[height])) sol += solve(height + 1, p[height]);
    sol = sol % mod;
    if (!(left && p[height + 1] > base) && !(!left && p[height + 1] < base)) sol += solve(height + 1, base);
    sol = sol % mod;
    return dp[height][base] = sol;
}





int main() {
    cin >> n;
    memset(dp, -1, sizeof dp);
    cin >> s;
    for (int i = 0; i < n; i++) {
        cin >> a;
        p.push_back(a);
    }
    printf("%lld \n", pow(2, 30) - 1);
    cout << solve(0, s);
}
