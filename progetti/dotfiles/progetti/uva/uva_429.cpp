#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
#include <string>
#include <map>
#include <sstream>
using namespace std;

map<string, int> m;
vector< vector<int> > g;
vector<int> d;
string z;

int editDistance(string s, string k) {
    if (s.size() != k.size() ) return -1;
    int sol = 0;
    for (int i = 0; i < s.size(); i++) {
        if (s.at(i) != k.at(i)) sol++;
    }
    return sol;
}

int bfs(int s, int t) {
    queue<int> q;
    int visited[m.size() + 4];
    memset(visited, -1, sizeof visited);
    q.push(s);
    visited[s] = 0;
    while (q.size() > 0) {
        int a = q.front(); q.pop();
        for (int i = 0; i < g[a].size(); i++) {
            if (visited[g[a][i]] == -1) {
                visited[g[a][i]] = visited[a] + 1;
                q.push(g[a][i]);
            }
        }
    }
    return visited[t];
}

int main() {
    int T;
    scanf(" %d", &T);
    getline(cin, z);
    getline(cin, z);
    while(T--) {
        string s,k;
        int counter = 0;
        bool adding = true;
        m.clear();
        while (getline(cin, z)) {
            if (z == "") break;
            istringstream iss(z);
            if (adding) {
                iss >> s;
                if (s == "*") {
                    adding = false;
                    g.clear(); g.resize(m.size() + 3);
                    for (map<string, int>::iterator it = m.begin(); it != m.end(); it++) {
                        for (map<string, int>::iterator bt = m.begin(); bt != m.end(); bt++) {
                            if (it == bt) continue;
                            if (editDistance(it->first, bt->first) == 1) {
                                int a = it->second, b = bt-> second;
                                g[a].push_back(b);
                                g[b].push_back(a);
                            }
                        }
                    }
                    continue;
                }
                m[s] = counter++;
            }
            else {
                iss >> s >> k;
                cout << s << " " << k << " " << bfs(m[s], m[k]) << endl;
            }
        }
        if (T > 0) cout << endl;
    }
}
