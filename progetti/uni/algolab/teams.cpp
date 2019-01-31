
#include <bits/stdc++.h>

using namespace std;

int main () {
	long long t;
	//ios_base::sync_with_stdio(false);
	cin >> t;
	while (t--) {
		long long n, k;
		cin >> n >> k;
		long long skill[n];
		for (long long i = 0; i < n; i++) cin >> skill[i];

		long long mysum = 0;
		for (long long i = 0; i < n; i++) mysum += skill[i];

		vector<pair<long long, long long> > v;
		unordered_map<long long, unordered_map<long long, long long> > m;


		long long lol = 0;
		long long half = n/2;

		long long ohalf = n - half;
		long long bits[half], bots[ohalf];
		memset(bits, 0, sizeof bits);
		memset(bots, 0, sizeof bots);
		for (long long i = 0; i < pow(3,half); i++) {
			long long thesum = 0, themany = 0;

			//cout << "bits ";
			for (long long z = 0; z < half; z++) { // TODO 2018/10/28 22:37 -  ci arrivo ad half
				//cout << bits[z] << " ";
				}

			for (long long z = 0; z < half; z++) { // TODO 2018/10/28 22:37 -  ci arrivo ad half
				if (bits[z] == 1) themany++, thesum+= skill[z];
				if (bits[z] == 2) themany++, thesum-= skill[z];
			} //cout << " sum " << thesum << endl;

			v.push_back(make_pair(thesum, themany));

			bits[0]++;
			long long t = 0;
			while (t < half - 1 && bits[t] > 2) {
				bits[t] = 0;
				bits[t+1]++;
				t++;
			}

		}

		for (long long i = 0; i < pow(3,ohalf); i++) {
			long long thesum = 0, themany = 0;
			for (long long z = 0; z < ohalf; z++) { // TODO 2018/10/28 22:37 -  ci arrivo ad half
				if (bots[z] == 1) themany++, thesum+= skill[half + z];
				if (bots[z] == 2) themany++, thesum-= skill[half + z];
			} 


			auto found = m.find(thesum);
			if (found == m.end()) {
				unordered_map<long long, long long> ciao;
				ciao[themany] = 1;
				m[thesum] = ciao;
				//cout << "Una nuova somma " << thesum << endl;
			} else {
				auto found2 = m[thesum].find(themany);
				if (found2 == m[thesum].end()) {
					m[thesum][themany] = 1;
					//cout << "Un nuovo (" << thesum << " " << themany << ")" << endl;
				} else {
					m[thesum][themany]++;
					//cout << "Aggiungo uno a (" << thesum << " " << themany << ")" << endl;
				}
			}

			bots[0]++;
			long long t = 0;
			while (t < ohalf - 1 && bots[t] > 2) {
				bots[t] = 0;
				bots[t+1]++;
				t++;
			}
		}


		long long total = 0;

		for (auto p : v) {
			//cout << p.first << " " << p.second << " " << -p.first<< endl;
			//if (n - p.second == k) {
				//// TODO 2018/10/28 22:49 -  
				//if (p.first == 0) total++;
			//}
			if (half - p.second <= k) {
				auto found = m.find(0);
				if (found != m.end()) {
					//cout << "ho trovato uno che fa somma " << -p.first << ": ";
					for (auto it = m[(-1)*p.first].begin(); it != m[(-1)*p.first].end(); it++) {
						//cout << it->first << " " << it->second << ",";
						if (n - (p.second + it->first) <= k) {
							total += it->second;
						}
					}
					//cout << endl;
				}
			}
		}
		cout << total << endl;

		//for (auto it = m.begin(); it != m.end(); it++) {
			//cout << it->first << endl;;
			//for (auto at = it->second.begin(); at != it->second.end(); at++) {
				//cout << at->first << " " << at->second << endl;
			//}
		//}

		//cout << (m.find(0) == m.end());
	}
}
