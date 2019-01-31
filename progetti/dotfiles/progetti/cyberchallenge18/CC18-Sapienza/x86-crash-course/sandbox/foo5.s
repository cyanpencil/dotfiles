# int foo(int x, int y) {
#    return sum(x,y) + 1;
# }

.globl foo

foo: # x <-> ecx, y <-> edx
    movl 4(%esp), %ecx
    movl 8(%esp), %edx
    subl $8, %esp
    movl %ecx, (%esp)
    movl %edx, 4(%esp)
    call sum
    incl %eax
    addl $8, %esp
    ret
