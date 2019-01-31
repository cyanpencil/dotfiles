# -*- coding: utf-8 -*-

from functools import wraps
from flask import request, session, Response, render_template, redirect
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from Crypto.Util.number import bytes_to_long, long_to_bytes
import os
import datetime
import hashlib
import random
import re
import xorshift
import json
import zlib
import base64
import lcg
app = Flask(__name__)
WELCOME_MONEY = 200
USERNAME_REGEX = re.compile(r'[a-zA-Z0-9]{4,32}')
USERNAME_INVALID = 'Nome non valido, deve matchare questa regex [a-zA-Z0-9]{6,32}'
USERNAME_PASSWORD_INVALID = 'username e/o password sbagliati'
USERNAME_TAKEN = 'Nome preso'
LOTTO_ID = 1
SLOT_ID = 2
COIN_ID = 3
app.secret_key = 'super secret key'
app.config['SESSION_TYPE'] = 'filesystem'
# session.init_app(app)


'''
SQLALCHEMY_DATABASE_URI = "mysql+mysqlconnector://{username}:{password}@{hostname}/{databasename}".format(
    username="<the username from the 'Databases' tab>",
    password="<the password you set on the 'Databases' tab>",
    hostname="<the database host address from the 'Databases' tab>",
    databasename="<the database name you chose, probably yourusername$comments>",
)
'''
SQLALCHEMY_DATABASE_URI = "sqlite:///test.db"
app.config["SQLALCHEMY_DATABASE_URI"] = SQLALCHEMY_DATABASE_URI
app.config["SQLALCHEMY_POOL_RECYCLE"] = 299
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
db = SQLAlchemy(app)

class User(db.Model):
    __tablename__ = 'user'

    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(256))
    password = db.Column(db.String(256))
    apikey = db.Column(db.String(256))
    slotseed = db.Column(db.Integer)
    coinseed = db.Column(db.String(2048))
    money = db.Column(db.BigInteger)

# Un'altra volta va
# class Seed(db.Model):
#     __tablename__ = 'seed'
#     user_id = db.Column(db.Integer, db.ForeignKey("user.id"), nullable=False, primary_key=True)
#     game_id = db.Column(db.Integer, db.ForeignKey("game.id"), nullable=False)    
#     value = db.Column(db.String(4096))
class Play(db.Model):
    __tablename__ = 'play'

    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey("user.id"), nullable=False)
    game_id = db.Column(db.Integer, db.ForeignKey("game.id"), nullable=False)
    bet = db.Column(db.Integer, nullable=False)
    win = db.Column(db.Integer, nullable=False) # ma serve?
    timestamp = db.Column(db.DateTime, default=datetime.datetime.utcnow)

class Game(db.Model):
    __tablename__ = 'game'
    id = db.Column(db.Integer, primary_key=True)
    description = db.Column(db.String(1024))

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'GET':
        return render_template('register.html')
    elif request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        match = USERNAME_REGEX.match(username)
        if match == None or match.start() != 0 or match.end() != len(username):
            return render_template('register.html', error=USERNAME_INVALID)
        if User.query.filter_by(username=username).count() > 0:
            return render_template('register.html', error=USERNAME_TAKEN)
        apikey = hashlib.sha256(os.urandom(32)).hexdigest()
        user = User(id=bytes_to_long(os.urandom(4)),
                    username=username,
                    password=hashlib.sha256(password).hexdigest(),
                    money=WELCOME_MONEY,
                    slotseed=bytes_to_long(os.urandom(4)),
                    coinseed=base64.b64encode(zlib.compress(str(random.Random().getstate()), 8)),
                    apikey=apikey)
        db.session.add(user)
        db.session.commit()
        session['apikey'] = apikey
        return redirect('/user/{}'.format(username))

