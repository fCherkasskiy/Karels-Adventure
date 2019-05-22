import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.lang.Math.*;

/*****************************************************************
* An Editor is a JFrame that will visually represent selected changes and store it.
* An Editor knows how to create a 'level' and how to load an existing one.
* An Editor allows the user to create a level exactly how they want it.
* @author Rushil Sidhu
* @version 1.5
****************************************************************/
public class Editor extends JFrame {
       
   int w = 2000; // Width of the frame in pixels.
   int h = 1000; // Height of the frame in pixels.
   
   // how many boxes for width and height
   static int xPoints;
   static int yPoints;
   
   // Determine the length of seperation between boxes and the size of each box in pixels.
   int spacing = 1;
   int boxSize = 9;
   
   int cBoxSize = 50; // the size of the colored boxes in pixels.
   
   // variables for mouse position and if it is clicked or not.
   int mouseX; 
   int mouseY;  
   boolean mouseClicked;
   
   static int colorNum; // The current selected color.
   static int[] coloredPoints; // The array of all boxes
   String[] colorArr = mapLoader.ColorList.COLOR_LIST; // Defined string from mapLoader that has hexadecimal values of colors.
   
   /************************************************************* 
   * Runs the Editor constructor.
   * Sets up the editor for use.
   **************************************************************/
   public Editor() {
   
      yPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter height of level (Only options: 20,24,30,40,60):", "20"));  // Read user input for height
      while(yPoints != 20 && yPoints != 24 && yPoints != 30 && yPoints != 40 && yPoints != 60)
         yPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid height. (Only options: 20,24,30,40,60):", "20"));  // Read user input for height
         
      xPoints = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter width of level (a big number will go off the sceen):", "150")); // Read user input for width
      
      //sets everything to white.
      coloredPoints = new int[xPoints*yPoints];
      for(int i = 0; i < coloredPoints.length; i++)
         coloredPoints[i] = 1;
   
      // These are to define the procedures of the editor window.
      this.setTitle("Karel's Adventure Level Creator");
      this.setSize(2000,2000);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(true);
      this.setFocusable(true);
      this.requestFocus();
      this.setFocusTraversalKeysEnabled(false);
   
   
      // These call the various classes for the editor.
      SBoard board = new SBoard();
      this.setContentPane(board);
      
      Move movement = new Move();
      this.addMouseMotionListener(movement);
      
      Pressed click = new Pressed();
      this.addMouseListener(click);
   
      // These define the buttons displayed at the top of the editor.
      JButton saveButton = new JButton(); // The save button writes to a file the information of the current map being edited.
      saveButton.setBounds(w/2-100,10,100,30);
      saveButton.setText("Save");
      this.add(saveButton);
      saveButton.addActionListener(new Listener1()); // Sets the action to listener1, which is defined later.
         
      JButton loadButton = new JButton(); // Takes in data from a file that will be written onto the editor to be changed.
      loadButton.setBounds(w/2+5,10,100,30);
      loadButton.setText("Load");
      this.add(loadButton);
      loadButton.addActionListener(new Listener2());
            
   
      JButton fillButton = new JButton(); // Useful tool to set the background to a certain color.
      fillButton.setBounds(w/2+105,10,100,30);
      fillButton.setText("Fill All");
      this.add(fillButton);
      fillButton.addActionListener(new Listener3());
            
   }
   
