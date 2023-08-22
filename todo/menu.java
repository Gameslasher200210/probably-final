package todo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing Menu Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("MENU");

            JMenuItem clearTasks = new JMenuItem("CLEAR ALL COMPLETED TASK");
            JMenuItem clearAll = new JMenuItem("CLEAR EVERYTHING");
            JMenuItem exitMenuItem = new JMenuItem("EXIT");

            // Add action listeners to menu items
            clearTasks.addActionListener(e -> {
                // Implement open functionality here
                JOptionPane.showMessageDialog(frame, "Open menu clicked");
            });

            clearAll.addActionListener(e -> {
                // Implement save functionality here
                JOptionPane.showMessageDialog(frame, "Save menu clicked");
            });

            exitMenuItem.addActionListener(e -> System.exit(0));

            // Add menu items to the file menu
            fileMenu.add(clearTasks);
            fileMenu.add(clearAll);
            fileMenu.addSeparator();
            fileMenu.add(exitMenuItem);

            // Add the file menu to the menu bar
            menuBar.add(fileMenu);

            // Set the menu bar for the frame
            frame.setJMenuBar(menuBar);

            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
}

