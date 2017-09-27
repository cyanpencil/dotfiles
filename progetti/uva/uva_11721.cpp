
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
#include <limits.h>
using namespace std;

vector<pair<int, pair<int, int> > > ed;
vector<vector<int> > adj;

int dist[2000];

void bellman_ford(int source, int n) {
    int P[4000];
    memset(dist, 0, sizeof dist);

    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < ed.size(); j++) {
            if (dist[ed[j].second.second] > dist[ed[j].second.first] + ed[j].first) {
                dist[ed[j].second.second] = dist[ed[j].second.first] + ed[j].first;
                P[ed[j].second.second] = ed[j].second.first;
            }
        }
    }

    bool sol[2000];
    memset(sol, 0, sizeof sol);
    bool found = false;
    for (int i = 0; i < ed.size(); i++) {
        if (dist[ed[i].second.second] > dist[ed[i].second.first] + ed[i].first) {
            found = true;
            bool visited[2000];
            memset(visited, 0, sizeof visited);

            queue<int> q;
            q.push(ed[i].second.second);
            visited[ed[i].second.second] = true;
            while(q.size() > 0) {
                int u = q.front();
                //cout << j << "-" << u  << "+" << incycle << endl;
                q.pop();
                if (sol[u]) continue;
                sol[u] = 1;
                for (int k = 0; k < adj[u].size(); k++) {
                    if (!visited[adj[u][k]]) {
                        q.push(adj[u][k]);
                        visited[adj[u][k]] = true;
                    }
                }
            }
        }
    }

    if (found) {
        for (int j = 0; j < n; j++)
            if (sol[j]) cout << " " << j ;
        cout << endl;
    }

    if (!found) cout << " impossible" << endl;
}



int main() {
    int t, n, m;
    scanf(" %d", &t);
    for (int z = 1; z <= t; z++) {
        scanf(" %d %d", &n, &m);
        ed.clear();
        adj.clear();
        adj.resize(n);
        for (int i = 0; i < m; i++) {
            int a, b, c;
            scanf(" %d %d %d", &a, &b, &c);
            ed.push_back(make_pair(c, make_pair(b, a)));
            adj[b].push_back(a);
        }



        cout << "Case " << z << ":";
        //bellman_ford(0, n + 1);
        bellman_ford(0, n);


    }

}
