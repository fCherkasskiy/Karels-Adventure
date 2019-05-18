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
    Scanner infile;
    int width;
    int height;
    String filename;
    String maptype;
    int platform;
    int jump;
    int scale;
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
        System.out.println(width);
        System.out.println(height);
        System.out.println(filename);
        System.out.println(maptype);
        System.out.println(jump);
        System.out.println(scale);
        JFrame display = new JFrame();

        display.setTitle(level);

        addALittleMan(display);      

        display.setAlwaysOnTop(true);
        display.setResizable(false);
        display.setSize(610, 630);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // public static void main(String[] args) throws Exception {
//         Game2 game = new Game2();
//     }
    public void addALittleMan(JFrame jFrame) throws Exception {
       // String filename = "long.txt";
//        String maptype = "parcour";
//        int platform = 4;
//        int jump = 15;
//        int scale = 10;
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