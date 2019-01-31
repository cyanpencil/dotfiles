#include <bits/stdc++.h>

using namespace std;

int main () {
    int t;
    cin >> t;
    while (t--) {
	int n, m;
	cin >> n >> m;
	int b[m];
	int off[n][m];
	int on[n][m];
	int half = n/2;
	int ohalf = n - half;
	int howmany = 1 << half;
	int howomany = 1 << ohalf;
	vector<vector<int> > sums(howmany);
	vector<vector<int> > wows(howomany);
	for (int i = 0; i < m; i++) cin >> b[i];
	for (int i = 0; i < n; i++) {
	    int k = 0;
	    for (int z = 0; z < m; z++) {
		cin >> on[i][k] >> off[i][k++];
		b[z] -= (on[i][k - 1]);
	    }
	}
	//cout << "b" << endl;
	//for (int i = 0; i < m; i++) {
	    //cout << b[i];
	//}
	//cout << "b" << endl;
	for (int lol = 0; lol < howmany; lol++) {
	    sums[lol].resize(m+1);
	    int count = 0;
	    for (int l = 0; l < half; l++) {
		if (lol & (1<<l)) {
		    for (int i = 0; i < m; i++) {
			sums[lol][i] += off[l][i] - on[l][i];
		    }
		    count++;
		}
	    }
	    sums[lol][m] = count;
	    //for (int i = 0; i < m; i++) {
		//cout << sums[lol][i];
	    //}
	    //cout << endl;
	}
	for (int lol = 0; lol < howomany; lol++) {
	    wows[lol].resize(m+1);
	    int count = 0;
	    for (int l = 0; l < ohalf; l++) {
		if (lol & (1<<l)) {
		    for (int i = 0; i < m; i++) {
			wows[lol][i] += off[half + l][i] - on[half + l][i];
		    }
		    count++;
		}
	    }
	    wows[lol][m] = count;
	    //for (int i = 0; i < m; i++) {
		//cout << wows[lol][i];
	    //}
	    //cout << endl;
	}
	//for (int i = 0; i < howmany; i++) {
	    //for (int z = 0; z < m; z++) {
		//cout << sums[i][z] << " ";
	    //}
	    //cout << endl;
	//}
	//cout << endl;
	//for (int i = 0; i < howomany; i++) {
	    //for (int z = 0; z < m; z++) {
		//cout << wows[i][z] << " ";
	    //}
	    //cout << endl;
	//}
	//cout << endl;
	//cout << endl;

	//cout << wows.size() << endl;

	//cout << m+1 << endl;

	sort (wows.begin(), wows.end(), [m](const vector<int>& a, const vector<int>& b) {
	  //for (int i = 0; i < m && i < 2 && i < a.size() && i < b.size(); i++) {
		  //if (a[i] < b[i]) return true;
	  //}
	  //return false;
	  return a < b;
	});
	//for (int i = 0; i < howomany; i++) {
	    //for (int z = 0; z < m; z++) {
		//cout << wows[i][z] << " ";
	    //}
	    //cout << endl;
	//}
	//
	
	int mymin = 99999999;

	for (int left = 0; left < howmany; left++) {
	    vector<int> search;
	    //cout << "Searching for " << endl;
	    for (int i = 0; i < m; i++) {
		//cout << b[i] << "-";
		search.push_back(b[i] - sums[left][i]);
		//cout << search[i] << " ";
	    }
	    //cout << endl;
	    auto k = lower_bound(wows.begin(), wows.end(), search);
	    bool ok = false;
	    if (k != wows.end()) {
		ok = true;
		for (int i = 0; i < m; i++) {
		    //cout << wows[k - wows.begin()][i] << " ";
		    if (wows[k - wows.begin()][i] != search[i]) { //// TODO 2018/10/26 00:38 -  non controllare l'elemento m
			ok = false; 
			break;
		    }
		}
	    }
	    if (ok) {
		int c = sums[left][m] + wows[k - wows.begin()][m];
		mymin = min(mymin, c);
	    }
	    //cout << endl;
	    //cout << endl;
	}

	if (mymin != 99999999) cout << mymin << endl;
	else cout << "impossible" << endl;
    }
}
