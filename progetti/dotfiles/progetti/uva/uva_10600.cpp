#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;
typedef pair<int, int> ii;

vector<vector<int> > g, e;
vector<int> s;
int d[101];
int N, M;
vector<pair<int , ii> > es;

class UnionFind {
    public:
    vector<int> p, rank;
    UnionFind(int N) {rank.assign(N, 0);
        p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i;}
    int findSet(int i) {return (p[i] == i) ? i : (p[i] = findSet(p[i]));}
    bool isSameSet(int i, int j) {return findSet(i) == findSet(j);}
    void unionSet(int i, int j) {
        if (!isSameSet(i,j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) p[y] = x;
            else {
                p[x] = y;
                if (rank[x] == rank[y]) rank[y]++;
            }
        }
    }
};

int main() {
    int T;
    scanf(" %d", &T);
    while(T--) {
        scanf("%d %d", &N, &M);
        s.clear();
        es.clear();
        for (int i = 0; i < M; i++) {
            int a, b, c;
            scanf(" %d %d %d", &a, &b, &c);
            es.push_back(make_pair(c, ii(a, b)));
        }
        sort(es.begin(), es.end());
        UnionFind uf(20*M);
        int sol = 0, sol2 = 1 << 30;
        for (int i = 0; i < M; i++) {
            if (! uf.isSameSet(es[i].second.first, es[i].second.second)) {
                uf.unionSet(es[i].second.first, es[i].second.second);
                sol += es[i].first;
                s.push_back(i);
            }
        }
        for (int j = 0; j < s.size(); j++) {
            UnionFind us(20*M); 
            int sol3 = 0;
            for (int i = 0; i < M; i++) {
                if (i == s[j]) continue;
                if (! us.isSameSet(es[i].second.first, es[i].second.second)) {
                    us.unionSet(es[i].second.first, es[i].second.second);
                    sol3 += es[i].first;
                }
            }
            bool ok = true;
            for (int i = 1; i <= N; i++) if (! us.isSameSet(i, 1)) ok = false;
            if (ok) sol2 = min(sol2, sol3);
        }
        printf("%d %d\n", sol, sol2);
    }
}
