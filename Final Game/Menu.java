import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;
/*****************************************************************
* A Menu is a driver that acts upon the user's clicking.
* A Menu helps the user by passing data to other classes.
* @author Ari Bobesh
* @version 1.0
****************************************************************/
public class Menu extends JFrame implements ActionListener
{
   JFrame submenu;
    /************************************************************* 
	 * Runs the Menu constructor.
	 * @param args    required for main method
    * @throws FileNotFoundException     throws an exception if the file does not exist
	 **************************************************************/
   public static void main(String[] args) throws Exception {
        Menu main = new Menu(false);
   }
   /************************************************************* 
	 * Constructs a Menu JFrame with pre-define buttons, all with action listeners.
    * Also makes an invisible submenu.
	 * @param success    whether or not the user has just completed a level, causes the Success!!! banner to pop up
	 **************************************************************/
   public Menu(boolean success)
   {
      java.awt.Window win[] = java.awt.Window.getWindows(); 
      for(int i=0;i<win.length;i++){ 
         win[i].dispose(); 
      } 
      this.setLayout(new BorderLayout());
      this.setTitle("Karel's Adventure");
      this.setSize(610,630);
      if (success==true)
      {
         JLabel label = new JLabel("Success!!! You completed the level.",SwingConstants.CENTER);
         label.setFont(new Font("Sans Serif", Font.BOLD, 35));
         this.add(label, BorderLayout.SOUTH);
      }
      JButton gamebutton = new JButton("<html><center>Play<br>Preset Levels<center></html>");
      gamebutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      gamebutton.setPreferredSize(new Dimension(200, 200));
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton, BorderLayout.WEST);
      JButton builderbutton = new JButton("<html><center>Make<br>Custom Levels<center></html>");
      builderbutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      builderbutton.setPreferredSize(new Dimension(200, 200));
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton,BorderLayout.EAST);
      JButton userbutton = new JButton("<html><center>Play<br>Custom Levels<center></html>");
      userbutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      userbutton.setPreferredSize(new Dimension(200, 200));
      userbutton.addActionListener(new Listener3());
      this.add(userbutton,BorderLayout.CENTER);
      JButton closebutton = new JButton("EXIT GAME");
      closebutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      closebutton.setPreferredSize(new Dimension(10, 50));
      closebutton.addActionListener(new Listener4());
      closebutton.setBackground(Color.RED);
      closebutton.setOpaque(true);
      this.add(closebutton, BorderLayout.NORTH);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      submenu = new JFrame();
      submenu.setLayout(new GridLayout(3,3));
      JButton s1 = new JButton("Back to Menu");
      JButton s2 = new JButton("Pirate's Cove");
      JButton s3 = new JButton("Polar Express");
      JButton s4 = new JButton("<html><center>"+"Stairway To"+"<br>"+"Heaven"+"<center></html>");
      JButton s5 = new JButton("Old Valley Ranch");
      JButton s6 = new JButton("Parcour");
      JButton s7 = new JButton("Pure Skill");
      JButton s8 = new JButton("Pure Skill 2");
      JButton s9 = new JButton("Titanic");
      s1.setBackground(Color.RED);
      s1.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s2.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s3.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s4.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s5.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s6.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s7.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s8.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s9.setFont(new Font("Sans Serif", Font.BOLD, 20));
      s1.setActionCommand("1");
      s2.setActionCommand("2");
      s3.setActionCommand("3");
      s4.setActionCommand("4");
      s5.setActionCommand("5");
      s6.setActionCommand("6");
      s7.setActionCommand("7");
      s8.setActionCommand("8");
      s9.setActionCommand("9");
      submenu.add(s1);
      submenu.add(s2);
      submenu.add(s3);
      submenu.add(s4);
      submenu.add(s5);
      submenu.add(s6);
      submenu.add(s7);
      submenu.add(s8);
      submenu.add(s9);
      s1.addActionListener(this);
      s2.addActionListener(this);
      s3.addActionListener(this);
      s4.addActionListener(this);
      s5.addActionListener(this);
      s6.addActionListener(this);
      s7.addActionListener(this);
      s8.addActionListener(this);
      s9.addActionListener(this);
      submenu.setTitle("Level Submenu");
      submenu.setVisible(false);
      submenu.setResizable(false);
      submenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      submenu.setSize(610,630);
      this.requestFocus();
   }
   /************************************************************* 
	 * Unhides the submenu, which lets the use choose built-in levels.
    **************************************************************/
   private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            submenu.setVisible(true);
         }
      }
   /************************************************************* 
   * Calls the editor class
   **************************************************************/
   private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            Editor editor = new Editor();
         }
      }
   /************************************************************* 
   * Lets the user input the name of their custom level before calling Game
   **************************************************************/
   private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               String level = JOptionPane.showInputDialog(null, "Enter level name");
               level = "./User Maps/"+ level+".txt";
               Game2 user = new Game2(level);
               dispose();
            }
            catch(Exception a)
            {
               System.out.println(a.getStackTrace()[0].getLineNumber());
            }         
         }
      }
   /************************************************************* 
   * Lets the user exit the application
   **************************************************************/
   private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
   /************************************************************* 
   * Reads the submenu's button press and opens a level or returns to the menu.
   * @param e    action event of button being pressed
   **************************************************************/
   public void actionPerformed(ActionEvent e) {
      System.out.println("Action: "+e.getActionCommand());
      try
      {
         switch (e.getActionCommand()) {
         case "1":
            submenu.dispose();
            break;
         case "2":
            Game2 game1 = new Game2("./Maps/Pirate's Cove.txt");
            break;
         case "3":
            Game2 game2 = new Game2("./Maps/Polar Express.txt");
            break;
         case "4":
            Game2 game3 = new Game2("./Maps/Stairway To Heaven.txt");
            break;
         case "5":
            Game2 game4 = new Game2("./Maps/Old Valley Ranch.txt");
            break;
         case "6":
            Game2 game5 = new Game2("./Maps/Parcour.txt");
            break;
         case "7":
            Game2 game6 = new Game2("./Maps/Pure Skill.txt");
            break;
         case "8":
            Game2 game7 = new Game2("./Maps/Pure Skill 2.txt");
            break;
         case "9":
            Game2 game8 = new Game2("./Maps/Titanic.txt");
            break;
         default:
            System.out.println("Failed to open built-in map.");
         }
      }
      catch(Exception a)
      {
         System.out.println(a.getStackTrace()[0].getLineNumber());
      }     
   }
}