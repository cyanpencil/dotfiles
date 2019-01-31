#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;
typedef pair<int, int> ii;

int n, m, s, t;
vector<vector<int> > g, e;
vector<int> d;


int districa() {
    priority_queue<ii, vector<ii>, greater<ii> > q;
    q.push(ii(0, s));
    d.assign(n + 1, 1 << 30);
    d[s] = 0;
    while(q.size() > 0) {
        int j = q.top().second, k = q.top().first;
        q.pop();
        if (k > d[j]) continue;
        for (int i = 0; i < g[j].size(); i++) {
            if (k + e[j][i] < d[g[j][i]]) {
                d[g[j][i]] = k + e[j][i];
                q.push(ii(d[g[j][i]], g[j][i]));
            }
        }
    }
    return d[t];
}


int main() {
    int T;
    scanf(" %d", &T);
    for (int z = 1; z <= T; z++) {
        scanf(" %d %d %d %d", &n, &m, &s, &t);
        g.clear(); e.clear();
        g.resize(n + 1);
        e.resize(n + 1);
        for (int i = 0; i < m; i++) {
            int a, b, c;
            scanf(" %d %d %d", &a, &b, &c);
            g[a].push_back(b);
            g[b].push_back(a);
            e[a].push_back(c);
            e[b].push_back(c);
        }
        int sol = districa();
        printf("Case #%d: ", z);
        if (sol == 1 << 30) printf("unreachable\n");
        else printf("%d\n", sol);
    }
}
