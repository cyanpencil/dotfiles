#include <string.h>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

void foo(char v[4]) {
    //int* p = (int*)v;
    v[0] = 'a';
    //*p = (*p & 0x000000FF) << 24 |  
         //(*p & 0x0000FF00) << 8  | 
         //(*p & 0x00FF0000) >> 8  | 
         //(*p & 0xFF000000) >> 24;
}

int main() {
    string c = "ciao" - 2;
    cout << c << endl;
}
