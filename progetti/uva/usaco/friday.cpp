/*
ID: lucadb91
PROG: friday
LANG: C++    
*/
#include <iostream>
#include <fstream>
#include <string>
#include <cstring>

using namespace std;

int main() {	
    freopen("friday.in", "r", stdin);
    freopen("friday.out", "w", stdout);
    
    int n;
    scanf (" %d", &n);	/* the two input integers */
    
    int month[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
    int days[7];
    memset(days, 0, sizeof days);
    
    int y = 1900;
    int m = 0;
    int d = 1;
    int count = 1;
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 12; j++) {
            count += (13);
            days[ count % 7] ++;
            if (j != 1) {
                count += (month[j] - 13);
            }
            else {
                if (i == 100 || (i % 4 == 0 && i % 100 != 0)) {
                    count += 29 - 13;
                }
                else count += 28 - 13;
            }
        }
    }
    
    for (int i = 0; i < 6; i++) printf("%d ", days[i]);
    printf("%d\n", days[6]);
    
    
    
    
    
    return 0;
}
