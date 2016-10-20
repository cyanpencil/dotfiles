#!/usr/bin/env python
#
# Piccolo programma per interfacciare fra di loro engine di othello attraverso la grafica
#
# Dipendenze:
# Python 3
# Qt4 (libreria di python)
# Pyexpect
# Composition manager (per i sistemi unix, altrimenti non si vede la trasparenza della finestra)
#
# Utilizzo:
# Una volta aperto il programma desiderato per giocare a othello, eseguire questo script di python,
# mentre la finestra di othello è ben visibile. Poi fare click sulla prima casella della griglia di
# othello (quella in alto a sinistra), e in seguito sull'ultima (quella in basso a destra), in modo
# da permettere di capire la disposizione delle pedine nella griglia di othello.
#
# Attenzione! Non muovere, nè ridimensionare, la finestra su cui è aperto othello.
# Non muovere il mouse durante l'esecuzione della partita

import sys, subprocess, time, os, signal, pexpect
from PyQt4 import *
from PyQt4.QtGui import *
from PyQt4 import QtGui, QtCore
from PyQt4.QtCore import *


# Percorso dell' engine da terminale
cli = pexpect.spawnu("/home/luca/Downloads/edax/gtp.sh")

# Tempo (in secondi) da aspettare per essere sicuri che l'engine grafico abbia fatto
# la sua mossa
move_waiting_time = 4

# Questo valore determina il margine di errore con cui vengono riconosciute le pedine
# Un valore troppo basso renderà il programma troppo preciso, confondendo magari sfumature
# della texture delle pedine con lo sfondo, mentre un valore troppo alto potrebbe far si che
# il programma vede la tavola completamente vuota
acceptable_color_error = 40




EMPTY = 0
BLACK = 2
WHITE = 1
start_x, start_y = 0, 0
space_x, space_y = 0, 0
points = []
board = [[EMPTY for i in range(8)] for j in range(8)]
old_board = [[EMPTY for i in range(8)] for j in range(8)]
c_black, c_white, c_empty = -1, -1, -1


def click(x, y):
    subprocess.call("xte 'mousemove " + str(x) + " " + str(y) +"'", shell=True)
    subprocess.call("xte 'mouseclick 1'", shell=True)
    subprocess.call("xte 'mousemove 0 0'", shell=True)