   /*****************************************************************
   * An SBoard is the Sketch Board which is the display for the editor.
   * An SBoard paints the entire editor and repeats the paint to display all changes.
   ****************************************************************/
   public class SBoard extends JPanel {
      public void paintComponent(Graphics g) {
      
         // Creates the background gray box.
         g.setColor(Color.LIGHT_GRAY);
         g.fillRect(0,0,w,h);
         
         // Creates the color selection boxes.
         for(int y = 0; y < 16; y++){
            g.setColor(Color.decode(colorArr[y]));
            g.fillRect(0,y*cBoxSize,cBoxSize,cBoxSize);
            
         }
         
         // This is the procedure to find out whether or not you are trying to switch to a different color.
         if(mouseX >= 0 && mouseX < cBoxSize){
            if(mouseClicked){
               if((int)Math.round((double)mouseY/(double)cBoxSize)-1 < 16)
                  colorNum = (int)Math.round((double)mouseY/(double)cBoxSize)-1;
            }
         }
         
         // This is the procedure for painting in every tiny box, this also checks if you are trying to color the boxes.
         for(int i = 0; i < xPoints; i++){  // Double for loop to define a width and height amount of boxes.
            for(int j = 0; j < yPoints; j++){
               int x = coloredPoints[i+xPoints*j];
               g.setColor((Color.decode(colorArr[x]))); // Sets each specific box to their designated color.
               
               if(mouseX >= spacing + i*boxSize+105 && mouseX < spacing + i*boxSize+boxSize-spacing+105){ // This checks if the mouse is hovering over a box.
                  if(mouseY >= spacing + j*boxSize+boxSize+70 && mouseY < spacing+j*boxSize+boxSize+boxSize-2*spacing + 70) {
                     g.setColor(Color.decode(colorArr[colorNum]));
                     if (mouseClicked) {
                        coloredPoints[i+xPoints*j] = colorNum; // If the mouse is clicked while the mouse is hovering over this block, change the color to the selected color.
                     }
                  }
               }
               g.fillRect(spacing + i*boxSize+100,spacing + j*boxSize+50, boxSize-2*spacing, boxSize-2*spacing); // Creates the box itself.
            }
         }
         repaint(); // calls the function to paint itself continously.
      }
   }

   /*****************************************************************
   * A Move checks where the mouse is at all times and stores it into a variable.
   ****************************************************************/
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

   /*****************************************************************
   * A Pressed checks whether the mouse is pressed or not.
   ****************************************************************/
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



/*****************************************************************
* The listener for the save button.
****************************************************************/
class Listener1 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      // Asks for a file, map type, and velocity.
      String name = JOptionPane.showInputDialog("Name your file: "); 
      String type = JOptionPane.showInputDialog("Is this a parcour or normal map ('parcour' or 'map')","parcour");
      int jump = Integer.parseInt(JOptionPane.showInputDialog("Input a whole number jump velocity","25"));
      int stepColor = Integer.parseInt(JOptionPane.showInputDialog("Which color do you want to be the walking color? (In order same as the one on the left, 1 is black, 2 is white, etc.)","5")) - 1;
      try
      {
         PrintStream p = new PrintStream(new File("./User Maps/"+name + ".txt")); // Writes all of the important information to a file.
         p.append(Editor.xPoints+ "\n"); // The number of boxes horizontally.
         p.append(Editor.yPoints+ "\n"); // The number of boxes vertically.
         p.append(type+"\n"); // The map type.
         p.append(stepColor+"\n"); // The color of the physical blocks.
         p.append(jump+"\n"); // The velocity.
         p.append(600/Editor.yPoints+"\n");
         for(int i = 0; i < Editor.coloredPoints.length; i++) // Writes the values for each box into the file. 
            p.append(Editor.coloredPoints[i] + " ");
      }
      catch(Exception a)
      {
         System.err.println("File not found.");
      }         
   }
}

/*****************************************************************
* The listener for the load button.
****************************************************************/
class Listener2 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      String name = JOptionPane.showInputDialog("What file would you like to open?:");
       
      try {
         Scanner infile = new Scanner(new File("./User Maps/"+name + ".txt"));
         Editor.xPoints=Integer.parseInt(infile.next()); //
         Editor.yPoints=Integer.parseInt(infile.next());
         for (int k=0; k<4; k++)
         {
            Object thing = infile.next(); // Ignores the next 4 things.
         }
         Editor.coloredPoints = new int[Editor.xPoints*Editor.yPoints]; // Resets the array for the boxes into the one in the file.
         for(int i = 0; i < Editor.xPoints*Editor.yPoints; i++)
            Editor.coloredPoints[i]=Integer.parseInt(infile.next());
                  
      }
      catch (Exception except) {
         System.err.println("File not found.");
      }
               
        
   }
}

/*****************************************************************
* The listener for the fill button.
****************************************************************/
class Listener3 implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      
      int doClear = JOptionPane.showConfirmDialog(null, "Are you sure you want to fill? (THIS WILL REPLACE EVERYTHING IN CANVAS)", 
      "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Creates the confirmation box to fill.
      if(doClear == (JOptionPane.YES_OPTION)){
         JOptionPane.showMessageDialog(null, "Fill successful.");
         for(int i = 0; i < Editor.coloredPoints.length; i++)
            Editor.coloredPoints[i] = Editor.colorNum; // Changes every point into the selected color.
      }
   }
}

