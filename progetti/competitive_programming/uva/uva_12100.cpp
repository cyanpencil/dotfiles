
#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int main() {
    int t;
    cin >> t;

    while (t--){
        int n, m;
        cin >> n >> m;
        int jobs[n];
        queue<int> q;
        for (int i = 0; i < n; i++) {
            cin >> jobs[i];
            if (i == m)
                q.push(-1);
            else 
                q.push(jobs[i]);
        }


        int sol = 0;
        int mine = jobs[m];

        while(q.size() > 0) {
            int a = q.front();
            q.pop();
            int mmax = 0;
            for (int i = 0; i < n; i++) {
               mmax = max(mmax, jobs[i]);
            }
            if (a == -1 && mine >= mmax) break;
            else if (a >= mmax) {
                sol++;
                for (int i = 0; i < n; i++) {
                    if (jobs[i] == a) {
                        jobs[i] = -1000;
                        break;
                    }
                }
            }
            else q.push(a);
        }

        cout << sol + 1<< endl;
    }
}
