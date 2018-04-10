# int foo(int x, int y) {
#     if (x>y) return x;
#     else return y;
# }

.globl foo

foo: # x <-> ecx, y <-> edx
    movl 4(%esp), %ecx
    movl 8(%esp), %eax
    cmpl 8(%esp), %ecx
    cmovgl %ecx, %eax
    ret
