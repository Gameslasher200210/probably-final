package todo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addingElements extends JPanel{
    private JButton addButton;
    private JTextField textField;
    addingElements(String textFieldText){
        this.addButton = new JButton("+");
        this.textField = new JTextField(textFieldText,50);

    //styling elements 
    //ADD BUTTON
    Font buttonFont = new Font("Arial", Font.BOLD, 16);
    addButton.setFont(buttonFont);
    addButton.setContentAreaFilled(false);
    addButton.setOpaque(true);
    addButton.setBackground(Color.WHITE);
    addButton.setForeground(Color.BLACK);
    addButton.setFocusPainted(false);
    addButton.setBorderPainted(false);
    //TEXTFIELD
    Font textfield = new Font("Arial",Font.PLAIN, 16);
    textField.setFont(textfield);
    textField.setForeground(Color.BLACK);
    textField.setBackground(Color.WHITE);
    textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
   // Border roundedBorder = BorderFactory.createRoundedBorder(10, 10);
   // textField.setBorder(roundedBorder);
     this.add(addButton);
     this.add(textField);
    }
 
    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getTextField() {
        return textField;
    }
    
}
