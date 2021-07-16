package View;

import javax.swing.*;
import java.util.ArrayList;

public abstract class OperationDialog extends JDialog {

    protected ArrayList<JLabel> labels = new ArrayList<>();
    protected ArrayList<JTextField> fields = new ArrayList<>();
    protected JTextArea jTextArea;
    protected JScrollPane scrollPane;
    protected JComboBox comboBox;
    protected ArrayList<JButton> buttons = new ArrayList<>();

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
