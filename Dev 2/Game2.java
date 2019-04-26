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

public class Game2 
{
    public Game2() throws Exception
    {
        JFrame display = new JFrame();

        display.setTitle("Title");
        

        // Deseaneaza doar daca linia asta apare inainte the setSize()
        addALittleMan(display);
        //addBackground(display);
        //addALittleMan(display);
        

        display.setAlwaysOnTop(true);
        display.setResizable(false);
        display.setSize(1510, 630);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public static void main(String[] args) throws Exception {
        Game2 game = new Game2();
    }

    public static void addBackground(JFrame jFrame) throws Exception {
        //Map2 bg = new Map2();
        //bg.setOpaque(false);
        //jFrame.add(bg);
        
    }

    public static void addALittleMan(JFrame jFrame) throws Exception {
       String filename = "map.txt";
       String maptype = "map";
       int platform = 4;
       int jump = 50;
       int scale = 15;
       AnimationPh3 per = new AnimationPh3(filename,maptype,platform,jump,scale); //send filename,maptype, width, and height, platform color, jump, scale
       //per.setBackground(new Color(0,0,0,0));
       per.setOpaque(false);
       //per.setBackground(new Color(0,0,0,90));
       jFrame.add(per);
       per.setFocusable(true);
       per.requestFocus();
       per.setFocusTraversalKeysEnabled(false);
    }
}