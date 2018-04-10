	.file	"struct.c"
	.text
	.globl	sum
	.type	sum, @function
sum:
.LFB0:
	.cfi_startproc
	movl	8(%esp), %eax
	addl	4(%esp), %eax
	ret
	.cfi_endproc
.LFE0:
	.size	sum, .-sum
	.globl	foo
	.type	foo, @function
foo:
.LFB1:
	.cfi_startproc
	movl	4(%esp), %eax
	movl	8(%esp), %edx
	movl	%edx, (%eax)
	movl	12(%esp), %edx
	movl	%edx, 4(%eax)
	ret	$4
	.cfi_endproc
.LFE1:
	.size	foo, .-foo
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04.3) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
