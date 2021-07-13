package View.Listener;

import Business.ProdottoBusiness;
import Business.PuntoVenditaBusiness;
import Model.IProdotto;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopOperationDialogListener implements ActionListener {
    AppFrame appFrame;
    ShopOperationDialog shopOperationDialog;
    ShopManagerChooserDialog shopManagerChooserDialog;
    ShopProductsChooserDialog shopProductsChooserDialog;
    public final static String BTN_ADD_SHOP = "btnAdd";
    public final static String BTN_EDIT_SHOP = "btnEdit";
    public final static String BTN_MANAGER = "btnManager";
    public final static String BTN_ADD_MANAGER = "btnAddManager";
    public final static String BTN_PRODUCTS = "btnProducts";


    public ShopOperationDialogListener(AppFrame appFrame, ShopOperationDialog dialog){
        this.appFrame = appFrame;
        this.shopOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_SHOP:
                ArrayList<IProdotto> prodotti = new ArrayList<>();
                for (String s:shopProductsChooserDialog.getSelectedProducts()){
                    prodotti.add(ProdottoBusiness.getInstance().findByName(s).getProdotto());
                }
                int st = PuntoVenditaBusiness.getInstance().addNewShop(shopOperationDialog.getTxtShopVia(), shopOperationDialog.getTxtShopCAP(), shopOperationDialog.getTxtShopCitta(), shopOperationDialog.getTxtMagVia(), shopOperationDialog.getTxtMagCitta(), shopOperationDialog.getTxtMagCAP(), shopOperationDialog.getTxtMagCorsie(), shopOperationDialog.getTxtMagScaffali(), shopManagerChooserDialog.getSelectedUser(), prodotti);
                if (st == 4) {
                    String esit = "Punto Vendita e magazzino creato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    shopOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                } else {
                    String esit = "Errore durante la creazione del punto vendita e/o del magazzino!!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                }
                break;
            case BTN_EDIT_SHOP:
                int st_e = PuntoVenditaBusiness.getInstance().updateShopAndWarehouse(shopOperationDialog.getID(), shopOperationDialog.getTxtShopVia(), shopOperationDialog.getTxtShopCAP(), shopOperationDialog.getTxtShopCitta(), shopOperationDialog.getTxtMagVia(), shopOperationDialog.getTxtMagCitta(), shopOperationDialog.getTxtMagCAP(), shopOperationDialog.getTxtMagCorsie(), shopOperationDialog.getTxtMagScaffali(), shopManagerChooserDialog.getSelectedUser(), null);
                if (st_e == 2){
                    String esit = "Punto vendita e magazzino modificati con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    shopOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica del punto vendita e/o del magazzino!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                }
                break;
            case BTN_MANAGER:
                shopManagerChooserDialog = new ShopManagerChooserDialog(appFrame);
                break;
            case BTN_PRODUCTS:
                shopProductsChooserDialog = new ShopProductsChooserDialog(appFrame);
                break;
            case BTN_ADD_MANAGER:
                new ManagerRegistrationDialog(appFrame);
        }
    }
}
