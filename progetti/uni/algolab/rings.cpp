#include <bits/stdc++.h>

using namespace std;

struct boat {
    int pos;
    int len;
};

int main () {
    int t;
    cin >> t;
    while (t--) {
	int n;
	cin >> n;
	int p[n], l[n];
	vector<boat> b;
	for (int i = 0; i < n; i++) {
	    cin >> l[i] >> p[i];
	    b.push_back({p[i], l[i]});
	}
	sort (b.begin(), b.end(), [](boat a, boat b) {
	  //return ((a.pos - a.len) < (b.pos - b.len));
	  return a.pos < b.pos;
	});
	int count = 0;
	int final_pos = -9999999;
	for (int i = 0; i < n; i++) {
	    //cout << "prova " << i << " final " << final_pos << endl;
	    boat bb = b[i];
	    if (bb.pos - bb.len >= final_pos) {
		count++;
		final_pos = bb.pos;
		//cout << "ho scelto " << bb.pos << " final " << final_pos << endl;
	    }
	    else if (bb.pos >= final_pos) {
		int chosen = i;
		int pos_pos = max(final_pos,bb.pos - bb.len) + bb.len;
		for (int z = i + 1; z < n; z++ ) {
		    if (b[z].pos >= final_pos) {
			int prov_pos = max(final_pos, b[z].pos - b[z].len) + b[z].len;
			if (prov_pos < pos_pos) {
			    chosen = z;
			    pos_pos = prov_pos;
			    //cout << prov_pos << endl;
			}
		    }
		}
		final_pos = max(final_pos, b[chosen].pos - b[chosen].len) + b[chosen].len;
		i = chosen;
		count++;
		//cout << "ho scelto " << b[chosen].pos << "final " << final_pos<< endl;
	    }
	}
	cout << count << endl;
    }
}

