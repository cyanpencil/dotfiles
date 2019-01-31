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

int n, caso = 0; 
int cubi[500][6];

string fa []= {"front", "back", "left", "right", "top", "bottom"};

int convert(int f) {
    switch (f) {
        case 0: return 1;
        case 1: return 0;
        case 2: return 3;
        case 3: return 2;
        case 4: return 5;
        case 5: return 4;
    }
    return -1;
}



int main() {
    while (scanf(" %d", &n) == 1) {
        caso++;
        if (n == 0) break;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                cubi[n - 1 - i][j] = rint();
            }
        }
        int dp [100];
        pair<int, int> history[100][500];

        memset(dp, 0, sizeof dp);
        memset(history, -1, sizeof history);

        for (int i = 0; i < n; i++) {
            bool colors[100];
            memset(colors, 0, sizeof colors);
            for (int j = 0; j < 6; j++) {
                int top = cubi[i][j], bottom = cubi[i][convert(j)];
                if (!colors[bottom] && dp[bottom] + 1 > dp[top]) {
                    if (dp[bottom] != 0) colors[top] = true;
                    cout << "Coloro " << top << endl;
                    for (int k = 0; k < dp[bottom]; k++) {
                        history[top][k] = history[bottom][k];
                    }
                    history[top][dp[bottom]] = pair<int,int>(i, j);
                    dp[top] = dp[bottom] + 1;
                }
            }
        }

        int sol = 0, maxx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > sol) {
                sol = dp[i];
                maxx = i;
            }
        }
        cout << "Case #" << caso << endl;
        cout << sol << endl;
        for (int i = 0; i < n; i++) {
            if (history[maxx][i].first == -1) break;
            cout << history[maxx][i].first + 1 << " " << fa[history[maxx][i].second] << endl;
        }
        cout << endl;
    }
}
