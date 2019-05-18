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
      this.setTitle("Karel's Adventure Level Creator");
      this.setSize(600,600);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(true);
      JButton gamebutton = new JButton("Play Levels");
      gamebutton.setBounds(200,100,200,100);
      gamebutton.addActionListener(new Listener1());
      this.add(gamebutton);
      JButton builderbutton = new JButton("Make Levels");
      builderbutton.setBounds(200,200,200,100);
      builderbutton.addActionListener(new Listener2());
      this.add(builderbutton);
      JButton userbutton = new JButton("Play Custom Levels");
      userbutton.setBounds(200,300,200,100);
      userbutton.addActionListener(new Listener3());
      this.add(userbutton);
   }
   private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               Game2 game = new Game2();
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
               Game2 user = new Game2();
            }
            catch(Exception a)
            {
               System.out.println("oops");
            }         
         }
      }

}