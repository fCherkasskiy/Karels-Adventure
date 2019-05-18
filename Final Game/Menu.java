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
        Menu main = new Menu();
   }
   public Menu() throws Exception
   {
      this.setTitle("Karel's Adventure");
      this.setSize(610,630);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(false);
      JButton gamebutton = new JButton("Play Levels");
      gamebutton.setBounds(205,100,200,100);
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton);
      JButton builderbutton = new JButton("Make Levels");
      builderbutton.setBounds(205,200,200,100);
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton);
      JButton userbutton = new JButton("Play Custom Levels");
      userbutton.setBounds(205,300,200,100);
      userbutton.addActionListener(new Listener3());
      this.add(userbutton);
   }
   private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               Game2 game = new Game2("./Maps/leveldata1.txt");
            }
            catch(Exception a)
            {
               System.out.println("oops");
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
               System.out.println("oops");
            }         
         }
      }

}