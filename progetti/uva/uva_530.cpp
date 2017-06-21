#include <iostream>
#include <fstream>
#include <math.h>
using namespace std;

vector<int> primes;

bitset<1000000> bs;
void sieve(int n) {
    bs.set();
    bs[0] = 0; bs[1] = 0;
    for (int i = 2; i <= n; i++) if (bs[i]) {
        for (int j = i*i; j <= n; j *= i) bs[j] = 0;
        primes.push_back(i);
    }
}

long long bin (int n, int k) {
    if (primes.size() == 0) sieve(100);
    int p_n[100], a_n[100];
    
}


long long fact(long long n) {
    return n == 1 ? 1 : n * fact(n - 1);
}

int main() {
    long long n, k;
    while(scanf(" %lld %lld", &n, &k) == 2 && n != 0) {
        printf("%lld\n", sol);
    }
}
