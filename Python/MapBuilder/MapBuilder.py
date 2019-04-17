# Ari Bobesh, Suhas N.

from Tkinter import Tk, Canvas


final = 0
fileopened = 0

# filename = raw_input("Input filename: ")
filename = "new"

if filename == "new":
    # size1 = raw_input("Canvas width: ")
    # size2 = raw_input("Canvas height: ")
    size1, size2 = 200, 200
elif filename == "final":
    filename = raw_input("Input filename: ")
    final = 1
    r = open(filename, "r")
    r2 = r.read().split()
    size1 = int(r2[0])*10
    size2 = int(r2[1])*10
    r.close()
    fileopened = 1
else:
    r = open(filename, "r")
    r2 = r.read().split()
    size1 = int(r2[0]) * 10
    size2 = int(r2[1]) * 10
    r.close()
    fileopened = 1

w, h = int(size1), int(size2)
stop, run = False, True
x, y = 0, 0
x2, y2 = 0, 0
x3, y3 = 0, 0
arr = []

colors = ["black", "green",
          "red", "blue",

          "white", "#FFA500",
          "#FFFF00", "#00FFFF",

          "#0000A0", "#ADD8E6",
          "#800080", "#FF00FF",

          "#808000", "#008000",
          "#A52A2A", "#808080"]

counter, counter2 = 0, 0
xin, yin = 0, 0
tool, color = "", "black"

root, root2 = Tk(), Tk()
canvas, canvas2 = Canvas(root, width=w, height=h, bg="white"), Canvas(root2, width=80, height=600, bg="white")
canvas.pack()
canvas2.pack()


def setup(start, listimp):
    global xin, yin, arr
    root.update()
    o = 0
    arr = []
    for wid in range(h / 10):
        row = []
        for leng in range(w / 10):
            if start:
                val = 1
            else:
                xin, yin = o % (w / 10), o / (w / 10)
                val = int(listimp[o])
                o += 1
            row.append(val)  # death, power up, special terrain, color
        arr.append(row)


for i in range(640, 0, -1):
    for j in range(480, 0, -1):
        if j == 80 or j == 160 or j == 240 or j == 320 or j == 400 or j == 480:
            canvas2.create_rectangle((0, 0), (80, j),
                                     outline="black", fill="white")
for m in range(w / 10):
    canvas.create_line(m * 10, 0, m * 10, h)

for n in range(h / 10):
    canvas.create_line(0, n * 10, w, n * 10)

canvas2.create_rectangle((0, 400), (40, 440),
                         outline="black", fill="black")  # black
canvas2.create_rectangle((40, 400), (80, 440),
                         outline="green", fill="green")  # green
canvas2.create_rectangle((0, 440), (40, 480),
                         outline="red", fill="red")  # red
canvas2.create_rectangle((40, 440), (80, 480),
                         outline="blue", fill="blue")  # blue

canvas2.create_rectangle((0, 360), (40, 320),
                         outline="white", fill="white")  # white
canvas2.create_rectangle((40, 360), (80, 320),
                         outline="#FFA500", fill="#FFA500")  # orange
canvas2.create_rectangle((0, 400), (40, 360),
                         outline="#FFFF00", fill="#FFFF00")  # yellow
canvas2.create_rectangle((40, 400), (80, 360),
                         outline="#00FFFF", fill="#00FFFF")  # cyan

canvas2.create_rectangle((0, 280), (40, 240),
                         outline="#0000A0", fill="#0000A0")  # d blue
canvas2.create_rectangle((40, 280), (80, 240),
                         outline="#AEE8E6", fill="#AEE8E6")  # light blue
canvas2.create_rectangle((0, 320), (40, 280),
                         outline="#800080", fill="#800080")  # purple
canvas2.create_rectangle((40, 320), (80, 280),
                         outline="#FF00FF", fill="#FF00FF")  # pink

canvas2.create_rectangle((0, 200), (40, 160),
                         outline="#808000", fill="#808000")  # olive
canvas2.create_rectangle((40, 200), (80, 160),
                         outline="#008000", fill="#008000")  # green
canvas2.create_rectangle((0, 240), (40, 200),
                         outline="#A52A2A", fill="#A52A2A")  # brown
canvas2.create_rectangle((40, 240), (80, 200),
                         outline="#808080", fill="#808080")  # gray

x = 1000


