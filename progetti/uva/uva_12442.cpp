#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


int g[50001];
bool visited[50001];

int dfs(int n) {
    visited[n] = 1;
    if (!visited[g[n]]) return dfs(g[n]) + 1;
    return 0;
}

int main() {
    int T;
    scanf(" %d", &T);
    for (int t = 0; t < T; t++) {
        int n;
        scanf(" %d", &n);
        for (int i = 0; i < n; i++) {
            int k, h;
            scanf(" %d %d", &k, &h);
            g[k] = h;
        }
        int sol = 0, soli = 0;
        memset(visited, 0, sizeof visited);
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            memset(visited, 0, sizeof visited);
            int partial = dfs(i);
            if (partial > sol) 
                sol = partial,
                soli = i;
        }
        printf("Case %d: %d\n", t + 1, soli);
    }

}
