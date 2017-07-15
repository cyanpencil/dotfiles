from random import randint
import time
import curses

myscreen = curses.initscr()
(max_y, max_x) = myscreen.getmaxyx()
curses.noecho()
curses.start_color()
curses.use_default_colors()
light_blue = 4
dark_blue = 1
black = 2
brown = 3
trail_color = [5,6,7,8]
inventory_color = 9
curses.init_pair(light_blue, 33, -1)
curses.init_pair(dark_blue, 17, -1)
curses.init_pair(black, 16, -1)
curses.init_pair(brown, 94, -1)
curses.init_pair(trail_color[0], 39, -1)
curses.init_pair(trail_color[1], 27, -1)
curses.init_pair(trail_color[2], 20, -1)
curses.init_pair(trail_color[3], 17, -1)
curses.init_pair(inventory_color, 21, -1)
curses.curs_set(0)
myscreen.keypad(1)

asteroids = []
trail = []
a = ""
x = 50
y = 50
frame = 0

inventory = ["Erba", "Droga"]

def draw_status():
    myscreen.addstr(1, 2, "Score: " + str(frame))
    myscreen.addstr(3, 1, "-" * (max_x - 2), curses.A_UNDERLINE)

def draw_inventory():
    myscreen.addstr(max_y - 10, 0, "*----Inventory-----*")
    for i in range(1, 10):
        myscreen.addstr(max_y - 10 + i, 0, "|                  |")
    for i, e in enumerate(inventory):
        myscreen.addstr(max_y - 8 + i, 2, inventory[i], curses.color_pair(inventory_color))
    myscreen.addstr(max_y -1, 0, "*------------------*")

def redraw():
    global myscreen
    myscreen.erase()
    myscreen.border(0)
    for e,t in enumerate(trail):
        if (frame - t[2] < 4):
            myscreen.addstr(t[1], t[0], '*', curses.color_pair(trail_color[frame - t[2]]))
        elif (frame - t[2] >= 5):
            del trail[e]
    for ast in asteroids:
        myscreen.addstr(ast[1], ast[0], 'x', curses.color_pair(brown))
    myscreen.addstr(y, x, 'o', curses.color_pair(light_blue) | curses.A_BOLD)
    draw_status()
    draw_inventory()

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
            myscreen.addstr(int(max_y/2 -1) + 2*e, int(max_x/2 - len(s)/2), s)
            myscreen.addstr(int(max_y/2 -1) + 2*e, int(max_x/2 - len(o)/2), o, curses.A_UNDERLINE)
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

def game_over():
    myscreen.erase()
    myscreen.addstr(int(max_y/2), int(max_x/2 - len("Game over")/2), "Game over")
    myscreen.refresh()
    time.sleep(1)
    main_menu()


def update_asteroids():
    global asteroids
    for k in range(randint(0, int(frame/10))):
        asteroid = [max_x -2, randint(4, max_y - 2)]
        asteroids += [asteroid]
    for e,ast in enumerate(asteroids):
        ast[0] -= 1
        if ast[0] < 1:
            del asteroids[e]

def update_trail():
    global trail
    t = [x, y, frame + 1]
    trail += [t]


def play():
    global x, y, frame
    myscreen.timeout(200)
    a = 0
    while a != ord('q'):
        frame += 1
        update_asteroids()
        redraw()
        if check_collisions():
            game_over()
            break
        myscreen.refresh()

        a = myscreen.getch()

        if a == ord('w') or a == curses.KEY_UP:
            y -= 1
        if a == ord('a') or a == curses.KEY_LEFT:
            x -= 1
        if a == ord('s') or a == curses.KEY_DOWN:
            y += 1
        if a == ord('d') or a == curses.KEY_RIGHT:
            x += 1
        if a == ord('c'):
            myscreen.clear()

        update_trail()
    curses.endwin()

main_menu()


# a = myscreen.getch()
# curses.endwin()
# print(a)

# curses.endwin()
# for i in range(0, curses.COLORS):
    # print(i)

print(trail)
