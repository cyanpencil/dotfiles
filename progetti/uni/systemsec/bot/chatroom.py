import select
import sys
import socket
import time
import argparse

IP = '127.0.0.1'
PORT = 4567

parser = argparse.ArgumentParser(description='Simple chat client.', epilog="Default values: %s %i" %(IP, PORT))
parser.add_argument('-l', type=str, help="Listening IP", metavar="IP", default=IP, dest="IP")
parser.add_argument('-p', type=int, help="Listening Port", metavar="Port", default=PORT, dest="PORT")

args = parser.parse_args()
IP = args.IP
PORT = args.PORT

socks = []
listenfd = socket.socket()
listenfd.bind((IP, PORT))
print "[+] Bound to %s on port %i" %(IP, PORT)
listenfd.listen(5)
print "[+] Listening..."
while True:
	(readfds, [], []) = select.select(socks+[listenfd],[],[])
	for readfd in readfds:
		if readfd == listenfd:
			socks += [listenfd.accept()[0]]
			print "[+] Accepted new connection, Connection count: %i" %(len(socks))
		else:
			try:
				data = readfd.recv(4096)
			except:
				data = None
			if data == None or len(data) == 0:
				readfd.close()
				socks.remove(readfd)
				print "[+] Dropped one connection, Connection count: %i" %(len(socks))
			else:
				for sock in socks:
					if sock != readfd:
						try:
							sock.sendall(data)
						except socket.error:
							pass

				time.sleep(.1)
