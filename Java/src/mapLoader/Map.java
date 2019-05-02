package mapLoader;

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

import java.lang.Math.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class Map extends JComponent
//Cavnas,JPanel  
{
   int width=1000,height=400;
   int speed=5;
   int jump=30;
   int bottom=height;//actually the top border (everything is flipped)
   int top = 400;//same as above
   int left=width-30;
   int jumptm=0;
   int len=width/10*height/10;
   int scale=1;
   boolean ready=false;
   private Integer[] map = new Integer[len];
   Integer[] disp = new Integer[len];
   
   int x = 0, y = bottom, velx = 0, vely = 0, g = 2;
   
  
   public Map()
   {
      for (int i=0; i<len;i++)
      {
         //System.out.print(".");
         map[i]=0;
         disp[i]=0;
      }     
   }
   
      
   public static void main(String[] args) throws Exception
   {
      JFrame display = new JFrame();
      
      
      
      display.setTitle("Karel's Adventure");
      display.setVisible(true);
      display.setSize(1010,430);//1010,430
      display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      display.setResizable(false);
      display.setAlwaysOnTop(true);
      Map bg = new Map();
      display.add(bg);
      //all code under here until the next commented line is all for the ground
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
      bg.setMap(mapfile);
      }
   }
   
   public void setMap(Integer[] bgd)
   {
      map=bgd;
      ready=true;
      repaint();
   }
   public Integer[] getMap()
   {
      return map;
   }
   //-----------------------------------------------------------------------------------------
   
   //-----------------------------------------------------------------------------------------

   //-----------------------------------------------------------------------------------------

   //-----------------------------------------------------------------------------------------
   
   public void paintComponent(Graphics h)
   {
      super.paintComponent(h);
      disp=getMap();
      System.out.println("Starting to render map.");
      int r=0;
      int g=0;
      int b=0;
      if (ready==false)
      {
      }
      else
      {
         System.out.println(disp[0]);
         for (int i=0; i<len;i++)
         {
            //System.out.println(Integer.toString(i) + "     "+ Integer.toString(disp[i]));
            if (disp[i]==0)
            {
               r=0;
               b=0;
               g=0;
            }//black
            if (disp[i]==1){r=255;b=255;g=255;}//white
            if (disp[i]==2){r=255;b=0;g=0;}//red
            if (disp[i]==3){r=0;b=255;g=0;}//blue
            if (disp[i]==4){r=0;b=0;g=255;}//green
            if (disp[i]==5){r=0;b=255;g=255;}//cyan
            if (disp[i]==6){r=255;b=0;g=255;}//yellow
            if (disp[i]==7){r=255;b=0;g=165;}//orange
            if (disp[i]==8){r=0;b=160;g=0;}//db
            if (disp[i]==9){r=173;b=230;g=216;}//lb
            if (disp[i]==10){r=128;b=128;g=0;}//purple
            if (disp[i]==11){r=255;b=255;g=0;}//pink
            if (disp[i]==12){r=128;b=0;g=128;}//olive
            if (disp[i]==13){r=0;b=0;g=128;}//green
            if (disp[i]==14){r=165;b=42;g=42;}//brown
            if (disp[i]==15){r=128;b=128;g=128;}//grey
            h.setColor(new Color(r,g,b,254));
            h.fillRect((i%(width/10))*10*scale,(i/(width/10))*10*scale,10*scale,10*scale);
         }
      }
   }

      
}