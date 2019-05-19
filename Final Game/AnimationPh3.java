import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.lang.Math.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class AnimationPh3 extends JPanel implements ActionListener, KeyListener
//Cavnas,JPanel  
{
   Timer tm = new Timer(30,this);
   int scale=0;
   String maptype="";
   int width=0,height=0;
   int speed=8;
   int jump=0;//50 for map, 30 for pc, 15 for long
   int bottom;//200 actually the top border (everything is flipped)
   int top = 180;//same as above
   int left;//=width-30;
   String filename = "";
   int jumptm=0;
   int longi;//=width/scale;
   int latit;//=height/scale;
   boolean jumping=false;
   boolean firstrun=true;
   Integer[] groundy;
   private Integer[] gnd;
   int border=0;//350
   int fakex=0;
   int fakey=0;
   int x = 0, y, velx = 0, vely = 0, g = 2;
   public AnimationPh3(String file,String type, int platform,int j,int sc) throws Exception
   {
      filename = file;
      maptype = type;
      System.out.println(maptype);
      Scanner infile = new Scanner(new File(filename));
      jump = j;
      scale = sc;
      
      width = Integer.parseInt(infile.next())*scale;
      height = Integer.parseInt(infile.next())*scale;
      for (int i=0;i<4;i++)
      {
         Object asdf = infile.next();
      }
      bottom = height;
      y=bottom;
      left = width-10-scale;
      System.out.println(left);
      longi = width/scale;
      latit = height/scale;
      addKeyListener(this);
      //this.setOpaque(false);
      //this.setBackground(new Color(0,0,0,0));
      //this.setSize(10,10);
      //String filename = "long.txt";//JOptionPane.showInputDialog("What file?");
      
      int len = longi*latit;
      System.out.println(Integer.toString(len)+" "+Integer.toString(longi)+" "+Integer.toString(latit));
      Integer[] mapfile = new Integer[len];
      int val=0;
      int token1=0;
      while (infile.hasNext() ) {
         token1 = Integer.parseInt(infile.next());
         mapfile[val]=token1;
         val++;
         //System.out.println(Integer.toString(val) +"    "+ Integer.toString(token1));
      }
      groundy = new Integer[longi];
      for (int i=0;i<longi;i++)
      {
         groundy[i]=0;
      }
      int s = 0;
      for (int t=0; t<longi;t++)
      {
         int p = 0;
         for (int i=0;i<len;i++)
         {
            //System.out.println(i);
            if (i%longi==s)
            {
               if (mapfile[i]==platform)
               {
                  groundy[s]=latit-p;
                                    
               }
               p++;
            
            }
            
         }
         s++;
      }
      saveGnd(groundy);
      for (int r=0; r<longi;r++)
      {
         //System.out.print(Integer.toString(r)+" ");
         //System.out.println(per.groundy[r]);
      }
   }
   public static void main(String[] args) throws Exception
   {

   }
   public void paintComponent(Graphics h)
   {
      
      try
      {
         Map2 bg = new Map2(filename, scale,x-fakex,fakey);
         bg.paintComponent(h);
         bg.setOpaque(true);
      }
      catch (Exception e)
      {
      }
      super.paintComponent(h);
      //System.out.println("did this work?");
      ImageIcon i = new ImageIcon("./Sprites/north20.png");
      i.paintIcon(this, h, fakex, height-y-fakey);
      //h.setColor(Color.RED);
      //h.fillRect(x,height-y,10,10);
      tm.start();
   }
   public void saveGnd(Integer[] grnd)
   {
      //System.out.println("hello from aniset");
      gnd=grnd;
   }
   public Integer[] getGnd()
   {
      //System.out.println("hello from aniget");
      return gnd;
   }
   public void actionPerformed(ActionEvent e)
   {
      if (fakex<350)
      {
         fakex=x;
      }
      fakey=0;
      int xr=x+19;
      xr=xr/scale;
      int yr=y+19;
      Integer[] grounddata=new Integer[longi];
      if (x < border)
      {
         velx=0;
         x=border;
      }
      //System.out.println(xr);
      if (x >= left)
      {
         System.out.println("Success");
         x=0;
         fakex=0;
         velx=0;
         try
         {
            Menu success= new Menu(true);
         }
         catch(Exception a)
         {
            System.out.println("oops");
         }
      }
      if (getGnd()!=null)
      {
         //System.out.print(maptype);
         firstrun=false;
         grounddata=getGnd();
         int slope=0;
         //System.out.println(x);
         slope=Math.abs(grounddata[x/scale]-grounddata[xr]);
         //System.out.println(Integer.toString(slope)+"   "+Integer.toString(grounddata[x/scale])+"   "+Integer.toString(grounddata[xr]));
         if ((maptype.equals("map")&&jumping==true)||(maptype.equals("parcour")&&g==2))//for parcour do g==2
         {  
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if you're jumping
            //System.out.println("jump");
         }
         else if (slope>2 && ((velx==-speed && (Math.max(grounddata[x/scale],grounddata[xr])==grounddata[x/scale]))||(velx==speed && Math.max(grounddata[x/scale],grounddata[xr])==grounddata[xr])))//too steep and (not gravity right or not gravity left or not jumping)
         {
            velx=-velx;//is you're not jumping, and ur slope is too steep
         }
         else
         {
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if all above are false
         }
      }
      if ((-scale>yr-Math.max(grounddata[xr]*scale,grounddata[x/scale]*scale))&&maptype.equals("parcour"))
      {
         top=0; // for parcour map
      }
      if (y > bottom)
      {
         //System.out.println("antigravity!!!");
         // y=bottom;
//          if (vely<0)
//          {
//             vely=0;
//          }
//          g=0;
         System.out.println(y);
         fakey=height-y;
      }
      if (y < top)
      {
         y=top;
         if (vely<0)
         {
            vely=0;
            if (jumping==true)
            {
               speed=speed;
            }
            jumping=false;
         }
         g=0;
      }
      if (y>=top)
      {
         //System.out.println("gravity!!");
         g=2;
         vely=vely-g;
         y=y+vely;
      }

      x=x+velx;
      repaint();
      //System.out.println(yr-Math.max(grounddata[xr]*scale,grounddata[x/scale]*scale));
      if (yr<=50)
         {
            System.out.print("You died. Awaiting respawn.");
            x=0;
            y=600;
            fakex=0;
            fakey=0;
         }
   }
   public void keyPressed(KeyEvent e)
   {
      int c = e.getKeyCode();
      
      if (c == KeyEvent.VK_LEFT)
      {
         velx=-speed;
         System.out.println("left");
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velx=speed;
         System.out.println("right");
      }
      if (c == KeyEvent.VK_UP)
      {
         if (y<=top)
         {
            vely=jump;
            speed=speed;
            jumping=true;
            System.out.println("up");
         }
         
      }
   }
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e)
   {
      velx=0;
      //vely=0;
   }
}