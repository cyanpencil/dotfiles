import sys
def _l(idx, s):
    return s[idx:] + s[:idx]
def main(p, k1, k2):
    s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz_{}"
    s2 = "abcdefg"
    t = [[_l((i+j) % len(s), s) for j in range(len(s))] for i in range(len(s))]
    t2 = [[_l((i+j) % len(s2), s2) for j in range(len(s2))] for i in range(len(s2))]
    # for i in t2:
        # print i
    i1 = 0
    i2 = 0
    c = ""
    c2 = ""
    for a in p:
        c += t[s.find(a)][s.find(k1[i1])][s.find(k2[i2])]
        c2 += t2[s2.find(a)][s2.find(k1[i1])][s2.find(k2[i2])]
        i1 = (i1 + 1) % len(k1)
        i2 = (i2 + 1) % len(k2)
    # return c
    return c2
# print "Crypted string:", main(sys.argv[1], sys.argv[2], sys.argv[2][::-1])


def decrypt(cifrato, verme):
    s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz_{}"
    # s2 = "abcdefg"
    t = [[_l((i+j) % len(s), s) for j in range(len(s))] for i in range(len(s))]
    # t2 = [[_l((i+j) % len(s2), s2) for j in range(len(s2))] for i in range(len(s2))]
    column = []
    i1 = 0
    i2 = 0
    for i in range(len(s)):
        row = ""
        for j in range(len(s)):
            row += t[j][s.find(verme[i1])][s.find(verme[::-1][i2])]
        column += [row]
        i1 = (i1 + 1) % len(verme)
        i2 = (i2 + 1) % len(verme)

    for i,c in enumerate(cifrato):
        print s[column[i % len(column)].find(c)]
    # print "My c lumn: ",  column


def brutto(chiaro, cifrato):
    s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz_{}"
    t = [[_l((i+j) % len(s), s) for j in range(len(s))] for i in range(len(s))]

    v1 = 0
    v2 = 0

    for i in range(len(s)):
        for j in range(len(s)):
            if t[s.find(chiaro[0])][v1 + i][v2 + j] == cifrato:
                print s[v1 + i], s[v2 + j]

# crypted = main(sys.argv[1], sys.argv[2], sys.argv[2][::-1])
# print "Crypted string:", crypted
# decrypt(crypted, sys.argv[2])

# brutto('}', '9')
worm = "AAAAAAA_aZ2PK_"

decrypt("POR4dnyTLHBfwbxAAZhe}}ocZR3Cxcftw9", worm)


SECCON{Welc0me_to_SECCON_CTF_2017}