@app.route('/lotto', methods=['GET', 'POST'])
def lotto():
    # seed per il super star
    # i dieci numeri
    # quanto paghi
    if request.method == 'POST':
        try:
            seed = int(session['seed'])
        except KeyError, ValueError:
            session['seed'] = bytes_to_long(os.urandom(4))
            seed = session['seed']
        apikey = session['apikey']
        if db.session.query(User).filter_by(apikey=apikey).count() != 1:
            # error        
            return redirect('/login')        
        if not seed:
            # error
            return render_template('lotto.html', error='Richiesta impossibile')
        amount = int(request.form['amount'])
        if amount <= 0 or amount > 100:
            # error
            return render_template('lotto.html', error='Richiesta impossibile')
        
        # genero i vincitori
        rand = xorshift.XorShift32(seed)
        numbers = []
        for i in range(10):
            while True:
                candidate = rand.random() % 90 + 1
                if candidate not in numbers:
                    numbers.append(candidate)
                    break
        numbers.sort()

        # parso la scelta dell'utente
        bet = []
        for i in range(1, 90 + 1):
            # vede quali checkbox sono stati selezionati
            if 'num{}'.format(i) in request.form and request.form['num{}'.format(i)] == 'on':
                bet.append(i)
        bet.sort()
        # controllo se ci sono errori
        if len(bet) != len(numbers):
            # error
            return render_template('lotto.html', error='Richiesta impossibile')
    
        user = db.session.query(User).with_for_update().filter_by(apikey=apikey).first()
        if amount > user.money:
            db.session.commit()
            # error
            return render_template('lotto.html', error='Richiesta impossibile')
        got = 0
        numberscpy = numbers[::]
        for x in bet:
            if x in numberscpy:
                numberscpy.remove(x)
                got += 1

        # vinci pari a soldi proporzionalmente (max(X - 5, 0))^2
        def poly(x):
            return pow(max(x - 5, 0), 2)
        user.money += amount * poly(got) - amount
        db.session.add(Play(
            id = db.session.query(Play).count(),
            user_id = user.id,
            game_id = SLOT_ID,
            bet = amount,
            win = amount * poly(got),
            timestamp = datetime.datetime.utcnow()
        ))
        db.session.commit()
        session['seed'] = rand.seed()
        return render_template('lotto.html', winning=numbers, win=amount * poly(got), bet=bet)
    if 'apikey' in session:
        user = User.query.filter_by(apikey=session['apikey']).first()
        if user != None:
            return render_template('lotto.html', username=user.username)
        else:
            return redirect('/login')        
    else:
        return redirect('/login')
@app.route('/slot', methods=['GET', 'POST'])
def slot():
    # linear congruential generator
    # gen num da 8 12 15
    DISKS = [
        ['🍎', '🍑', '🍒', '🍓', '🍎', '🍑', '🍒', '🍓'],
        ['🍒', '🍎', '🍒', '🍑', '🍒', '🍓', '🍒', '🍎', '🍒', '🍑', '🍒', '🍓'],
        ['🍎', '🍑', '🍒', '🍓', '🍎', '🍑', '🍒', '🍓', '🍎', '🍑', '🍒', '🍓', '🍎', '🍓', '🍑']
    ]
    a, p, c, f = '🍎'.decode('utf-8'), '🍑'.decode('utf-8'), '🍒'.decode('utf-8'), '🍓'.decode('utf-8')
    DISKSD = [
        [a, p, c, f, a, p, c, f],
        [c, a, c, p, c, f, c, a, c, p, c, f],
        [a, p, c, f, a, p, c, f, a, p, c, f, a, f, p]
    ]
    if 'apikey' not in session:
        return redirect('/login')        
    if request.method == 'POST':
        apikey = session['apikey']
        if db.session.query(User).filter_by(apikey=apikey).count() != 1:
            # error
            return redirect('/login')
        
        amount = int(request.form['amount'])
        if amount < 20 or amount > 200000:
            return render_template('slot.html', error='Richiesta impossibile')
        user = User.query.with_for_update().filter_by(apikey=apikey).first()
        if amount > user.money:
            db.session.commit()
            # error
            return render_template('slot.html', error='Richiesta impossibile')
        seed = user.slotseed
        rand = lcg.Lcg32(seed)
        a, b, c = rand.random() % 8, rand.random() % 12, rand.random() % 15
        x, y, z = DISKS[0][a], DISKS[1][b], DISKS[2][c]
        if x == '🍒' and y == '🍒' and z == '🍒':
            payout = 10
        elif x == '🍑' and y == '🍑' and z == '🍑':
            payout = 8
        elif x == '🍎' and y == '🍎' and z == '🍎':
            payout = 6
        elif x == '🍓' and y == '🍓' and z == '🍓':
            payout = 3
        elif x == '🍒' and y == '🍑' and z == '🍒':
            payout = 2
        elif x == '🍎' and y == '🍎' and z == '🍓':
            payout = 2
        elif x == '🍓' and y == '🍓' and z == '🍑':
            payout = 1
        else:
            payout = 0
        user.money += amount * payout - amount
        user.slotseed = rand.seed()
        db.session.add(Play(
                id = db.session.query(Play).count(),
                user_id = user.id,
                game_id = SLOT_ID,
                bet = amount,
                win = amount * payout,
                timestamp = datetime.datetime.utcnow()
            ))
        db.session.commit()
        return render_template('slot.html', disks=DISKSD, winning=[a,b,c], win=amount * payout)
    else:
        return render_template('slot.html', disks=DISKSD)
        
