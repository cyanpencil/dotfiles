#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
#include <string>
#include <sstream>
#include <map>
using namespace std;
typedef pair<int, int> ii;


int M;
string z;
string L1, L2;
vector<pair<string, ii> > w;
map<string, int> m;



int main() {
    while(scanf(" %d", &M)) {
        w.clear(); m.clear();
        cin >> L1 >> L2;
        int num_languages = 0;
        for (int i = 0; i < M; i++) {
            string a, b, c;
            cin >> a >> b >> c;
            if (! m.count(a)) m[a] = num_languages++;
            if (! m.count(b)) m[b] = num_languages++;
            w.push_back(make_pair(c, ii(m[a], m[b])));
            cout << "Ho letto " << M << endl;
        }
    }
}
