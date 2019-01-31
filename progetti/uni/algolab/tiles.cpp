#include <bits/stdc++.h>

using namespace std;

int w, h;
bool field[98][15];
int dp[98][32768];

unordered_set<int> possibili;

int prova(int col, int row) {
	//cerr << "provo " << col << " " << row << endl;
	//cerr << h << w << endl;
	if (col >= h - 3) return 0;
	if (dp[col][row] != -1) return dp[col][row];

	int sol = 0;
	int i = 0;


	for (auto r : possibili) {
		int count = 0;
		for (i = 0; i < w-2; i++) {
			if (!(r & (1 << i))) continue;
			if ((!field[col][i]) || (!field[col+1][i])) {break;}
			if ((row & (1 << i))) {break;}
			count++;
		}
		if (i == w - 2) sol = max(sol, prova(col+1, r) + count/2);
	}

	return dp[col][row] = sol;
}

void riempi(int roba) {
	for (int i = 0; i < w-3; i++) {
		if ((!(roba & (1 << i))) && (!(roba & (1 << (i+1))))) {
			int wow = roba + (1 << i) + (1 << (i+1));
			if (possibili.find(wow) != possibili.end()) continue;
			possibili.insert(wow);
			riempi(wow);
		}
	}
}

int main () {
	ios_base::sync_with_stdio(false);
    int t;
	int k;
    cin >> t;
    while (t--) {
		cin >> h >> w;
		possibili.clear();
		possibili.insert(0);
		riempi(0);
		memset (dp, -1, sizeof dp);

		for (int j = 0; j < w; j++) cin >> k;
		for (int i = 0; i < h-2; i++) {
			cin >> k;
			for (int j = 0; j < w - 2; j++) {
				cin >> k;
				field[i][j] = k;
			}
			if (w > 1) cin >> k;
		}
		if (h > 1) for (int j = 0; j < w; j++) cin >> k;

		cout << prova(0,0) << endl;
    }
}
