
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
    int t;
    cin >> t;
    char l [400];
    memset(l, -1, sizeof l);
    l[0] = 'O';
    l[1] = 'I';
    l[2] = 'Z';
    l[3] = 'E';
    l[4] = 'A';
    l[5] = 'S';
    l[6] = 'G';
    l[7] = 'T';
    l[8] = 'B';
    l[9] = 'P';
    string s;
    getline(cin, s);
    for (int i = 0; i < t; i++) {
        getline(cin, s);
        while(s.size() > 0) {
            for (int i = 0; i < s.size(); i++) {
                char c = s.at(i);
                int n =s.at(i) - '0';
                if (c == ',' || c == '.' || c == ' ') {
                    cout << c;
                }
                else if (n >= 0 && l[n] != -1) {
                    cout << l[s.at(i) - '0'];
                }
                else {
                    cout << s.at(i);
                }
            }

            cout << '\n';

            getline(cin, s);
        }
        if (i == t - 1) {
            continue;
        }
        cout << '\n';
    }
}
