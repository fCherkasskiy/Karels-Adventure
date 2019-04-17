package mapEditor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Panel03 extends JPanel {

    private JLabel label1; // NUMBER
    private JLabel label2; // COUNT
    private JTextField box;
    private int number;
    private int count;

    public Panel03() {
        setLayout(new FlowLayout());
        number = 4;
        count = 0;

        label1 = new JLabel(Integer.toString(number));
        label1.setFont(new Font("Serif", Font.BOLD, 100));
        label1.setForeground(Color.blue);
        add(label1);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        add(panel);

        box = new JTextField(Integer.toString(number), 5);
        box.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(box);

        JButton button1 = new JButton("Set");
        button1.addActionListener(new Listener1());
        panel.add(button1);

        JButton button2 = new JButton("Next");
        button2.addActionListener(new Listener2());
        panel.add(button2);

        JButton button3 = new JButton("Quit");
        button3.addActionListener(new Listener3());
        panel.add(button3);

        label2 = new JLabel("Iterations: 0");
        add(label2);
    }

    private class Listener1 implements ActionListener { // SET
        public void actionPerformed(ActionEvent e) {
            number = Integer.parseInt(box.getText());
            count = 0;
            label1.setText("" + number);
            label2.setText("Iterations: " + count);
        }
    }

    private class Listener2 implements ActionListener { // NEXT
        public void actionPerformed(ActionEvent e) {
            if(number % 2 == 0) number = (number / 2);
            else number = (3 * number + 1);
            count ++;
            label1.setText("" + number);
            label2.setText("Iterations: " + count);
        }
    }

    private class Listener3 implements ActionListener { // QUIT
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}