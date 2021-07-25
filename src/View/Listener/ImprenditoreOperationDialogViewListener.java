package View.Listener;

import Business.FornitoreBusiness;
import Business.ProduttoreBusiness;
import View.AppFrame;
import View.Dialog.ImprenditoreOperationDialogView;
import View.Panel.ImprenditoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImprenditoreOperationDialogViewListener implements ActionListener {

    private AppFrame appFrame;
    private ImprenditoreOperationDialogView operationDialogView;

    public final static String BTN_ADD_PRODUCER = "btnAddProducer";
    public final static String BTN_ADD_SUPPLIER = "btnAddSupplier";
    public final static String BTN_EDIT_PRODUCER = "btnEditProducer";
    public final static String BTN_EDIT_SUPPLIER = "btnEditSupplier";

    public ImprenditoreOperationDialogViewListener(AppFrame appFrame, ImprenditoreOperationDialogView dialog){
        this.appFrame = appFrame;
        this.operationDialogView = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case BTN_ADD_PRODUCER:
                if (!operationDialogView.getTxtNome().isEmpty() && !operationDialogView.getTxtSito().isEmpty() && !operationDialogView.getTxtCitta().isEmpty() && !operationDialogView.getTxtNazione().isEmpty()) {
                    int statusProd = ProduttoreBusiness.getInstance().addNewProducer(operationDialogView.getTxtNome(), operationDialogView.getTxtSito(), operationDialogView.getTxtCitta(), operationDialogView.getTxtNazione());
                    if (statusProd == 1) {
                        String esit = "Produttore aggiunto con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        operationDialogView.dispose();
                        appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                    } else {
                        String esit = "Errore durante l'aggiunta del produttore!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                    }
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Tutti i campi sono obbligatori", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_EDIT_PRODUCER:
                int st = ProduttoreBusiness.getInstance().update(operationDialogView.getID(), operationDialogView.getTxtNome(), operationDialogView.getTxtSito(), operationDialogView.getTxtCitta(), operationDialogView.getTxtNazione());
                if (st == 1) {
                    String esit = "Produttore modificato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    operationDialogView.dispose();
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica del produttore!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                }
                break;
            case BTN_ADD_SUPPLIER:
                if (!operationDialogView.getTxtNome().isEmpty() && !operationDialogView.getTxtSito().isEmpty() && !operationDialogView.getTxtCitta().isEmpty() && !operationDialogView.getTxtNazione().isEmpty()) {
                    int statusFor = FornitoreBusiness.getInstance().addNewSupplier(operationDialogView.getTxtNome(), operationDialogView.getTxtSito(), operationDialogView.getTxtCitta(), operationDialogView.getTxtNazione());
                    if (statusFor == 1) {
                        String esit = "Fornitore aggiunto con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        operationDialogView.dispose();
                        appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                    } else {
                        String esit = "Errore durante l'aggiunta del fornitore!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                    }
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Tutti i campi sono obbligatori", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_EDIT_SUPPLIER:
                int stEditSupplier = FornitoreBusiness.getInstance().update(operationDialogView.getID(), operationDialogView.getTxtNome(), operationDialogView.getTxtSito(), operationDialogView.getTxtCitta(), operationDialogView.getTxtNazione());
                if (stEditSupplier == 1) {
                    String esit = "Fornitore modificato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    operationDialogView.dispose();
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica del fornitore!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                }
                break;
        }
    }
}
