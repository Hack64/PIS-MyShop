package View.Listener;

import Business.*;
import Model.Disponibilita;
import Model.IProdotto;
import Model.PuntoVendita;
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
                IProdotto prodotto = ProdottoBusiness.getInstance().find(listChooserDialog.getIdArticolo()).getProdotto();
                int old_qta = ListaBusiness.getInstance().isProductAlreadyInList(listChooserDialog.getSelectedList().getLista(), prodotto);
                st = ListaBusiness.getInstance().addProductToList(listChooserDialog.getSelectedList().getLista(), prodotto, listChooserDialog.getQuantita());
                if (st == 2){
                    PuntoVendita pv = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
                    Disponibilita d = ProdottiMagazzinoBusiness.getInstance().findByProductAndWarehouse(prodotto.getIdProdotto(), MagazzinoBusiness.getInstance().findWarehouseByShopID(pv.getIdPuntoVendita()).getIdMagazzino());
                    d.setQta(d.getQta() - listChooserDialog.getQuantita() + old_qta);
                    ProdottiMagazzinoBusiness.getInstance().update(d);
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
