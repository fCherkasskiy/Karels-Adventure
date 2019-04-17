package mapEditor;

import javax.swing.*;

public class driver_mapEditor{
    public static void main(String[] args) {
        JFrame mainPanel = new JFrame();

        Panel03 panel = new Panel03();
        dialogue_mapEditor dialogue = new dialogue_mapEditor();
        dialogue.createFrame();

        mainPanel.add(dialogue);
        mainPanel.setVisible(true);
    }
}
