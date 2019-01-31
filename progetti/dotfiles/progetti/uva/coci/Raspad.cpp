#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


int n, m;
char field[100001][51];
int calc(int a, int b) {
    //la prima linea
    int compts = 0;
    if (field[a][0] == '1') compts++;
    for (int j = 0; j < m - 1; j++) {
        if (field[a][j] == '0' && field[a][j+1] == '1') {
            compts++;
        }
    }

    //le altre righe
    for (int i = a + 1; i < b; i++) {
        for (int j = 0; j < m; j++) {
            if (i > 0 && j < m - 1 && field[i][j] == '1' && ((j > 0 && field[i][j-1] == '1')  || (field[i][j+1] == '1')) && field[i-1][j] == '0'&& field[i-1][j+1] == '1'){
                compts--; 
                continue;
            }
            if (j > 1 && field[i][j] == '1' && field[i][j-1] == '1'){
                continue;
            }
            if (field[i][j] == '1' && field[i-1][j] == '0') {
                compts++;
            }
        }
    }
    //cout << a << " " << b << " " << compts << endl;
    return compts;
}


int main() {
    scanf(" %d %d", &n, &m);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            scanf(" %c", &field[i][j]);
        }
    }

    int sol = 0;
    for (int a = 0; a <= n; a++) {
        for (int b = a + 1; b <= n; b++) {
            sol += calc(a, b);
        }
    }
    cout << sol << endl;

    cout << calc(0,2) << endl;
}
