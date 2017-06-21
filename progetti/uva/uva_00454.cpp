#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    scanf(" %d\n", &n);
    for (int c = 0; c < n; c++) {
        vector<pair<string, int>> v;
        vector<string> vv;
        string s;
        int z = 0;
        while(getline(cin, s)) {
            if (s.size() == 0) break;
            v.push_back(make_pair(s, z));
            vv.push_back(s);
            z++;
        }
        for (int i = 0; i < v.size(); i++) {
            sort(v[i].first.begin(), v[i].first.end());
            int j = 0;
            while(j < v[i].first.size()) {
                if (v[i].first.at(j) != ' ') break;
                j++;
            }
            if (j > 0) v[i].first.erase(0, j);
        }
        sort(v.begin(), v.end());
        vector<string> res;
        //for (int i = 0; i < v.size(); i++) cout << v[i].first << endl;
        for (int i = 0; i < v.size(); i++) {
            int p = i + 1;
            vector<string> d;
            d.push_back(vv[v[i].second]);
            while (p < v.size() && v[i].first == v[p].first) {d.push_back(vv[v[p].second]);p++;}
            sort(d.begin(), d.end());
            for (int j = 0; j < d.size();j++) {
                for (int k = j + 1; k < d.size(); k++) {
                    //cout << d[j] << " = " << d[k] << endl;
                    res.push_back(d[j] + " = " + d[k]);
                }
            }

            i = p - 1;
                //if (v[i].first == v[p].first) {
                    //if (v[i].second < v[p].second) {
                        //cout << vv[v[i].second] << " = " << vv[v[p].second] << endl;
                    //}
                    //else {
                        //cout << vv[v[p].second] << " = " << vv[v[i].second] << endl;
                    //}
                //}
                //p++;
            //}
        }
        sort(res.begin(), res.end());
        for (int i = 0; i < res.size(); i++) cout << res[i] << endl;
        if (c < n - 1) cout << endl;
        //for (int <F4>
    }
}
