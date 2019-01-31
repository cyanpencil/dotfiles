
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int main() {
    int t;
    cin >> t;
    unsigned long long f[45];
    f[0] = 1;
    f[1] = 1;
    f[2] = 2;
    for (unsigned long long j =3; j < 24; j++){
        f[j] = f[j - 1] * j;
    }
    for (int j =0; j < t; j++){
        char s [32];
        unsigned long long n;
        cin >> s;
        cin >> n;
        int ss = strlen(s);
        sort(s, s+strlen(s));
        for (int i = ss; i >= 1; i--) {
            int rapporto = (int) (n / f[i - 1]);
            if (rapporto > 0) {
                char z = s[ss - i];
                s[ss - i] = s[ss - i + rapporto];
                s[ss - i + rapporto] = z;
                if (i > 1) 
                    sort(s+ ss - i + 1, s + ss);
            }
            n = n % f[i - 1];
        }
        cout << s;
        cout << endl;
    }



}
