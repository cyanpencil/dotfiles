SHELL = /bin/sh
CFLAGS = -Wall -no-pie -fno-stack-protector --param asan-stack=0 -mpreferred-stack-boundary=2 
CC = gcc
all = rop

default: $(all)

rop: rop.c
	$(CC) $(CFLAGS) $< -o $@
	sudo chown root  $@
	sudo chmod u+s $@

clean:
	rm -f $(all)
