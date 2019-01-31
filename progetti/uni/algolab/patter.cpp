
#include <bits/stdc++.h>

using namespace std;

int n,k,x;
int dp[200001][2];
bool mem[200001];

int dfs(int block, int reverse) {
	if (block < 0) return 0;
	if (dp[block][reverse] != -1) return dp[block][reverse];
	int count = 0;
	for (int i = 0; i < k; i++) {

		if ((x & (1 << i))) {
			if (mem[k*block + k - i - 1]) {
				if (!reverse) count++;
			} else {
				if (reverse) count++;
			}
		} else {
			if (!mem[k*block + k - i - 1]) {
				if (!reverse) count++;
			} else {
				if (reverse) count++;
			}
		}

		//if (reverse) {
			//if ((x & (1 << i) == 0) == mem[k*block + k - i - 1]) count++;
		//} else {
			//if ((x & (1 << i) == 0) != mem[k*block + k - i - 1]) count++;
		//}
	}

	int a = dfs(block - 1, reverse) + (k - count);
	int b = dfs(block - 1, !reverse) + (count + 1);

	return dp[block][reverse] = min(a,b);
}

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> n >> k >> x;
		memset(dp, -1, sizeof dp);
		for (int i = 0; i < n; i++) cin >> mem[i];

		cout << dfs(n/k-1, 0) << endl;
    }
}
