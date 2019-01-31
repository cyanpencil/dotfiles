
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
    int n[10];
    while(scanf(" %d", &n[0]) == 1) {
        for (int i = 1; i < 10; i++) {
            n[i] = rint();
        }

        bool ciao = true;
        for (int i = 0; i < 5; i++) {
            if (!n[i] xor n[i +5]) {
                ciao = false;
            }
        }
        if (ciao) cout << "Y" << endl;
        else cout << "N" << endl;
    }

}
