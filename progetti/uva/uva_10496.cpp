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
    int t = rint(), s_x, s_y;
    while(t--) {
        int X = rint(), Y = rint();
        s_x = rint(), s_y = rint();
        int n = rint();
        int B[20][2];
        int D[21][21];
        memset(B, 0, sizeof B);
        memset(D, 0, sizeof D);
        for (int i = 0; i < n; i++) {
            B[i][0] = rint();
            B[i][1] = rint();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                D[i][j] = abs(B[i][0] - B[j][0]) + abs(B[i][1] - B[j][1]);
            }
            D[i][20] = abs(B[i][0] - s_x) + abs(B[i][1] - s_y);
            D[20][i] = abs(B[i][0] - s_x) + abs(B[i][1] - s_y);
        }

        int perm[n];
        for (int i = 0; i < n; i++) perm[i] = i;

        int MIN = 999999999;

        do {
            int s = 0;
            s += D[20][perm[0]];
            for (int i = 0; i < n - 1; i++) {
                s += D[perm[i]][perm[i + 1]];
            }
            s += D[perm[n - 1]][20];
            MIN = min(MIN, s);
        }
        while (next_permutation(perm, perm + n));

        cout << "The shortest path has length " <<  MIN << endl;

    }

}
