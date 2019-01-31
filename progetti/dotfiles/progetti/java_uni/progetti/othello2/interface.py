#!/usr/bin/env python
#
# Piccolo programma per interfacciare fra di loro engine di othello attraverso la grafica
#
# Dipendenze:
# Python 3
# Qt4 (libreria di python)
# Composition manager (per i sistemi unix, altrimenti non si vede la trasparenza della finestra)
#
# Utilizzo:
# Eseguire ./engine.sh
#
# Attenzione! Non muovere, nè ridimensionare, la finestra su cui è aperto othello.
# Non muovere il mouse durante l'esecuzione della partita



# Intervallo di tempo in secondi tra due screenshot mentre aspetto che il programma grafico
# faccia la sua mossa.
move_waiting_time = 0.1

# Tempo (in secondi) di attesa per evitare click consecutivi troppo veloci. (Alcune applicazioni non
# reggono la velocità supersonica di edax)
time_between_clicks = 0.2

# Tempo (in secondi) da aspettare ad ogni click della procedura per far ricominciare il gioco
# (A volte è necessario aspettare che termini l'animazione di apertura di un menu)
clear_board_click_delay = 1

# Questo valore determina il margine di errore con cui vengono riconosciute le pedine
# Un valore troppo basso renderà il programma troppo preciso, confondendo magari sfumature
# della texture delle pedine con lo sfondo, mentre un valore troppo alto potrebbe far si che
# il programma vede la tavola completamente vuota
acceptable_color_error = 100



import sys, subprocess, time, os, signal
from PyQt4 import *
from PyQt4.QtGui import *
from PyQt4 import QtGui, QtCore
from PyQt4.QtCore import *


EMPTY = 0
BLACK = 2
WHITE = 1
my_color = 1
start_x, start_y = 0, 0
space_x, space_y = 0, 0
points = [[-1 for i in range(8)] for j in range(8)]
board = [[EMPTY for i in range(8)] for j in range(8)]
old_board = [[EMPTY for i in range(8)] for j in range(8)]
easy_board = [[EMPTY for i in range(8)] for j in range(8)]
color = [[-1 for i in range(3)] for j in range(3)]
grid_started = False
grid_set = False
click_combo = []

valid_gtp_commands = ["protocol_version", "name", "version", "known_command", "list_commands", "quit", "boardsize", "clear_board", "komi", "play", "genmove", "list_games", "set_game"]


def print_gtp_error(id, error=""):
    if id is not None:
        print("?%d %s\n" % (id, error))
    else:
        print("? %s\n" % error)
    sys.stdout.flush()


def print_gtp_response(id, response=""):
    if id is not None:
        print("=%d %s\n" % (id, response))
    else:
        print("= %s\n" % response)
    sys.stdout.flush()

def play_gtp():
    global B, board, my_color

    while True:
        command = input().lower().replace("\t", " ")
        if command.startswith("#") or command.isspace():
            continue

        split = command.strip().split(" ")

        try:
            id = int(split[0])
            split = split[1:]
        except ValueError:
            id = None

        if len(split)==0:
            print_gtp_error(id, "syntax error")
            continue

        command, arguments = split[0], split[1:]

        if command == "protocol_version":
            print_gtp_response(id, "2")
        elif command == "name":
            print_gtp_response(id, "Unknown Graphic Program  :D ")
        elif command == "version":
            print_gtp_response(id, "1.337")
        elif command == "known_command":
            if len(arguments)>=1:
                print_gtp_response(id, "true" if arguments[0] in valid_gtp_commands else "false")
            else:
                print_gtp_error(id, "syntax error")
        elif command == "list_commands":
            print_gtp_response(id, "\n".join(valid_gtp_commands))
        elif command == "quit":
            print_gtp_response(id)
            exit(0)
        elif command == "boardsize":
            if len(arguments)>=1 and arguments[0].isdigit():
                if int(arguments[0])==8:
                    print_gtp_response(id)
                else:
                    print_gtp_error(id, "unacceptable size")
            else:
                print_gtp_error(id, "syntax error")
        elif command == "clear_board":
            clear_board()

            print_gtp_response(id)
        elif command == "komi":
            print_gtp_response(id)
        elif command == "play":
            if len(arguments)>=2 and arguments[0] in ["w", "b", "white", "black"]:

                if arguments[1] != "pass":
                    color = WHITE if arguments[0].startswith("w") else BLACK
#                    if (color == my_color) :
#                        print_gtp_error(id, "wrong color")
#                        pass

                    x = ord(arguments[1][0]) - ord('a')
                    y = int(arguments[1][1]) - 1

                    #si dovrebbe mettere un controllo per vedere se la mossa è valida
                    click(points[x][y].x(), points[x][y].y())
                    board[x][y] = color
                    time.sleep(time_between_clicks)
                    print_gtp_response(id)