def screenshot():
    global c_black, c_white, c_empty, board, old_board, points, acceptable_color_error
    screenshot = QPixmap.grabWindow(QApplication.desktop().winId()).toImage()

    print("Colori:", c_empty, c_white, c_black)

    #mi copio tutto nella vecchia board
    for i in range(8):
        for j in range(8):
            old_board[i][j] = board[i][j]

    if c_black == -1:
        othello_luv = [points[0][0], points[3][4], points[3][3]]
        for i,p in enumerate(othello_luv):
            r, g, b = 0, 0, 0
            for x in range(-1, 2):
                for y in range(-1, 2):
                    r += QtGui.qRed(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
                    g += QtGui.qGreen(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
                    b += QtGui.qBlue(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
            if i == 0:
                c_empty = r + g + b
            elif i == 1:
                c_white = r + g + b
            else:
                c_black = r + g + b


    for i in range(8):
        for j in range(8):
            r, g, b = 0, 0, 0
            for x in range(-1, 2):
                for y in range(-1, 2):
                    r += QtGui.qRed(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
                    g += QtGui.qGreen(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
                    b += QtGui.qBlue(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
            if c_empty - acceptable_color_error < r + g + b < c_empty + acceptable_color_error:
                board[i][j] = EMPTY
            elif c_white - acceptable_color_error < r + g + b < c_white + acceptable_color_error:
                board[i][j] = WHITE
            elif c_black - acceptable_color_error < r + g + b < c_black + acceptable_color_error:
                board[i][j] = BLACK
            else:
                board[i][j] = EMPTY

    print("Board:")
    for i in range(8):
        print(board[i])
    print("Old_Board:")
    for i in range(8):
        print(old_board[i])

started = False
started2 = False

def move(x, y, color):
    global c
    what_color = 'w'
#    if color == BLACK:
#        what_color = 'b'
    if color == WHITE:
        what_color = 'w'
    letter = chr(x + ord('a'))
    print("Mando alla cli : play " + str(what_color) + " " + str(letter) + str(y))
    cli.sendline("play " + str(what_color) + " " + str(letter) + str(y))
    cli.readline()
    cli.readline()
    cli.readline()

def read_cli_move():
    for i in range(8):
        for j in range(8):
            if board[i][j] != EMPTY and old_board[i][j] == EMPTY:
                move(i, j + 1, board[i][j])
                print("Non ci sta: " + str(i), str(j))

def read_graphic_move():
    global cli
    b = cli.readline()
    cli.readline()
    print("Output console: ", b)
    return b[2:-2]

def play():
    global cli, points, old_board, move_waiting_time
    screenshot()
    while(True):
        print("Playing...")
        cli.sendline("genmove b")
        cli.readline()
        m = read_graphic_move()

        try:
            x = ord(m[0]) - ord('a')
            y = int(m[1]) - 1
        except ValueError:
            print("Something went wrong while reading the console output")
            exit()
        click(points[x][y].x(), points[x][y].y())

        time.sleep(move_waiting_time)
        screenshot()
        old_board[x][y] = 1
        read_cli_move()



class mymainwindow(QtGui.QMainWindow):
    def __init__(self):
        super(mymainwindow, self).__init__()
        QtGui.QMainWindow.__init__(self)
        self.mousex, self.mousey = 0, 0
        self.setWindowFlags(
            QtCore.Qt.WindowStaysOnTopHint |
            QtCore.Qt.FramelessWindowHint |
            QtCore.Qt.Popup |
            QtCore.Qt.X11BypassWindowManagerHint
            )
        self.setMouseTracking(True)
        self.setAttribute(QtCore.Qt.WA_TranslucentBackground)
        self.setAttribute(QtCore.Qt.WA_PaintOnScreen)
        self.setGeometry(QApplication.desktop().screenGeometry())

    def mouseReleaseEvent(self, event):
        global started, start_x, start_y, space_x, space_y, points, screenshot, started2, mywindow
        print("Registered a click")
        if started:
            mywindow.hide()
            time.sleep(1)
            if not started2:
                space_y = (self.mousey - start_y) / 7
                space_x = (self.mousex - start_x) / 7
                for i in range(8):
                    row = []
                    for j in range(8):
                        row += [QPoint(int(start_x + space_x * i), int(start_y + space_y * j))]
                    points += [row]
                started2 = True
                play()
        else:
            started = True
            start_x = event.pos().x()
            start_y = event.pos().y()

    def mouseMoveEvent(self, event):
        self.mousex = event.pos().x()
        self.mousey = event.pos().y()
        print(self.mousex, self.mousey)
        self.update()

    def keyPressEvent(self, event):
        if event.key() == QtCore.Qt.Key_Escape:
            exit()


    def paintEvent(self, event):
        qp = QtGui.QPainter()
        qp.begin(self)
        background_color = QColor(0, 0, 0, 0)

        qp.setCompositionMode(QPainter.CompositionMode_Source);
        qp.fillRect(event.rect(), background_color);
        qp.eraseRect(self.rect())
        qp.setCompositionMode (QPainter.CompositionMode_SourceOver);

        self.drawCircles(event, qp)
#        self.drawText(event, qp)
        qp.end()

    def drawText(self, event, qp):
        global started
        qp.setPen(QtGui.QColor(168, 134, 53))
        qp.setFont(QtGui.QFont('Decorative', 20))
        if not started:
            qp.drawText(event.rect(), QtCore.Qt.AlignCenter, "Fai click sulla prima casella in alto a sinistra")
        else:
            qp.drawText(event.rect(), QtCore.Qt.AlignCenter, "Adesso fai click sull'ultima casella in basso a destra")

    def drawCircles(self, event, qp):
        global started, start_x, start_y
        qp.setPen(QtGui.QColor(255, 0, 0))
        center = QPoint(self.mousex, self.mousey)
        if not started:
            qp.drawEllipse(center, 10, 10)
        else:
            space_y = float(self.mousey - start_y) / 7
            space_x = float(self.mousex - start_x) / 7
            for i in range(8):
                for j in range(8):
                    qp.drawEllipse(QPoint(start_x + (space_x * j), start_y + (space_y * i)), 10, 10)
                    qp.drawPoint(QPoint(start_x + (space_x * j), start_y + (space_y * i)))



app = QtGui.QApplication(sys.argv)
mywindow = mymainwindow()
mywindow.show()
mywindow.activateWindow()
app.exec_()
