
#include <bits/stdc++.h>

using namespace std;

int n,I,k;
int h[1000];
bool possible[1000][2];


int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		memset(possible, 0, sizeof possible);
		cin >> n >> I >> k;
		for (int i = 0; i < n; i++) cin >> h[i];

		int turn = 0;


		for (int i = 0; i < n; i++) {
			if (h[i] == 0) continue;
			int curr = turn%2;
			int next = (turn+1)%2;
			for (int j = 0; j < k; j++) {
				if (possible[j][curr]) {
					possible[j][next] = 1;
					possible[(j + h[i]) % k ][next] = 1;
				}
			}
			possible[(h[i])%k][next] = 1;

			//for (int i = 0; i < k; i++) {
				//cout << possible[i][curr] << " ";
			//}
			//cout << endl;
			//for (int i = 0; i < k; i++) {
				//cout << possible[i][next] << " ";
			//}
			//cout << endl;

			turn++;
		}

		//for (int i = 0; i < k; i++) {
			//cout << possible[i][turn] << " ";
		//}
		//cout << endl;
		//for (int i = 0; i < k; i++) {
			//cout << possible[i][(turn+1)%2] << " ";
		//}
		//cout << endl;

		if (possible[I][0] || possible[I][1]) cout << "yes" << endl;
		else cout << "no" << endl;
    }
}
