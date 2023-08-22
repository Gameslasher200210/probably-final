package todo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JPanel {
    JFrame frame;
    Jlist2Gui list;
     JMenuBar menuBar;
     fileActions actions;
    menu(JFrame frame, Jlist2Gui list ){
            this.frame = frame;
            this.list = list;
            menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("MENU");

            JMenuItem clearTasks = new JMenuItem("CLEAR ALL COMPLETED TASK");
            JMenuItem clearAll = new JMenuItem("CLEAR EVERYTHING");
            JMenuItem exitMenuItem = new JMenuItem("EXIT");
             // Add action listeners to menu items
            clearTasks.addActionListener(e -> {
                // Implement open functionality here
                list.clearCompletedList();
                actions.clearFileContent("completed.txt");
                JOptionPane.showMessageDialog(frame, "All completed tasks cleared.");
            });

            clearAll.addActionListener(e -> {
              
                    list.clearUncompletedList();
                    list.clearCompletedList();
                    actions.clearFileContent("uncompleted.txt");
                    actions.clearFileContent("completed.txt");
                    JOptionPane.showMessageDialog(frame, "All tasks cleared.");
               
            });
            

            exitMenuItem.addActionListener(e -> System.exit(0));

            // Add menu items to the file menu
            fileMenu.add(clearTasks);
            fileMenu.add(clearAll);
            fileMenu.addSeparator();
            fileMenu.add(exitMenuItem);

            // Add the file menu to the menu bar
            menuBar.add(fileMenu);
          
    }
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}

