package todo;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class printToGui {
    public JScrollPane scrollPane;
    public JPanel uncompletedJPanel;
    public JPanel completedPanel;
    public JPanel allPanels;
    public JList<String> uncompletedList;
    public JList<String> completedList;
    public JLabel label;
    public DefaultListModel<String> uncompletedListModel;
    public DefaultListModel<String> completedListModel;
    public fileActions actions = new fileActions();
    panel2gui gui = new panel2gui();

    public printToGui() {
        // Initialize components and setup UI
        uncompletedListModel = new DefaultListModel<>();
        uncompletedList = new JList<>(uncompletedListModel);
        uncompletedJPanel = new JPanel();

        completedListModel = new DefaultListModel<>();
        completedList = new JList<>(completedListModel);
        completedPanel = new JPanel();

        uncompletedJPanel.add(uncompletedList);
        completedPanel.add(completedList);

        allPanels = new JPanel();
        allPanels.setLayout(new BoxLayout(allPanels, BoxLayout.Y_AXIS));
        allPanels.add(uncompletedJPanel);
        label = new JLabel("COMPLETED TASKS");
        allPanels.add(label);
        allPanels.add(completedPanel);

        uncompletedJPanel.add(uncompletedList);
        completedPanel.add(completedList);
        
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(allPanels);

        displayFileContent(uncompletedListModel, "uncompleted.txt");
        displayFileContent(completedListModel, "completed.txt");

        addJListClickListener(uncompletedList);
        addJListClickListener(completedList);

        setupContextMenu(uncompletedList, "uncompleted.txt");
        setupContextMenu(completedList, "completed.txt");
    }

    public void setupContextMenu(JList<String> jList, String fileName) {
        JPopupMenu contextMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");

        contextMenu.add(editItem);
        contextMenu.add(deleteItem);

        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                    editListItem(jList, selectedIndex, null, fileName); // Call the editListItem method here
                }
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                    DefaultListModel<String> model = (DefaultListModel<String>) jList.getModel();
                    model.remove(selectedIndex);
                    actions.removeByLineNumber(fileName, ++selectedIndex);
                }
            }
        });

        jList.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int index = jList.locationToIndex(e.getPoint());
                    if (index != -1 && jList.getCellBounds(index, index).contains(e.getPoint())) {
                        jList.setSelectedIndex(index);
                        contextMenu.show(jList, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    public void displayFileContent(DefaultListModel<String> listModel, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                listModel.addElement(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            listModel.addElement("Error reading file: " + e.getMessage());
        }
    }

    public void addJListClickListener(JList<String> jList) {
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = jList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String selectedItem = jList.getSelectedValue();
                        System.out.println("Selected item: " + selectedItem);
                    }
                }
            }
        });
    }

    public void editListItem(JList<String> jList, int index, String value, String fileName) {
        DefaultListModel<String> model = (DefaultListModel<String>) jList.getModel();
        String selectedItem = model.getElementAt(index);

        JTextField textField = new JTextField(selectedItem);

        int response = JOptionPane.showConfirmDialog(
                jList,
                textField,
                "Edit Item",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (response == JOptionPane.OK_OPTION) {
            String editedText = textField.getText();
            model.setElementAt(editedText, index);
            actions.update(fileName, editedText, ++index );
        }
    }
}
