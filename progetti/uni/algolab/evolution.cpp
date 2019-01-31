#include <bits/stdc++.h>
//#include "prettyprint.hpp"

using namespace std;

struct specie {
	string name;
	int age;
};

vector<specie> species;

bool cmp (int a, int b) {
	return species[a].age < species[b].age;
}

int main () {
	ios_base::sync_with_stdio(false);
    int t;
    cin >> t;
    while (t--) {
		species.clear();
		unordered_map<string, int> mappa;
		int n, q;
		cin >> n >> q;
		vector<vector<int>> ancestors(n);
		int P[50000];
		bool leave[50000];
		memset(leave, 1, sizeof leave);
		memset(P, -1, sizeof P);
		int foglia[50000] = {-1};
		for (int i = 0; i < n; i++) {
			string s; int a;
			cin >> s >> a;
			specie sample = {s,a};
			species.push_back(sample);
			mappa[s] = species.size() - 1;
			//foglia[species.size() - 1] = species.size() - 1;
		}
		for (int i = 0; i < n-1; i++) {
			string s, p;
			cin >> s >> p;
			int ss = mappa[s];
			int pp = mappa[p];
			//leave[pp] = false;
			P[ss] = pp;
		}
		for (int i = 0; i < n; i++) {
			//if (!leave[i]) {
				//continue;
			//}
			int current = i;
			while (P[current] != -1 && ancestors[i].size() < 200) {
				current = P[current];
				foglia[current] = i;
				ancestors[i].push_back(current);
			}
			//cout << species[i].name << " " << ancestors[i] << endl;
		}
		for (int i = 0; i < q; i++) {
			string s; int b;
			cin >> s >> b;
			int original = mappa[s];
			//int ss = leave[original] ? original : foglia[original];
			int ss = mappa[s];
			specie prova = {"", b};
			species.push_back(prova);

			while (species[ancestors[ss][ancestors[ss].size()-1]].age < b) {
				ss = ancestors[ss][ancestors[ss].size()-1];
			}

			int sol = lower_bound(ancestors[ss].begin(), ancestors[ss].end(), species.size()-1, cmp) - ancestors[ss].begin();

			if (ancestors[ss].size() > sol) {
				if (species[ancestors[ss][sol]].age > b) {
					if (sol > 0) cout << species[ancestors[ss][sol-1]].name << " ";
					else cout << species[ss].name << " ";
				} else {
					cout << species[ancestors[ss][sol]].name << " ";
				}
			}
			else cout << species[ss].name << " ";
			//cout << species[ss].name << " ";
			//for (int i = 0; i < ancestors[ss].size(); i++) {
				//cout << "(" << species[ancestors[ss][i]].name << " " << species[ancestors[ss][i]].age << ") - ";
			//}
			//cout << endl;


			species.erase(species.begin() + species.size()-1);
		}
		cout << endl;
    }
}
