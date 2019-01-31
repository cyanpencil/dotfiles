#include <stdio.h>


int fib(int n) {
    if (F[n] != 0) return F[n];
    if (n <= 1) return 1;
    int sol = fib(n - 1) + fib(n - 2);
    F[n] = sol;
    return F[n];
}

int main() {
    int a= 1, b = 1, c;
    for (int i = 2; i < 1000000; i++) {
	c = a + b;
	a = b;
	b = c;
    }
    printf("%d", fib(999999999));
}
