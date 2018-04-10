# int mystrlen(const char* s) { s <-> ecx
#     int cnt = 0; --> eax
#     while (*s++) cnt++;
#     return cnt;
# }

#     int cnt = 0 --> eax
# L:  char dl = *s
#     s++
#     if (dl == 0) goto E
#     cnt++
#     goto L
# E:  return cnt

.globl mystrlen

mystrlen:
    movl 4(%esp), %ecx
    xorl %eax, %eax    #     int cnt = 0; --> eax
L:  movb (%ecx), %dl   # L:  char dl = *s;
    incl %ecx          #     s++;
    cmpb $0, %dl
    je E               #     if (dl == 0) goto E;
    incl %eax          #     cnt++
    jmp L              #     goto L
E:  ret                # E:  return cnt;
