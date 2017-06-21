#include <fstream>
using namespace std;

int main() {
    long long f[200];
    f[0] = 0; f[1] = 1; f[2] = 1;
    for (int i = 3; i < 100; i++) {
        f[i] = f[i - 1] + f[i - 2];
    }
    for (int i = 3; i < 100; i++) {
        printf("%d : %lld\n",i,  f[i]);
    }
}
