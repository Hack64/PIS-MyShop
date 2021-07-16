package View.Listener;

import Business.FornitoreBusiness;
import Model.Responses.FornitoreResponse;
import View.AppFrame;
import View.Dialog.ImprenditoreOperationDialogView;
import View.Panel.ImprenditoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FornitorePanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

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
        String esit;
        switch (cmd){
            case BTN_ADD_SUPPLIER:
                System.out.println("Aggiungi FORNITORE");
                new ImprenditoreOperationDialogView(appFrame, false);
                break;
            case BTN_EDIT_SUPPLIER:
                if (table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    FornitoreResponse fr = FornitoreBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new ImprenditoreOperationDialogView(appFrame, fr.getFornitore(), false);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_SUPPLIER:
                if (table.getSelectedRowCount()==1){
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
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
