import javax.swing.JFrame;

public class Game2 {
    public Game2() {
    }

    public static void main(String[] args) throws Exception {
        JFrame display = new JFrame();

        display.setTitle("Title");
        

        // Deseaneaza doar daca linia asta apare inainte the setSize()
        //addALittleMan(display);
        addBackground(display);
        //addALittleMan(display);
        

        display.setAlwaysOnTop(true);
        display.setResizable(false);
        display.setSize(1000, 400);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void addBackground(JFrame jFrame) throws Exception {
        Map2 bg = new Map2();
        //bg.setOpaque(false);
        jFrame.add(bg);
        
    }

    public static void addALittleMan(JFrame jFrame) throws Exception {
        AnimationPh3 per = new AnimationPh3();
        //per.setBackground(new Color(0,0,0,5));
        //per.setOpaque(false);
        //per.setBackground(new Color(0,0,0,90));
        jFrame.add(per);
        per.setFocusable(true);
        per.requestFocus();
        per.setFocusTraversalKeysEnabled(false);
    }
}