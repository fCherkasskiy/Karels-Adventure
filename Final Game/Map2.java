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

public class Map2 extends JPanel
//Cavnas,JPanel  
{
   int width;
   int height;
   int scale;
   int len;
   int transx = 0;
   int transy = 0;
   private Integer[] map;
   public Map2(String file, int sc, int x, int y) throws Exception
   {
      
      
      //this.setOpaque(true);
      //this.setSize(1000,400);
      //System.out.println("sup dud");
      String filename = file;//JOptionPane.showInputDialog("What file?");
      Scanner infile = new Scanner(new File(filename));
      int longi=Integer.parseInt(infile.next());
      int latit=Integer.parseInt(infile.next());
      for (int i=0;i<4;i++)
      {
         Object asdf = infile.next();
      }
      len = longi*latit;
      map = new Integer[len];
      width = longi*10;
      height = latit*10;
      
      
      
      scale = sc;
      
      transx=x;
      transy=y;
      //System.out.println(Integer.toString(len)+" "+Integer.toString(longi)+" "+Integer.toString(latit));
      Integer[] mapfile = new Integer[len];
      int val=0;
      int token1=0;
      while (infile.hasNext() ) {
         token1 = Integer.parseInt(infile.next());
         mapfile[val]=token1;
         val++;
         //System.out.println(Integer.toString(val) +"    "+ Integer.toString(token1));
      
      }
      setMap(mapfile);
   }  
   public static void main(String[] args) throws Exception
   {
   }
   public void setMap(Integer[] bgd)
   {
      map=bgd;
      //System.out.println("hello from set");
      repaint();
   }
   public Integer[] getMap()
   {
      //System.out.println("hello from get");
      return map;
   }
   public void paintComponent(Graphics h)
   {
      //System.out.println("paitning");
      super.paintComponent(h);
      for (int s=0;s<10000;s++)
      {
         h.setColor(new Color(173,216,230,255));
         h.fillRect(s%100*scale,Math.round(s/100)*scale,scale,scale);
      }
      Integer[] disp = new Integer[len];
      disp=getMap();
      //System.out.println("Starting to render map.");
      int r=0;
      int g=0;
      int b=0;
      for (int i=0; i<len;i++)
      {
         //System.out.println(i);
         if (disp[i]==0){r=0;b=0;g=0;}
         if (disp[i]==1){r=255;b=255;g=255;}
         if (disp[i]==2){r=255;b=0;g=0;}
         if (disp[i]==3){r=0;b=255;g=0;}
         if (disp[i]==4){r=0;b=0;g=255;}
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
         //System.out.println(transy);
         //System.out.println((i/(width/10))*scale+transy);
         h.fillRect((i%(width/10))*scale-transx,(i/(width/10))*scale-transy,scale,scale);
      }
   }   
}