
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

vector<vector<int> > G;
vector<vector<int> > D;

bool visited[1100000];
int mmin = -1;
pair<int, pair<int, int> > worst;

pair<int, pair<int, int> >  dfs(int a, int b, pair<int, pair<int, int> >  mmin) {
    if (a == b) return mmin;
    pair<int, pair<int, int>> gh;
    for (int i = 0; i < G[a].size(); i++) {
        if (!visited[G[a][i]]) {
            visited[G[a][i]] = true;
            pair<int, pair<int, int> > ah;
            if (mmin.first > D[a][i]) {
                ah  = dfs(G[a][i], b, mmin);
            }
            else {
                ah  = dfs(G[a][i], b, make_pair(D[a][i], make_pair(a, G[a][i])));
            }
            if (ah.first > gh.first) gh = ah;
            visited[G[a][i]] = false;
        }
    }
    return gh;
}


int main() {
    int N;
    bool first = false;
    while (scanf(" %d", &N) == 1) {
        if (first) cout << endl;
        first = true;
        G.clear();
        D.clear();
        G.resize(N);
        D.resize(N);

        int original = 0;

        for (int i = 0; i < N - 1; i++) {
            int a , b, c;
            scanf(" %d %d %d", &a, &b, &c);
            a--; b--;
            G[a].push_back(b);
            G[b].push_back(a);
            D[a].push_back(c);
            D[b].push_back(c);
            original += c;
        }

        int k;
        scanf(" %d", &k);

        int total_cost = original;

        for (int i = 0; i < k; i++) {
            int a , b, c;
            scanf(" %d %d %d", &a, &b, &c);
            a--; b--;
            memset(visited, 0, sizeof visited);
            pair<int, pair<int, int> > gh = dfs(a, b, make_pair(-1000000, make_pair(0, 0)));
            cout << " al " << i << " ho trovato " << gh.first << endl;
            int oa = gh.second.first, ob = gh.second.second;
            if (gh.first > c) {
                //remove edge
                for (int j = 0; j < G[oa].size(); j++) {
                    if (G[oa][j] == ob) G[oa].erase(G[oa].begin() + j);
                }
                for (int j = 0; j < G[ob].size(); j++) {
                    if (G[ob][j] == oa) G[ob].erase(G[ob].begin() + j);
                }
                for (int j = 0; j < D[oa].size(); j++) {
                    if (D[oa][j] == ob) D[oa].erase(D[oa].begin() + j);
                }
                for (int j = 0; j < D[ob].size(); j++) {
                    if (D[ob][j] == ob) D[ob].erase(D[ob].begin() + j);
                }

                // add edge
                //G[a].push_back(b);
                //G[b].push_back(a);
                D[a].push_back(c);
                D[b].push_back(c);

                total_cost = total_cost + c - gh.first;
            }
        }

        cout << original << endl << total_cost << endl;

        int m;
        scanf(" %d", &m);

        for (int i = 0; i < m; i++) {
            int a , b, c;
            scanf(" %d %d %d", &a, &b, &c);
        }

    }
}
