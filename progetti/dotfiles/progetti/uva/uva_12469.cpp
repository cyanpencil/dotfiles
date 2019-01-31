#include <iostream>
#include <fstream>
#include <vector>
#include <math.h>
#include <cstring>
using namespace std;

int win[1001][1001];

bool prova(int s, int m) {
    if (win[s][m] != -1) return win[s][m];
    if (m >= s) return win[s][m] = 1;
    else {
        for (int i = 1; i <= m && i <= (s/3); i++) {
            if (s - i > 0) {
                if (prova(s - i, i*2) == 0) {
                    return win[s][m] = 1;
                }
            }
        }
    }
    return win[s][m] = 0;
}

int main() {
    int n;
    memset(win, -1, sizeof win);
    while (scanf(" %d", &n) == 1 && n >= 2) {
        if (prova(n, n - 1)) cout << "Alicia\n";
        else cout << "Roberto\n";
    }
}
