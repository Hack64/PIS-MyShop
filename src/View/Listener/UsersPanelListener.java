package View.Listener;

import Business.ServizioBusiness;
import Business.UtenteBusiness;
import Model.Responses.ServizioResponse;
import View.AppFrame;
import View.CustomOperationDialogView;
import View.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_EDIT_USER = "btnEdit";
    public final static String BTN_DELETE_USER = "btnDelete";

    public UsersPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_EDIT_USER:
                if(table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    ServizioResponse sr = ServizioBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new CustomOperationDialogView(appFrame, sr.getServizio(), false);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_USER:
                if (table.getSelectedRowCount()==1){
                    int rowToDelete = table.getSelectedRow();
                    int colToDelete = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                    int i = UtenteBusiness.getInstance().deleteByIDFromShop(idToDelete, appFrame.getPuntoVendita().getIdPuntoVendita());
                    if (i==1){
                        esit = "Utente eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per eliminarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }

                break;
        }
    }
}
