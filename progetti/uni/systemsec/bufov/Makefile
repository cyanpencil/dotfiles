SHELL = /bin/sh
CFLAGS = -Wall -no-pie -static -fno-stack-protector --param asan-stack=0 -mpreferred-stack-boundary=2 -u system
CC = gcc

BIN=envadd

default: all

all: $(BIN)

envadd: envadd.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f $(BIN)
