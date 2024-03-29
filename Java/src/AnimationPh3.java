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
   private Timer tm = new Timer(30,this);
   String maptype="map";
   private int width=1500, height=600;
   private int speed=5;
   private int jump=50;
   private int bottom=height;//actually the top border (everything is flipped)
   private int top = 180;//same as above
   private int left=width-30;
   private int scale=15;
   private int longi = width / scale;
   private boolean jumping=false;
   boolean firstrun=true;
   private Integer[] groundy;
   private Integer[] gnd;
   private int x = 0, y = bottom, velx = 0, vely = 0, g = 2;
   AnimationPh3() throws Exception {
      addKeyListener(this);
      //this.setOpaque(false);
      //this.setBackground(new Color(0,0,0,0));
      //this.setSize(10,10);
      String filename = "E:\\TJHSST\\Karels-Adventure\\Maps\\map.txt";//JOptionPane.showInputDialog("What file?");
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
         groundy[i]=0;
      int s = 0;
      for (int t=0; t<longi;t++) {
         int p = 0;
         for (int i=0;i<len;i++)
            //System.out.println(i);
            if (i%longi==s)
               if (mapfile[i]==4)
                  groundy[s]=latit-p;
         s++;
      }
      saveGnd(groundy);
   }

   public void paintComponent(Graphics h) {
      try {
         Map2 bg = new Map2();
         bg.paintComponent(h);
         //bg.setOpaque(true);
      }
      catch (Exception e) {
      }
      super.paintComponent(h);
      //System.out.println("did this work?");
      ImageIcon i = new ImageIcon("E:\\TJHSST\\Karels-Adventure\\resources\\north20.png");
      i.paintIcon(this, h, x, height-y);
      //h.setColor(Color.RED);
      //h.fillRect(x,height-y,10,10);
      tm.start();
   }
   private void saveGnd(Integer[] grnd) {
      //System.out.println("hello from aniset");
      gnd=grnd;
   }
   private Integer[] getGnd() {
      //System.out.println("hello from aniget");
      return gnd;
   }
   public void actionPerformed(ActionEvent e) {
      int xr = x + 19;
      xr = xr / scale;
      int yr = y + 19;
      Integer[] grounddata = new Integer[longi];
      if (x < 0) {
         velx = 0;
         x = 0;
      }
      if (x > left) {
         velx = 0;
         x = left;
      }
      if (getGnd()!=null) {
         firstrun = false;
         grounddata = getGnd();
         int slope = 0;
         slope=Math.abs(grounddata[x/scale]-grounddata[xr]);
         System.out.println(slope + "   " + grounddata[x / scale] + "   " + grounddata[xr]);
         if ((maptype.equals("map") && jumping) || (maptype.equals("parcour") && g == 2))//for parcour do g==2
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if you're jumping
            //System.out.println("jump");
         else if (slope > 2 && ((velx==-speed && (Math.max(grounddata[x / scale], grounddata[xr])==grounddata[x / scale])) || (velx == speed && Math.max(grounddata[x / scale], grounddata[xr]) == grounddata[xr])))//too steep and (not gravity right or not gravity left or not jumping)
            velx=-velx;//is you're not jumping, and ur slope is too steep
         else {
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if all about are false
         }
      }
      if ((-10 > yr - Math.max(grounddata[xr] * scale, grounddata[x / scale] * scale)) && maptype.equals("parcour"))
         top=0; // for parcour map
      if (y > bottom) {
         //System.out.println("antigravity!!!");
         y=bottom;
         if (vely<0)
            vely=0;
         g=0;
      }
      if (y < top) {
         y=top;
         if (vely<0) {
            vely=0;
            jumping=false;
         }
         g=0;
      }
      if (y >= top) {
         //System.out.println("gravity!!");
         g=2;
         vely=vely-g;
         y=y+vely;
      }


      x=x+velx;
      repaint();
      //System.out.println(yr-Math.max(grounddata[xr]*scale,grounddata[x/scale]*scale));
      if (yr<=70) {
         System.out.print("You died. Awaiting respawn.");
         System.exit(0);
      }
   }
   public void keyPressed(KeyEvent e) {
      int c = e.getKeyCode();
      if (c == KeyEvent.VK_LEFT) {
         velx=-speed;
         System.out.println("left");
      }
      if (c == KeyEvent.VK_RIGHT) {
         velx=speed;
         System.out.println("right");
      }
      if (c == KeyEvent.VK_UP) {
         if (y<=top) {
            vely=jump;
            jumping=true;
            System.out.println("up");
         }
      }
   }
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e) {
      velx=0;
      vely=0;
   }
}