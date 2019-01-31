#include <bits/stdc++.h>
using namespace std;

int n;
int explode[100000];
bool disarmed[100000];
int totaltime = 0;

int deactivate(int bomb) {
	if (disarmed[bomb]) return 0;
	//cout << "provo a disattivare " << bomb << endl;


	int count = 0;

	if (2*bomb + 1 < n) {
		count += deactivate(2*bomb + 1);
	}
	if (2*bomb + 2 < n) {
		count += deactivate(2*bomb + 2);
	}
	disarmed[bomb] = 1;
	return count + 1;
}

int main() {
	int t;
	cin >> t;
	while(t--) {
		cin >> n;
		vector<pair<int, int> > v;
		for (int i = 0; i < n; i++) {
			cin >> explode[i];
			v.push_back(make_pair(explode[i], i));
			disarmed[i] = false;
		}
		sort(v.begin(), v.end());
		bool fail = false;
		totaltime = 0;
		for (auto p:v) {
			//cout << p.first << " " << p.second << endl;

			if (!disarmed[p.second]) {
				totaltime += deactivate(p.second);
				if (totaltime > explode[p.second]) {
					fail = true;
					break;
				}
			}
		}
		if (fail) cout << "no" << endl;
		else cout << "yes" << endl;
	}
}
