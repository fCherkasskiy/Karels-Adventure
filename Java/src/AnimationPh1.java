import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AnimationPh1 extends JPanel implements ActionListener, KeyListener /*Cavnas,JPanel*/{
    Timer tm = new Timer(30,this);
    int width = 1000, height = 400;
    int speed = 5;
    int jump = 30;
    int bottom = height;  // actually the top border (everything is flipped)
    int top = 70;  // same as above
    int left = width-30-15;
    int jumptm = 0;

    int x = 0, y = bottom, velx = 0, vely = 0, g = 2;


    public AnimationPh1() {
        addKeyListener(this);

    }

//    public void paintComponent(Graphics h) {
//        super.paintComponent(h);
//        h.setColor(Color.BLACK);
//        h.fillRect(x,height-y,30,30);
//        tm.start();
//    }

//    public void paintComponent(Graphics h) {
//        super.paintComponent(h);
//        ImageIcon i = new ImageIcon("E:/");
//        i.paintIcon(this, h, x, y);
//     }

    public void actionPerformed(ActionEvent e) {
        if (x < 0) {
            velx=0;
            x=0;
        }
        if (x > left) {
            velx=0;
            x=left;
        }
        if (y > bottom) {
            y=bottom;
            if (vely<0)
            {
                vely=0;
            }
            g=0;
        }
        if (y < top) {
            y=top;
            if (vely<0) {
                vely=0;
            }
            g=0;
//            if (jumptm!=0){
//                System.out.print("jump reset");
//            }
//            jumptm=0;
//            System.out.print("jump reset");
        }
        if (y >= top) {
            g=2;
            /*if (jumptm>50)
             {
             vely=0;
             }
             jumptm++;*/
            vely = vely - g;
            y = y + vely;
        }
        x = x + velx;
        repaint();
    }
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT) {
            velx=-speed;
            System.out.println("left");
        }
        if (c == KeyEvent.VK_RIGHT) {
            velx=speed;
            System.out.println("right");
        }
        if (c == KeyEvent.VK_UP) {
            if (y<=top) {
                vely=jump;
                System.out.println("up");
            }
        }
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e) {
        velx=0;
        vely=0;
    }

    public static void main(String[] args) {
        JFrame display = new JFrame();
        display.setTitle("Karel's Adventure");
        display.setVisible(true);
        display.setSize(1000,400);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setResizable(false);
        display.setAlwaysOnTop(true);
        AnimationPh1 per = new AnimationPh1();
        display.add(per);
        per.setFocusable(true);
        per.requestFocus();
        per.setFocusTraversalKeysEnabled(false);
    }
}
