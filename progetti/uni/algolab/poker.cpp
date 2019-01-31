#include <bits/stdc++.h>
//#include "prettyprint.hpp"

using namespace std;

int n;
int m[5];
int c[5][2000];
int *dp;
//map<vector<int>, int> mappa;

bool checkzero (vector<int> &s) {
	bool allzero = true;
	for (int i = 0; i < n; i++) {
		if (s[i] != -1) allzero = false;
	}
	return allzero;
}

int hosh(vector<int> &s) {
	int sum = 0;
	int mul = 1;
	for (int i = n-1; i >= 0; i--) {
		sum += (s[i]+1)*mul;
		mul *= (m[i]+1);
	}
	//if (sum < 0) cout << s << endl;
	return sum;
}

int solve(vector<int> &s) {
	int h = hosh(s);
	if (h == 0) return 0;
	if (dp[h] != -1) {
		//cout << "found " << dp[h] << " with hash " << h << endl;
		return dp[h];
	}
	//if (checkzero(s)) return 0;
	int maxsol = -10;
	int bits = 0;
	bool sel[n] = {0};
	for (int i = 0; i < (1 << n); i++) {
		for (int z = 0; z < n; z++) {
			if (s[z] >= 0 && bits & (1 << z)) {
				sel[z] = 1;
			} else {
				sel[z] = 0;
			}
			int color = -1;
			bool possible = true;
			for (int x = 0; x < n; x++) {
				if (!sel[x] || s[x] == -1) continue;
				if (color == -1) color = c[x][s[x]];
				else if (c[x][s[x]] != color) {
					possible = false;
					break;
				}
			}
			if (possible) {
				int counter = 0;
				for (int x = 0; x < n; x++) {
					if (sel[x]) {s[x]--; counter++;}
				}
				if (counter == 0) continue;
				int parsol = solve(s) + (counter > 1 ? (1 << (counter - 2)) : 0);
				for (int x = 0; x < n; x++) {
					if (sel[x]) {s[x]++;}
				}
				maxsol = max(maxsol, parsol);
				//cout << newvec << " " << counter << " " << parsol << endl;
			}
		}
		bits++;
	}
	return dp[h] = maxsol;
}


int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> n;
		for (int i = 0; i < n; i++) cin >> m[i];
		for (int i = 0; i < n; i++) 
			for (int z = 0; z < m[i]; z++) cin >> c[i][z];
		int no = n;
		for (int i = 0; i < n; i++) no*=(m[i]+1);
		dp = (int*)malloc(no*(sizeof(int)));
		memset(dp, -1, 4*no);
		vector<int> bellazio;
		for (int i = 0; i < n; i++) bellazio.push_back(m[i]-1);
		cout << solve(bellazio) << endl;
    }
}
