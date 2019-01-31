import lcg

d = [
    [0, 1, 2, 3, 0, 1, 2, 3],
    [2, 0, 2, 1, 2, 3, 2, 0, 2, 1, 2, 3],
    [0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 3, 1]
]

sol = [
    [1,2,3],
    [0,3,3],
    [3,2,2],
    [2,3,1],
    [1,2,2],
    [0,0,2],
    [3,2,3],
    [2,0,0],
    [1,2,2],
    [0,1,0],
    [3,2,0],
    [2,0,1],
    [1,2,1],
    [0,3,3],
    [3,2,3],
    [2,1,3]
]

win = {
        (2,2,2) : 10,
        (1,1,1) : 8,
        (0,0,0) : 6,
        (3,3,3) : 3,
        (2,1,2) : 1,
        (0,0,3) : 2,
        (3,3,1) : 1
}


# s = []
# for i in range(2**32):
    # if i % 1000000 == 0:
        # print(i)
    # rand = lcg.Lcg32(i)
    # ok = True
    # for k in sol:
        # a, b, c = rand.random() % 8, rand.random() % 12, rand.random() % 15
        # x, y, z = d[0][a], d[1][b], d[2][c]
        # if x  != k[0] or y != k[1] or z != k[2]:
            # ok = False
            # break
    # if (ok):
        # s += [i]


# print(s)
# print(len(s))


# l = [712628600, 798832280, 1890614256, 2766475756, 3514194196]


# s = []
# for i in l:
    # if i % 10000 == 0:
        # print(i)
    # rand = lcg.Lcg32(i)
    # ok = True
    # for k in sol:
        # a, b, c = rand.random() % 8, rand.random() % 12, rand.random() % 15
        # x, y, z = d[0][a], d[1][b], d[2][c]
        # if x  != k[0] or y != k[1] or z != k[2]:
            # ok = False
            # break
    # if (ok):
        # s += [i]

# print(s)
# print(len(s))

import requests
form_data = {}
headers = {'User-Agent': 'Mozilla/5.0'}
cookies = {'session':'.eJwFwTEOhDAMBMC_uKZwYhM7-U0CuxKiQaI6ne7vN_OV-Vw3PjIkLElFzhVR0Uv1ysU8cnVGA6BWE5PN1ZcZC7WF5gwrRLddNnmBU0Zp3t3SPX9_JJgaOA.DYWFDw.yVQMFiYNAbhtUopXgv4Y7oqI4kM'}

z = 1428609492
rand = lcg.Lcg32(z)

for i in range(16): #pass moves already known
    rand.random()
    rand.random()
    rand.random()

sess = requests.Session()

for i in range(10000):
    a, b, c = rand.random() % 8, rand.random() % 12, rand.random() % 15
    x, y, z = d[0][a], d[1][b], d[2][c]
    form_data.clear()
    print(x,y,z)
    if (x,y,z) in win:
        form_data['amount'] = 200000
    else:
        form_data['amount'] = 20
    r = sess.post("http://chqmatteo.pythonanywhere.com/slot", cookies=cookies,  headers=headers,  data=form_data)


