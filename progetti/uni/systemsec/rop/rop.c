#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
char helpstr[20];
int test = -1;
void print_test(int *value) {
	printf("\nValue of test is %d\n", *value);
}

void cpybuf(char *src) {
	char buf[12];
	strcpy(buf, src);
}

int main(int argc, char **argv) {

	if (argc != 2) {
		printf("Usage: %s <input>\n", argv[0]);
		return 1;
	}
	setuid(0);

	printf("\nEnter your help string:");
	fgets(helpstr, 20, stdin);
	if (helpstr[strlen(helpstr) - 1] == '\n'
	    || helpstr[strlen(helpstr) - 1] == '\r') {
		helpstr[strlen(helpstr) - 1] = '\0';
	}

	printf("\nYour help string is %s\n", helpstr);

	cpybuf(argv[1]);

	printf("\n Did not get exploited...\n");
	print_test(&test);
	return 0;
}
