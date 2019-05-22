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
   Timer tm = new Timer(30,this);//sets an animation timer to 30 milliseconds
   int scale=0;//scale of the map
   String maptype="";//type of map
   int width=0,height=0;//width, height of map
   int speed=8;//speed of the character horizontally
   int jump=0;//initial jump velocity
   int bottom;//bottom of the map
   int top = 180;//top of the map
   int left;//left boundary
   String filename = "";//map file name
   int longi;//map width in squares
   int latit;//map height in squares
   boolean jumping=false;//is the characcter jumping?
   Integer[] groundy;//y values of ground (platforms)
   private Integer[] gnd;//ground array
   int border=0;//padding to map area (not used, but still included for the future)
   int fakex=0;//display x
   int fakey=0;//display y
   int x = 0, y, velx = 0, vely = 0, g = 2;//simulated x,y,velocities, and gravity
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
      addKeyListener(this);//adds a keyboard listener  
      //saves all the important variables
      filename = file;
      maptype = type;
      System.out.println(maptype);
      Scanner infile = new Scanner(new File(filename));
      jump = j;
      scale = sc;
      //reads the file for some parameters
      width = Integer.parseInt(infile.next())*scale;
      height = Integer.parseInt(infile.next())*scale;
      for (int i=0;i<4;i++)
      {
         Object temp = infile.next();//used to skip a few lines in the file
      }
      bottom = height;//sets the bottom of the map as it's height
      y=bottom;//sets the starting y value as the bottom of the map
      left = width-10-scale;//sets the left boundary slightly within the map
      longi = width/scale;//sets longi
      latit = height/scale;//sets latit    
      int len = longi*latit;//defines the length of the data array
      Integer[] mapfile = new Integer[len];//creates the data array
      //parses the data file and saves all ground values
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
      saveGnd(groundy);//saves the platform y-value array
   }
   /***************************************************************
	* Paints the moving sprite as well as the background.
   * Catches ClassNotFoundException
   * @param h	 graphics object 
	**************************************************************/
   public void paintComponent(Graphics h)
   {     
      try //attempts to call the Map class and paint it
      {
         Map2 bg = new Map2(filename, scale,x-fakex,fakey);
         bg.paintComponent(h);
         bg.setOpaque(true);
      }
      catch (Exception e)
      {
      }
      super.paintComponent(h);
      ImageIcon i = new ImageIcon("./Sprites/north20.png");//paints the character
      i.paintIcon(this, h, fakex, height-y-fakey);//draws the character at the display coordinates (helps with map scrolling)
      tm.start();//starts the animation timer
   }
   /***************************************************************
	* Sets an array of ground y values.
   * @param grnd	 ground array input
	**************************************************************/
   public void saveGnd(Integer[] grnd)
   {
      gnd=grnd;//saves the array
   }
   /***************************************************************
	* Gets the array of ground y values.
   * @return ground y-value array
	**************************************************************/
   public Integer[] getGnd()
   {
      return gnd;//returns the array
   }
   /***************************************************************
	* In charge of keeping karel within the map boundaries and processing map interactions.
   * @param e	 action event that karel has moved
	**************************************************************/
   public void actionPerformed(ActionEvent e)
   {
      if (fakex<350)//while the value of the display x is less than half the panel width, move the character, not the map
      {
         fakex=x;
      }
      fakey=0;
      int xr=x+19;//right most x coordinate of the character
      xr=xr/scale;
      int yr=y+19;//bottom most coordinate of the character
      Integer[] grounddata=new Integer[longi];
      if (x < border)//if the character has exited the map towards the left,put them back inside
      {
         velx=0;
         x=border;
      }
      if (x >= left)//if the character has exited to the right, put them back inside and call the menu with the success panel
      {
         x=0;
         fakex=0;
         velx=0;
         try
         {
            Menu success= new Menu(true);
         }
         catch(Exception a)
         {
            System.out.println("Failed to return to menu.");
         }
      }
      if (getGnd()!=null)//if the rest of the code has run and there is now map data
      {
         grounddata=getGnd();//set this array as the array of ground values
         int slope=0;//slope variable
         slope=Math.abs(grounddata[x/scale]-grounddata[xr]);//calculated by finding the difference in height or the right and left x coordinates of the character
         if ((maptype.equals("map")&&jumping==true)||(maptype.equals("parcour")&&g==2))//if your jumping, ignore slope
         {  
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;
         }
         else if (slope>2 && ((velx==-speed && (Math.max(grounddata[x/scale],grounddata[xr])==grounddata[x/scale]))||(velx==speed && Math.max(grounddata[x/scale],grounddata[xr])==grounddata[xr])))//too steep and (not gravity right or not gravity left or not jumping)
         {
            velx=-velx;//is you're not jumping, and the slope is too steep rebound off the wall (ignored by parcour maps)
         }
         else
         {
            top=Math.max(grounddata[x/scale],grounddata[xr])*scale+24;//if all above are false, climb the slope
         }
      }
      if ((-scale>yr-Math.max(grounddata[xr]*scale,grounddata[x/scale]*scale))&&maptype.equals("parcour"))
      {
         top=0;//if the character has fallen under the platform in a parcour map, let them fall
      }
      if (y > bottom)
      {
         fakey=height-y;//if the player exits the top of the screen, move with them
      }
      if (y < top)//if the player has come into contact with the platform:
      {
         y=top;
         if (vely<0)
         {
            vely=0;//stop falling
            jumping=false;//reset the jump
         }
         g=0;//set gravity to zero
      }
      if (y>=top)//if the player is not on a platform
      {
         g=2;//set gravity to two
         vely=vely-g;//keep subtracting gravity from the y velocity
         y=y+vely;//change the character's y position
      }
      x=x+velx;//change the character's x position
      repaint();//repaint everything
      if (yr<=50)//if the character has died, reset them
         {
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
      int c = e.getKeyCode();//gets the key that has been pressed
      if (c == KeyEvent.VK_LEFT)
      {
         velx=-speed;//moves left
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velx=speed;//moves right
      }
      if (c == KeyEvent.VK_UP)
      {
         if (y<top)//jumps if the player's jump is reset
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
      int c = e.getKeyCode();//gets the key that has been released
      if (c == KeyEvent.VK_LEFT)
      {
         velx=0;//stops horizontal movement
      }
      if (c == KeyEvent.VK_RIGHT)
      {
         velx=0;//stops horizontal movement
      }
   }
   public void keyTyped(KeyEvent e){}
}