#!/bin/python2.7
from pwn import *

io = process("./bomb")
io.recvline()
io.recvline()

io.sendline("Border relations with Canada have never been better.")
print io.recvline()
io.sendline("1 2 4 8 16 32")
print io.recvline()
io.sendline("0 207")
print io.recv(400)
io.sendline("1 0")
print io.recv(400)
io.interactive()
# io.sendline("flyers")
# print io.recv(400)


# io.interactive()

"""
two inputs, second one <= 14

eax = 2
edx = 14
esi = 0
edi = input

eax = edx
eax -= 0
ecx = eax (= edx = 14)
ecx >> 31
eax += ecx
eax >> 1 (arithmetic)
ecx = addrof (rax + rsi)
if (ecx <= edi) {
    eax = 0
    if (ecx >= edi) {
        end;
    }
    else {
        esi = addrof (rcx + 1)
        func4()
        eax = addrof (rax*2 + 1)
        end;
    }
}
else {
    edx = addrof (rcx - 1)
    func4()
    eax += eax
    end;
}
"""

