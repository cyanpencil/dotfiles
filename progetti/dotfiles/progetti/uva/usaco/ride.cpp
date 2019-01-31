/*
ID: lucadb91
PROG: ride
LANG: C++    
*/
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {	
    freopen("ride.in", "r", stdin);
    freopen("ride.out", "w", stdout);
    
    char a[100], b[100];
    scanf ("%s %s", a, b);
    int sum = 1;
    for (int i = 0; a[i] != '\0'; i++)
        sum *= (a[i] - 'A' + 1);
    int sam = 1;
    for (int i = 0; b[i] != '\0'; i++)
        sam *= (b[i] - 'A' + 1);
    if (sum % 47 == sam % 47)
        printf("GO\n");
    else 
        printf("STAY\n");
    
    return 0;

}
