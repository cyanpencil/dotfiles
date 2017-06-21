#include <cstring>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include <math.h>

using namespace std;

int main() {
    //freopen("input.txt", "r", stdin);
    //freopen("output.txt", "w", stdout);
    int n;
    while (scanf(" %d", &n) == 1 && n >= 0) {
        bool trovato = false;
        for (int i = sqrt(n) + 1; i > 0; i--) {
            bool sepofa = true;
            int r = n;
            for (int j = 0; j < i; j++) {
                if (r % i != 1) {
                    sepofa = false;
                    break;
                }
                r = ((r - 1) / i) * (i - 1);
            }
            if (r % i != 0) {
                sepofa = false;
            }
            if (sepofa) {
                printf("%d coconuts, %d people and 1 monkey\n",n, i);
                trovato = true;
                break;
            }
        }
        if (!trovato) {
            printf("%d coconuts, no solution\n", n);
        }
    }
}




