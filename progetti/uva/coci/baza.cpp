#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int A[1001][1001];


int main() {
    int n, m, q;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> A[i][j];
        }
    }
    cin >> q;
    for (int i = 0; i < q; i++) {
        int Q[m];
        int sol = 0;
        for (int j = 0; j < m; j++) {
            cin >> Q[j];
        }
        for (int z = 0; z < n; z++) {
            bool okay = true;
            for (int j = 0; j < m; j++) {
                if (Q[j] == -1) continue;
                if (A[z][j] != Q[j]) {okay = false; break;}
            }
            if (okay) sol++;
        }
        cout << sol << endl;
    }
}
