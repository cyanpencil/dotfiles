
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

vector<vector<int> > T;
int marbles[20000];
int submarbles[20000];
int subtree[20000];
int father[20000];

int sons(int n) {
    int sol = 0;
    //if (subtree[n] != 0) return subtree[n] + 1;
    for (int i = 0; i < T[n].size(); i++) {
        sol += sons(T[n][i]);
    }
    return subtree[n] = sol + 1;
}

int themarb(int n) {
    int sol = 0;
    //if (submarbles[n] != 0) return submarbles[n] + 1;
    for (int i = 0; i < T[n].size(); i++) {
        sol += themarb(T[n][i]);
    }
    return submarbles[n] = sol + marbles[n];
}

int main() {
    int n;
    while(scanf(" %d", &n) == 1) {
        if (n == 0) break;
        T.clear();
        T.resize(n + 1);
        queue<int> q;
        memset(marbles, 0, sizeof marbles);
        memset(submarbles, 0, sizeof marbles);
        memset(subtree, 0, sizeof marbles);
        memset(father, 0, sizeof father);
        int moves = 0;
        for (int i = 0; i < n; i++) {
            int v, m, d;
            scanf(" %d %d %d", &v, &m, &d);
            for (int k = 0; k < d; k++) {
                int u;
                cin >> u;
                T[v].push_back(u);
                father[u] = v;
            }
            marbles[v] = m;
            if (d == 0) q.push(v);
        }

        for (int i = 1; i <= n; i++) {
            subtree[i] = sons(i);
            submarbles[i] = themarb(i);
        }

        int total = 0;
        for (int i = 1; i <= n; i++) 
            if (marbles[i] > 1)
                q.push(i);

        while(q.size() > 0) {
            int node = q.front();
            q.pop();
            //cout << "provo con " << node << " " << marbles[node] << endl;
            if (marbles[node] <= 1) continue;

            //prima i figli
            for (int i = 0; i < T[node].size(); i++) {
                if (subtree[T[node][i]] > themarb(T[node][i])) {
                    int much = min(subtree[T[node][i]] - submarbles[T[node][i]], marbles[node] - 1);
                    total += much;
                    marbles[T[node][i]] += much;
                    marbles[node] -= much;
                    q.push(T[node][i]);
                }
            }

            //poi i padri
            if (marbles[node] > 1) {
                int much = marbles[node] - 1;
                total += much;
                marbles[father[node]] += (marbles[node] - 1);
                marbles[node] = 1;
                //cout << "do " << much << " a mio padre" << endl;
                q.push(father[node]);
            }
        }

        cout << total << endl;
        //for (int i = 1; i <= n; i++) {
            //cout << i << " " << marbles[i] << endl;
        //}
    }
}
