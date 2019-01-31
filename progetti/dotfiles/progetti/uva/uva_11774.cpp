
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int p(unsigned long long a, unsigned long long b) {
    return (unsigned long long) pow(a, b);
}


int main() {
    unsigned long long n, m, t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        cin >> n >> m;
        unsigned long long  gcd = std::__gcd(m ,n);
        n /= gcd;
        m /= gcd;
        cout << "Case " << i + 1 << ": " <<n + m << endl;
    }

}
