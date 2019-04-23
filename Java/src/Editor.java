import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;

public class Editor extends JFrame {

   int w = 2000;
   int h = 1000;
   int xPoints = 200;
   int yPoints = 100;
   int spacing = 1;
   int boxSize = 9;
   int cBoxSize = 95;
   int mouseX;
   int mouseY;
   boolean mouseClicked;
   int[] colored = new int[xPoints*yPoints];
   int colorNum;
   static String[] colorArr = {
                "#FFFFFF",
                "#808000", "#008000",
                "#A52A2A", "#808080",

                "#0000A0", "#ADD8E6",
                "#800080", "#FF00FF",

                "#FFFFFF", "#FFA500",
                "#FFFF00", "#00FFFF",

                "#000000", "#00FF00",
                "#FF0000", "#0000FF"};
   //Color[] colorArr = {Color.white,Color.green, Color.red, Color.BLUE,Color.yellow, Color.orange,Color.black,Color.cyan,Color.magenta,Color.lightGray,Color.white};
   public Editor() {
      
      Scanner myObj = new Scanner(System.in);
      System.out.println("Enter X length of pixel.");
      xPoints = myObj.nextInt();  // Read user input
      System.out.println("Enter Y length of pixel.");
      yPoints = myObj.nextInt();  // Read user input
      w = 10*xPoints;
      h = 10*yPoints;
      cBoxSize = h/16;
      
      this.setTitle("Karel's Adventure Level Creator");
      if(w < 500 && h < 500)
         this.setSize(500,500);
      else if(w < 500)
         this.setSize(500,h+100);
      else if(h < 500)
         this.setSize(w,500);
      else
         this.setSize(w,h);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(false);
   
   
      SBoard board = new SBoard();
      this.setContentPane(board);
   
      Move movement = new Move();
      this.addMouseMotionListener(movement);
   
      Pressed click = new Pressed();
      this.addMouseListener(click);
   
      JButton saveButton = new JButton();
      saveButton.setBounds(w/2-100,10,100,30);
      saveButton.setText("Save");
      this.add(saveButton);
      saveButton.addMouseListener(
            new MouseListener() {
            
               @Override
               public void mouseClicked(MouseEvent e) {
                  JOptionPane.showMessageDialog(null, "Saving not yet implemented");/*
                  FileOutputStream outputStream = new FileOutputStream("Game.txt");
                  outputStream.write(xPoints + "\n");
                  outputStream.write(yPoints + "\n");
                  for(int i = 0; i < colored.length; i++)
                     outputStream.write(colored[i] + " ");
 
                  outputStream.close();*/
                  
               
               }
            
               @Override
               public void mousePressed(MouseEvent e) {
               
               }
            
               @Override
               public void mouseReleased(MouseEvent e) {
               
               }
            
               @Override
               public void mouseEntered(MouseEvent e) {
               
               }
            
               @Override
               public void mouseExited(MouseEvent e) {
               
               }
            });
   
      JButton loadButton = new JButton();
      loadButton.setBounds(w/2+5,10,100,30);
      loadButton.setText("Load");
      this.add(loadButton);
      loadButton.addMouseListener(
            new MouseListener() {
            
               @Override
               public void mouseClicked(MouseEvent e) {
                  JOptionPane.showMessageDialog(null, "Loading not yet implemented.");
                  for(int i = 0; i < colored.length; i++)
                     colored[i] = 0;
               
               }
            
               @Override
               public void mousePressed(MouseEvent e) {
               
               }
            
               @Override
               public void mouseReleased(MouseEvent e) {
               
               }
            
               @Override
               public void mouseEntered(MouseEvent e) {
               
               }
            
               @Override
               public void mouseExited(MouseEvent e) {
               
               }
            });
   
      JButton clearButton = new JButton();
      clearButton.setBounds(w/2+105,10,100,30);
      clearButton.setText("Clear");
      this.add(clearButton);
      clearButton.addMouseListener(
            new MouseListener() {
            
               @Override
                                        public void mouseClicked(MouseEvent e) {
                  JOptionPane.showMessageDialog(null, "Clear successful.");
                  for(int i = 0; i < colored.length; i++)
                     colored[i] = 0;
               
               }
            
               @Override
                                        public void mousePressed(MouseEvent e) {
               
               }
            
               @Override
                                        public void mouseReleased(MouseEvent e) {
               
               }
            
               @Override
                                        public void mouseEntered(MouseEvent e) {
               
               }
            
               @Override
                                        public void mouseExited(MouseEvent e) {
               
               }
            });
   }
   public class SBoard extends JPanel {
      public void paintComponent(Graphics g) {
         g.setColor(Color.LIGHT_GRAY);
         g.fillRect(0,0,w+100,h+100);
         for(int y = 1; y <= 16; y++){
            g.setColor(Color.decode(colorArr[y]));
            g.fillRect(0,y*cBoxSize-cBoxSize,cBoxSize,y*cBoxSize);
            if(mouseX >= 0 && mouseX < cBoxSize){
               if(mouseY >= y*cBoxSize && mouseY <= y*cBoxSize+cBoxSize){
                  if(mouseClicked)
                     colorNum = y;
               }
            }
         }
         for(int i = 0; i < xPoints; i++){
            for(int j = 0; j < yPoints; j++){
               int x = colored[i+xPoints*j];
               g.setColor((Color.decode(colorArr[x])));
               if(mouseX >= spacing + i*boxSize+105 && mouseX < spacing + i*boxSize+boxSize-spacing+105){
                  if(mouseY >= spacing + j*boxSize+boxSize+70 && mouseY < spacing+j*boxSize+boxSize+boxSize-2*spacing + 70) {
                     g.setColor(Color.DARK_GRAY);
                     if (mouseClicked) {
                        colored[i+xPoints*j] = colorNum;
                               //mouseClicked = false;
                     }
                  }
               }
               g.fillRect(spacing + i*boxSize+100,spacing + j*boxSize+50, boxSize-2*spacing, boxSize-2*spacing);
            }
         }
      }
   }

   public class Move implements MouseMotionListener{
   
      @Override
       public void mouseDragged(MouseEvent e) {
         mouseX = e.getX();
         mouseY = e.getY();
      
      }
   
      @Override
       public void mouseMoved(MouseEvent e) {
         mouseX = e.getX();
         mouseY = e.getY();
      }
   }
   public class Pressed implements MouseListener {
   
      @Override
       public void mouseClicked(MouseEvent e) {
      }
   
      @Override
       public void mousePressed(MouseEvent e) {
         mouseClicked = true;
      }
   
      @Override
       public void mouseReleased(MouseEvent e) {
         mouseClicked = false;
      
      }
   
      @Override
       public void mouseEntered(MouseEvent e) {
      
      }
   
      @Override
       public void mouseExited(MouseEvent e) {
      
      }
   }
}


