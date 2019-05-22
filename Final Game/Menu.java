import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;
import javax.sound.sampled.*; 


/*****************************************************************
* A Menu is a driver that acts upon the user's clicking.
* A Menu helps the user by passing data to other classes.
* @author Ari Bobesh
* @version 1.0
****************************************************************/
public class Menu extends JFrame implements ActionListener
{
   JFrame submenu; //creates submenu frame which will be hidden or unhidden by button presses
   static AudioPlayer audioPlayer;
   static String soundOption;
   static float soundVolume;
   /************************************************************* 
	* Runs the Menu constructor.
   * Allows the user to select a volume for the music being played.
	* @param args    required for main method
   * @throws FileNotFoundException     throws an exception if the file does not exist
	**************************************************************/
   public static void main(String[] args) throws Exception {
      Menu main = new Menu(false); //calls the Menu class
      
      soundOption = JOptionPane.showInputDialog(null, "Enter sound volume (low, medium, high)", "medium");
      while(!soundOption.equals("low") && !soundOption.equals("medium") && !soundOption.equals("high"))
         soundOption = JOptionPane.showInputDialog(null, "Invalid option. (low, medium, high)", "medium");
      if(soundOption.equals("low"))
         soundVolume = -15.0f;
      if(soundOption.equals("medium"))
         soundVolume = -10.0f;
      if(soundOption.equals("high"))
         soundVolume = -5.0f;
         
      try //attempts to play audio
      { 
         AudioPlayer.filePath = "music.wav";  // Plays the music file continuously.
         audioPlayer = new AudioPlayer(soundVolume); 
         audioPlayer.clip.start();
      
      }  
          
      catch (Exception ex)  
      { 
         System.out.println("Error with playing sound."); 
         ex.printStackTrace(); 
          
      } 
   }
   /************************************************************* 
	 * Constructs a Menu JFrame with pre-define buttons, all with action listeners.
    * Also makes an invisible submenu.
	 * @param success    whether or not the user has just completed a level, causes the Success!!! banner to pop up
	 **************************************************************/
   public Menu(boolean success) // takes "success" boolean.  True - display level completion banner, False - don't
   {
      //code closes all active panels and frames to make sure that the JRE does not overload on processes
      java.awt.Window win[] = java.awt.Window.getWindows(); 
      for(int i=0;i<win.length;i++){ 
         win[i].dispose(); 
      }
      //code set up Menu frame
      this.setLayout(new BorderLayout());
      this.setTitle("Karel's Adventure");
      this.setSize(610,630);
      //level completion banner
      if (success==true)
      {
         JLabel label = new JLabel("Success!!! You completed the level.",SwingConstants.CENTER);
         label.setFont(new Font("Sans Serif", Font.BOLD, 35));
         this.add(label, BorderLayout.SOUTH);
      }
      //play preset levels button
      JButton gamebutton = new JButton("<html><center>Play<br>Preset Levels<center></html>");//instantiates the button with it's name
      gamebutton.setFont(new Font("Sans Serif", Font.BOLD, 20));//sets the appearance
      gamebutton.setPreferredSize(new Dimension(200, 200));//sets the size
      gamebutton.addActionListener(new Listener1());//adds an action listener
      this.add(gamebutton, BorderLayout.WEST);//adds the button to the frame
      //make custom levels button (repeat above)
      JButton builderbutton = new JButton("<html><center>Make<br>Custom Levels<center></html>");
      builderbutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      builderbutton.setPreferredSize(new Dimension(200, 200));
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton,BorderLayout.EAST);
      //play custom levels button (repeat above)
      JButton userbutton = new JButton("<html><center>Play<br>Custom Levels<center></html>");
      userbutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      userbutton.setPreferredSize(new Dimension(200, 200));
      userbutton.addActionListener(new Listener3());
      this.add(userbutton,BorderLayout.CENTER);
      //exit game button (repeat above)
      JButton closebutton = new JButton("EXIT GAME");
      closebutton.setFont(new Font("Sans Serif", Font.BOLD, 20));
      closebutton.setPreferredSize(new Dimension(10, 50));
      closebutton.addActionListener(new Listener4());
      closebutton.setBackground(Color.RED);
      closebutton.setOpaque(true);
      this.add(closebutton, BorderLayout.NORTH);
      //finishes JFrame set up by making it visible
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //starts constructing the submenu 
      submenu = new JFrame();
      submenu.setLayout(new GridLayout(3,3));
      JButton[] s = new JButton[9];//creates a button array
      s[0] = new JButton("Back to Menu");//adds all the names in order
      s[1] = new JButton("Pirate's Cove");
      s[2] = new JButton("Polar Express");
      s[3] = new JButton("<html><center>"+"Stairway To"+"<br>"+"Heaven"+"<center></html>");
      s[4] = new JButton("Old Valley Ranch");
      s[5] = new JButton("Parcour");
      s[6] = new JButton("Pure Skill");
      s[7] = new JButton("Pure Skill 2");
      s[8] = new JButton("Titanic");
      s[0].setBackground(Color.RED);//sets the back button as having a special appearance
      for (int k=0; k<9;k++)//sets all buttons to have the same appearance, sets action commands, and adds an action listener
      {
         s[k].setFont(new Font("Sans Serif", Font.BOLD, 20));
         s[k].setActionCommand(Integer.toString(k+1));
         submenu.add(s[k]);
         s[k].addActionListener(this);
      }
      //finishes setting up the submenu
      submenu.setTitle("Level Submenu");
      submenu.setVisible(false);
      submenu.setResizable(false);
      submenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      submenu.setSize(610,630);
      this.requestFocus();//requests focus onto the submenu for the action listeners
   }
   /************************************************************* 
	 * Unhides the submenu, which lets the use choose built-in levels.
    **************************************************************/
   private class Listener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         submenu.setVisible(true);//opens the submenu when the "preset levels" button is clicked
      }
   }
   /************************************************************* 
   * Calls the editor class
   **************************************************************/
   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Editor editor = new Editor();//opens the map editor when the "make levels" button is clicked
      }
   }
   /************************************************************* 
   * Lets the user input the name of their custom level before calling Game
   **************************************************************/
   private class Listener3 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)//requests the user for the map name when the "custom levels" button is clicked
      {
         try
         {
            String level = JOptionPane.showInputDialog(null, "Enter level name");
            level = "./User Maps/"+ level+".txt";
            Game2 user = new Game2(level);//starts the game
            dispose();//disposes of the menu
         }
         catch(Exception a)
         {
            System.out.println(a.getStackTrace()[0].getLineNumber());//outputs the line number of the error
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
         System.exit(0);//closes the application when the "exit game" button is pressed
      }
   }
      /************************************************************* 
   * Reads the submenu's button press and opens a level or returns to the menu.
   * @param e    action event of button being pressed
   **************************************************************/
   public void actionPerformed(ActionEvent e) {
      System.out.println("Action: "+e.getActionCommand());//gets the action command for the clicked submenu button
      try
      {
         switch (e.getActionCommand()) //opens a different map based on which button was clicked
         {
            case "1":
               submenu.dispose();//disposes of the submenu
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
               System.out.println("Failed to open built-in map.");//gives an error that the button code was incorrect
         }
      }
      catch(Exception a)
      {
         System.out.println(a.getStackTrace()[0].getLineNumber());//outputs the line number of the error
      }     
   }
}
