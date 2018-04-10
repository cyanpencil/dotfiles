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
io.sendline("\x09\x0F\x0E\x05\x06\x07")
print io.recv(400)


# io.interactive()

"""
tutti i numeri <= 6
ogni numero diverso da tutti quelli successivi


for (int i = 0, i < 6, i++)
    num_i = 7 - num_i

for (int i = 0, i < 6, i++) 
    rdx = obj.node1
    if (num_i > 1)
        for (j = 1, j < num_i, j++)
            rdx = [rdx + 8]

    [rsp + 0x20 + i*2] = rdx

rcx = [num_0]
for (int i = 1, i < 6, i++) 
    rdx = [num_i + 8]
    [rcx + 8] = rdx
    rcx = rdx

"""

