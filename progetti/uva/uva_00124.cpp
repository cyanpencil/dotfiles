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

char alph[40], cc[500];
int n, cn;
vector<vector <int> > maggiore;
vector<vector <int> > minore;
bool I[20];
vector<int> l;
vector<vector<int> > sol;

void prova(int pos) {
    if (pos == n) {
        sol.push_back(l);
        return;
    }
    for (int i = 0; i < n; i++) {
        if (I[i]) continue;
        if (maggiore[i].size() > pos) continue;
        bool ok = true;
        for (int j = 0; j < minore[i].size(); j++) {
            if (I[minore[i][j]]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            l[pos] = i;
            I[i] = true;
            prova(pos + 1);
            I[i] = false;
            l[pos] = -100;
        }
    }

}

int main() {


    bool already = false;
    string line, line2;

    getline(cin, line);
    getline(cin, line2);
    while (line.length() > 0) {

        if (already) cout << endl;

        already = true;

        for (int i = 0; i < line.length(); i++) {
            if (i % 2 == 0) {
                alph[i / 2] = line.at(i);
            }
        }

        n = (line.length() / 2) + 1;
        cn = (line2.length() / 2) + 1;

        for (int i = 0; i < line2.length(); i++) {
            if (i % 2 == 0) {
                cc[i / 2] = line2.at(i);
            }
        }

        maggiore.clear();
        minore.clear();
        maggiore.resize(n);
        minore.resize(n);

        for (int i = 0; i < cn / 2; i++) {
            int k, m;
            char c = cc[i*2 + 1];
            for (int j = 0; j < n; j++) {
                if (alph[j] == c)
                    k = j;
            }
            c = cc[i*2];
            for (int j = 0; j < n; j++) {
                if (alph[j] == c)
                    m = j;
            }
            maggiore[k].push_back(m);
            minore[m].push_back(k);
        }

        memset(I, 0, sizeof I);
        l.clear();
        l.resize(n);
        sol.clear();



        prova(0);



        vector<vector <char> > rsol;
        rsol.resize(sol.size());

        for (int i = 0; i < sol.size(); i++) {
            for (int j = 0; j < sol[i].size(); j++) {
                rsol[i].push_back(alph[sol[i][j]]);
            }
        }

        sort(rsol.begin(), rsol.end());

        for (int i = 0; i < rsol.size(); i++) {
            for (int j = 0; j < rsol[i].size(); j++) {
                cout << rsol[i][j];
            }
            cout << endl;
        }


        getline(cin, line);
        getline(cin, line2);
    }

}
