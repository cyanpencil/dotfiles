#include <vector>
#include <cstring>
#include <iostream>
#include <fstream>
#include <queue>
#include <stack>

using namespace std;

int tests;
int n, m;
vector<int> adj[2000], edj[2000];
vector<bool> matchedge[2000];
int colore[2000];
vector<int> bipartito[2];
bool matchato[2000];
stack<int> sss;

#define LEFT 0
#define RIGHT 1

bool bicolour(int start) {
	queue<int> q;
	q.push(start);
	colore[start] = 0;
	
	
	while (q.size()) {
		int nodo = q.front();
		q.pop();
		for (int i = 0; i < adj[nodo].size(); i++) {
			if (colore[nodo] == colore[adj[nodo][i]]){
				return false;
			}
			else if (colore[adj[nodo][i]] == -1) {
				colore[adj[nodo][i]] =  1 - colore[nodo];
				q.push(adj[nodo][i]);
			}
		}
	}
	return true;
}

int Aug(int start, bool in_da_match) {
	if (colore[start] == RIGHT && !matchato[start]) {
		//ho trovato un augmenting path
		int prev = sss.top();
		matchato[prev] = true;
		sss.pop();
		while(sss.size()) {
			int nodo = sss.top();
			matchato[nodo] = true;
			
			int index = 0;
			int leftie = 0;
			if (colore[prev] == LEFT) leftie = prev;
			if (colore[nodo] == LEFT) leftie = nodo;
			for (index = 0; index < adj[leftie].size(); index++) {
				if (adj[leftie][index] == prev) {
					break;
				}
			}
			matchedge[leftie][index] = true;
			
//			printf("Matchiamo l'arco tra %d e %d\n", prev, nodo);
			sss.pop();
			prev = nodo;
		}
		return 3;
	}
	for (int i = 0; i < adj[start].size(); i++) {
		if (matchedge[start][i] == in_da_match) {
			sss.push(adj[start][i]);
			Aug(adj[start][i], !in_da_match);
			if (sss.size() == 0) return 0;
			sss.pop();
		}
	}
}


int find_augmenting() {
	
	for (int i = 0; i < bipartito[LEFT].size(); i++) {
		if (!matchato[bipartito[LEFT][i]]) {
			while(!sss.empty()) sss.pop();
			sss.push(bipartito[LEFT][i]);
			Aug(bipartito[LEFT][i], 0);
			while(!sss.empty()) sss.pop();
		}
	}
	
}

int main() {
	#ifndef ONLINE_JUDGE
	freopen("input.txt", "r", stdin);
	#endif
	
	scanf(" %d", &tests);
	for (int z = 1; z <= tests; z++) {

			//resetto tutto quanto
		for (int i = 0; i < 2000; i++) {
			adj[i].clear();
			edj[i].clear();
			matchedge[i].clear();
		}
		memset(colore, -1, sizeof colore);
		memset(matchato, 0, sizeof matchato);
		
			//leggo l'input del testcase
		scanf(" %d %d", &n, &m);
		int a, b;
		for (int i = 0; i < m; i++) {
			scanf(" %d %d", &a, &b);
			a--; b--;
			adj[a].push_back(b);
			edj[a].push_back(b);
			edj[b].push_back(a);
			matchedge[a].push_back(false);
//			adj[b].push_back(a);
		}
		
			//controllo se il grafo è bicolorabile
		bool possible = true;
		for (int i = 0; i < n; i++) {
			if (colore[i] == -1)
				if (!bicolour(i))
					possible = false;
		}
		if (!possible) {
			printf("Case #%d: Impossible\n", z);
			continue;
		}
		
			//divido i nodi in sinistri e destri
		bipartito[0].clear();
		bipartito[1].clear();
		for (int i = 0; i < n; i++) {
			bipartito[colore[i]].push_back(i);
		}
		
//		for (int i = 0; i < bipartito[0].size(); i++) {
//			cout << bipartito[0][i] << endl;
//		}
		find_augmenting();
		
		bool Z[2000];
		for (int i = 0; i < bipartito[LEFT].size(); i++) {
			if (!matchato[bipartito[LEFT][i]]) {
				queue<int> q;
				q.push(bipartito[LEFT][i]);
				bool vis[2000];
				memset(vis, 0, sizeof vis);
				vis[bipartito[LEFT][i]] = true;
				while (q.size()) {
					int nodo = q.front();
					q.pop();
					for (int j = 0; j < edj[nodo].size(); i++) {
						if (!vis[edj[nodo][j]]) {
							if (matchato[edj[nodo][j]] != matchato[nodo]){
								q.push(edj[nodo][j]);
								vis[edj[nodo][j]] = true;
								Z[edj[nodo][j]] = true;
							}
						}
					}
				}
			}
		}
		vector<int> sol;
		for (int i = 0; i < n; i++) {
			if (colore[i] == LEFT && !Z[i]) {
				sol.push_back(i + 1);
			}
			if (colore[i] == RIGHT && Z[i]) {
				sol.push_back(i + 1);
			}
		}
		
		if (sol.size() == n / 2) {
			printf("Case #%d: %d\n", z, sol.size());
			for (int i = 0; i < sol.size(); i++) {
				printf("%d ", sol[i]);
			}
			printf("\n");
		}
		else {
			printf("Case #%d: Impossible\n", z);
		}
	}
}
