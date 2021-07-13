package View.Listener;

import Business.ProduttoreBusiness;
import Model.Responses.ProduttoreResponse;
import View.AppFrame;
import View.ImprenditoreOperationDialogView;
import View.ImprenditoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduttorePanelListener implements ActionListener {

    AppFrame appFrame;
    JTable table;

    public final static String BTN_ADD_PRODUCER = "btnAdd";
    public final static String BTN_EDIT_PRODUCER = "btnEdit";
    public final static String BTN_DELETE_PRODUCER = "btnDelete";

    public ProduttorePanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_PRODUCER:
                System.out.println("Aggiungi produttore");
                new ImprenditoreOperationDialogView(appFrame, true);
                break;
            case BTN_EDIT_PRODUCER:
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                ProduttoreResponse pr = ProduttoreBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                new ImprenditoreOperationDialogView(appFrame, pr.getProduttore(), true);
                break;
            case BTN_DELETE_PRODUCER:
                String esit;
                int rowToDelete = table.getSelectedRow();
                int colToDelete = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                int i = ProduttoreBusiness.getInstance().deleteByID(idToDelete);
                if (i==1){
                    esit = "Produttore eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione del produttore";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
