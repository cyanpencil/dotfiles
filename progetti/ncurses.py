from random import randint
import curses

myscreen = curses.initscr()
(max_y, max_x) = myscreen.getmaxyx()
curses.noecho()
curses.curs_set(0)
myscreen.keypad(1)

asteroids = []
a = ""
x = 50
y = 50

def redraw():
    myscreen.erase()
    myscreen.border(0)
    myscreen.addstr(y, x, 'o')
    for ast in asteroids:
        myscreen.addstr(ast[1], ast[0], 'x')

def select(selection):
    if selection == 1:
        curses.endwin()
        exit()
    if selection == 0:
        play()

def main_menu():
    selection = 0
    a = 0
    while a != 10:
        myscreen.erase()
        myscreen.border(0)
        options = ["Play", "Quit"]
        for e,o in enumerate(options):
            s = o
            if selection == e:
                s = "-> " + s + " <-"
            myscreen.addstr(30 + 2*e, int(max_x/2 - len(s)/2), s)
        myscreen.refresh()
        a = myscreen.getch()
        if a == curses.KEY_UP:
            selection += 1
        if a == curses.KEY_DOWN:
            selection -= 1
        selection = selection % 2
    select(selection)



def check_collisions():
    for ast in asteroids:
        if x == ast[0] and y == ast[1]:
            myscreen.erase()
            myscreen.addstr(20, 20, "Game over")
            return True
    return False

def update_asteroids():
    global asteroids
    for k in range(randint(0, 5)):
        asteroid = [max_x -2, randint(1, max_y - 2)]
        asteroids += [asteroid]
    for ast in asteroids:
        ast[0] -= 1


def play():
    global x, y
    myscreen.timeout(200)
    a = 0
    while a != ord('q'):
        update_asteroids()
        redraw()
        if check_collisions():
            break
        myscreen.refresh()

        a = myscreen.getch()

        if a == ord('w'):
            y -= 1
        if a == ord('a'):
            x -= 1
        if a == ord('s'):
            y += 1
        if a == ord('d'):
            x += 1
        if a == ord('c'):
            myscreen.clear()
    curses.endwin()

main_menu()


# a = myscreen.getch()
# curses.endwin()
# print(a)
