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

    int n = 16;
    char country [16][100];
    double p[16][16];
    memset(p, 0, sizeof p);

    for (int i = 0; i < n; i++) {
        scanf("%s", &country[i]);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int a;
            scanf(" %d", &a);
            p[i][j] = (double)a / 100.0;
        }
    }

    double round[17][16];
    memset(round, 0, sizeof round);
    for (int i = 1; i < 17; i++) {
        for (int j = 0; j < n; j++) {
            round[i][j] = 0;
        }
        round[i][i - 1] = 1.0;
    }

    double next_round[17][16];
    for (int k = 0; k < 4; k++) {
        for (int i = 1; i <= n / 2; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int z = 0; z < n; z++) {
                    if (z == j) continue;
                    if (round[2*i][j] == 0)     sum += (p[j][z] * round[2*i][z]);
                    if (round[2*i - 1][j] == 0) sum += (p[j][z] * round[2*i - 1][z]);
                    //if (k == 1 && i == 1 && j == 0) {
                        //cout << "confronto con " << z << endl;
                        //cout << "aggiungo" << p[j][z] << " * " << round[2*i][z] << endl;
                        //cout << "aggiungo" << p[j][z] << " * " << round[2*i - 1][z] << endl;
                        //cout << "sum " << sum << endl;
                    //}
                }
                next_round[i][j] = (round[2*i - 1][j] + round[2*i][j])  * sum;
            }
        }
        memset(round, 0, sizeof round);
        for (int i = 1; i < 17; i++) {
            for (int j = 0; j < n; j++) {
                round[i][j] = next_round[i][j];
            }
        }

    }

    for (int i = 0; i < n; i++) {
        //cout << next_round[1][i] << endl;
        printf("%-10s p=%.2lf\%\n", country[i], next_round[1][i]* 100.0);
    }
}
