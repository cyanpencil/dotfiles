#include <stdio.h>
#include "struct.h"

void foo();

void do_stuff(struct ARG_TYPE * arg, int k) {
	if (arg[k].flag == 0)
		printf("even [%d]: %s\n", arg[k].id, arg[k].str);
	else
		printf("odd [%d]: %s\n", arg[k].id, arg[k].str);
}

int main(int argc, char * argv[]) {

	if (argc <= 1)
		return -1;

	struct ARG_TYPE data[16];

	int k = 1;
	while (k < argc) {

		struct ARG_TYPE * t = &(data[k]); 
		t->id = k;
		t->str = argv[k];

		if (k % 2 == 0)
			t->flag = 0;
		else
			t->flag = 1;		

		do_stuff(data, k);
		k += 1;
	}

	foo();

	return 0;
}
