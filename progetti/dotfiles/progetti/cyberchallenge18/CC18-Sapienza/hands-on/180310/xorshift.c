#include "xorshift.h"
/* The state word must be initialized to non-zero */
uint32_t xorshift32(uint32_t state[static 1])
{
	/* Algorithm "xor" from p. 4 of Marsaglia, "Xorshift RNGs" */
	uint32_t x = state[0];
	x ^= x << 13;
	x ^= x >> 17;
	x ^= x << 5;
	state[0] = x;
	return x;
}

/* The state array must be initialized to not be all zero */
uint32_t xorshift128(uint32_t state[static 4])
{
	/* Algorithm "xor128" from p. 5 of Marsaglia, "Xorshift RNGs" */
	uint32_t s, t = state[3];
	t ^= t << 11;
	t ^= t >> 8;
	state[3] = state[2]; state[2] = state[1]; state[1] = s = state[0];
	t ^= s;
	t ^= s >> 19;	
	state[0] = t;
	return t;
}

/*
	int b[3];
	int c[16];
	int main() {
		int state[1] = {random()};
		for (int i = 0; i < 10000000; i++) {
			uint s = xorshift32(state);
			b[s%3]++;
		}
		double s = b[0] + b[1] + b[2];
		printf("%f %f %f\n", b[0] / s, b[1] / s, b[2] / s);

		for (int i = 0; i < 10000000; i++) {
			uint s = xorshift32(state);
			c[s%16]++;
		}
		s = 0;
		for (int i = 0; i < 16; i++) {
			printf("%f\n", c[i] / 10000000.0);
			c[i] = 0;
		}


		int state4[4] = {random(),random(),random(),random()};
		for (int i = 0; i < 10000000; i++) {
			uint s = xorshift128(state);
			c[s%16]++;
		}
		s = 0;
		for (int i = 0; i < 16; i++) {
			printf("%.2f ", c[i] / 10000000.0);
			c[i] = 0;
		}
		puts("\n");
		
		for (int i = 0; i < 10000000; i++) {
			c[random() % 16]++;
		}
		for (int i = 0; i < 16; i++) {
			printf("%.2f ", c[i] / 10000000.0);
			c[i] = 0;
		}
		puts("\n");
	}
*/