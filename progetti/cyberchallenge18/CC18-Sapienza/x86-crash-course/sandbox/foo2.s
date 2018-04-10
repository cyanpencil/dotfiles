# void foo(int* x) {
#     (*x)++;
# }

# void foo(int* x) {
#     ecx = primo parametro
#     *(ecx)++
# }

.globl foo

foo:
    movl 4(%esp), %ecx
    incl (%ecx)
    ret
