package View.Listener;

import Business.ListaBusiness;
import Business.PuntoVenditaBusiness;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopOperationDialogListener implements ActionListener {
    AppFrame appFrame;
    ShopOperationDialog shopOperationDialog;
    public final static String BTN_ADD_SHOP = "btnAdd";
    public final static String BTN_EDIT_SHOP = "btnEdit";


    public ShopOperationDialogListener(AppFrame appFrame, ShopOperationDialog dialog){
        this.appFrame = appFrame;
        this.shopOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_SHOP:
                int st = PuntoVenditaBusiness.getInstance().addNewShop(shopOperationDialog.getTxtShopVia(), shopOperationDialog.getTxtShopCAP(), shopOperationDialog.getTxtShopCitta(), shopOperationDialog.getTxtMagVia(), shopOperationDialog.getTxtMagCitta(), shopOperationDialog.getTxtMagCAP(), shopOperationDialog.getTxtMagCorsie(), shopOperationDialog.getTxtMagScaffali());
                if (st == 2) {
                    String esit = "Punto Vendita (e magazzino) creato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    shopOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                } else {
                    String esit = "Errore durante la creazione della lsita!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                }
                break;
            /* case BTN_EDIT_SHOP:
                int st_e = ListaBusiness.getInstance().updateListName(shopOperationDialog.getNomeLista(), shopOperationDialog.getIdLista());
                if (st_e == 1){
                    String esit = "Lista modificata con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    shopOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ListsPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica della lsita!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ListsPanel(appFrame));
                }
                break;*/
        }
    }
}
