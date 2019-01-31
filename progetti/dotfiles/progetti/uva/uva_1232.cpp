#include <iostream>
#include <fstream>
#include <cstring>
#include <algorithm>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;



int rint() {
    int a;
    scanf(" %d", &a);
    return a;
}

//int main() {
    int c = rint();
    while(c--) {
        int n = rint();
        for (int i = 0; i < n; i++) {

        }
    }
//}

int getSum(int fw[], int i) {
   int sum = 0;
   // Fenwick's index start from 1
   i++;  
   
   while(i > 0) {
      sum += fw[i];
      // i & (-i)  returns the decimal value of last set digit
      // eg: if i = 12 (1100) then  i & (-i) will 4 (100)
      i -= i & (-i);
   }
   return sum;   
}  

// newVal will be updated to Fenwick and all its ancestor
void updateFW(int fw[], int n, int i, int newVal) { 
   // Fenwick's index start from 1
   i++;
   while (i <= n) {
      fw[i] += newVal;
      i += i & (-i);
   } 
}

// Build Fenwick's tree
int *constructFenwick(int a[], int n){ 
  int *fw = new int [n+1];
  for (int i = 0; i <= n; i++) fw[i] = 0;
  for (int i=0; i<n; i++) updateFW(fw, n, i, a[i]);
  return fw;
}

  
int main() {

    int a[100000];
    a[3] += 7;
    memset(a, 0, sizeof a);
    int n = 100000;
    int *fw = constructFenwick(a, n);

    int c = rint();
    while(c--) {
        int n = rint();
        for (int i = 0; i < n; i++) {
            int start = rint();
            int end = rint();
            int h = rint();

            int h_start = getSum(fw, start);
            int h_end = getSum(fw, end);
            a[start]

        }
    }

   cout<<getSum(fw, 4);
   updateFW(fw, n, 3, 7);
   cout<<"\nAfter update ";
   cout<<getSum(fw, 4) << "\n";
   return 0; 
}
