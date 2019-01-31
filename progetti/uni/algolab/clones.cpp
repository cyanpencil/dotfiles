#include <bits/stdc++.h>
#include <time.h>
using namespace std;

struct jedi {
    int a, b;
};

vector<jedi> j;

int n, m;
int selected;
bool provato = false, token = true, skip = false;

int provalo(int start_pos, int final_pos, int s) {
    int count = 1;
    //cout << "provo con " << start_pos  << " " << final_pos << endl;
    for (int l = 1; l < n; l++) {
	int i = (l+s) % n;
	if (start_pos <= final_pos) {
	    if (j[i].a >= start_pos && j[i].a <= final_pos) continue;
	    if (j[i].b >= start_pos && j[i].b <= final_pos) continue;
	    if (j[i].a > j[i].b && j[i].b >= start_pos) continue;
	    if (j[i].a > j[i].b && j[i].a <= final_pos) continue;
	    if (j[i].a < start_pos && j[i].b >= start_pos) continue;
	} else {
	    if (j[i].a <= final_pos) continue;
	    if (j[i].a >= start_pos) continue;
	    if (j[i].b >= start_pos) continue;
	    if (j[i].b <= final_pos) continue;
	    if (j[i].a > j[i].b && j[i].b >= final_pos) continue;
	}
	final_pos = j[i].b;
	count++;
    }
    return count;
}

bool smaller(int uno, int due) {
    if (j[uno].b < j[due].b) {
	if (j[uno].a > j[uno].b && j[due].a < j[due].b) return 0;
	if (j[uno].a > j[uno].b && j[due].a > j[due].b) return 1;
	if (j[uno].a < j[uno].b && j[due].a < j[due].b) return 1;
	if (j[uno].a < j[uno].b && j[due].a > j[due].b) return 1;
    } else {
	if (j[uno].a > j[uno].b && j[due].a < j[due].b) return 0;
	if (j[uno].a > j[uno].b && j[due].a > j[due].b) return 0;
	if (j[uno].a < j[uno].b && j[due].a < j[due].b) return 0;
	if (j[uno].a < j[uno].b && j[due].a > j[due].b) return 1;
    }
    return 0;
}

int main () {
    ios_base::sync_with_stdio(false);
    srand (time(NULL));
    int t;
    cin >> t;
    while (t--) {
	cin >> n >> m;
	int map [2*n], map2[2*n + 2];
	int count [2*n];
	memset (count, 0, sizeof count);
	j.clear();
	for (int i = 0; i < n; i++) {
	    int a, b;
	    cin >> a >> b;
	    j.push_back({a,b});
	    map2[2*i] = a;
	    map2[2*i + 1] = b;
	}
	map2[2*n] = 1;
	sort (map2, map2 + 2*n + 2);
	int maplen = 0;
	unordered_map <int, int> mm;
	for (int i = 0; i < 2*n; i++) {
	    if (i < 2*n -1 && map2[i] == map2[i+1]) continue;
	    map[maplen] = map2[i];
	    mm[map2[i]] = maplen;
	    maplen++;
	}
	for (auto jj : j) {
	    if (jj.a < jj.b) {
		count[mm[jj.a]]++;
		count[mm[jj.b]]--;
	    } else {
		count[mm[jj.a]]++;
		count[mm[1]]++;
		count[mm[jj.b]]--;
	    }
	}
	int mini = 99999999, minisum = 0;
	int sum = 0;
	for (int i = 1; i < maplen; i++) {
	    sum += count[i];
	    if (sum < mini) {
		minisum = i;
		mini = sum;
	    }
	    //cout << "somma " << sum << endl;
	}
	//cout << "minimo" << map[minisum] << " con somma " << mini << " " << minisum << endl;
	selected = map[minisum];
	//selected = (rand() % m) + 1;

	sort (j.begin(), j.end(), [](jedi a, jedi b) {
	  return a.b < b.b;
	});
	int m_count = 0;
	provato = false, token = true, skip = false;
	bool didit = false;
	for (int s = 0; s < n; s++) {
	    int start_pos = j[s].a, final_pos = j[s].b;

	    skip = false;
	    if (start_pos < final_pos) {
		if (!(start_pos <= selected && final_pos >= selected)) skip = true;
	    } else {
		//cout << final_pos - selected << endl;
		if (!(start_pos >= selected && final_pos >= selected)) {
		    //cout << "forse" << endl;
		    if (!(start_pos <= selected)) skip = true;
		}
	    }
	    // 250012504
	    // 624434323
	    // 124409294
	    if (skip) {
		continue;
	    }

	    //cout << " provando " << j[s].a << " " << j[s].b  << "(" << selected << ")" << endl;
	    int count = provalo(j[s].a, j[s].b, s);

	    m_count = max(m_count, count);
	    //cout << "ho provato con " << start_pos << " " << final_pos << endl;
	}
	//if (!didit) {
	    int min_s = -1;
	    for (int i = 0; i < n; i++) {
		if (j[i].a > selected) {
		    if (min_s == -1 || smaller(i , min_s)) {
			min_s = i;
		    }
		}
	    }
	    if (min_s != -1)  {
		    int a = provalo(j[min_s].a, j[min_s].b, min_s);

		    m_count = max(m_count, a);
	    }
	//}
	cout << m_count << endl;
    }
}

