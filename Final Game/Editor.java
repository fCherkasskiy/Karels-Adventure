import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.lang.Math.*;

public class Editor extends JFrame {
       
   int w;
   int h;
   static int xPoints;
   static int yPoints;
   int spacing = 1;
   int boxSize = 9;
   int cBoxSize;
   int mouseX;
   int mouseY;
   boolean mouseClicked;
   static int colorNum;
   static int[] coloredPoints;
   String[] colorArr = mapLoader.ColorList.COLOR_LIST;
   public Editor() {
   
      yPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height of level (Only options: 20,24,30,40,60):", "20"));  // Read user input
      xPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width of level (a big number will go off the sceen):", "150")); // Read user input
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
   
      JButton saveButton = new JButton();
      saveButton.setBounds(w/2-100,10,100,30);
      saveButton.setText("Save");
      this.add(saveButton);
      saveButton.addActionListener(new Listener1());
         
      JButton loadButton = new JButton();
      loadButton.setBounds(w/2+5,10,100,30);
      loadButton.setText("Load");
      this.add(loadButton);
      loadButton.addActionListener(new Listener2());
            
   
      JButton clearButton = new JButton();
      clearButton.setBounds(w/2+105,10,100,30);
      clearButton.setText("Fill All");
      this.add(clearButton);
      clearButton.addActionListener(new Listener3());
            
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



class Listener1 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      String name = JOptionPane.showInputDialog("Name your file: ");
      String type = JOptionPane.showInputDialog("Is this a parcour or normal map ('parcour' or 'map')","parcour");
      int jump = Integer.parseInt(JOptionPane.showInputDialog("Input a whole number jump velocity","25"));
      try
      {
         PrintStream p = new PrintStream(new File("./User Maps/"+name + ".txt"));
         p.append(Editor.xPoints+ "\n");
         p.append(Editor.yPoints+ "\n");
         p.append(type+"\n");
         p.append(4+"\n");
         p.append(jump+"\n");
         p.append(600/Editor.yPoints+"\n");
         for(int i = 0; i < Editor.coloredPoints.length; i++)
            p.append(Editor.coloredPoints[i] + " ");
      }
      catch(Exception a)
      {
         System.err.println("File not found.");
      }         
   }
}
class Listener2 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      String name = JOptionPane.showInputDialog("What file would you like to open?:");
       
      try {
         Scanner infile = new Scanner(new File("./User Maps/"+name + ".txt"));
         Editor.xPoints=Integer.parseInt(infile.next());
         Editor.yPoints=Integer.parseInt(infile.next());
         for (int k=0; k<4; k++)
         {
            Object thing = infile.next();
         }
         Editor.coloredPoints = new int[Editor.xPoints*Editor.yPoints];
         for(int i = 0; i < Editor.xPoints*Editor.yPoints; i++)
            Editor.coloredPoints[i]=Integer.parseInt(infile.next());
                  
      }
      catch (Exception except) {
         System.err.println("File not found.");
      }
               
        
   }
}
class Listener3 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      
      //String doClear = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear? (Type: \"yes\")", "no"); // Read user input
      int doClear = JOptionPane.showConfirmDialog(null, "Are you sure you want to fill? (THIS WILL REPLACE EVERYTHING IN CANVAS)", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if(doClear == (JOptionPane.YES_OPTION)){
         JOptionPane.showMessageDialog(null, "Fill successful.");
         for(int i = 0; i < Editor.coloredPoints.length; i++)
            Editor.coloredPoints[i] = Editor.colorNum;        
      }
   }
}

