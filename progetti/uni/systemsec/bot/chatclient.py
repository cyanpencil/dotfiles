import select
import sys
import socket
import argparse
import tty
import termios

IP = '127.0.0.1'
PORT = 4567
marker = "> "

parser = argparse.ArgumentParser(description='Simple chat client.', epilog="Default values: %s %i" %(IP, PORT))
parser.add_argument('-d', type=str, help="Destination IP", default=IP, metavar="IP", dest="IP")
parser.add_argument('-p', type=int, help="Destination Port", default=PORT, metavar="Port", dest="PORT")

args = parser.parse_args()
IP = args.IP
PORT = args.PORT

fd = socket.socket()
try:
	fd.connect((IP, PORT))
	print "[+] Connected to IP %s on port %i" %(IP, PORT)
except socket.error, e:
	print "[!] Could not connect: %s" %(e.strerror)
	sys.exit()


# Set Terminal into raw mode
tcattr = termios.tcgetattr(sys.stdin.fileno())
tty.setraw(sys.stdin.fileno())

typed = ""
sys.stdout.write("%s" %(marker))
sys.stdout.flush()
while True:
	(readfds, [], []) = select.select([fd, sys.stdin],[],[])
	for readfd in readfds:
		if readfd == fd:
			try:
				data = readfd.recv(4096)
			except:
				data = None
			if data == None or len(data) == 0:
				fd.close()
				sys.stdout.write("\r[!] Connection lost!\r\n")
				sys.exit()
			else:
				data = data.replace("\n", "\r\n")
				sys.stdout.write("\r[+] %s\r\n" %(data))
				sys.stdout.write("%s%s" %(marker, typed))
				sys.stdout.flush()

		if readfd == sys.stdin:
			data = sys.stdin.read(1)
			for c in data:
#				print "Debug: %i" %(ord(c))
# Up Last command = 27 + '[' + 'A'
				if c == "\x04":
					# Reset terminal
					termios.tcsetattr(sys.stdin.fileno(), termios.TCSANOW, tcattr)
					sys.exit()
				if c == "\r" or c == "\n":
					if len(typed) > 0:
						fd.sendall(typed)
						sys.stdout.write("\rSent: %s\r\n" %(typed))
						sys.stdout.flush()
						typed = ""
						sys.stdout.write("%s" %(marker))
						sys.stdout.flush()
				elif c == "\b" or ord(c) == 127:
					if len(typed) == 0:
						continue
					typed = typed[:-1]
					sys.stdout.write("\b \b")
					sys.stdout.flush()
				else:
					typed += c
					sys.stdout.write(c)
					sys.stdout.flush()


