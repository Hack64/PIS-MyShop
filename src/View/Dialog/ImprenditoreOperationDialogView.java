package View.Dialog;

import Model.Fornitore;
import Model.Imprenditore;
import Model.Produttore;
import View.AppFrame;
import View.Decorator.FornitoreOperationDialogDecorator;
import View.Decorator.OperationDialog;
import View.Decorator.ProduttoreOperationDialogDecorator;
import View.Listener.ImprenditoreOperationDialogViewListener;

import javax.swing.*;
import java.awt.*;

public class ImprenditoreOperationDialogView extends JDialog {

    private AppFrame appFrame;
    private Produttore p;
    private Fornitore f;
    private boolean isProducer;
    private OperationDialog operationDialog;

    public ImprenditoreOperationDialogView(AppFrame appFrame, Imprenditore i, boolean isProducer) {
        super(appFrame, "Aggiungi");
        this.isProducer = isProducer;
        if (isProducer){
            this.p = (Produttore) i;
            this.f = null;
        }else {
            this.p = null;
            this.f = (Fornitore) i;
        }
        if (i == null){
            this.p = null;
            this.f = null;
        }
        this.appFrame = appFrame;
        setUp();
    }


    public void setUp(){
        ImprenditoreOperationDialogViewListener imprenditoreOperationDialogViewListener = new ImprenditoreOperationDialogViewListener(appFrame, this);
        setLayout(new GridBagLayout());
        setSize(320,300);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,2,5,2);

        operationDialog = new ImprenditoreOperationDialog();

        if(isProducer){
            operationDialog = new ProduttoreOperationDialogDecorator(operationDialog);
        } else {
            operationDialog = new FornitoreOperationDialogDecorator(operationDialog);
        }

        for (JButton b:operationDialog.getButtons()){
            b.addActionListener(imprenditoreOperationDialogViewListener);
        }

        c.gridy=0;
        c.gridx=0;
        add(operationDialog.getLabels().get(0), c);
        c.gridx=1;
        add(operationDialog.getFields().get(0), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(1), c);
        c.gridx=1;
        add(operationDialog.getFields().get(1), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(2), c);
        c.gridx=1;
        add(operationDialog.getFields().get(2), c);
        c.gridy++;
        c.gridx=0;
        add(operationDialog.getLabels().get(3), c);
        c.gridx=1;
        add(operationDialog.getFields().get(3), c);
        c.gridy++;
        c.gridx=0;
        c.insets =  new Insets(30,0,5,0);
        add(operationDialog.getButtons().get(1), c);
        c.gridx=1;
        operationDialog.getButtons().get(0).addActionListener(e -> dispose());
        add(operationDialog.getButtons().get(0), c);

        if (p!=null) {
            operationDialog.getButtons().get(1).setActionCommand("btnEditProducer");
            operationDialog.getButtons().get(1).setText("Modifica");
            setProductFields();
        } else if (f!=null){
            operationDialog.getButtons().get(1).setText("Modifica");
            operationDialog.getButtons().get(1).setActionCommand("btnEditSupplier");
            setServiceFields();
        }
        setVisible(true);
    }

    public void setServiceFields(){
        operationDialog.getFields().get(0).setText(f.getNome());
        operationDialog.getFields().get(1).setText(f.getSito());
        operationDialog.getFields().get(2).setText(f.getCitta());
        operationDialog.getFields().get(3).setText(f.getNazione());
    }

    public void setProductFields(){
        operationDialog.getFields().get(0).setText(p.getNome());
        operationDialog.getFields().get(1).setText(p.getSito());
        operationDialog.getFields().get(2).setText(p.getCitta());
        operationDialog.getFields().get(3).setText(p.getNazione());
    }

    public String getTxtNome() {
        return operationDialog.getFields().get(0).getText();
    }

    public String getTxtSito() {
        return operationDialog.getFields().get(1).getText();
    }

    public String getTxtCitta() {
        return operationDialog.getFields().get(2).getText();
    }

    public String getTxtNazione() {
        return operationDialog.getFields().get(3).getText();
    }

    public int getID(){
        if (p != null && f == null){
            return p.getIdProduttore();
        } else if (f != null && p == null){
            return f.getIdFornitore();
        }
        return -1;
    }
}

