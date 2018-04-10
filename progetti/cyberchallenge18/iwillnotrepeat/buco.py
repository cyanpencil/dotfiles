import binascii
from binascii import hexlify
S = ['4de61dd9dab5e0701f5e664ff522de12bd588051da4d3f62df3c3303e696139af0280308f5720d5e45efaa03bc6d37d84294',
 '06b25cded0e2fb74045f681bd4378a5bba10901fd6513b2cc0343c0aa3c6138df02d1f46e63a090d07f3b602bc653bcd5ad1',
 '00fa5890c4f0e062175d2348bd30c25dbb44c951c0503f6fd83d3114b28e1390b4611146e53a091c45f8bc01f56f2ac1459f',
 '07be1dc2ccf8fc67131a3355f326c957ba438403cc03362adf213b14b5ca5290b537155aa1680d0b54eff916bc7e3fcc06d1',
 '15fc5990c1f4e574565b665cf22cce12ac5e8a04d24b7a3dca3b3a09abc60191a533134da17c070c07eeb803fd207efc4294',
 '54f552dccdb5fd64115d234fbd2ad912eb7d841fc142372adc3c324684871599b92f0340ee68065c09eeb803fd207efc4294']


mychars = [32, 44, 46, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86,
        87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112,
        113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
        123,125,40,41,91,93,45,33,34,39,40,41,47]



def crib_drag(mystr, pos, i):
    if pos*2 + len(mystr) > len(S[i]):
        return []
    h = int(mystr.encode().hex(), 16)
    m = int(S[i][pos*2:pos*2+len(mystr)*2], 16)
    key = m ^ h
    w = []
    for l in S:
        if pos*2 + len(mystr)*2 > len(l):
            continue
        z = int(l[pos*2:pos*2+len(mystr)*2], 16) ^ key
        z = str(hex(z))[2:]
        if len(z) % 2 != 0:
            z = "0"+z
        w += [binascii.unhexlify(z).decode()]
    return w


mystr = " the "
for j in range(6):
    for i in range(52 - len(mystr)):
        w = crib_drag(mystr, i, j)
        ok = True
        for l in w:
            for c in l:
                if not ord(c) in mychars:
                    ok = False
        if (ok and len(w) > 0):
            print(i)
            for l in w:
                print(l)
            print()

# w = crib_drag(" as long as ", 29, 1)
# w = crib_drag("pad is the be", 29, 0)
# w = crib_drag(" as long as you", 29, 1)
# w = crib_drag("hand and abbreviat", 29, 2)
# w = crib_drag(" as long as you keep", 29, 1)
# w = crib_drag("hand and abbreviation", 29, 2)
# w = crib_drag(" pad is the best ciphe", 28, 0)
# w = crib_drag(" pad is the best ciphe", 28, 0)

# w = crib_drag("r ", 0, 1)
# w = crib_drag("s, ", 0, 3)
# w = crib_drag("the ", 0, 2)
# w = crib_drag("s, re", 0, 3)
# w = crib_drag("r anyw", 0, 1)
# w = crib_drag("r anywh", 0, 1)
# w = crib_drag("the message", 0, 2)
# w = crib_drag(" gold nugget", 0, 5)
# w = crib_drag("the messages ", 0, 2)
# w = crib_drag("9t is said that", 0, 0)
# w = crib_drag("s, remove unnecessary", 0, 3)
# w = crib_drag("9t is said that the one-time pad is the best ciphe", 0, 0)
# for l in w:
    # print(l)

