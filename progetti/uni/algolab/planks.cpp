#include <bits/stdc++.h>
//#include "prettyprint.hpp"

using namespace std;
int n;
int p[20];
int totsum;
int goal;
vector<int> r;

//int dp[(1 << 20) - 1][21];
int dp[(1 << 20) - 1][21];
//unordered_map<pair<int,int>, int> dp;

int dpo(int z, int wow) {
	if (z == (1 << n) - 1) return 1;
	//if (wow > n) return 0;
	//if (dp[z][wow] != -1) return dp[z][wow];
	if (dp[z][wow] != -1) return dp[z][wow];
	//if (dp.find(make_pair(z,wow)) != dp.end()) return dp[make_pair(z,wow)];
	int count = 0;
	for (int i = wow; i < r.size(); i++) {
		if (z & r[i]) continue;
		count += dpo(z + r[i], i+1);
		//count += dpo(z + r[i]);
	}
	//return dp[z][wow] = count;
	return dp[z][wow] = count;
	//return dp[make_pair(z,wow)] = count;
}

int main () {
    int t;
    cin >> t;
    while (t--) {
		cin >> n;
		memset(dp, -1, sizeof dp);
		//dp.clear();
		totsum = 0;
		for (int i = 0; i < n; i++) {
			cin >> p[i];
			totsum += p[i];
		}
		if (totsum % 4 != 0) {
			cout << 0 << endl;
			continue;
		}
		goal = totsum/4;

		r.clear();
		int bits = 0;
		int partial;
		for (int i = 0; i < (1 << n); i++) {
			partial = 0;
			for (int j = 0; j < n; j++) {
				if (bits & (1 << j)) {
					partial += p[j];
				}
			}
			if (partial == goal) {
				r.push_back(bits);
			}
			bits++;
		}

		//int count = 0;
		//vector<int> split, splot;
		//for (int a = 0; a < r.size(); a++) {
			//for (int b = a + 1; b < r.size(); b++) {
				//if ((r[a] & r[b])) continue;
				//split.push_back(r[a] + r[b]);
			//}
		//}
		//for (int a = 0; a < r.size(); a++) {
			//for (int i = 0; i < split.size(); i++) {
				//if (r[a] & split[i]) continue;
				//splot.push_back(r[a] + split[i]);
			//}
		//}
		//for (int a = 0; a < r.size(); a++) {
			//for (int i = 0; i < splot.size(); i++) {
				//if (r[a] & splot[i]) continue;
				//count++;
			//}
		//}
		cout << dpo(0, 0) << endl;


		//for (int a = 0; a < split.size(); a++) {
			//for (int b = a + 1; b < split.size(); b++) {
				//if (split[a] & split[b]) continue;
				//count++;
			//}
		//}


		//int count = 0;
		//for (int a = 0; a < r.size(); a++) {
			//for (int b = a + 1; b < r.size(); b++) {
				//if ((r[a] & r[b])) continue;
				//for (int c = b + 1; c < r.size(); c++) {
					//if (r[a] & r[c] || r[b] & r[c]) continue;
					//for (int d = c + 1; d < r.size(); d++) {
						//if (r[a] & r[d] || r[b] & r[d] || r[c] & r[d]) continue;
						//count++;
					//}
				//}
			//}
		//}
		//cout << count << endl;
    }
}
