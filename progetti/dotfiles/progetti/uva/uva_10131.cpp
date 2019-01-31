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

struct ele {
    int w, s, idx;
};

bool sorter(ele a, ele b) {return a.w > b.w;}

int main() {
    int w [1000], s[1000];
    ele e[1000];
    int idx = 0;
    while (scanf(" %d %d", &w[idx], &s[idx]) == 2) {
        e[idx].w = w[idx];
        e[idx].s = s[idx];
        e[idx].idx = idx;
        idx++;
    }

    sort(e, e + idx, sorter);

    for (int i = 0; i < idx; i++) {
        cout << e[i].w << " ";
    }

}
