#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "xorshift.h"

const char * flag = "Giusto";
int main() {
	// seediamo in generatore casuale
	srand(time(NULL));

	int seed[1] = {random()};
	
	puts("A che numero sto pensando?\n");

	// printf("%d\n", xorshift32(seed));
	
	while (1) {
		int g = 0;
		// cast da uint a int
		int ans = xorshift32(seed);

		if (scanf("%d", &g)) {
			if (g == ans) {
				printf("%s\n", flag);
				fflush(stdout);
				break;
			}
			else {
				printf("No era %d\nProva ancora\n", ans);
				fflush(stdout);
			}
		}
		else {
			puts("No\n");
			break;
		}
	}
}
