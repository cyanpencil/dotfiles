#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int d[1000][1000];
vector< vector<int> > g;
int n, m, k;

int solve() {
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>> >, greater<pair<int, pair<int, int>> >  > q;
    //priority_queue<pair<pair<int, int>, int> > q;
    d[0][0] = g[0][0];
    q.push(make_pair(g[0][0], make_pair(0, 0)));
    while (q.size() > 0) {
        int a, b, c;
        a = q.top().second.first;
        b = q.top().second.second;
        c = q.top().first;
        q.pop();
        //if (d[a][b] != -1 && d[a][b] <= c) continue;
        //d[a][b] = c;
        if (a + 1 < n  && (d[a + 1][b] > c + g[a + 1][b] || d[a + 1][b] == -1)) {q.push(make_pair(c + g[a + 1][b], make_pair(a + 1, b))); d[a + 1][b] = c + g[a + 1][b];}
        if (a - 1 >= 0 && (d[a - 1][b] > c + g[a - 1][b] || d[a - 1][b] == -1)) {q.push(make_pair(c + g[a - 1][b], make_pair(a - 1, b))); d[a - 1][b] = c + g[a - 1][b];}
        if (b + 1 < m  && (d[a][b + 1] > c + g[a][b + 1] || d[a][b + 1] == -1)) {q.push(make_pair(c + g[a][b + 1], make_pair(a, b + 1))); d[a][b + 1] = c + g[a][b + 1];}
        if (b - 1 >= 0 && (d[a][b - 1] > c + g[a][b - 1] || d[a][b - 1] == -1)) {q.push(make_pair(c + g[a][b - 1], make_pair(a, b - 1))); d[a][b - 1] = c + g[a][b - 1];}
    }
    printf("%d\n", d[n - 1][m - 1]);
}

int main() {
    int z;
    scanf(" %d", &z);
    while(z--) {
        scanf(" %d %d", &n, &m);
        g.clear();
        memset(d, -1, sizeof d);
        for (int j = 0; j < n; j++) {
            vector<int> row;
            for (int i = 0; i < m; i++) {
                scanf(" %d", &k);
                row.push_back(k);
            }
            g.push_back(row);
        }
        solve();
    }
}
