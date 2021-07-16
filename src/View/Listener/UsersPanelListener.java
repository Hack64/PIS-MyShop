package View.Listener;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.PuntoVendita;
import Model.Utente;
import View.AppFrame;
import View.Dialog.EmailDialog;
import View.Panel.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_DISABLE_USER = "btnDisable";
    public final static String BTN_DELETE_USER = "btnDelete";
    public final static String BTN_SEND_EMAIL = "btnSend";

    public UsersPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
        switch (cmd){
            case BTN_DISABLE_USER:
                if(table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    Utente u = UtenteBusiness.getInstance().findByID((Integer)(table.getModel().getValueAt(rowToEdit, colToEdit))).getUtente();
                    Utente currentUser = (Utente)SessionManager.getInstance().getSession().get("loggedUser");
                    System.out.println(table.getModel().getValueAt(rowToEdit, 8));
                    if (u.getIdUtente() != currentUser.getIdUtente()){
                        if (Integer.parseInt(table.getModel().getValueAt(rowToEdit, 8).toString()) == 0){
                            int res = UtenteBusiness.getInstance().disableUser(u, p);
                            if (res == 1){
                                esit = "Utente disabilitato!";
                                JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                                appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                            }
                        } else {
                            int res = UtenteBusiness.getInstance().enableUser(u, p);
                            if (res == 1){
                                esit = "Utente abilitato!";
                                JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                                appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                            }
                        }
                    } else {
                        esit = "Non puoi bannarti da solo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
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
                    int i = UtenteBusiness.getInstance().deleteByIDFromShop(idToDelete, p.getIdPuntoVendita());
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
            case BTN_SEND_EMAIL:
                if (table.getSelectedRowCount() == 1){
                    int row = table.getSelectedRow();
                    int col = 3;
                    String email = table.getModel().getValueAt(row, col).toString();
                    new EmailDialog(appFrame, email);
                } else {
                    new EmailDialog(appFrame, null);
                }
                break;
        }
    }
}
