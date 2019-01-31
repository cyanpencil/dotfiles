# int foo(int x, int y) {
#     if (x>y) return x;
#     else return y;
# }

# int foo(int x, int y) {
#     if (x<=y)
#        goto E;
#     eax = x;
#     goto T;
# E:  eax = y;
# T:  return eax;
# }

.globl foo

foo: # x <-> ecx, y <-> edx
    movl 4(%esp), %ecx
    movl 8(%esp), %edx
    cmpl %edx, %ecx     #     // ecx <= edx?
    jle E               #     if (x<=y) goto E;
    movl %ecx, %eax     #     eax = x;
    jmp T               #     goto T;
E:  movl %edx, %eax     # E:  eax = y;
T:  ret                 # T:  return eax;
