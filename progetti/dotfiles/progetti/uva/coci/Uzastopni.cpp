
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int calc(int a, int b) {
    return (a + b) * (abs(a - b) + 1) / 2;
}


int main() {
    long long n;
    cin >> n;
    //cout << calc(1, 100) << endl;
    for (long long i = 1; i < 10*sqrt(2*n); i++) {
        double a = ((double)((double)2*(double)n)/((double)i + 1.0) - (double)i)/(2.0);
        if (ceil(a) == a && a > 0) {
            cout << (long long)a << " " << (long long)a + i << endl;
        }
    }
}
