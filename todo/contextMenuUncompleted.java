package todo;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class contextMenuUncompleted {

    private final fileActions actions = new fileActions();
    private final Jlist2Gui Jlist;

    public contextMenuUncompleted(Jlist2Gui Jlist) {
        this.Jlist = Jlist;
        addJListClickListener(Jlist.uncompletedList);
        addJListClickListener(Jlist.completedList);

        setupContextMenu(Jlist.uncompletedList, "uncompleted.txt");
      
    }

    public void setupContextMenu(JList<String> jList, String fileName) {
        JPopupMenu contextMenu = new JPopupMenu();

        JMenuItem complete = new JMenuItem("Mark as complete");
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");

        contextMenu.add(complete);
        contextMenu.add(editItem);
        contextMenu.add(deleteItem);

        editItem.addActionListener(e -> {
            int selectedIndex = jList.getSelectedIndex();
            if (selectedIndex != -1) {
                editListItem(jList, selectedIndex, null, fileName);
            }
        });

        deleteItem.addActionListener(e -> {
            int selectedIndex = jList.getSelectedIndex();
            if (selectedIndex != -1) {
                DefaultListModel<String> model = (DefaultListModel<String>) jList.getModel();
                model.remove(selectedIndex);
                actions.removeByLineNumber(fileName, selectedIndex + 1);
            }
        });

        complete.addActionListener(e -> {
            int selectedIndex = jList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = jList.getSelectedValue();
                DefaultListModel<String> uncompletedModel = Jlist.uncompletedListModel;
                DefaultListModel<String> completedModel = Jlist.completedListModel;

                uncompletedModel.remove(selectedIndex);
                actions.removeByLineNumber("uncompleted.txt", selectedIndex + 1);

                completedModel.addElement(selectedItem);
                actions.add("completed.txt", selectedItem);
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

    public void addJListClickListener(JList<String> jList) {
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedItem = jList.getSelectedValue();
                    System.out.println("Selected item: " + selectedItem);
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
            actions.update(fileName, editedText, index + 1);
        }
    }
}
