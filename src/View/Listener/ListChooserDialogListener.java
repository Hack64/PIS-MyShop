package View.Listener;

import Business.ListaBusiness;
import Business.ProdottoBusiness;
import Business.ServizioBusiness;
import View.AppFrame;
import View.Dialog.ListChooserDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListChooserDialogListener implements ActionListener {

    private AppFrame appFrame;
    private ListChooserDialog listChooserDialog;
    private final static String BTN_ADD_PRODUCT = "btnOkProduct";
    private final static String BTN_ADD_SERVICE = "btnOkService";

    public ListChooserDialogListener(AppFrame appFrame, ListChooserDialog dialog){
        this.appFrame = appFrame;
        this.listChooserDialog = dialog;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        int st;
        switch(cmd){
            case BTN_ADD_PRODUCT:
                st = ListaBusiness.getInstance().addProductToList(listChooserDialog.getSelectedList().getLista(), ProdottoBusiness.getInstance().find(listChooserDialog.getIdArticolo()).getProdotto(), listChooserDialog.getQuantita());
                if (st == 1){
                    JOptionPane.showMessageDialog(appFrame, "Prodotto aggiunto alla lista con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    listChooserDialog.dispose();
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Errore durante l'aggiunta alla lista", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_SERVICE:
                st = ListaBusiness.getInstance().addServiceToList(listChooserDialog.getSelectedList().getLista(), ServizioBusiness.getInstance().find(listChooserDialog.getIdArticolo()).getServizio());
                if (st == 1){
                    JOptionPane.showMessageDialog(appFrame, "Servizio aggiunto alla lista con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    listChooserDialog.dispose();
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Errore durante l'aggiunta alla lista", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
