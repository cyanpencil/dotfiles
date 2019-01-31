#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
#include <set>
using namespace std;

int n;
int primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
set<int> ps;
bool used[17];
vector<int> nums;

void prova() {
    if (nums.size() == n) {
        for (int i = 0; i < nums.size(); i++) {
            if (i == n - 1) cout << nums[i];
            else cout << nums[i] << " ";
        }
        cout << endl;
        return;
    }
    for (int i = 2; i <= n; i++) {
        if (!used[i]) {
            if (ps.count(i + nums[nums.size() - 1]) && (nums.size() == n - 1 ? ps.count(i + 1) : true)) {
                    used[i] = true;
                    nums.push_back(i);
                    prova();
                    nums.erase(nums.end() - 1);
                    used[i] = false;
            }
        }
    }
}



int main() {
    for (int i = 0; i < 11; i++) ps.insert(primes[i]);
    nums.push_back(1);
    memset(used, 0, sizeof used);
    used[0] = true;
    used[1] = true;
    int z = 1;
    while (scanf(" %d", &n) == 1) {
        if (z > 1) printf("\n");
        printf("Case %d:\n", z++);
        prova();
    }
}
