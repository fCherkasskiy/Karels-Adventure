package mapLoader;

import javax.swing.*;
import java.awt.*;

public class Driver extends JPanel{

    private static int blockSize;
    private static ColorReader loader;

    public static void main(String[] args) throws Exception{
        loader = new ColorReader("E:\\TJHSST\\Karels-Adventure\\maps\\" + JOptionPane.showInputDialog(null, "Enter the path and filename using '\\':", "Path"));
        blockSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter block size", "10"));
        Driver driver = new Driver();
        JFrame frame = new JFrame("Epic");
        frame.setSize(loader.getLength() * blockSize, loader.getHeight() * blockSize);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(driver);
        frame.pack();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int a = 0; a < loader.getHeight(); a++){
            for(int b = 0; b < loader.getLength(); b++){
                g.setColor(Color.decode(loader.getColor(a, b)));
                g.fillRect(b * blockSize, a * blockSize, blockSize, blockSize);
            }
        }
    }
}
