#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int main() {
    int n,m, s, e, z=1;
    char c[2];
    int H [30][30], V[30][30];
    while (scanf(" %d", &n) == 1) {
        memset(H, 0, sizeof H);
        memset(V, 0, sizeof V);
        scanf(" %d", &m);
        for (int i = 0; i < m; i++) {
            scanf(" %s", &c);
            scanf(" %d %d", &s, &e);
            s--; e--;
            if (c[0] == 'H') H[e][s] = 1;
            else V[s][e] = 1;
        }
        if (z > 1) cout <<  endl << "**********************************" << endl;
        if (z > 1) cout << endl;
        cout << "Problem #" << z << endl << endl;

        bool found_something = false;
        for (int l = 1; l <= n; l++) {
            int count = 0;
            for (int s_x = 0; s_x <= n - l; s_x++) {
                for (int s_y = 0; s_y <= n - l; s_y++) {
                    bool ok = true;
                    for (int i = 0; i < l; i++) {
                        if (!H[s_x + i][s_y]) ok = false;
                    }
                    for (int i = 0; i < l; i++) {
                        if (!V[s_x][s_y + i]) ok = false;
                    }
                    for (int i = 0; i < l; i++) {
                        if (!H[s_x + i][s_y + l]) ok = false;
                    }
                    for (int i = 0; i < l; i++) {
                        if (!V[s_x + l][s_y + i]) ok = false;
                    }
                    if (ok) count++, found_something = true;
                }
            }
            if (count) cout << count << " square (s) of size " << l << endl;
        }
        if (!found_something) cout << "No completed squares can be found." << endl;
        z++;
    }
}
