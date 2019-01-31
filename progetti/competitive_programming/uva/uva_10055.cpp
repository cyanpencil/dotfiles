#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;

int main() {
    long long n, m;
    string s;
    while (getline(cin, s)) {
        istringstream iss(s);
        iss >> n >> m;
        cout << ((m - n) > 0 ? m - n : n - m) << "\n";
    }
}
