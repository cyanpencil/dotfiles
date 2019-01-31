#!/usr/bin/python3
import socket
import sys

args = sys.argv[1:]

for addr in args:
    s = socket.socket()
    s.connect((addr, 11213))
    PAYLOAD = b'x'*4096 + b'\r\n'
    for i in range(0, 10001):
        header = b'set memtier-' + bytes(str(i), 'utf-8') + b' 0 9999 4096\r\n'
        s.send(header + PAYLOAD)
    print(s.recv(100))
