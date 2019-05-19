import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.PrintStream;
import java.lang.Math.*;
/*****************************************************************
	* An Animation is a character that reads and stores information about its properties.
	* An Animation knows its simulated location in x and y as well as it's display location.
   * An Animation also knows how to draw Maps.
	* @author Ari Bobesh
	* @version 3.0
	****************************************************************/
public class AnimationPh3 extends JPanel implements ActionListener, KeyListener 
{
   Timer tm = new Timer(30,this);
   int scale=0;
   String maptype="";
   int width=0,height=0;
   int speed=8;
   int jump=0;
   int bottom;
   int top = 180;
   int left;
   String filename = "";
   int longi;
   int latit;
   boolean jumping=false;
   boolean firstrun=true;
   Integer[] groundy;
   private Integer[] gnd;
   int border=0;
   int fakex=0;
   int fakey=0;
   int x = 0, y, velx = 0, vely = 0, g = 2;
   /************************************************************* 
	 * Constructs a sprite with interactive properties defined by the level file.
    * Also parses the level file for data relevant to the sprite and saves it.
	 * @param file    level filename
    * @param type    level type (parcour or map)
    * @param platform    the color of the tangible platforms
    * @param j    jump velocity
    * @param sc    level scale factor
    * @throws FileNotFoundException     throws an exception if the file does not exist
	 **************************************************************/
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
      int len = longi*latit;
      System.out.println(Integer.toString(len)+" "+Integer.toString(longi)+" "+Integer.toString(latit));
      Integer[] mapfile = new Integer[len];
      int val=0;
      int token1=0;
      while (infile.hasNext() ) {
         token1 = Integer.parseInt(infile.next());
         mapfile[val]=token1;
         val++;
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
   }
   /***************************************************************
	* Paints the moving sprite as well as the background.
   * Catches ClassNotFoundException
   * @param h	 graphics object 
	**************************************************************/
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
      ImageIcon i = new ImageIcon("./Sprites/north20.png");
      i.paintIcon(this, h, fakex, height-y-fakey);
      tm.start();
   }
   /***************************************************************
	* Sets an array of ground y values.
   * @param grnd	 ground array input
	**************************************************************/
   public void saveGnd(Integer[] grnd)
   {
      gnd=grnd;
   }
   /***************************************************************
	* Gets the array of ground y values.
   * @return ground y-value array
	**************************************************************/
   public Integer[] getGnd()
   {
      return gnd;
   }
   /***************************************************************
	* In charge of keeping karel within the map boundaries and processing map interactions.
   * @param e	 action event that karel has moved
	**************************************************************/
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
      {;
         firstrun=false;
         grounddata=getGnd();
         int slope=0;
         slope=Math.abs(grounddata[x/scale]-grounddata[xr]);
         if ((maptype.equals("map")&&jumping==true)||(maptype.equals("parcour")&&g==2))//for parcour do g==2
         {  
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if you're jumping
         }
         else if (slope>2 && ((velx==-speed && (Math.max(grounddata[x/scale],grounddata[xr])==grounddata[x/scale]))||(velx==speed && Math.max(grounddata[x/scale],grounddata[xr])==grounddata[xr])))//too steep and (not gravity right or not gravity left or not jumping)
         {
            velx=-velx;//is you're not jumping, and the slope is too steep
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
         g=2;
         vely=vely-g;
         y=y+vely;
      }

      x=x+velx;
      repaint();
      if (yr<=50)
         {
            System.out.print("You died. Awaiting respawn.");
            x=0;
            y=600;
            fakex=0;
            fakey=0;
         }
   }
   /***************************************************************
	* A key listener that moves karel based on key pressed
   * @param e	 key event that user has pressed
   *************************************************************/
   public void keyPressed(KeyEvent e)
   {
      int c = e.getKeyCode();
      
      if (c == KeyEvent.VK_LEFT)
      {
         velx=-speed;
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velx=speed;
      }
      if (c == KeyEvent.VK_UP)
      {
         if (y<=top)
         {
            vely=jump;
            speed=speed;
            jumping=true;
         }
         
      }
   }
   /***************************************************************
	* A key listener that stops karel's horizontal movement when a key is released.
   * @param e	 key event that user has released
   *************************************************************/
   public void keyReleased(KeyEvent e)
   {
      int c = e.getKeyCode();
      if (c == KeyEvent.VK_LEFT)
      {
         velx=0;
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velx=0;
      }
   }
   public void keyTyped(KeyEvent e){}
}