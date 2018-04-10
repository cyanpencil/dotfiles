from bs4 import BeautifulSoup

import requests

f = open("parsed.txt", "w")
string = '1'*256
form_data = {'coin': string, 'amount': '1000'}
cookie = {'session':'.eJwFwTEOhDAMBMC_uKZwYhM7-U0CuxKiQaI6ne7vN_OV-Vw3PjIkLElFzhVR0Uv1ysU8cnVGA6BWE5PN1ZcZC7WF5gwrRLddNnmBU0Zp3t3SPX9_JJgaOA.DYWFDw.yVQMFiYNAbhtUopXgv4Y7oqI4kM'}




sol = ""
for i in range(80):
    page = requests.post("http://chqmatteo.pythonanywhere.com/coin", cookies=cookie, data=form_data)
    soup = BeautifulSoup(page.content, "html.parser")
    print page.text
    x = soup.find_all('td')
    n = []
    for a in x:
        sol += a.get_text()
        # n += [int(a.get_text())]
        # if len(n) == 32:
            # num = 0
            # for i,l in enumerate(n):
                # # num += (l << (31 - i))
                # num += (l << (i))
            # print num
            # sol += [num]
            # n = []

# for i in range(80):
    # for j in range(8):
        # f.write(str(sol[8*i + 7 - j])+"\n")
f.write(sol)

f.close()
