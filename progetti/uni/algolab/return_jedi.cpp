#include <bits/stdc++.h>

using namespace std;

int n,k;
vector<vector<int>> w;
vector<vector<int>> tg;
vector<vector<int>> tw;
int omax[1000][1000];
int origin;
bool visited[1000];

priority_queue<int> pq;

vector<int> P;

struct edge {
	int x, y, w;
};

bool cmp(edge e1, edge e2) {
	return e1.w < e2.w;
}

int getfather(int p) {
	int i = p;
	while (P[i] != i) {
		i = P[i];
	}
	return P[p] = i;
}

void dfs_impazzita(int node, int maxx) {
	omax[origin][node] = maxx;
	for (int i = 0; i < tg[node].size(); i++) {
		if (!visited[tg[node][i]]) {
			visited[tg[node][i]] = true;
			dfs_impazzita(tg[node][i], max(maxx, tw[node][i]));
		}
	}
}

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> n >> k;

		w.clear();
		w.resize(n);
		tg.clear();
		tw.clear();
		tg.resize(n);
		tw.resize(n);
		P.clear();
		for (int i = 0; i < n; i++) P.push_back(i);

		for (int i = 0; i < n; i++) w[i].resize(n);

		vector<edge> edges;

		for (int i = 0; i < n; i++) {
			w[i][i] = 0;
			for (int j = i; j < n; j++) {
				if (i == j) continue;
				int p;
				cin >> p;
				w[i][j] = p;
				w[j][i] = p;
				edge e1 {i,j,p};
				edges.push_back(e1);
			}
		}
		sort(edges.begin(), edges.end(), cmp);
		int totalcost = 0;
		bool presi[edges.size()] = {0};

		for (int i = 0; i < edges.size(); i++) {
			if (getfather(edges[i].x) == getfather(edges[i].y)) continue;
			totalcost += edges[i].w;
			presi[i] = true;
			tg[edges[i].x].push_back(edges[i].y);
			tg[edges[i].y].push_back(edges[i].x);
			tw[edges[i].x].push_back(edges[i].w);
			tw[edges[i].y].push_back(edges[i].w);
			P[getfather(edges[i].x)] = edges[i].y;
		}

		//for (int i = 0; i < edges.size(); i++) cout << presi[i];
		//cout << endl;

		for (int i = 0; i < n; i++) {
			memset(visited, 0, sizeof visited);
			origin = i;
			dfs_impazzita(i, -1);
		}

		int the_minimum = 999999999;

		for (int i = 0; i < edges.size(); i++) {
			if (presi[i]) continue;
			the_minimum = min(the_minimum, edges[i].w - omax[edges[i].x][edges[i].y]);
		}
		cout << totalcost + the_minimum << endl;
    }
}
