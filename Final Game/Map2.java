import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;
/*****************************************************************
	* A Map is a background that reads and stores information about its colors.
	* A Map knows its display location.
   * A Map also knows how to draw Maps.
	* @author Ari Bobesh
	* @version 3.0
	****************************************************************/
public class Map2 extends JPanel
{
   int width;
   int height;
   int scale;
   int len;
   int transx = 0;
   int transy = 0;
   private Integer[] map;
   /************************************************************* 
	 * Constructs a map with colors defined by the file and x and y displacement by parameters from Animation.
	 * @param file    level filename
    * @param sc    level scale factor
    * @param x    x displacement for map scrolling
     @param y    y displacement for map scrolling
    * @throws FileNotFoundException     throws an exception if the file does not exist
	 **************************************************************/
   public Map2(String file, int sc, int x, int y) throws Exception
   {
      String filename = file;
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
      Integer[] mapfile = new Integer[len];
      int val=0;
      int token1=0;
      while (infile.hasNext() ) {
         token1 = Integer.parseInt(infile.next());
         mapfile[val]=token1;
         val++;
      }
      setMap(mapfile);
   }
   /***************************************************************
	* Sets an array of color values.
   * @param bgd	 background array input
	**************************************************************/  
   public void setMap(Integer[] bgd)
   {
      map=bgd;
      repaint();
   }
   /***************************************************************
	* Gets the array of background color values.
   * @return background color value array
	**************************************************************/
   public Integer[] getMap()
   {
      return map;
   }
   /***************************************************************
	* Paints the background based on the color array.
   * @param h	 graphics object 
	**************************************************************/
   public void paintComponent(Graphics h)
   {
      super.paintComponent(h);
      for (int s=0;s<10000;s++)
      {
         h.setColor(new Color(173,216,230,255));
         h.fillRect(s%100*scale,Math.round(s/100)*scale,scale,scale);
      }
      Integer[] disp = new Integer[len];
      disp=getMap();
      int r=0;
      int g=0;
      int b=0;
      for (int i=0; i<len;i++)
      {
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
         h.fillRect((i%(width/10))*scale-transx,(i/(width/10))*scale-transy,scale,scale);
      }
   }   
}