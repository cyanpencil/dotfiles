#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


int rint() {
    int a;
    scanf(" %d", &a);
    return a;
}

int main() {
    int n, k, z = 1;
    while (scanf(" %d %d", &n, &k) == 2 && n != 0) {
        int s, p;
        long long sizes = 0;
        vector<pair< long long, long long > > v;
        for (int i =0; i < n; i++) {
            long long s, p;
            cin >> s >> p;
            v.push_back(make_pair(s, p));
        }
        sort(v.begin(), v.end());
        for (int i =0; i < k - 1; i++) {
            sizes += v[i].first;
            //sol += ( (double) v[i].first / (double) v[v.size() -1].first) * (double) v[v.size()-1].second;
        }
        double sol = 1e18;
        for (int i = k - 1; i < n; i++) {
            double t = (double) sizes * (double)(v[i].second / (double)v[i].first) + v[i].second;
            if (t < sol) {
                sol = t;
            }
        }
        printf("Case #%d: %.6lf\n", z, sol);
        z++;
    }

}
