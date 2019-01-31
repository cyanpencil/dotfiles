#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int iii = 0;
char word[5001];
char nope[5001];
int nn[5001][3];

int calc(int a, int b, int c) {
    //cout << a << b << c << endl;
    if (a == 0 && b == 0 && c == 0) {
        cout << word << endl;
        return true;
    }
    if (nn[iii][0] > b + c) return false;
    if (nn[iii][1] > a + c) return false;
    if (nn[iii][2] > a + b) return false;
    bool z = false;
    if (true && a > 0 && nope[iii] != 'a') {
        word[iii] = 'a';
        iii++;
        z = calc(a - 1, b, c);
        iii--;
    }
    if (!z && b > 0 && nope[iii] != 'b') {
        word[iii] = 'b';
        iii++;
        z = calc(a, b - 1, c);
        iii--;
    }
    if (!z && c > 0 && nope[iii] != 'c') {
        word[iii] = 'c';
        iii++;
        z = calc(a, b, c - 1);
        iii--;
    }
    return z;
}

int main() {
    int N, A = 0, B = 0, C = 0;
    cin >> N;
    char c;
    for (int i = 0; i < N; i++) {
        cin >> c;
        if (c == 'a') A++;
        if (c == 'b') B++;
        if (c == 'c') C++;
    }
    cin >> nope;
    memset(nn, 0, sizeof nn);
    for (int i = N-1; i >= 0; i--) {
        if (i < N-1) {
            for (int j = 0; j < 3; j++) nn[i][j] = nn[i+1][j];
        }
        nn[i][(int) (nope[i] - 'a')]++;
    }
    calc(A, B, C);
}
