package View;

import Model.IProdotto;
import Model.Servizio;
import View.Listener.CustomOperationDialogViewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomOperationDialogView extends JDialog {

    private AppFrame appFrame;
    private boolean isProduct = false;
    private IProdotto p;
    private Servizio s;
    private OperationDialog operationDialog;

    public CustomOperationDialogView(AppFrame appFrame, IProdotto p,  boolean isProduct) {
        super(appFrame, "Aggiungi");
        this.p = p;
        s = null;
        this.isProduct = isProduct;
        this.appFrame = appFrame;
        setUp();
    }

    public CustomOperationDialogView(AppFrame appFrame, Servizio s,  boolean isProduct){
        super(appFrame, "Aggiungi");
        this.s = s;
        p = null;
        this.isProduct = isProduct;
        this.appFrame = appFrame;
        setUp();
    }

    public CustomOperationDialogView(AppFrame appFrame, boolean isProduct){
        super(appFrame, "Aggiungi");
        this.isProduct = isProduct;
        this.appFrame = appFrame;
        setUp();
    }

    public void setUp(){
        CustomOperationDialogViewListener customOperationDialogViewListener = new CustomOperationDialogViewListener(appFrame, this);
        setLayout(new GridBagLayout());
        setSize(475,455);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,2,5,2);


        operationDialog = new ItemOperationDialog();

        if(isProduct){
            operationDialog = new ProductOperationDialogDecorator(operationDialog);
        } else {
            operationDialog = new ServiceOperationDialogDecorator(operationDialog);
        }

        for (JButton b:operationDialog.getButtons()){
            b.addActionListener(customOperationDialogViewListener);
        }

        c.gridy=0;
        c.gridx=0;
        add(operationDialog.getLabels().get(0), c);
        c.gridx=1;
        add(operationDialog.getFields().get(0), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(4), c);
        c.gridx=1;
        add(operationDialog.getFields().get(1), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(1), c);
        c.gridx=1;
        add(operationDialog.getButtons().get(1), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(5), c);
        c.gridx=1;
        add(operationDialog.getComboBox(), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(2), c);
        c.gridx=1;
        add(operationDialog.getScrollPane(), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(3), c);
        c.gridx=1;
        add(operationDialog.getButtons().get(0), c);
        c.gridy++;
        c.gridx=0;
        c.insets =  new Insets(30,0,5,0);
        add(operationDialog.getButtons().get(3), c);
        c.gridx=1;
        operationDialog.getButtons().get(2).addActionListener(e -> dispose());
        add(operationDialog.getButtons().get(2), c);

        if (p!=null){
            operationDialog.getButtons().get(3).setActionCommand("btnEditProduct");
            operationDialog.getButtons().get(3).setText("Modifica");
            setProductFields();
        } else if (s!=null){
            operationDialog.getButtons().get(3).setText("Modifica");
            operationDialog.getButtons().get(3).setActionCommand("btnEditService");
            setServiceFields();
        }

        setVisible(true);
    }

    public void setServiceFields(){
        operationDialog.getFields().get(0).setText(s.getNome());
        operationDialog.getFields().get(1).setText(Float.toString(s.getCosto()));
        operationDialog.getjTextArea().setText(s.getDescrizione());
        operationDialog.getComboBox().setSelectedItem(s.getFornitore().getNome());
    }

    public void setProductFields(){
        operationDialog.getFields().get(0).setText(p.getNome());
        operationDialog.getFields().get(1).setText(Float.toString(p.getCosto()));
        operationDialog.getjTextArea().setText(p.getDescrizione());
        operationDialog.getComboBox().setSelectedItem(p.getProduttore().getNome());
    }

    public String getTxtNome() {
        return operationDialog.getFields().get(0).getText();
    }

    public String getTxtDescrizione() {
        return operationDialog.getjTextArea().getText();
    }

    public String getTxtPrezzo() {
        return operationDialog.getFields().get(1).getText();
    }

    public Object getProduttore() {
        return operationDialog.getComboBox().getSelectedItem();
    }

    public int getID(){
        if (p != null && s == null){
            return p.getIdProdotto();
        } else if (s != null && p == null){
            return s.getIdServizio();
        }
        return -1;
    }
}
