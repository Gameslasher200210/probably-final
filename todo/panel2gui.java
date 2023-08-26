package todo;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class CustomPanel extends JPanel {
    private JLabel label;

    public CustomPanel(String text) {
        label = new JLabel("<html><body style='width: 140px'>" + text + "</body></html>");
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        setPreferredSize(new Dimension(190, 190)); // Fixed size of the panel
    }
}

class CustomPanelRenderer implements ListCellRenderer<CustomPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends CustomPanel> list, CustomPanel value, int index, boolean isSelected, boolean cellHasFocus) {
        value.setBackground(isSelected ? Color.BLUE : Color.WHITE);
        value.setForeground(isSelected ? Color.WHITE : Color.BLACK);
        return value;
    }
}

public class panel2gui {
    public static DefaultListModel<CustomPanel> createPanelListModelFromFile(String fileName) {
        DefaultListModel<CustomPanel> model = new DefaultListModel<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                model.addElement(new CustomPanel(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void createAndShowPanelListGUI(DefaultListModel<CustomPanel> model) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom Panel JList Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JList<CustomPanel> customPanelList = new JList<>(model);
            customPanelList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            customPanelList.setVisibleRowCount(0);
            customPanelList.setCellRenderer(new CustomPanelRenderer());

            frame.add(new JScrollPane(customPanelList));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        DefaultListModel<CustomPanel> model = createPanelListModelFromFile("uncompleted.txt");
        createAndShowPanelListGUI(model);
    }
}

