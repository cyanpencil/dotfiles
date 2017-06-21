#include <cstring>
#include <fstream>
#include <iostream>
#include <vector>
using namespace std;
typedef pair<int,int> ii;

char s[4][4];
char m[4][4];

ii solution;

ii dir[] = {ii(0,1), ii(0,-1), ii(1,1), ii(1,-1), ii(1, 0), ii(-1,1), ii(-1,-1), ii(-1, 0)};

int check_win() {
    bool patta = true;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            char c = s[i][j];
            if (c == '.') {
                patta = false;
                continue;
            }
            if (i > 1 && i < 4 && j > 1 && j < 4) continue;
            for (int d = 0; d < 8; d++) {
                bool ok = true;
                for (int k = 0; k < 4; k++) {
                    int x = i + k*dir[d].first;
                    int y = j + k*dir[d].second;
                    if (x >= 0 && x < 4 && y >= 0 && y < 4) {
                        if (s[x][y] != c) {
                            ok = false;
                            break;
                        }
                    }
                    else {
                        ok = false;
                        break;
                    }
                }
                //if (ok && c=='x') printf("Ho trovato una riga partendo da (%d, %d) e andando in direzione (%d, %d)\n", i, j, dir[i].first, dir[i].second);
                if (ok) {
                    if (c == 'o') return 0;
                    if (c == 'x') return 1;
                }
            }
        }
    }
    if (patta) return 2;
    return -1;
}

int prova() {
    int res = check_win();
    //printf("In questo caso, check_win=%d\n" ,res);
    if (res != -1) return res;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (s[i][j] == '.') {
                s[i][j] = 'x';
                if (check_win() == 1) {
                    solution = ii(i,j);
                    s[i][j] = '.';
                    return 1;
                }
                bool forced_win = true;
                for (int q = 0; q < 4 && forced_win; q++) {
                    for (int w = 0; w < 4 && forced_win; w++) {
                        if (s[q][w] == '.') {
                            s[q][w] = 'o';
                            //for (int z = 0; z < 4; z++) {
                                //for (int r = 0; r < 4; r++) {
                                    //cout << s[z][r];
                                //}
                                //cout << endl;
                            //}
                            //cout << endl;
                            //printf("Provo la 'o' in (%d, %d) con la 'x' messa in (%d, %d)\n", q, w, i, j);
                            int sol = prova();
                            if (sol != 1) {
                                forced_win = false;
                            }
                            s[q][w] = '.';
                        }
                    }
                }
                s[i][j] = '.';
                if (forced_win)  {
                    //printf("Ci sta una forced win in (%d,%d)\n", i,j);
                    solution = ii(i,j);
                    return 1; 
                }
            }
        }
    }
    return 0;
}

int main() {
    //freopen("input.txt", "r", stdin);
    char k;
    while(scanf(" %c", &k) == 1 && k == '?') {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                scanf(" %c", &s[i][j]);
            }
        }
        //bool risolto = false;
        //for (int i = 0; i < 4 && !risolto; i++) {
            //for (int j = 0; j < 4 && !risolto; j++) {
                //if (s[i][j] == '.') {
                    //s[i][j] = 'x';
                    //bool forced_win = true;
                    //for (int q = 0; q < 4 && forced_win; q++) {
                        //for (int w = 0; w < 4 && forced_win; w++) {
                            //if (s[q][w] == '.') {
                                //s[q][w] = 'o';
                                //int sol = prova();
                                //printf("Con i:%d, j:%d, q:%d, w:%d, ho sol:%d\n", i, j, q, w, sol);
                                //if (sol != 1) {
                                    //forced_win = false;
                                //}
                                //s[q][w] = '.';
                            //}
                        //}
                    //}
                    //if (forced_win) {
                        //risolto = true;
                        //printf("(%d,%d)\n", i, j);
                    //}
                    //s[i][j] = '.';
                    ////if (prova(i, j)) {
                    ////risolto = true;
                    ////printf("(%d,%d)\n", i, j);
                    ////}
                //}
            //}
        //}
        int res = prova();
        if (res != 1) printf("#####\n");
        else printf("(%d,%d)\n", solution.first, solution.second);
        //if (!risolto) printf("#####\n");
    }
}

