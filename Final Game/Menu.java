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
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.lang.Math.*;

public class Menu extends JFrame
{
   public static void main(String[] args) throws Exception {
        Menu main = new Menu(false);
   }
   public Menu(boolean success) throws Exception
   {
      this.setLayout(new BorderLayout());
      this.setTitle("Karel's Adventure");
      this.setSize(610,630);
      if (success==true)
      {
         JLabel label = new JLabel("Success!!! You completed the level.",SwingConstants.CENTER);
         label.setFont(new Font("Serif", Font.BOLD, 35));
         label.setForeground(Color.black);
         this.add(label, BorderLayout.NORTH);
      }
      JButton gamebutton = new JButton("Play Levels");
      //gamebutton.setBounds(205,100,200,100);
      gamebutton.setPreferredSize(new Dimension(300, 200));
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton, BorderLayout.WEST);
      JButton builderbutton = new JButton("Make Levels");
      //builderbutton.setBounds(205,200,200,100);
      builderbutton.setPreferredSize(new Dimension(200, 200));
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton,BorderLayout.SOUTH);
      JButton userbutton = new JButton("Play Custom Levels");
      //userbutton.setBounds(205,300,200,100);
      userbutton.setPreferredSize(new Dimension(300, 200));
      userbutton.addActionListener(new Listener3());
      this.add(userbutton,BorderLayout.EAST);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               Game2 game = new Game2("./Maps/long.txt");
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

}