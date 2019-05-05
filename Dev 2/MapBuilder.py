# Ari Bobesh, Suhas N.

from Tkinter import Tk,Canvas, Button
from PIL import Image, ImageTk
from random import *
import time
import string
global filename
final=0
fileopened=0
filename="map.txt"
filename=raw_input("Input filename: ")
if filename=="new":
   size1=raw_input("Canvas width: ")
   size2=raw_input("Canvas height: ")
elif filename=="final":
   filename=raw_input("Input filename: ")
   final=1
   r=open(filename,"r")
   r2=r.read().split()
   size1=int(r2[0])*10
   size2=int(r2[1])*10
   r.close()
   fileopened=1
else:
   r=open(filename,"r")
   r2=r.read().split()
   size1=int(r2[0])*10
   size2=int(r2[1])*10
   r.close()
   fileopened=1
w,h=int(size1),int(size2)
global counter, counter2, x, y, x2, y2, tool, color, stop, run,arr
stop = False
run=True
x=0
y=0
x2=0
y2=0
x3=0
y3=0
arr=[]
row=[]
col=[]
colors=["black","#ffffff","red","blue","green","#00ffff","#ffff00","#ffa500","#0000A0","#ADD8E6","#800080","#FF00FF","#808000","#008000","#A52A2A","#808080"]

counter=0
tool=''
counter2=0
color="black"
root=Tk()
canvas=Canvas(root,width=w,height=h,bg="white")
canvas.pack()
root2=Tk()
canvas2=Canvas(root2,width=80,height=600,bg="white")
canvas2.pack()
def setup(start,listimp):
   root.update()
   global arr
   o=0
   arr=[]
   #print listimp
   for wid in range(h/10):
      
      row=[]
      for leng in range(w/10):
         if start==True:
            val=1
         else:
            xin=o%(w/10)
            yin=o/(w/10)
            #print yin,xin
            #print o
            val=int(listimp[o])
            o+=1
            #if val!=1:
            #   print "yay"
               
         row.append(val)#death,powerup,special terrain,color
         """In the form 1-white,death,powerup 2-white,death,nopowerup..."""
         """for testing purposes only color: 0-black,1-white,2-red,3-blue,
            4-green,5-aqua,6-yellow,7-orange"""
      arr.append(row)

   #print arr
   
#print arr
for i in range(640,0,-1):
   for j in range(480,0,-1):
      if j==80 or j==160 or j==240 or j==320 or j==400 or j==480:
         canvas2.create_rectangle((0,0),\
                        (80,j),\
                        outline="black", fill = "white")
for m in range(w/10):
   canvas.create_line(m*10,0,m*10,h)
for n in range(h/10):
   canvas.create_line(0,n*10,w,n*10)                          
canvas2.create_rectangle((0,400),\
                        (40,440),\
                        outline="black", fill = "black")
canvas2.create_rectangle((40,400),\
                        (80,440),\
                        outline="green", fill = "green")
canvas2.create_rectangle((0,440),\
                        (40,480),\
                        outline="red", fill = "red")
canvas2.create_rectangle((40,440),\
                        (80,480),\
                        outline="blue", fill = "blue")
                        
canvas2.create_rectangle((0,360),\
                        (40,320),\
                        outline="#ffffff", fill = "#ffffff")#white
canvas2.create_rectangle((40,360),\
                        (80,320),\
                        outline="#ffa500", fill = "#ffa500")#orange
canvas2.create_rectangle((0,400),\
                        (40,360),\
                        outline="#ffff00", fill = "#ffff00")#yellow
canvas2.create_rectangle((40,400),\
                        (80,360),\
                        outline="#00ffff", fill = "#00ffff")#cyan

canvas2.create_rectangle((0,280),\
                        (40,240),\
                        outline="#0000A0", fill = "#0000A0")#d blue
canvas2.create_rectangle((40,280),\
                        (80,240),\
                        outline="#ADD8E6", fill = "#ADD8E6")#l blue
canvas2.create_rectangle((0,320),\
                        (40,280),\
                        outline="#800080", fill = "#800080")#purple
canvas2.create_rectangle((40,320),\
                        (80,280),\
                        outline="#FF00FF", fill = "#FF00FF")#pink

canvas2.create_rectangle((0,200),\
                        (40,160),\
                        outline="#808000", fill = "#808000")#olive
canvas2.create_rectangle((40,200),\
                        (80,160),\
                        outline="#008000", fill = "#008000")#green
canvas2.create_rectangle((0,240),\
                        (40,200),\
                        outline="#A52A2A", fill = "#A52A2A")#brown
canvas2.create_rectangle((40,240),\
                        (80,200),\
                        outline="#808080", fill = "#808080")#grey
