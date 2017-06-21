#include <fstream>
#include <iostream>
#include <math.h>

using namespace std;

int main() {
    int n;
    while (scanf(" %d", &n) == 1) {
        long long sum = 0;
        for (int i = 0; i < n; i++) sum += (rand() % 6  + 1);
        cout << sum / (double) n << endl;
    }
}
