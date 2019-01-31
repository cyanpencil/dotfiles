#include <bits/stdc++.h>

using namespace std;

vector<vector<long long>> g;
vector<vector<long long>> s;
long long n,m,k;

long long dp[1000][4000];

long long dfs(long long node, long long mossa) {
	if (mossa == k) return 0;
	if (dp[node][mossa] != -1) return dp[node][mossa];

	long long mxscore = 0;
	for (int i = 0; i < g[node].size(); i++) {
		if (g[g[node][i]].size() == 0) {
			mxscore = max(mxscore, dfs(0, mossa+1) + s[node][i]);
		} else {
			mxscore = max(mxscore, dfs(g[node][i], mossa+1) + s[node][i]);
		}
	}

	return dp[node][mossa] = mxscore;
}

int main () {
    int t;
    cin >> t;
    while (t--) {
		g.clear();
		s.clear();

		long long x;
		cin >> n >> m >> x >> k;

		g.resize(n);
		s.resize(n);

		memset(dp, -1, sizeof dp);

		//printf ("%d %d %lld %d\n", n,m,x,k);

		for (int i = 0; i < m; i++) {
			long long u,v,p;
			cin >> u >> v >> p;
			//cout << u << " " << v << " " << p << endl;
			g[u].push_back(v);
			s[u].push_back(p);
		}



		int dogood = -1;

		//for (int i = k-1; i >= 0; i--) {
			//if (dp[0][i] == -1) continue;
			//cout << dp[0][i] << " ";
		//}
		//for (int i = k-1; i >= 0; i--) if (dp[0][i] >= x) {dogood = k - i;break;}
		for (int i = k-1; i >= 0; i--) {
			long long w = dfs(0,i);
			if (w >= x) {dogood = k-i; break;}
		}

		if (dogood == -1) cout << "Impossible" << endl;
		else cout << dogood << endl;


    }
}
