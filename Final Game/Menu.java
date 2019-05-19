import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;

public class Menu extends JFrame implements ActionListener
{
   JFrame submenu;
   public static void main(String[] args) throws Exception {
        Menu main = new Menu(false);
   }
   public Menu(boolean success) throws Exception
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
         label.setFont(new Font("Serif", Font.BOLD, 35));
         label.setForeground(Color.black);
         this.add(label, BorderLayout.SOUTH);
      }
      JButton gamebutton = new JButton("Play Levels");
      gamebutton.setPreferredSize(new Dimension(200, 200));
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton, BorderLayout.WEST);
      JButton builderbutton = new JButton("Make Levels");
      builderbutton.setPreferredSize(new Dimension(200, 200));
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton,BorderLayout.EAST);
      JButton userbutton = new JButton("Play Custom Levels");
      userbutton.setPreferredSize(new Dimension(200, 200));
      userbutton.addActionListener(new Listener3());
      this.add(userbutton,BorderLayout.CENTER);
      JButton closebutton = new JButton("EXIT GAME");
      closebutton.setPreferredSize(new Dimension(10, 15));
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
      JButton s4 = new JButton("Stairway To Heaven");
      JButton s5 = new JButton("Old Valley Ranch");
      JButton s6 = new JButton("Parcour");
      JButton s7 = new JButton("7");
      JButton s8 = new JButton("8");
      JButton s9 = new JButton("9");
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
   }
//    public void close()
//    {
//       WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
//    }
   private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            submenu.setVisible(true);
            // try
//             {
//                String level = JOptionPane.showInputDialog(null, "Enter level name");
//                level = "./Maps/"+ level+".txt";
//                Game2 game = new Game2(level);
//                dispose();
//             }
//             catch(Exception a)
//             {
//                System.out.println(a.getStackTrace()[0].getLineNumber());
//             }
         }
      }
   private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            Editor editor = new Editor();
         }
      }
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
   private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
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
            Game2 game6 = new Game2("./Maps/Pirate's Cove.txt");
            break;
         case "8":
            Game2 game7 = new Game2("./Maps/Pirate's Cove.txt");
            break;
         case "9":
            Game2 game8 = new Game2("./Maps/Pirate's Cove.txt");
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