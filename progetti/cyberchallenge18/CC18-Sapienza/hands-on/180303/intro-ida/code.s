.globl _foo

_foo:
	xor %eax, %eax
	cmp %eax, %eax
	jnz	l2
	call l
	.string "hello"
l2:
	.byte 0x0
l:
	popl %eax
	ret


