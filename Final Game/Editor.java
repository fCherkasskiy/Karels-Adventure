import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.lang.Math.*;
//import mapLoader.ColorList.COLOR_LIST;

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
   int colorNum;
   int[] coloredPoints = new int[xPoints*yPoints];
   String[] colorArr = mapLoader.ColorList.COLOR_LIST;
   public Editor() {
   
      yPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height of level (20,24,30,40,60).", "20"));  // Read user input
      xPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width of level (a big number will go off the sceen)", "150")); // Read user input
      cBoxSize = 50;
      w = cBoxSize*17;
      h = cBoxSize*17;
      coloredPoints = new int[xPoints*yPoints];
      for(int i = 0; i < coloredPoints.length; i++)
         coloredPoints[i] = 1;
   
      this.setTitle("Karel's Adventure Level Creator");
      this.setSize(2000,2000);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(true);
      this.setFocusable(true);
      this.requestFocus();
      this.setFocusTraversalKeysEnabled(false);

   
   
      SBoard board = new SBoard();
      this.setContentPane(board);
   
      Move movement = new Move();
      this.addMouseMotionListener(movement);
   
      Pressed click = new Pressed();
      this.addMouseListener(click);
      
      //JCheckBox exchangingCard1 = new JCheckBox("A");
      //checkBoxPanel.add(exchangingCard1);
   
      JButton saveButton = new JButton();
      saveButton.setBounds(w/2-100,10,100,30);
      saveButton.setText("Save");
      this.add(saveButton);
      saveButton.addMouseListener(
            new MouseListener() {
            
               @Override
                public void mouseClicked(MouseEvent e) {
                  String name = JOptionPane.showInputDialog("The file name you want to save it as.");
                  try {
                     PrintStream p = new PrintStream(new File("./User Maps/"+name + ".txt"));
                     p.append(xPoints+ "\n");
                     p.append(yPoints+ "\n");
                     p.append("map"+"\n");
                     p.append(4+"\n");
                     p.append(25+"\n");
                     p.append(30+"\n");
                     for(int i = 0; i < coloredPoints.length; i++)
                        p.append(coloredPoints[i] + " ");
                  
                  }
                  catch (Exception except) {
                     System.err.println("File not found.");
                  }
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
                  for(int i = 0; i < coloredPoints.length; i++)
                     coloredPoints[i] = 8;
               
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
                  for(int i = 0; i < coloredPoints.length; i++)
                     coloredPoints[i] = 8;
               
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
         g.fillRect(0,0,5000,5000);
         for(int y = 0; y < 16; y++){
            g.setColor(Color.decode(colorArr[y]));
            g.fillRect(0,y*cBoxSize,cBoxSize,cBoxSize);
            
         }
         if(mouseX >= 0 && mouseX < cBoxSize){
                  if(mouseClicked){
                     if((int)Math.round((double)mouseY/(double)cBoxSize)-1 < 16)
                        colorNum = (int)Math.round((double)mouseY/(double)cBoxSize)-1;
                        }
         }
         for(int i = 0; i < xPoints; i++){
            for(int j = 0; j < yPoints; j++){
               int x = coloredPoints[i+xPoints*j];
               g.setColor((Color.decode(colorArr[coloredPoints[i+xPoints*j]])));
               if(mouseX >= spacing + i*boxSize+105 && mouseX < spacing + i*boxSize+boxSize-spacing+105){
                  if(mouseY >= spacing + j*boxSize+boxSize+70 && mouseY < spacing+j*boxSize+boxSize+boxSize-2*spacing + 70) {
                     g.setColor(Color.decode(colorArr[colorNum]));
                     if (mouseClicked) {
                        coloredPoints[i+xPoints*j] = colorNum;
                        //mouseClicked = false;
                     }
                  }
               }
               g.fillRect(spacing + i*boxSize+100,spacing + j*boxSize+50, boxSize-2*spacing, boxSize-2*spacing);
            }
         }
      repaint();
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



