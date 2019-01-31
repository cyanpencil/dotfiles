#include <fstream>
using namespace std;

int main() {
    long long t [1000003];
    t[3] = 0; t[4] = 1;
    long long c = 1;
    long long s = 0;
    for (int i = 1; i < 1000001; i += 2) {
        s += c;
        t[i + 3] = t[i + 2] + (s);
        s += c;
        t[i + 4] = t[i + 3] + (s);
        c++;
    }
    int n;
    while (scanf(" %d", &n) == 1 && n >= 3) {
        printf("%lld\n", t[n]);
    }
}
