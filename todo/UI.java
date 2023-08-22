package todo;

import javax.swing.*;
import java.awt.BorderLayout;

public class UI {
    public JFrame frame;

    public UI() {
        frame = new JFrame("TO DO LISTS APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        Jlist2Gui Jlist = new Jlist2Gui();
        menu menuPanel = new menu(frame, Jlist); // Create the menu panel
        addingElements ui = new addingElements("ADD A NEW TASK");

        new addButtonListener(ui, Jlist);
        new contextMenuCompleted(Jlist);
        new contextMenuUncompleted(Jlist);

        frame.setJMenuBar(menuPanel.getMenuBar()); // Set the menu bar in the frame
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
