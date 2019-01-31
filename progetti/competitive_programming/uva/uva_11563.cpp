
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;



int main() {

    int c, n, a;
    while(scanf(" %d %d %d", &c, &n, &a) == 3) {
        int cache_size = 0;
        bool cache[100001];
        memset(cache, 0, sizeof cache);
        queue<int> q;


        int sol = 0;
        for (int i = 0; i < a; i++) {
            int o;
            cin >> o;
            if (!cache[o]) {
                if (cache_size == c) {
                    cache[o] = true;
                    cache[q.front()] = false;
                    q.pop();
                    q.push(o);
                    sol++;
                }
                else if (cache_size < c) {
                    cache[o] = true;
                    cache_size++;
                    q.push(o);
                    sol++;
                }
            }
        }

        cout << sol << endl;
    }
}
