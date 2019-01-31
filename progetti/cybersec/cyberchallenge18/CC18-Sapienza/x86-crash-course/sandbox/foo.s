# int foo(int x) {
#     return x+1;
# }

# int foo(int x) {
#     eax = primo parametro (indirizzo esp+4);
#     eax++;
#     return eax;
# }

.globl foo

foo:
    movl 4(%esp), %eax
    incl %eax
    ret
