#!/usr/bin/env python
#
# Piccolo programma per salvarsi le coordinate di un programma di Othello
#
# Utilizzo:
# Una volta aperto il programma desiderato per giocare a othello, eseguire questo script di python,
# mentre la finestra di othello è ben visibile. Poi fare click sulla prima casella della griglia di
# othello (quella in alto a sinistra), e in seguito sull'ultima (quella in basso a destra), in modo
# da permettere di capire la disposizione delle pedine nella griglia di othello.
# Infine, opzionalmente, fare la sequenza di click necessaria per far ricominciare la partita
# al programma di Othello. 
# Una volta premuto il tasto "Enter" o il tasto "Esc", questo programma terminerà.

import sys, time, subprocess, os
from PyQt4 import *
from PyQt4.QtGui import *
from PyQt4 import QtGui, QtCore
from PyQt4.QtCore import *


EMPTY = 0
BLACK = 2
WHITE = 1
start_x, start_y = 0, 0
space_x, space_y = 0, 0
points = []
board = [[EMPTY for i in range(8)] for j in range(8)]
old_board = [[EMPTY for i in range(8)] for j in range(8)]
color = [[-1 for i in range(3)] for j in range(3)]
click_combo = []
grid_started = False
grid_set = False
click_combo_started = False


def click(x, y):
    subprocess.call("xte 'mousemove " + str(x) + " " + str(y) +"'", shell=True)
    subprocess.call("xte 'mouseclick 1'", shell=True)

def get_colors():
    global color, board, old_board, points, acceptable_color_error
    screenshot = QPixmap.grabWindow(QApplication.desktop().winId()).toImage()

    #mi copio tutto nella vecchia board
    for i in range(8):
        for j in range(8):
            old_board[i][j] = board[i][j]

    othello_luv = [points[0][0], points[3][3], points[3][4]]
    for i,p in enumerate(othello_luv):
        r, g, b = 0, 0, 0
        for x in range(-1, 2):
            for y in range(-1, 2):
                r += QtGui.qRed(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
                g += QtGui.qGreen(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
                b += QtGui.qBlue(screenshot.pixel(QPoint(p.x() + x, p.y() + y)))
#                c_empty = r + g + b
        color[i][0] = r
        color[i][1] = g
        color[i][2] = b


def save_coordinates():
    global points, color
    path = os.path.dirname(os.path.realpath(__file__))
    with open(path + "/coordinates", "w") as f:
        for i in range(3):
            f.write(str(color[i][0]) + " " + str(color[i][1]) + " " + str(color[i][2]) + "\n")
        for i in range(8):
            for j in range(8):
                f.write(str(points[i][j].x()) + " " + str(points[i][j].y()) + "\n")
        f.write(str(len(click_combo)) + "\n")
        for i in range(len(click_combo)):
            f.write(str(click_combo[i][0]) + " " + str(click_combo[i][1]) + "\n")
        f.close()



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
        global grid_started, start_x, start_y, space_x, space_y, click_combo
        global points, screenshot, grid_set, mywindow, click_combo_started
        if grid_started:
            if click_combo_started:
                print("Registered a click")
                mywindow.hide()
                click_combo += [(self.mousex, self.mousey)]
                click(self.mousex, self.mousey)
                mywindow.show()
            else:
                mywindow.hide() #spostare in keypress
                print("Salvando...")
                time.sleep(1)
                if not grid_set:
                    space_y = (self.mousey - start_y) / 7
                    space_x = (self.mousex - start_x) / 7
                    for i in range(8):
                        row = []
                        for j in range(8):
                            row += [QPoint(int(start_x + space_x * i), int(start_y + space_y * j))]
                        points += [row]
                    get_colors()
                    grid_set = True
                    click_combo_started = True
                    mywindow.show()
                    print("Ora fare la sequenza di click necessaria per resettare la board...")
                    print("Premere \"Enter\" una volta terminato")
        else:
            print("Cliccare sull'ultima casella in basso a destra della tavola di gioco")
            grid_started = True
            start_x = event.pos().x()
            start_y = event.pos().y()

    def mouseMoveEvent(self, event):
        self.mousex = event.pos().x()
        self.mousey = event.pos().y()
        self.update()

    def keyPressEvent(self, event):
        if event.key() == QtCore.Qt.Key_Escape or event.key() == QtCore.Qt.Key_Return:
            if grid_set:
                print("Salvando...")
                save_coordinates()
                print("Fatto!")
            exit()


    def paintEvent(self, event):
        qp = QtGui.QPainter()
        qp.begin(self)
        qp.setRenderHint(QPainter.Antialiasing)
        background_color = QColor(0, 0, 0, 0)

        qp.setCompositionMode(QPainter.CompositionMode_Source);
        qp.fillRect(event.rect(), background_color);
        qp.eraseRect(self.rect())
        qp.setCompositionMode (QPainter.CompositionMode_SourceOver);

        self.drawCircles(event, qp)
#        self.drawText(event, qp)
        qp.end()

    def drawText(self, event, qp):
        global grid_started
        qp.setPen(QtGui.QColor(168, 134, 53))
        qp.setFont(QtGui.QFont('Decorative', 20))
        if not grid_started:
            qp.drawText(event.rect(), QtCore.Qt.AlignCenter, "Fai click sulla prima casella in alto a sinistra")
        else:
            qp.drawText(event.rect(), QtCore.Qt.AlignCenter, "Adesso fai click sull'ultima casella in basso a destra")

    def drawCircles(self, event, qp):
        global grid_started, start_x, start_y, click_combo_started
        qp.setPen(QtGui.QColor(255, 0, 0))
        center = QPoint(self.mousex, self.mousey)
        if click_combo_started:
            qp.setPen(QtGui.QColor(20, 20, 255))
            qp.drawEllipse(center, 10, 10)
        elif not grid_started:
            qp.drawEllipse(center, 10, 10)
        else:
            space_y = float(self.mousey - start_y) / 7
            space_x = float(self.mousex - start_x) / 7
            for i in range(8):
                for j in range(8):
                    qp.drawEllipse(QPoint(start_x + (space_x * j), start_y + (space_y * i)), 10, 10)
                    qp.drawPoint(QPoint(start_x + (space_x * j), start_y + (space_y * i)))



print("Cliccare sulla prima casella in alto a sinistra del tavolo di gioco")
app = QtGui.QApplication(sys.argv)
mywindow = mymainwindow()
mywindow.show()
mywindow.activateWindow()
app.exec_()
