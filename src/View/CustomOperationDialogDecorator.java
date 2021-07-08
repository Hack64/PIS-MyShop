package View;

import javax.swing.*;
import java.util.ArrayList;

public abstract class CustomOperationDialogDecorator extends OperationDialog {
    protected OperationDialog operationDialog;

    @Override
    public abstract JScrollPane getScrollPane();

    @Override
    public abstract ArrayList<JLabel> getLabels();

    @Override
    public abstract ArrayList<JTextField> getFields();

    @Override
    public abstract JTextArea getjTextArea();

    @Override
    public abstract JComboBox getComboBox();

    @Override
    public abstract ArrayList<JButton> getButtons();
}
