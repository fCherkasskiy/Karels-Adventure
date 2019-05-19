import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math.*;

public class Menu extends JFrame
{
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
      //gamebutton.setBounds(205,100,200,100);
      gamebutton.setPreferredSize(new Dimension(200, 200));
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton, BorderLayout.WEST);
      JButton builderbutton = new JButton("Make Levels");
      //builderbutton.setBounds(205,200,200,100);
      builderbutton.setPreferredSize(new Dimension(200, 200));
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton,BorderLayout.EAST);
      JButton userbutton = new JButton("Play Custom Levels");
      //userbutton.setBounds(205,300,200,100);
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
            try
            {
               Game2 game = new Game2("./Maps/long.txt");
               dispose();
            }
            catch(Exception a)
            {
               System.out.println(a.getStackTrace()[0].getLineNumber());
            }
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

}