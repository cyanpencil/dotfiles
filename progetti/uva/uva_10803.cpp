
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

vector<vector<int> > G;
vector<vector<double> > DD;

vector<pair<int, int> > city;

int main() {
    int N,n;
    scanf(" %d", &N);
    for (int z = 0; z < N; z++) {
        city.clear();
        G.clear();
        DD.clear();
        scanf(" %d", &n);
        for (int i = 0; i<  n; i++) {
            int a, b;
            scanf(" %d %d", &a, &b);
            city.push_back(make_pair(a, b));
        }

        G.resize(n);
        DD.resize(n);

        for (int i = 0; i<  n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double a = pow((double)(city[i].first - city[j].first), 2);
                double b = pow((double)(city[i].second - city[j].second), 2);
                //double dist = sqrt(pow((double)(city[i].first - city[j].first), 2) + pow((double)(city[i].second - city[j].second), 2));
                double dist = sqrt(a + b);
                if (dist <= 10) {
                    G[i].push_back(j);
                    DD[i].push_back(dist);
                }
            }
        }



        double D[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                D[i][j] = 9999999999;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < G[i].size(); j++) {
                D[i][G[i][j]] = DD[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }

        double M = -1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                M = max(M, D[i][j]);

        if (M == 9999999999) printf("Case #%d:\nSend Kurdy\n\n", z + 1);
        else printf("Case #%d:\n%.4f\n\n", z + 1, M);


    }


}
