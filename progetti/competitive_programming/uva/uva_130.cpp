#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;



int main() {
    int n, k;
    while (scanf("%d %d", &n, &k) == 2 && n != 0) {
        k--;
        for (int j = 0; j < n; j++) {
            int r = n, you = 0;
            int i = j;
            bool alive = true;
            for (int z = 1; z < n; z++) {
                //printf("i:%d , you:%d,  r:%d, dies:%d\n", i, you, r, (i+k)%r);
                //ogni volta muore i + k.
                //se [i + 2k + 1] % r = you, allora cambia
                if ((i + k) % r == 0) {alive = false; break;}
                if ((((i + k) % (r)) + k)  % (r - 1) == 0) {
                    i = 1;
                }
                else {
                    if ((((i + k) % (r)) + k)  % (r - 1) < (i+k)%r) {
                        i--;
                    }
                    i += k + 1;
                    i = i % r;
                }
                r--;
            }
            if (alive) { printf("%d\n", j + 1);break;}
        }
    }
}


/*
1 2 3 4 5, k = 2
  x
1 4 3 5
      x
1   3 4
    x
    1 4
    x



1 2 3 4 5, k = 4
        x
1 2 3   4
        x
  2 3   1
  -------
1 2 3
  x
  1 3
  x


1 2 3 4 5, k = 4
    x  
1   2 4 5
    x
1   4   5
        x
1       4
      
 */
