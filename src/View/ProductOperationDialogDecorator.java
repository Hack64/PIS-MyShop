package View;

import Business.ProduttoreBusiness;

import javax.swing.*;
import java.util.ArrayList;

public class ProductOperationDialogDecorator extends CustomOperationDialogDecorator {

    public ProductOperationDialogDecorator(OperationDialog operationDialog){
        this.operationDialog = operationDialog;
    }

    @Override
    public JScrollPane getScrollPane() {
        scrollPane = this.operationDialog.getScrollPane();
        return scrollPane;
    }

    @Override
    public ArrayList<JLabel> getLabels() {
        if (labels.size() > 0 ) return labels;
        labels.addAll(this.operationDialog.getLabels());
        JLabel lblProduttore = new JLabel("Produttore: ");
        labels.add(lblProduttore);
        return labels;
    }

    @Override
    public ArrayList<JTextField> getFields() {
        if (fields.size() > 0 ) return fields;
        fields.addAll(this.operationDialog.getFields());
        return fields;
    }

    @Override
    public JTextArea getjTextArea() {
        jTextArea = this.operationDialog.getjTextArea();
        return jTextArea;
    }

    @Override
    public JComboBox getComboBox() {
        if(comboBox != null) return comboBox;
        ArrayList<String> produttori = ProduttoreBusiness.getInstance().findAllProducersNames();
        comboBox = new JComboBox(produttori.toArray());
        return comboBox;
    }

    @Override
    public ArrayList<JButton> getButtons() {
        if (buttons.size() > 0) return buttons;
        buttons.addAll(this.operationDialog.getButtons());
        JButton btnOk = new JButton("OK");
        btnOk.setActionCommand("btnAddProduct");
        buttons.add(btnOk);
        return buttons;
    }
}
