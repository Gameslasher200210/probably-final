package todo;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
public class Jlist2Gui extends JScrollPane {
    public JPanel uncompletedJPanel;
    public JPanel completedPanel;
    public JPanel allPanels;
    public JList<String> uncompletedList;
    public JList<String> completedList;
    public JLabel label;
    public DefaultListModel<String> uncompletedListModel;
    public DefaultListModel<String> completedListModel;
    public fileActions actions = new fileActions();

    public Jlist2Gui() {
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

        this.setViewportView(allPanels);

        displayFileContent(uncompletedListModel, "uncompleted.txt");
        displayFileContent(completedListModel, "completed.txt");
        

  
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
     
    }

  