def where_click():
    global tool, color, counter2, stop, run
    global counter, counter2, x, y, x2, y2, tool, color, stop, run, arr, xin, yin, filename
    counter2 = 0

    def getorigin(eventorigin):
        global counter, counter2, x, y, x2, y2, tool, color, stop, run, arr, xin, yin, filename
        x = eventorigin.x
        y = eventorigin.y
        counter2 = 1
        root.bind('c', clear)
        root.bind('s', save)
        root.bind('o', openit)
        tool = "paintbrush"
        if tool == "paintbrush":
            if x % 10 <= 9 and y % 10 <= 9:
                x = x - x % 10
                y = y - y % 10

                if final == 0:
                    canvas.create_rectangle(x + 1, y + 1, x + 9, y + 9, outline=color, fill=color)
                else:
                    canvas.create_rectangle(x, y, x + 10, y + 10, outline=color, fill=color)

                arr[y / 10][x / 10] = colors.index(color)
                """arr[x/10][y/10][0]=1
                arr[x/10][y/10][1]=1
                arr[x/10][y/10][2]=1
                arr[x/10][y/10][3]=1
                print arr[x][y]"""
    if not stop:
        root.bind('<B1-Motion>', getorigin)
        root.bind('<Button-1>', getorigin)
    if run:
        canvas.after(100, where_click)


def where_click2():
    def getorigin2(eventorigin2):
        global counter, x, y, x2, y2, counter2, tool, color, stop
        x = eventorigin2.x
        y = eventorigin2.y
        if x < 80:
            color = colorpick(x, y, "null")
    root2.bind('<B1-Motion>', getorigin2)
    root2.bind('<Button-1>', getorigin2)
    canvas2.after(100, where_click2)


def colorpick(_x, _y, inp):
    global color
    color = "white"
    if not inp == "null":
        color = colors[inp]
    else:
        if _x < 80:
            if _y > 400:
                if _x < 40:
                    if _y < 440:
                        color = "black"
                    else:
                        color = "red"
                else:
                    if _y < 440:
                        color = "green"
                    else:
                        color = "blue"
            elif _y > 320:
                if _x < 40:
                    if _y < 360:
                        color = "white"
                    else:
                        color = "#ffff00"
                else:
                    if _y < 360:
                        color = "#ffa500"
                    else:
                        color = "#00ffff"
            elif _y > 240:
                if _x < 40:
                    if _y < 280:
                        color = "#0000A0"
                    else:
                        color = "#800080"
                else:
                    if _y < 280:
                        color = "#ADD8E6"
                    else:
                        color = "#FF00FF"
            elif _y > 160:
                if _x < 40:
                    if _y < 200:
                        color = "#808000"
                    else:
                        color = "#A52A2A"
                else:
                    if _y < 200:
                        color = "#008000"
                    else:
                        color = "#808080"
    return color


##############


def clear(evt):
    global x, y, x2, y2, x3, y3, counter, tool, counter2
    x = 0
    y = 0
    x2 = 0
    y2 = 0
    x3 = 0
    y3 = 0
    counter = 0
    tool = ''
    counter2 = 0
    canvas.create_rectangle((0, 0), (w, h),
                            outline="white", fill="white")


def save(evt):
    global filename
    if filename == "new":
        filename = raw_input("Save as: ")
    f = open(filename, "w")
    xs = w / 10
    ys = h / 10
    f.write(str(xs) + "\n")
    f.write(str(ys) + "\n")
    for t in range(ys):
        for v in range(xs):
            f.write(str(arr[t][v])+" ")
        f.write("\n")
    f.close()


def openit(evt):
    global xin, yin, color
    f = open(filename, "r")
    inlist = f.read().split()
    xs = int(inlist[0])
    inlist.pop(0)
    ys = int(inlist[0])
    inlist.pop(0)
    setup(False, inlist)
    for u in range(xs * ys):
        xin = u % xs
        yin = u / xs
        tr = int(inlist[u])
        color = colorpick(100, 0, tr)
        if final == 0:
            canvas.create_rectangle(xin * 10 + 1, yin * 10 + 1, xin * 10 + 9, yin * 10 + 9, outline=color, fill=color)
        else:
            canvas.create_rectangle(xin * 10, yin * 10, xin * 10 + 10, yin * 10 + 10, outline=color, fill=color)
    f.close()


where_click()
where_click2()
setup(True, False)
if fileopened == 1:
    openit(filename)
root.mainloop()
