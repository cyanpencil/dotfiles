
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
    while(t--) {
        long long l;
        cin >> l;

        long long o = 0;
        long long n = 0;
        for (int i = 0; i < 1000000; i++) {
            o = n;
            if (i < 10) {
                n += i;
            }
            else if (i < 100) {
                n += 9 + (i - 9) * 2;
            }
            else if (i < 1000) {
                n += 9 + (180) + (i - 99) * 3;
            }
            else if (i < 10000) {
                n += 9 + 180 + 2700 + (i - 999) * 4;
            }
            else if (i < 100000) {
                n += 9 + 180 + 2700 + 36000 + (i - 9999) * 5;
            }
            else if (i < 1000000) {
                n += 9 + 180 + 2700 + 36000 + 450000 + (i - 99999) * 6;
            }
            else if (i < 10000000) {
                n += 9 + 180 + 2700 + 36000 + 450000 + 5400000 +(i - 999999) * 7;
            }
            if (n >= l) {
                break;
            }
        }


        long long count = 0;
        n = 0;
        long long result = -1;
        for (int i = 0; i < 100000; i++) {
            count++;
            n += (long long) floor(log10(count) + 1);
            //cout << " count " << count << endl;
            if (n >= l - o) {
                break;
            }
        }

        //cout << "count" << count << " l " << l << " n " << n << " o " << o << endl;
        //cout << "length " << floor(log10(count)) + 1 << endl;
        //cout << "remain" << n - (l - o) << endl;
        //cout << "take cipher" <<  floor(log10(count)) - ((n - (l - o))) << endl;
        result = ((long long) (count / pow(10, floor(log10(count)) - ( floor(log10(count)) - (n - (l - o)) )))) % 10;

        //floor(log10(count)) + 1 = 4
        //n = o + 4

        //1234

        cout << result << endl;

    }

}
