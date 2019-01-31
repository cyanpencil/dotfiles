
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
    cin >> n;
    long long fib[200];
    fib[0] = 0;
    fib[1] = 1;
    for (int i = 2; i < 70; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    while(n--) {
        long long a;
        cin >> a;
        cout << a << " = ";
        bool started = false;
        for (int i = 70; i > 1; i--) {
            if (a >= fib[i]) {
                cout << "1";
                a -= fib[i];
                started = true;
            }
            else if (started) {
                cout << "0";
            }
        }
        cout << " (fib)" << endl;
        //if (n >= 1) cout << endl;
    }

}
