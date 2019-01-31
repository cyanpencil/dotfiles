
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int main() {
    int n;
    while(scanf(" %d", &n) == 1) {
        char l[30];
        for (int i =0; i < n; i++) {
            cin >> l[i];
        }
        double len = 0.0;
        double x1 = 1/2.0, x2=n - 1/2.0;
        if (l[0] == 'T') {
            for (int i =0; i < n; i++) {
                if (l[i] == 'S') {
                    x1 = i;
                    break;
                }
                if (l[i] == 'C') {
                    x1 = i + 1/2.0;
                    break;
                }
            }
            len += sqrt(pow(x1 - 1/2.0, 2) + pow(1 - sqrt(3)/2, 2));
        }
        if (l[n-1] == 'T') {
            for (int i =n-1; i >= 0; i--) {
                if (l[i] == 'S') {
                    x2 = i + 1;
                    break;
                }
                if (l[i] == 'C') {
                    x2 = i + 1/2.0;
                    break;
                }
            }
            len += sqrt(pow(n - 1/2.0 - x2, 2) + pow(1 - sqrt(3)/2, 2));
        }

        len += (x2 - x1);

        len += n - 1;
        for (int i =0 ; i < n; i++) {
            if (i == 0 || i == n - 1) {
                if (l[i] == 'T') {
                    len += 3.0/2;
                }
                if (l[i] == 'S') {
                    len += 2.0;
                }
                if (l[i] == 'C') {
                    len += M_PI/2.0;
                }
            }
        }
        printf("%lf\n", len);
    }
}
