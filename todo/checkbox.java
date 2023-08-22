package todo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class checkbox {
    public Jlist2Gui jList2Gui;

    public checkbox(Jlist2Gui jList2Gui) {
        this.jList2Gui = jList2Gui;
         
        DefaultListModel<CheckBoxListItem> listModel = new DefaultListModel<>();
        // Assuming you want to use the uncompletedListModel from Jlist2Gui
        JList<CheckBoxListItem> newList = new JList<>(jList2Gui.uncompletedListModel);
        newList.setCellRenderer(new CheckBoxListCellRenderer());

        // You can add newList to your GUI components if needed
        // For example: jList2Gui.uncompletedJPanel.add(newList);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Checkbox JList Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<CheckBoxListItem> listModel = new DefaultListModel<>();
        JList<CheckBoxListItem> List = new JList<>(listModel);
        List.setCellRenderer(new CheckBoxListCellRenderer());

        // Add some items to the list
        listModel.addElement(new CheckBoxListItem("Item 1"));
        listModel.addElement(new CheckBoxListItem("Item 2"));
        listModel.addElement(new CheckBoxListItem("Item 3"));

        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = jList.locationToIndex(e.getPoint());
                if (index != -1) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        CheckBoxListItem item = listModel.getElementAt(index);
                        item.setSelected(!item.isSelected());
                        jList.repaint();
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        JOptionPane.showMessageDialog(frame, "You clicked: " + listModel.getElementAt(index));
                    }
                }
            }
        });

        frame.add(new JScrollPane(jList), BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

class CheckBoxListItem {
    private String label;
    private boolean isSelected;

    public CheckBoxListItem(String label) {
        this.label = label;
        this.isSelected = false;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

class CheckBoxListCellRenderer extends JCheckBox implements ListCellRenderer<CheckBoxListItem> {
    public Component getListCellRendererComponent(
            JList<? extends CheckBoxListItem> list, CheckBoxListItem value, int index,
            boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());
        setSelected(value.isSelected());
        setEnabled(list.isEnabled());
        setText(value.getLabel());
        return this;
    }
}
