package View;

import javax.swing.*;
import java.util.ArrayList;

public abstract class OperationDialog extends JDialog {

    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JTextField> fields = new ArrayList<>();
    JTextArea jTextArea;
    JScrollPane scrollPane;
    JComboBox comboBox;
    ArrayList<JButton> buttons = new ArrayList<>();

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public ArrayList<JTextField> getFields() {
        return fields;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}
