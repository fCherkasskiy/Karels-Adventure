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

public class AnimationPh2 extends JPanel implements ActionListener, KeyListener
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
   

   
   
   public AnimationPh2()
   {
      addKeyListener(this);
      
      
   }
   
      
   public static void main(String[] args) throws Exception
   {
      JFrame display = new JFrame();
      
      
      
      display.setTitle("Karel's Adventure");
      display.setVisible(true);
      display.setSize(1000,400);
      display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      display.setResizable(false);
      display.setAlwaysOnTop(true);
      AnimationPh2 per = new AnimationPh2();
      display.add(per);
      per.setFocusable(true);
      per.requestFocus();
      per.setFocusTraversalKeysEnabled(false); 
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
      }
      per.groundy = new Integer[longi];
      for (int i=0;i<longi;i++)
      {
         per.groundy[i]=0;
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
                  per.groundy[s]=latit-p;
                                    
               }
               p++;
            
            }
            
         }
         s++;
      }
      per.saveGnd(per.groundy);
      for (int r=0; r<longi;r++)
      {
         //System.out.print(Integer.toString(r)+" ");
         //System.out.println(per.groundy[r]);
      }
   }
   //-----------------------------------------------------------------------------------------
   
   //-----------------------------------------------------------------------------------------

   //-----------------------------------------------------------------------------------------

   //-----------------------------------------------------------------------------------------
   
   public void paintComponent(Graphics h)
   {
      super.paintComponent(h);
      ImageIcon i = new ImageIcon("D:\\Users\\Ari\\Documents\\TJHSST\\a_game\\north.png");
      i.paintIcon(this, h, x, height-y);
                                                                                                                        /**for(int f=0;f<longi;f++)
                                                                                                                        {
                                                                                                                           System.out.println("in");
                                                                                                                           h.setColor(Color.BLACK);
                                                                                                                           h.drawLine(f*10,groundy[f]*10,f*10+10,groundy[f]*10);
                                                                                                                        }**/
                                                                                                                        /**int[] xSin = new int[longi];
                                                                                                                        int[] ySin = new int[longi];
                                                                                                                        for (int f=0;f<longi;f++)
                                                                                                                        {
                                                                                                                           System.out.println("in");
                                                                                                                           xSin[f]=f*10;
                                                                                                                           ySin[f]=groundy[f]*10;
                                                                                                                        }
                                                                                                                        h.setColor(Color.BLACK);
                                                                                                                        h.drawPolyline(xSin, ySin, xSin.length);**/
      tm.start();
   }
   public void saveGnd(Integer[] grnd)
   {
      // for (int r=0; r<longi;r++)
//       {
//         System.out.println(grnd[r]);
//       }
      gnd=grnd;
   }
   public Integer[] getGnd()
   {
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
         //System.out.println(top);
         // for (int r=0; r<longi;r++)
//          {
//             System.out.println(grounddata[r]);
//          }
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
                                                                                                      /**if (jumptm!=0)
                                                                                                      {
                                                                                                         System.out.print("jump reset");
                                                                                                      }
                                                                                                      jumptm=0;**/
                                                                                                      //System.out.print("jump reset");
         
      }
      if (y>=top)
      {
         g=2;
                                                                                                               /**if (jumptm>50)
                                                                                                               {
                                                                                                                  vely=0;
                                                                                                               }
                                                                                                               jumptm++;**/
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