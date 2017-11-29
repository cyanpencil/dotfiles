
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
    long long n;
    while (scanf(" %lld", &n) == 1 && n != 0) {
        long long k = n*4 - 3;
        //cout << n << endl;
        long long sol = 0;
        for (long long i = 1; i < sqrt(k); i+=2) {
            if (k % i == 0) {
                //if ((k / i) % 2 == 1) {
                    //cout << i  << "  "<< k/i << " -> " << (i+1)/2 << "  " << (k/i + 1)/2 << endl;
                    sol += 2;
                //}
            }
        }
        cout << n << " " << sol << endl;
    }

}
