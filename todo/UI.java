package todo;

import javax.swing.*;
import java.awt.BorderLayout;

public class UI {
    public JFrame frame;

    public UI() {
        frame = new JFrame("File To JList GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        addingElements ui = new addingElements("ADD A NEW TASK");
        Jlist2Gui Jlist = new Jlist2Gui();
       
        new addButtonListener(ui,Jlist);
       new contextMenu(Jlist);
       
        frame.add(ui, BorderLayout.NORTH); // Adding elements at the top
        frame.add(Jlist, BorderLayout.CENTER); // Adding the scroll pane in the center

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UI();
        });
    }
}