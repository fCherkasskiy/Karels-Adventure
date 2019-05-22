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
   int width;//width of map
   int height;//height of map
   int scale;//scale of map
   int len;//length of map data file
   int transx = 0;//amount of map x displacement for scrolling
   int transy = 0;//amount of map y displacement for scrolling
   private Integer[] map;//creates a data array for colors
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
      //opens and parses the file
      String filename = file;
      Scanner infile = new Scanner(new File(filename));
      int longi=Integer.parseInt(infile.next());
      int latit=Integer.parseInt(infile.next());
      for (int i=0;i<4;i++)
      {
         Object temp = infile.next();//skips a few file lines
      }
      //sets more variables
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
      setMap(mapfile);//saves the map color array
   }
   /***************************************************************
	* Sets an array of color values.
   * @param bgd	 background array input
	**************************************************************/  
   public void setMap(Integer[] bgd)
   {
      map=bgd;//sets the array
      repaint();
   }
   /***************************************************************
	* Gets the array of background color values.
   * @return background color value array
	**************************************************************/
   public Integer[] getMap()
   {
      return map;//gets the array
   }
   /***************************************************************
	* Paints the background based on the color array.
   * @param h	 graphics object 
	**************************************************************/
   public void paintComponent(Graphics h)
   {
      super.paintComponent(h);
      for (int s=0;s<10000;s++)//paints an "infinite" background around the map of sky blue
      {
         h.setColor(new Color(173,216,230,255));
         h.fillRect(s%100*scale,Math.round(s/100)*scale,scale,scale);
      }
      Integer[] disp = new Integer[len];
      disp=getMap();//gets the color array
      int r=0;//sets the red pigment to 0
      int g=0;//sets the green pigment to 0
      int b=0;//sets the blue pigment to 0
      for (int i=0; i<len;i++)//while there is still color data:
      {
         //read the array and set the color
         if (disp[i]==0){r=0;b=0;g=0;}//black
         if (disp[i]==1){r=255;b=255;g=255;}//white
         if (disp[i]==2){r=255;b=0;g=0;}//red
         if (disp[i]==3){r=0;b=255;g=0;}//blue
         if (disp[i]==4){r=0;b=0;g=255;}//green
         if (disp[i]==5){r=0;b=255;g=255;}//cyan
         if (disp[i]==6){r=255;b=0;g=255;}//yellow
         if (disp[i]==7){r=255;b=0;g=165;}//orange
         if (disp[i]==8){r=0;b=160;g=0;}//dark blue
         if (disp[i]==9){r=173;b=230;g=216;}//light blue
         if (disp[i]==10){r=128;b=128;g=0;}//purple
         if (disp[i]==11){r=255;b=255;g=0;}//pink
         if (disp[i]==12){r=128;b=0;g=128;}//olive
         if (disp[i]==13){r=0;b=0;g=128;}//green
         if (disp[i]==14){r=165;b=42;g=42;}//brown
         if (disp[i]==15){r=128;b=128;g=128;}//grey
         h.setColor(new Color(r,g,b,254));//set the color of the object
         h.fillRect((i%(width/10))*scale-transx,(i/(width/10))*scale-transy,scale,scale);//paint a rectangle of that color in the correct spot
      }
   }   
}