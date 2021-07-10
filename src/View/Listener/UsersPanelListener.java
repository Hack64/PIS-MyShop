package View.Listener;

import Business.ServizioBusiness;
import Business.UtenteBusiness;
import Model.Responses.ServizioResponse;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanelListener implements ActionListener {

    AppFrame appFrame;
    JTable table;

    public final static String BTN_EDIT_USER = "btnEdit";
    public final static String BTN_DELETE_USER = "btnDelete";

    public UsersPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_EDIT_USER:
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                ServizioResponse sr = ServizioBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                new CustomOperationDialogView(appFrame, sr.getServizio(), false);
                break;
            case BTN_DELETE_USER:
                String esit;
                int rowToDelete = table.getSelectedRow();
                int colToDelete = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                //TODO: cambia idpuntovendita
                int i = UtenteBusiness.getInstance().deleteByIDFromShop(idToDelete, 1);
                if (i==1){
                    esit = "Utente eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
