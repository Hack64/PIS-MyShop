package View.Listener;

import Business.ProduttoreBusiness;
import Model.Responses.ProduttoreResponse;
import View.AppFrame;
import View.Dialog.ImprenditoreOperationDialogView;
import View.Panel.ImprenditoriPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduttorePanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

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
        String esit;
        switch (cmd){
            case BTN_ADD_PRODUCER:
                System.out.println("Aggiungi produttore");
                new ImprenditoreOperationDialogView(appFrame, null, true);
                break;
            case BTN_EDIT_PRODUCER:
                if (table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    ProduttoreResponse pr = ProduttoreBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new ImprenditoreOperationDialogView(appFrame, pr.getProduttore(), true);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_PRODUCER:
                if (table.getSelectedRowCount()==1){
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
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
