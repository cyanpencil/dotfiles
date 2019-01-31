
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


bool B[10000][10000];
int C[1000][1000];

vector<pair<pair<int, int>, int> > sol;
int l, c;

void prova(int i, int x, int y) {
    if (C[x][y] < i && C[x][y] != -1) {
        return;
    }
    C[x][y] = i;
    if (i > 0) sol.push_back(make_pair(make_pair(x, y), i));
    if (B[y + 1][x + 1] && ! B[y + 2][x + 2] && y + 2 < l && x + 2 < c){
        prova(i + 1, x + 2, y + 2);
    }
    if (B[y + 1][x]     && ! B[y + 2][x]     && y + 2 < l){
        prova(i + 1, x, y + 2);
    }
    if (B[y + 1][x - 1] && ! B[y + 2][x - 2] && y + 2 < l && x - 2 > 0){
        prova(i + 1, x - 2, y + 2);
    }
    if (B[y][x - 1] && ! B[y][x - 2] && x - 2 > 0){
        prova(i + 1, x - 2, y);
    }
    if (B[y][x + 1] && ! B[y][x + 2] && x + 2 < c){
        prova(i + 1, x + 2, y);
    }

    if (i == 0) {
        if (!B[y][x + 1] && y < l    && x + 1< c) {
            sol.push_back(make_pair(make_pair(x + 1, y), i + 1));
        }
        if (!B[y][x - 1] && y < l    && x - 1> 0) {
            sol.push_back(make_pair(make_pair(x - 1, y), i + 1));
        }
        if (!B[y + 1][x] && y + 1< l && x < c) {
            sol.push_back(make_pair(make_pair(x, y + 1), i + 1));
        }
    }
}

int main() {
    bool flag = false;
    while(scanf(" %d %d", &l, &c) == 2){
        if (flag) cout << endl;
        flag = true;
        int p = 2*c;
        c++ ;l++;
        memset(B, 0, sizeof B);
        memset(C, -1, sizeof C);
        sol.clear();
        for (int i = 0; i < 2*p; i++) {
            int x, y;
            cin >> y >> x;
            B[y][x] = 1;
        }
        int X, Y;
        cin >> Y >> X;
        prova(0, X, Y);

        for (int i = 0; i < sol.size(); i++) {
            int k = sol[i].first.first;
            sol[i].first.first = -sol[i].first.second;
            sol[i].first.second = k;
        }
        sort(sol.begin(), sol.end());
        for (int i = 0; i < sol.size(); i++) {
            if ( i < sol.size() - 1 && sol[i].first.first ==sol[i+1].first.first && sol[i].first.second ==  sol[i+1].first.second ){
                continue;
            }
            cout << -sol[i].first.first << " " << sol[i].first.second  << " " << sol[i].second << endl; 
        }
    }
}
