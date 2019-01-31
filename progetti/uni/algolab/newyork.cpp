#include <bits/stdc++.h>
//#include "prettyprint.hpp"

using namespace std;

int n,m,k;
set<int> sol;
vector<vector<int>> sons;
multiset<int> mset;
int t[200000], p[200000], noleaf[200000] = {0};

int wow[200000];

void dfs (int node, int depth) {
	wow[depth] = node;
	mset.insert(t[node]);
	int removed = -1;
	if (mset.size() > m) {
		removed = t[wow[depth - m]];
		mset.erase(mset.find(removed));
	}
	if (mset.size() == m && abs((*(mset.begin())) - (*(mset.rbegin()))) <= k)
		sol.insert(wow[depth - m + 1]);

	for (int s : sons[node]) {
		dfs(s, depth + 1);
	}

	if (removed != -1) mset.insert(removed);
	mset.erase(mset.find(t[node]));
}

int main () {
	ios_base::sync_with_stdio(false);
    int T;
    cin >> T;
    while (T--) {
		sol.clear();
		cin >> n >> m >> k;
		sons.clear();
		sons.resize(n);
		for (int i = 0; i < n; i++) cin >> t[i];
		for (int i = 0; i < n-1; i++) {
			int u,v;
			cin >> u >> v;
			p[v] = u;
			noleaf[u] = 1;
			sons[u].push_back(v);
		}

		mset.clear();
		dfs(0,0);


		if (sol.size() == 0) cout << "Abort mission" << endl;
		else {
			for (auto i : sol) cout << i << " ";
			cout << endl;
		}
    }
}