#                    if B.can_move_here(row, column, player):
#                        B.place_disk(row, column, player)
#                        print_gtp_response(id)
#                    else:
#                        print_gtp_error(id, "illegal move")
                else:
                    print_gtp_response(id)
            else:
                print_gtp_error(id, "syntax error")

        elif command == "genmove":
            #DA IMPLEMENTARE



            if len(arguments)>=1 and arguments[0] in ["w", "b", "white", "black"]:
                my_color = WHITE if arguments[0].startswith("w") else BLACK

                while(read_graphic_move() == None):
                    time.sleep(move_waiting_time)
                    screenshot(copy_board=False)

                print_gtp_response(id, read_graphic_move())
                screenshot(copy_board=True)

#                if not B.can_move(player):
#                    print_gtp_response(id, "pass")
#                else:
#                    avg_output = run_network(player)
#                    row, column = tensorflow_utils.tensor_to_move(avg_output, alpha)
#
#                    if not B.can_move_here(row, column, player):
#                        print_gtp_response(id, "resign")
#                    else:
#                        print_gtp_response(id, move.to_string(row, column))
#                        B.place_disk(row, column, player)
            else:
                print_gtp_error(id, "syntax error")
        elif command == "list_games":
            print_gtp_response(id, "Othello")
        elif command == "set_game":
            if len(arguments)>=1 and arguments[0]=="othello":
                print_gtp_response(id)
            else:
                print_gtp_error(id, "unknown game")
        else:
            print_gtp_error(None, "unknown command")


def click(x, y):
    subprocess.call("xte 'mousemove " + str(x) + " " + str(y) +"'", shell=True)
    subprocess.call("xte 'mouseclick 1'", shell=True)
    subprocess.call("xte 'mousemove 0 0'", shell=True)


def screenshot(copy_board=True):
    global color, board, old_board, points, acceptable_color_error
    screenshot = QPixmap.grabWindow(QApplication.desktop().winId()).toImage()
    QPixmap.grabWindow(QApplication.desktop().winId()).save("screen", "png")


    #mi copio tutto nella vecchia board
    if copy_board:
        for i in range(8):
            for j in range(8):
                old_board[i][j] = board[i][j]

    for i in range(8):
        for j in range(8):
            r, g, b = 0, 0, 0
            for x in range(-1, 2):
                for y in range(-1, 2):
                    r += QtGui.qRed(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
                    g += QtGui.qGreen(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
                    b += QtGui.qBlue(screenshot.pixel(QPoint(points[i][j].x() + x, points[i][j].y() + y)))
            for x, c in enumerate(color):
                if c[0] - acceptable_color_error < r < c[0] + acceptable_color_error:
                    if c[1] - acceptable_color_error < r < c[1] + acceptable_color_error:
                        if c[2] - acceptable_color_error < r < c[2] + acceptable_color_error:
                            board[i][j] = x


def move_to_string(x, y, color):
    letter = chr(x + ord('a'))
    return(str(letter) + str(y))

def read_graphic_move():
    for i in range(8):
        for j in range(8):
            if board[i][j] != EMPTY and old_board[i][j] == EMPTY:
                if board[i][j] == my_color:
                    return move_to_string(i, j + 1, board[i][j])
    return None

def reset_local_board():
    for i in range(8):
        for j in range(8):
            board[i][j] = EMPTY

    board[3][3], board[4][4] = WHITE, WHITE
    board[3][4], board[4][3] = BLACK, BLACK

    for i in range(8):
        for j in range(8):
            old_board[i][j] = board[i][j]

def clear_board():
    global click_combo
    reset_local_board()
    for pos in click_combo:
        click(pos[0], pos[1])
        time.sleep(clear_board_click_delay)

def read_cli_move():
    global cli
    b = cli.readline()
    cli.readline()
    print("Output console: ", b)
    return b[2:-2]

def read_coordinates():
    global points, color, click_combo
    with open("coordinates", "r") as f:
        lines = f.readlines()
        for i in range(3):
            ccc = lines[i].split()
            color[i][0], color[i][1], color[i][2] = int(ccc[0]), int(ccc[1]), int(ccc[2])
        for index, line in enumerate(lines[3:67]):
            numbers = line.split()
            points[int(index / 8)][index % 8] = QPoint(int(numbers[0]), int(numbers[1]))
        len_combo = int(lines[67])
        for line in lines[68:]:
            numbers = line.split()
            click_combo += [(int(numbers[0]), int(numbers[1]))]

app = QtGui.QApplication(sys.argv)
read_coordinates()
reset_local_board()

play_gtp()
