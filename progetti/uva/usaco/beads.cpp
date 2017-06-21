/*
ID: lucadb91
PROG: beads
LANG: C++    
*/
#include <iostream>
#include <fstream>
#include <string>
#include <cstring>

using namespace std;
int n;

int sum(int a, int k) {
    a += k;
    if (a > 0) return a % n;
    else return n - a;
}

int main() {	
//    freopen("beads.in", "r", stdin);
//    freopen("beads.out", "w", stdout);
    
    scanf(" %d", &n);
    char A[n];
    
    int Max = 0;
    int go[n];
    memset(go, 0, sizeof go);
    
    scanf(" %s", A);
    printf("%s", A);
    
    for (int i = 0; i < n; i++) {
        char color = A[i];
        int left = 0, right = 0;
        while (left < n) {
            if (A[sum(i, -1 * (left + 1))] != color && A[sum(i, -1 * (left + 1))] != 'w') break;
            left++;
        }
        while (right < n && right + left + 1 < n) {
            if (A[sum(i, 1 * (right + 1))] != color && A[sum(i, 1 * (right + 1))] != 'w') break;
            right++;
        }
        Max = max(Max, (left + right + 1) % (n + 1) );
    }
    printf("%d\n", Max);
    
    
    return 0;
}
