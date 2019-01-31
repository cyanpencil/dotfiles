/*
ID: lucadb91
PROG: gift1
LANG: C++    
*/
#include <iostream>
#include <fstream>
#include <string>
#include <map>

using namespace std;

int main() {	
    freopen("gift1.in", "r", stdin);
    freopen("gift1.out", "w", stdout);

    map<string, int> mappa;
    
    int n;
    scanf(" %d", &n);
    char nomi [n][20];
    string nomis[n];
    for (int i = 0; i < n; i++) {
        scanf(" %s", nomi[i]);
        nomis[i] = nomi[i];
        mappa[nomis[i]] = 0;
    }
    
    int amount = 1, people = 1;
    char str[100];
    while (scanf(" %s", str) == 1) {
        string stronzo = str;
        scanf(" %d %d", &amount, &people);
        
        if (people > 0) {
            int monay = (int) (amount / people);
            mappa[stronzo] -= (monay * people);
//            mappa[stronzo] += (amount % people);
        
        for (int i = 0; i < people ; i++) {
            scanf(" %s", str);
            string stronzo = str;
            mappa[stronzo] += monay;
        }
        
        }
    }
    
    for (int i = 0; i < n; i++) {
        printf("%s %d\n", nomi[i], mappa[nomis[i]]);
    }
    
    return 0;
}
