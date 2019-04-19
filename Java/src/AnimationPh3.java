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

import java.lang.Math.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class AnimationPh3 extends JPanel implements ActionListener, KeyListener
//Cavnas,JPanel  
{
   Timer tm = new Timer(30,this);
   int width=1000,height=400;
   int speed=5;
   int jump=30;
   int bottom=height;//actually the top border (everything is flipped)
   int top = 400;//same as above
   int left=width-30;
   int jumptm=0;
   int longi=width/10;
   int latit=height/10;
   boolean firstrun=true;
   Integer[] groundy;
   private Integer[] gnd;
   int x = 0, y = bottom, velx = 0, vely = 0, g = 2;
   public AnimationPh3() throws Exception
   {
      addKeyListener(this);
      //this.setOpaque(false);
      //this.setBackground(new Color(0,0,0,0));
      this.setSize(10,10);
      String filename = "map.txt";//JOptionPane.showInputDialog("What file?");
      Scanner infile = new Scanner(new File(filename));
      int longi=Integer.parseInt(infile.next());
      int latit=Integer.parseInt(infile.next());
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
               if (mapfile[i]==4)
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
      super.paintComponent(h);
      //System.out.println("did this work?");
      //ImageIcon i = new ImageIcon("D:\\Users\\Ari\\Documents\\TJHSST\\a_game\\north.png");
      //i.paintIcon(this, h, x, height-y);
      h.setColor(Color.RED);
      h.fillRect(x,height-y,10,10);
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
      if (getGnd()!=null)
      {
         firstrun=false;
         Integer[] grounddata=new Integer[longi];
         grounddata=getGnd();
         int xr=x+17;
         xr=xr/10;
         int yr=y+24;
         System.out.println(yr);
         top=Math.max(grounddata[xr],grounddata[x/10])*10+24+40;
      }
      if (x < 0)
      {
         velx=0;
         x=0;
      }
      if (x > left)
      {
         velx=0;
         x=left;
      }
      if (y > bottom)
      {
         y=bottom;
         if (vely<0)
         {
            vely=0;
         }
         g=0;
      }
      if (y < top)
      {
         y=top;
         if (vely<0)
         {
            vely=0;
         }
         g=0;
      }
      if (y>=top)
      {
         g=2;
         vely=vely-g;
         y=y+vely;
      }
      x=x+velx;
      repaint();
      if (y<=82)
         {
            System.out.print("You died. Awaiting respawn.");
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
            System.out.println("up");
         }
         
      }
   }
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e)
   {
      velx=0;
      vely=0;
   }
}