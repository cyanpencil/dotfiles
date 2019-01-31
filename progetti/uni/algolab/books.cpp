
#include <bits/stdc++.h>

using namespace std;

int n,m;
int s[500001];
int bb[500001];
int w[500001];
int start[500001];
int  done[500001];
bool made[500001];
bool dead[500001];

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		cin >> n >> m;
		for (int i = 0; i < n; i++) cin >> s[i];
		for (int i = 0; i < m; i++) cin >> w[i];
		//sort(s, s + n);
		sort(w, w + m);

		int maxxx = 0;
		for (int i = 0; i < n; i++) maxxx = max(maxxx, s[i]);
		if (w[m-1] > maxxx) {
			cout << "impossible" << endl;
			continue;
		}
		memset(made, 0, sizeof made);
		memset(dead, 0, sizeof dead);

		unordered_set<int> ss;
		vector<int> tokill;

		int moved = 0;
		for (int i = 0; i < n; i++) {
			start[i] = upper_bound(w, w+m, s[i]) - w - 1;
			done[i] = start[i];
			if (start[i] >= 0) {
				ss.insert(i);
			}
		}


		int z;
		for (z = 0; moved < m; z++) {
			for (auto i : ss)  {
				while (!dead[start[i]] && start[i] >= 0 && made[start[i]]) start[i]--;
				if (dead[start[i]])  {
					tokill.push_back(i);
					dead[done[i]] = true;
					continue;
				}
				if (start[i] >= 0) {
					made[start[i]] = 1;
					start[i]--;
					moved++;
				} else {
					tokill.push_back(i);
					dead[done[i]] = true;
				}
			}
			for (auto i : tokill) 
				ss.erase(ss.find(i));
			tokill.clear();
		}

		cout << 3*(z-1) + 2 << endl;


    }
}
