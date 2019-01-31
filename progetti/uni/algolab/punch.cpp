
#include <bits/stdc++.h>

using namespace std;

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		int n,k;
		cin >> n >> k;
		int cost[n], volume[n];
		int dp[n][2*k];
		int ds[n][2*k];
		memset(dp, 0x7f, sizeof dp);
		memset(ds, -1, sizeof ds);

		for (int i = 0; i < n; i++) {
			cin >> cost[i] >> volume[i];
		}

		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
			ds[i][0] = 0;
		}

		for (int j = 1; j*volume[0] < 2*k; j++) {
				dp[0][j*volume[0]] = j*cost[0];
				ds[0][j*volume[0]] = 1;
				//cout << dp[0][j*volume[0]] << endl;
		}

		//cout << "fine" << endl;

		bool presa[2*k];

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 2*k; j++) {
				dp[i][j] = dp[i-1][j];
				ds[i][j] = ds[i-1][j];
			}
			memset(presa, 0, sizeof presa);
			for (int j = 0; j < 2*k; j++) {
				if ((j + volume[i] < 2*k) &&
					((dp[i][j+volume[i]] > dp[i][j] + cost[i]) ||
					 (dp[i][j+volume[i]] == dp[i][j] + cost[i] && 
					  ds[i][j+volume[i]] < ds[i][j] + 1))) {

					  dp[i][j+volume[i]] = dp[i][j] + cost[i];
					  ds[i][j+volume[i]] = presa[j] ? ds[i][j] : ds[i][j] + 1;
					  presa[j+volume[i]] = 1;
				 }
			}
			//cout << "dp: ";
			//for (int j = 0; j < 2*k; j++) {
				//cout << dp[i][j] << " ";
			//}
			//cout << endl;
			//cout << "ds: ";
			//for (int j = 0; j < 2*k; j++) {
				//cout << ds[i][j] << " ";
			//}
			//cout << endl;
			//cout << "pr: ";
			//for (int j = 0; j < 2*k; j++) {
				//cout << presa[j] << " ";
			//}
			//cout << endl;

		}

		int min_cost = 0x7f7f7f7f;
		int max_bev = 0;

		for (int i = k; i < 2*k; i++) {
			if (dp[n-1][i] < min_cost) {
				min_cost = dp[n-1][i];
				max_bev = ds[n-1][i];
			} else if (dp[n-1][i] == min_cost) {
				max_bev = max(max_bev, ds[n-1][i]);
			}
		}

		cout << min_cost << " " << max_bev << endl;
    }
}
