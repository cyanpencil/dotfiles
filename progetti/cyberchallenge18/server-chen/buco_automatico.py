import requests

form_data = {}

import xorshift
def lotto(seed):
        global form_data
        rand = xorshift.XorShift32(seed)
        numbers = []
        for i in range(10):
            while True:
                candidate = rand.random() % 90 + 1
                if candidate not in numbers:
                    numbers.append(candidate)
                    break
        numbers.sort()
        form_data.clear()
        for i in numbers:
            form_data["num"+str(i)] = "on"
        return rand.seed()

headers = {'User-Agent': 'Mozilla/5.0'}
cookies = {'session':'.eJwFwTEOhDAMBMC_uKZwYhM7-U0CuxKiQaI6ne7vN_OV-Vw3PjIkLElFzhVR0Uv1ysU8cnVGA6BWE5PN1ZcZC7WF5gwrRLddNnmBU0Zp3t3SPX9_JJgaOA.DYWFDw.yVQMFiYNAbhtUopXgv4Y7oqI4kM'}

sess = requests.Session()
s = 1649438448 #found by decoding the cookie
for i in range(100):
    s = lotto(s)
    form_data['amount'] = '100'
    if (i == 0):
        r = sess.post("http://chqmatteo.pythonanywhere.com/lotto", cookies=cookies,  headers=headers,  data=form_data)
    else:
        r = sess.post("http://chqmatteo.pythonanywhere.com/lotto", headers=headers,  data=form_data)
    print(r.cookies)
