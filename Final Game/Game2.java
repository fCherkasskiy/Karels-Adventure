import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;
/*****************************************************************
	* A Game is a JFrame and a Driver that collects and stores information about its properties.
	* A Game knows how to setup a layout with a level and an exit button.
   * A Game also knows how to add Animations and Maps to its frame.
	* @author Ari Bobesh
	* @version 2.0
	****************************************************************/
public class Game2 
{
    JFrame display;
    Scanner infile;
    int width;
    int height;
    String filename;
    String maptype;
    int platform;
    int jump;
    int scale;
    /************************************************************* 
	 * Constructs a jframe with properties specified by reading level file.
	 * @param level    level filename
    * @throws FileNotFoundException     throws an exception if the file does not exist
	 **************************************************************/
    public Game2(String level) throws Exception
    {
        infile = new Scanner(new File(level));
        width = Integer.parseInt(infile.next());
        height = Integer.parseInt(infile.next());
        filename=level;
        maptype = infile.next();
        platform = Integer.parseInt(infile.next());
        jump = Integer.parseInt(infile.next());
        scale = Integer.parseInt(infile.next());
        display = new JFrame();
        display.setTitle(level);
        display.setAlwaysOnTop(true);
        display.setResizable(false);
        display.setSize(610, 630);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        display.setLayout(new BorderLayout());
        JButton closebutton = new JButton("EXIT TO MENU");
        closebutton.setPreferredSize(new Dimension(10, 15));
        closebutton.addActionListener(new Listener1());
        closebutton.setBackground(Color.RED);
        closebutton.setOpaque(true);
        display.add(closebutton, BorderLayout.NORTH);
        addALittleMan(display);

    }
    private class Listener1 implements ActionListener
      {
         /***************************************************************
      	* Closes the game upon the press of the exit button
         * @param e	 action event that the button records
      	**************************************************************/
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               Menu exit = new Menu(false);
               display.dispose();
            }
            catch(Exception a)
            {
               System.out.println(a.getStackTrace()[0].getLineNumber());
            }
         }
      }
    /***************************************************************
	 * Adds a karel animation to game frame.
    * Requests user focus to the animation and disables focus-altering keys.
	 * @param jFrame	 passes the JFrame to which the character should be added
    * @throws ClassNotFoundException     throws an exception if the class does not exist
	 **************************************************************/
    public void addALittleMan(JFrame jFrame) throws Exception {
       AnimationPh3 per = new AnimationPh3(filename,maptype,platform,jump,scale); //send filename,maptype, width, and height, platform color, jump, scale
       per.setOpaque(false);
       jFrame.add(per);
       per.setFocusable(true);
       per.requestFocus();
       per.setFocusTraversalKeysEnabled(false);
    }
}