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

        //design

        Color bgColor = new Color(51, 51, 51);
        Color labelColor = Color.WHITE;
        Font font = new Font("Arial", Font.PLAIN, 18);
        

        uncompletedJPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 0), 2, true));
        uncompletedList.setBackground(bgColor);
        uncompletedJPanel.setBackground(bgColor);
        uncompletedList.setForeground(Color.WHITE);
        uncompletedList.setFont(font);

        completedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        completedList.setBackground(bgColor);
        completedPanel.setBackground(bgColor);
        completedList.setForeground(Color.WHITE);
        completedList.setFont(font);

        allPanels.setBackground(Color.DARK_GRAY);
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(labelColor);
        label.setFont(font);

  
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
    public void clearUncompletedList() {
      
        uncompletedListModel.clear();
      
       
    }
    
    
    public void clearCompletedList() {
        
        completedListModel.clear();
    }
    
     
    }