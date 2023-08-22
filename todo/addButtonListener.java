package todo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class addButtonListener implements ActionListener {
    private addingElements element;
    Jlist2Gui Jlist = new Jlist2Gui();
    public addButtonListener(addingElements element, Jlist2Gui Jlist) {
        this.element = element;
        this.Jlist = Jlist;
        // Register the listener with the Add button
        element.getAddButton().addActionListener(this);

        // Add the MouseListener to clear the text field
        element.getTextField().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getSource() == element.getTextField()) {
                    // Clear the text field after adding the task
                    element.getTextField().setText("");
                }
            }
        });

        // Add KeyListener to the text field for the Enter key
        element.getTextField().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    // Trigger the action when Enter key is pressed
                    element.getAddButton().doClick();
                     element.getTextField().setText("");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = element.getTextField().getText();
        fileActions act = new fileActions();
        act.add("uncompleted.txt", inputText);
        Jlist.uncompletedListModel.addElement(inputText);
        // Reset the placeholder text
        element.getTextField().setText("ADD A NEW TASK");

        // Add code to handle exceptions
    }
}
