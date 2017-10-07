
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

int main() {
    int a, b, s, n, m;
    while (scanf(" %d %d %d %d %d", &a, &b, &s, &n, &m) == 5) {
        if (a == 0) break;
        double h = n*a;
        double v = m * b;
        double dist = sqrt(h*h + v*v);
        double V = dist / (double)s;
        double thesin = v  / dist;
        double aaa = asin(thesin)*180.0 / M_PI;
        printf("%.2lf %.2lf\n", aaa, V);
    }

}
