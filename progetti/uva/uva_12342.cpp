
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int main() {
    int T;
    cin >> T;
    for (int i = 0; i < T; i++) {
        int a;
        cin >> a;
        double b = 0;
        b += max(0., min(300000, (a - 180000)) * 0.10);
        b += max(0., min(400000, (a - 480000)) * 0.15);
        b += max(0., min(300000, (a - 880000)) * 0.20);
        b += max(0., (a - 1180000) * 0.25);
        int c = ceil(b);
        if (b <= 0)
            printf("Case %d: %d\n", (i+1), 0);
        else 
            printf("Case %d: %d\n", (i+1), max(c, 2000));

    }
}

