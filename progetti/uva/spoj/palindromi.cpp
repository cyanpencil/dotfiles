#include <fstream>
#include <iostream>
#include <vector>
#include <string>
#include <cstring>
using namespace std;

int dp [1001][1001];
string str;

int pal(int s, int e) {
    if (s == e) return 1;
    if (s > e) return 0;
    if (dp[s][e] != -1) return dp[s][e];
    int sol;
    if (str.at(s) == str.at(e)) sol = pal(s + 1, e - 1) + 2;
    else sol = max(pal(s + 1, e), pal(s, e - 1));
    return dp[s][e] = sol;
}

int main() {
    cin >> str;
    memset(dp, -1, sizeof dp);
    printf("%d\n", pal(0, str.length() - 1));
}
