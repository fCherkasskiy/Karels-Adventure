# Ari Bobesh, Suhas N.

from Tkinter import Tk, Canvas

final = 0
fileopened = 0
filename = raw_input("Input filename: ")

if filename == "new":
    size1 = raw_input("Canvas width: ")
    size2 = raw_input("Canvas height: ")
elif filename == "final":
    filename = raw_input("Input filename: ")
    final = 1
    r = open(filename, "r")
    r2 = r.read().split()
    size1, size2 = int(r2[0]) * 10, int(r2[1]) * 10
    r.close()
    fileopened = 1
else:
    r = open(filename, "r")
    r2 = r.read().split()
    size1, size2 = int(r2[0]) * 10, int(r2[1]) * 10
    r.close()
    fileopened = 1

w, h = int(size1), int(size2)
stop, run = False, True
x, y = 0, 0
x2, y2 = 0, 0
x3, y3 = 0, 0
arr = []

colors = ["#808000", "#008000",
          "#A52A2A", "#808080",

          "#0000A0", "#ADD8E6",
          "#800080", "#FF00FF",

          "white", "#FFA500",
          "#FFFF00", "#00FFFF",

          "black", "green",
          "red", "blue"]

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

canvas2.create_rectangle((0, 400), (40, 440), outline=colors[12], fill=colors[12])  # black
canvas2.create_rectangle((40, 400), (80, 440), outline=colors[13], fill=colors[13])  # green
canvas2.create_rectangle((0, 440), (40, 480), outline=colors[14], fill=colors[14])  # red
canvas2.create_rectangle((40, 440), (80, 480), outline=colors[15], fill=colors[15])  # blue

canvas2.create_rectangle((0, 360), (40, 320), outline=colors[8], fill=colors[8])  # white
canvas2.create_rectangle((40, 360), (80, 320), outline=colors[9], fill=colors[9])  # orange
canvas2.create_rectangle((0, 400), (40, 360), outline=colors[10], fill=colors[10])  # yellow
canvas2.create_rectangle((40, 400), (80, 360), outline=colors[11], fill=colors[11])  # cyan

canvas2.create_rectangle((0, 280), (40, 240), outline=colors[4], fill=colors[4])  # d blue
canvas2.create_rectangle((40, 280), (80, 240), outline=colors[5], fill=colors[5])  # light blue
canvas2.create_rectangle((0, 320), (40, 280), outline=colors[6], fill=colors[6])  # purple
canvas2.create_rectangle((40, 320), (80, 280), outline=colors[7], fill=colors[7])  # pink

canvas2.create_rectangle((0, 200), (40, 160), outline=colors[0], fill=colors[0])  # olive
canvas2.create_rectangle((40, 200), (80, 160), outline=colors[1], fill=colors[1])  # green
canvas2.create_rectangle((0, 240), (40, 200), outline=colors[2], fill=colors[2])  # brown
canvas2.create_rectangle((40, 240), (80, 200), outline=colors[3], fill=colors[3])  # gray

x = 1000


def where_click():
    global counter2, x, y, tool, color, stop, run, arr
    counter2 = 0

    def getorigin(eventorigin):
        global counter2, x, y, tool, color, stop, run, arr
        x, y = eventorigin.x, eventorigin.y
        counter2 = 1
        root.bind('c', clear)
        root.bind('s', save)
        root.bind('o', openit)
        tool = "paintbrush"
        if x % 10 <= 9 and y % 10 <= 9:
            x, y = x - x % 10, y - y % 10
            if final == 0:
                canvas.create_rectangle(x + 1, y + 1, x + 9, y + 9, outline=color, fill=color)
            else:
                canvas.create_rectangle(x, y, x + 10, y + 10, outline=color, fill=color)
            arr[y / 10][x / 10] = colors.index(color)
    if not stop:
        root.bind('<B1-Motion>', getorigin)
        root.bind('<Button-1>', getorigin)
    if run:
        canvas.after(100, where_click)


def where_click2():
    def getorigin2(eventorigin2):
        global counter, x, y, x2, y2, counter2, tool, color, stop
        x, y = eventorigin2.x, eventorigin2.y
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
                        color = colors[12]
                    else:
                        color = colors[14]
                else:
                    if _y < 440:
                        color = colors[13]
                    else:
                        color = colors[15]
            elif _y > 320:
                if _x < 40:
                    if _y < 360:
                        color = colors[8]
                    else:
                        color = colors[10]
                else:
                    if _y < 360:
                        color = colors[9]
                    else:
                        color = colors[11]
            elif _y > 240:
                if _x < 40:
                    if _y < 280:
                        color = colors[4]
                    else:
                        color = colors[6]
                else:
                    if _y < 280:
                        color = colors[5]
                    else:
                        color = colors[7]
            elif _y > 160:
                if _x < 40:
                    if _y < 200:
                        color = colors[0]
                    else:
                        color = colors[2]
                else:
                    if _y < 200:
                        color = colors[1]
                    else:
                        color = colors[3]
    return color


# ===============================================================================


def clear(evt):
    global x, y, x2, y2, x3, y3, counter, tool, counter2
    x, y = 0, 0
    x2, y2 = 0, 0
    x3, y3 = 0, 0
    counter, counter2 = 0, 0
    tool = ''
    canvas.create_rectangle((0, 0), (w, h), outline="white", fill="white")


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
