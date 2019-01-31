#include <iostream>
using namespace std;

int main() {
    int t;
    cin >> t;
    for (int n = 0; n < t; n++) {
        int N, p_min = 1000000, p_max = -1;
        cin >> N;
        for (int i = 0; i < N; i++) {
            int valore;
            cin >> valore;
            p_min = min(p_min, valore);
            p_max = max(p_max, valore);
        }
        cout << (p_max - p_min) * 2 << "\n";
    }
    return 0;
}
