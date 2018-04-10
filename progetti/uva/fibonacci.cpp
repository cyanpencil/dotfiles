// Fibonacci: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
#include <stdio.h>
int F [10000000001];

int fib(int n) {
    if (F[n] != 0) return F[n];
    if (n <= 1) return 1;
    int sol = fib(n - 1) + fib(n - 2);
    F[n] = sol;
    return F[n];
}

int main() {
    printf("%d", fib(100000000));
    //int a = 1, b = 1, c;
    //for (int i = 0; i < 100000000; i++) {
	//c = a + b;
	//a = b;
	//b = c;
    //}
    //printf("%d", a);
}
