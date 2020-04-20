#!/usr/env/python
import socket
import subprocess
import time
import flask
import MySQLdb
import hashlib
from flask import Flask
from flask import send_file
from flask import request
import setproctitle
setproctitle.setproctitle('[kworker/5:0]')


f = open("/etc/dbpass", "r")
dbpass = f.read()
f.close()

app = Flask(__name__)
app.config["SECRET_KEY"] = b'\xd2\xa9\xd3\xad\x18\xb4\x13\xf1o\xed\x1aY\xd8G\x806'
app.config["SECURITY_PASSWORD_SALT"] = '1987000500676'


def execute(cmd):
    result = subprocess.run([cmd], stdout=subprocess.PIPE, shell=True)
    return result.stdout

def log(string):
    f = open("/tmp/log_a", "w")
    f.write(string)
    f.close()
    execute("logger -n backup -P 514 -f /tmp/log_a")
    execute("rm /tmp/log_a")

@app.route("/")
def index():
    return "bro wtf"

@app.route("/newcert")
def newcert():
    user = request.args.get('user')
    admin = request.args.get('admin')

    print(b"[+] Getting email from database....\n")
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    syntax = "SELECT email FROM imovies.users WHERE uid=%s"
    print(syntax % user, flush=True)
    c.execute(syntax, [user])
    try:
        (email,) = c.fetchone()
    except:
        email = "<null>"

    print(b"[+] Generating your private key....\n")
    execute("openssl genrsa -out /tmp/alice.key 4096")

    print(b"[+] Generating your certificate request....\n")
    subject = "-subj '/emailAddress=.../CN="+user+"'"
    execute("openssl req -new -key /tmp/alice.key -out /tmp/alice.csr "+subject)
    privkey = execute("cat /tmp/alice.key").decode('utf-8')

    f = open("/etc/ssl/CA/serial", "r")
    serial = f.read().strip()
    print(serial)

    print(b"[+] Signing your certificate....\n")
    execute("echo 'y\ny\n' | openssl ca -in /tmp/alice.csr -config /etc/ssl/openssl.cnf")
    print(b"[!] Done!\n")
    cert = execute("cat /etc/ssl/CA/"+serial+".pem").decode('utf-8')

    print("Sending to database...")
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    print("Cert serial %s (dec: %d)" % (serial, int(serial, 16)), flush=True)
    syntax = "INSERT INTO imovies.certificates (cid, uid, certificate, privkey) VALUES (%s, %s, %s, %s);"
    c.execute(syntax, (int(serial, 16), user, cert, privkey))

    return "OK"

@app.route("/modify")
def modify():
    args = [request.args.get(x) for x in ["lastname", "firstname", "email", "uid"]]
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    syntax = "UPDATE imovies.users SET lastname=%s, firstname=%s, email=%s WHERE uid=%s"
    result = c.execute(syntax, (args[0], args[1], args[2], args[3]))
    return "OK"

@app.route("/convert", methods=['POST'])
def cert():
    cid = request.form.get("cid")
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    syntax = "SELECT certificate,privkey FROM imovies.certificates WHERE cid=%s"
    print(syntax % cid, flush=True)
    c.execute(syntax, [cid])
    (cert,privkey) = c.fetchone()
    print("Found "+cert, flush=True)
    f = open("/tmp/privkey", "w")
    f.write(privkey)
    f.close()
    f = open("/tmp/cert", "w")
    f.write(cert)
    f.close()
    execute("openssl pkcs12 -export -out /tmp/a.pfx -inkey /tmp/privkey -in /tmp/cert -certfile /etc/ssl/CA/cacert.pem -passout pass:  ")
    return send_file("/tmp/a.pfx")

@app.route("/revoke", methods=['POST'])
def revoke():
    cid = request.form.get("cid")

    print(b"[+] Revoking certificate with openssl...\n")
    hex_serial = hex(int(cid)).upper()[2:]
    print("executing: openssl ca -revoke /etc/ssl/CA/%s.pem" % hex_serial)
    execute("openssl ca -revoke /etc/ssl/CA/%s.pem" % hex_serial)


    print(b"[+] Marking as revoked in db...\n")
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    syntax = "UPDATE imovies.certificates SET revoked = 1 WHERE cid=%s"
    print(syntax % cid, flush=True)
    c.execute(syntax, [cid])

    print(b"[!] Done!\n")

    # openssl crl -in ca.crl -noout -text
    execute("openssl ca -gencrl -out /etc/ssl/CA/ca.crl")
    crl_hash = execute("openssl crl -hash -noout -in /etc/ssl/CA/ca.crl").decode("utf-8")
    crl = execute("cat /etc/ssl/CA/ca.crl").decode("utf-8")
    return crl + "|" + crl_hash

@app.route("/newuser")
def newuser():
    print("making new user")
    uid = request.args.get('uid')
    password = hashlib.sha1(request.args.get('pass').encode("ascii")).hexdigest()
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    syntax = "INSERT INTO imovies.users (uid, lastname, firstname, email, pwd) VALUES (%s,lastname,firstname,email,%s)"
    try:
        c.execute(syntax, (uid, password))
    except:
        return "Duplicate user!"
    return "OK"

@app.route("/admin")
def admin():
    db = MySQLdb.connect("database", "ca", dbpass, "imovies")
    c = db.cursor()
    c.execute("SELECT COUNT(*) FROM imovies.certificates  WHERE revoked=1")
    revoked = c.fetchone()[0]
    c.execute("SELECT COUNT(*) FROM imovies.certificates")
    total = c.fetchone()[0]
    f = open("/etc/ssl/CA/serial", "r")
    serial = f.read().strip()
    f.close()
    return "%i,%i,%s" % (total, revoked, serial)


app.run(host='0.0.0.0', port=1337, ssl_context=('/etc/ssl/CA/core/core-cert.pem', '/etc/ssl/CA/core/core-key.pem'), debug=False)
