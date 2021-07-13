package View.Listener;

import Business.FornitoreBusiness;
import Business.ProduttoreBusiness;
import Model.Fornitore;
import Model.Responses.FornitoreResponse;
import Model.Responses.ProduttoreResponse;
import View.AppFrame;
import View.ImprenditoreOperationDialogView;
import View.ImprenditoriPanel;
import View.MainCatalogPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FornitorePanelListener implements ActionListener {


    AppFrame appFrame;
    JTable table;

    public final static String BTN_ADD_SUPPLIER = "btnAdd";
    public final static String BTN_EDIT_SUPPLIER = "btnEdit";
    public final static String BTN_DELETE_SUPPLIER = "btnDelete";

    public FornitorePanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_SUPPLIER:
                System.out.println("Aggiungi FORNITORE");
                new ImprenditoreOperationDialogView(appFrame, false);
                break;
            case BTN_EDIT_SUPPLIER:
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                FornitoreResponse fr = FornitoreBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                new ImprenditoreOperationDialogView(appFrame, fr.getFornitore(), false);
                break;
            case BTN_DELETE_SUPPLIER:
                String esit;
                int rowToDelete = table.getSelectedRow();
                int colToDelete = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                int i = FornitoreBusiness.getInstance().deleteByID(idToDelete);
                if (i==1){
                    esit = "Fornitore eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione del fornitore";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