# La prossima volta forse   
# @app.route('/roulette')
# def roulette():
#     pass

@app.route('/coin', methods=['GET', 'POST'])
def coin():
    if 'apikey' not in session:
        return redirect('/login')
    # marsienne twister complicato
    if request.method == 'POST':
        prediction = request.form['coin'].strip()
        if len(prediction) != 256 or any(map(lambda value: value != '1' and value != '0', prediction)):
            return render_template('coin.html', error='Richiesta impossibile')
        prediction = [1 if x == '1' else 0 for x in prediction]
        apikey = session['apikey']
        if db.session.query(User).filter_by(apikey=apikey).count() != 1:
            # error
            return redirect('/login')
        
        amount = int(request.form['amount'])
        if amount < 1000:
            return render_template('coin.html', error='Troppo povero')
        # what's the form of the bet
        # 1. array 1,0
        # 2. premio per la somma di 1 e somma di 0
        user = User.query.with_for_update().filter_by(apikey=apikey).first()
        if amount > user.money:
            db.session.commit()
            # error
            return render_template('coin.html', error='Richiesta impossibile')
        seed = eval(zlib.decompress(base64.b64decode(user.coinseed)))
        rand = random.Random()
        rand.setstate(seed)
        coinbytes = rand.getrandbits(256)
        headtails = []
        ones = 0
        for i in range(256):
            if coinbytes & (1 << i) != 0:
                headtails.append(1)
                ones += 1
            else:
                headtails.append(0)
        correct = 0
        for a, b in zip(headtails, prediction):
            if a == b:
                correct += 1
        win = int((correct + 20) / 256.0 * amount)
        user.money += win - amount
        user.coinseed = base64.b64encode(zlib.compress(str(random.Random().getstate()), 8))
        db.session.add(Play(
            id = db.session.query(Play).count(),
            user_id = user.id,
            game_id = COIN_ID,
            bet = amount,
            win = win,
            timestamp = datetime.datetime.utcnow()
        ))
        db.session.commit()
        return render_template('coin.html', headtails=headtails, win=win)
    else:
        return render_template('coin.html')
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'GET':
        return render_template('login.html')
    elif request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        match = USERNAME_REGEX.match(username)
        if match == None or match.start() != 0 or match.end() != len(username):
            return render_template('register.html', error=USERNAME_INVALID)
        users = User.query.filter_by(username=username,
                                    password=hashlib.sha256(password).hexdigest())
        if users.count() != 1:
            return render_template('register.html', error=USERNAME_PASSWORD_INVALID)
        session['apikey'] = users.first().apikey
        return redirect('/user/{}'.format(username))

@app.route('/user')
def all_users():
    users = User.query.all()
    users_public = [(user.username, user.money) for user in users]
    users_public.sort(key=lambda x: (-x[1], x[0]))
    return render_template('users.html', users=users_public)

@app.route('/user/<username>')
def profile(username):
    user = User.query.filter_by(username=username).first()
    if user.apikey == session['apikey']:
        allplays = Play.query.filter_by(user_id=user.id).all()
    else:
        allplays = None
    return render_template('user.html', name=user.username, money=user.money, plays=Play.query.filter_by(user_id=user.id).count(), allplays=allplays)

import sys

if __name__ == '__main__':
    if sys.argv[1] == 'init':
        db.drop_all()
        db.create_all()
        db.session.add(Game(id=LOTTO_ID, description='lotto'))
        db.session.add(Game(id=SLOT_ID, description='slot'))
        db.session.add(Game(id=COIN_ID, description='coin'))