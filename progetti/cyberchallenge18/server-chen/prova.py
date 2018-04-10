import random

rand = random.Random()

f = open("parsed.txt", "w")

rand.getrandbits(256)
rand.getrandbits(256)
rand.getrandbits(256)
rand.getrandbits(256)

for j in range(80):
    coinbytes = rand.getrandbits(256)
    headtails = []
    mystr = ''
    ones = 0
    for i in range(256):
        if coinbytes & (1 << i) != 0:
            headtails.append(1)
            mystr += '1'
            ones += 1
        else:
            headtails.append(0)
            mystr += '0'
    f.write(mystr)

f.close()

