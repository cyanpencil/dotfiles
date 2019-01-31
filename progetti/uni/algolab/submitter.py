import requests
import getpass
import time
import sys
import os

LOGIN_URL = "https://judge.inf.ethz.ch/team/checkpasswd.php"
UPLOAD_URL = "https://judge.inf.ethz.ch/team/upload.php"



def get_cookie():
    pwd = getpass.getpass("Password:")
    res = s.post(LOGIN_URL, data={"login":"lucadib", "passwd":pwd})
    cookie = res.cookies['PHPSESSID']
    print(cookie)
    with open("/home/luca/judgecookie", "w") as c:
        c.write(cookie)
    return cookie


if len(sys.argv) < 2:
    print("please provide argument")
    exit(0)

with requests.Session() as s:
    try :
        with open("/home/luca/judgecookie", "r") as c:
            cookie = c.read()
            print(cookie)
    except FileNotFoundError:
        cookie = get_cookie()

    while True:
        with open(sys.argv[1], 'rb') as f:
            files= {"file": ('bridges.cpp', f)}
            res = s.post(UPLOAD_URL, files=files, data={'probid':'AL1405', 'langid':'all', 'submit':'Submit solution', 'code':''}, cookies={'PHPSESSID':cookie})
            if "identify" in res.text:
                cookie = get_cookie()
                continue
            print(res.text)
        break
        # time.sleep(12)
