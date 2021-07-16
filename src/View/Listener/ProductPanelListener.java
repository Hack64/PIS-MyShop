package View.Listener;

import Business.SessionManager;
import Model.IProdotto;
import Model.Servizio;
import Model.Utente;
import View.AppFrame;
import View.Dialog.ListChooserDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPanelListener implements ActionListener {

    private AppFrame appFrame;
    private int idArticolo;
    private final static String BTN_ADD_SERVICE_TO_LIST = "btnAddService";
    private final static String BTN_ADD_PRODUCT_TO_LIST = "btnAddProduct";
    private final static String BTN_COMMENTS = "btnComments";

    public ProductPanelListener(AppFrame appFrame, int idArticolo){
        this.appFrame = appFrame;
        this.idArticolo = idArticolo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_PRODUCT_TO_LIST:
                if(SessionManager.getInstance().getSession().get("loggedUser") != null){
                    new ListChooserDialog(appFrame, true, idArticolo);
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Devi essere un utente registrato per aggiungere articoli alle liste!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_SERVICE_TO_LIST:
                if(SessionManager.getInstance().getSession().get("loggedUser") != null){
                    new ListChooserDialog(appFrame, false, idArticolo);
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Devi essere un utente registrato per aggiungere articoli alle liste!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }
}
