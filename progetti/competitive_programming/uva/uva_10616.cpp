
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;


int nums[201];
int n, m, d, q;
int mat[20][10];

int mod(int a , int b) {
    if (a > 0) return a % b;
    else {
        return b - (abs(a) % b);
    }
}

int calc(int d, int m) {
    memset(mat, 0, sizeof mat);
    for (int i = 0; i < n; i++) {
        int num = nums[i];
        for (int k = m - 1; k >= 0; k--){
            for (int j = d - 1; j>= 0; j--) {
                if (mat[k][j] != 0) {
                    //int z = abs(j + num) % d;
                    int z = mod(j + num, d);
                    if (k < m - 1) {
                        mat[k + 1][z] += mat[k][j];
                    }
                }
            }
        }
        //cout << d<< endl;
        //cout << "Aggiungo a " << num % d << " 1 " << endl;
        mat[0][mod(num, d)] += 1;
    }
}

int main() {
    int s = 0;
    while(scanf("%d %d", &n, &q) == 2 && n > 0) {
        s++;
        cout << "SET " << s << ":"<<endl;
        for (int i = 0; i < n; i++) cin >> nums[i];
        for (int i = 0; i < q; i++) {
            cout << q << endl;
            cin >> d >> m;
            calc(d, m);
            //cout << "Query " << d << " con m = " << m << endl;
            //for (int k = 0; k < m; k++) {
                //for (int j = 0; j < d; j++) {
                    //cout << mat[k][j] << " ";
                //}
                //cout << endl;
            //}
            cout << "QUERY " << i + 1 << ": " << mat[m-1][0] << endl;
        }
    }
}