x=1000
global pmg6
def whereClick():
   global tool, color, counter2, stop, run
   counter2=0
   def getorigin(eventorigin):
      global counter,x, y, x2, y2, counter2, tool, color, stop,arr

      x = eventorigin.x
      y = eventorigin.y
      counter2=1  
      root.bind('c',clear)
      root.bind('s',save)
      root.bind('o',openit)
      tool="paintbrush"
      #print "tool belt accessed"
      if tool=="paintbrush":
         if x%10<=9 and y%10<=9:
            x=x-x%10
            y=y-y%10               
            
            if final==0:
               canvas.create_rectangle(x+1,y+1,x+9,y+9,outline=color, fill=color);
            else:
               canvas.create_rectangle(x,y,x+10,y+10,outline=color, fill=color);
            arr[y/10][x/10]=colors.index(color)
            """arr[x/10][y/10][0]=1
            arr[x/10][y/10][1]=1
            arr[x/10][y/10][2]=1
            arr[x/10][y/10][3]=1
            print arr[x][y]"""
      #print "               " + str(stop)
   if stop==False:
      #print "click"
      root.bind('<B1-Motion>',getorigin)
      root.bind('<Button-1>',getorigin)
   if run==True:
      canvas.after(100,whereClick)
def whereClick2():
   def getorigin2(eventorigin2):
      global counter,x, y, x2, y2, counter2, tool, color, stop
      x = eventorigin2.x
      y = eventorigin2.y
      #print "ok"
      if x<80:
         color=colorpick(x,y,"null")
   root2.bind('<B1-Motion>',getorigin2)
   root2.bind('<Button-1>',getorigin2)
   canvas2.after(100,whereClick2)

def colorpick(x,y,inp):
   color="white"
   #"#0000A0","#ADD8E6","#800080","#FF00FF","#808000","#008000","#A52A2A","#808080"
   #print "yes"
   if inp==0:
      color="black"
   if inp==1:
      color="#ffffff"
   if inp==2:
      color="red"
   if inp==3:
      color="blue"
   if inp==4:
      color="green"
   if inp==5:
      color="#00ffff"
   if inp==7:
      color="#ffa500"
   if inp==6:
      color="#ffff00"
   if inp==8:
      color="#0000A0"
   if inp==9:
      color="#ADD8E6"
   if inp==10:
      color="#800080"
   if inp==11:
      color="#FF00FF"
   if inp==12:
      color="#808000"
   if inp==13:
      color="#008000"
   if inp==14:
      color="#A52A2A"
   if inp==15:
      color="#808080"
   if inp=="null":
      #print "no"
      if x<80:
            if y>400:
               if x<40:
                  if y<440:
                     
                     color="black"
                  else:
                     
                     color="red"
               else:
                  if y<440:
                     
                     color="green"
                  else:
                     
                     color="blue"
            elif y>320:    
                if x<40:
                  if y<360:
                     
                     color="#ffffff"
                  else:
                     
                     color="#ffff00"
                else:
                  if y<360:
                     
                     color="#ffa500"
                  else:
                     
                     color="#00ffff"
            elif y>240:
               if x<40:
                  if y<280:
                     
                     color="#0000A0"
                  else:
                     
                     color="#800080"
               else:
                  if y<280:
                     
                     color="#ADD8E6"
                  else:
                     
                     color="#FF00FF"
            elif y>160:
               if x<40:
                  if y<200:
                     
                     color="#808000"
                  else:
                     
                     color="#A52A2A"
               else:
                  if y<200:
                     
                     color="#008000"
                  else:
                     
                     color="#808080"
   #print color
   return color

#-------------------------------------------------------------------------------------------------------------------------------------------



#-----------------------------------------------------------------------------------------------------------------------------
def clear(evt):
   x=0
   y=0
   x2=0
   y2=0
   x3=0
   y3=0
   counter=0
   tool=''
   counter2=0
   canvas.create_rectangle((0,0),\
                           (w,h),\
                           outline="white", fill = "white")

def save(evt):
   #print arr
   global filename
   if filename=="new":
      filename=raw_input("Save as: ")
   f=open(filename,"w")
   xs=w/10
   ys=h/10
   f.write(str(xs)+"\n")
   f.write(str(ys)+"\n")
   for t in range(ys):
      for v in range(xs):
         f.write(str(arr[t][v])+" ")
      f.write("\n")  
   f.close()

def openit(evt):
   f=open(filename,"r")
   inlist=f.read().split()
   xs=int(inlist[0])
   inlist.pop(0)
   ys=int(inlist[0])
   
   
   inlist.pop(0)
   #print "the contents is:"+ str(inlist)
   setup(False,inlist)
   for u in range(xs*ys):
      xin=u%xs
      yin=u/xs
      #print yin,xin
      
      tr=int(inlist[u])
      #print tr
      color=colorpick(100,0,tr)
      if final==0:
         canvas.create_rectangle(xin*10+1,yin*10+1,xin*10+9,yin*10+9,outline=color, fill=color);
      else:
         canvas.create_rectangle(xin*10,yin*10,xin*10+10,yin*10+10,outline=color, fill=color);
      #root.update()
      
   f.close()
#-----------------------------------------------------------------------------------------------------------------------------
whereClick()
whereClick2()
setup(True,False)
if fileopened==1:
   openit(filename)

root.mainloop()
