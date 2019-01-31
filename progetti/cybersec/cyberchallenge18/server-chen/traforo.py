import requests
from bs4 import BeautifulSoup
from MTRecover import *
from MersenneTwister import *

string = '1'*256
form_data = {'coin': string, 'amount': '1000'}
cookie = {'session':'.eJwFwTEOhDAMBMC_uKZwYhM7-U0CuxKiQaI6ne7vN_OV-Vw3PjIkLElFzhVR0Uv1ysU8cnVGA6BWE5PN1ZcZC7WF5gwrRLddNnmBU0Zp3t3SPX9_JJgaOA.DYWFDw.yVQMFiYNAbhtUopXgv4Y7oqI4kM'}


bits = ""
for i in range(80):
    page = requests.post("http://chqmatteo.pythonanywhere.com/coin", cookies=cookie, data=form_data)
    soup = BeautifulSoup(page.content, "html.parser")
    x = soup.find_all('td')
    n = []
    for a in x:
        bits += a.get_text()

outputs = []
strs = []

k = 0
while(k < len(bits)):
    k += 256
    for i in range(8):
        z = k - 32 * (8 - (i))
        strs += [bits[z:z+32]]
        outputs += [int(strs[-1][::-1], 2)]


mtr = MT19937Recover()
r = mtr.go(outputs)

money = 300000000

for i in range(10000):
    print(money)
    coinbytes = r.getrandbits(256)
    headtails = ''
    for i in range(256):
        if coinbytes & (1 << i) != 0:
            headtails += '1'
        else:
            headtails += '0'

    form_data = {'coin': headtails, 'amount': int(money)}
    page = requests.post("http://chqmatteo.pythonanywhere.com/coin", cookies=cookie, data=form_data)
    money *= (276.0/256.0)    #assume we have won :)
