
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
    int n;
    while (scanf(" %d", &n) == 1) {
        int people[n];
        for (int i = 0; i< n; i++) people[i] = rint();
        sort(people, people + n);

        int m1 = 0, m2 = 0;
        for (int i = 0; i< n - 1; i+=2) m1 += min(abs(people[i] - people[i+1]), 24 - abs(people[i] - people[i+1]));
        for (int i = 1; i< n - 1; i+=2) m2 += min(abs(people[i] - people[i+1]), 24 - abs(people[i] - people[i+1]));
        m2 += min(abs(people[0] - people[n-1]), 24 - abs(people[0] - people[n-1]));
        cout << min(m1, m2) << endl;
    }

}
