package View.Listener;

import Business.ProdottoBusiness;
import Business.PuntoVenditaBusiness;
import Business.ServizioBusiness;
import Model.IProdotto;
import Model.Servizio;
import View.AppFrame;
import View.Dialog.*;
import View.Panel.ShopsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopOperationDialogListener implements ActionListener {
    private AppFrame appFrame;
    private ShopOperationDialog shopOperationDialog;
    private ShopManagerChooserDialog shopManagerChooserDialog;
    private ShopProductsChooserDialog shopProductsChooserDialog;
    private ShopServicesChooserDialog shopServicesChooserDialog;
    public final static String BTN_ADD_SHOP = "btnAdd";
    public final static String BTN_EDIT_SHOP = "btnEdit";
    public final static String BTN_MANAGER = "btnManager";
    public final static String BTN_ADD_MANAGER = "btnAddManager";
    public final static String BTN_PRODUCTS = "btnProducts";
    public final static String BTN_SERVICES = "btnServices";


    public ShopOperationDialogListener(AppFrame appFrame, ShopOperationDialog dialog){
        this.appFrame = appFrame;
        this.shopOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_SHOP:
                try {
                    ArrayList<IProdotto> prodotti = new ArrayList<>();
                    ArrayList<Servizio> servizi = new ArrayList<>();
                    for (String s:shopProductsChooserDialog.getSelectedProducts()){
                        prodotti.add(ProdottoBusiness.getInstance().findByName(s).getProdotto());
                    }
                    for (String s:shopServicesChooserDialog.getSelectedServices()){
                        servizi.add(ServizioBusiness.getInstance().findByName(s).getServizio());
                    }
                    int st = PuntoVenditaBusiness.getInstance().addNewShop(shopOperationDialog.getTxtShopVia(), shopOperationDialog.getTxtShopCAP(), shopOperationDialog.getTxtShopCitta(), shopOperationDialog.getTxtMagVia(), shopOperationDialog.getTxtMagCitta(), shopOperationDialog.getTxtMagCAP(), shopManagerChooserDialog.getSelectedUser(), prodotti, servizi);
                    if (st >= 2+(prodotti.size()*2)+servizi.size()) {
                        String esit = "Punto Vendita e magazzino creato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        shopOperationDialog.dispose();
                        appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                    } else {
                        String esit = "Errore durante la creazione del punto vendita e/o del magazzino!!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                    }
                } catch (NullPointerException exc){
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(appFrame, "Tutti i campi sono obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_EDIT_SHOP:
                try {
                    ArrayList<IProdotto> prodottiM = new ArrayList<>();
                    ArrayList<Servizio> serviziM = new ArrayList<>();
                    for (String s:shopProductsChooserDialog.getSelectedProducts()){
                        prodottiM.add(ProdottoBusiness.getInstance().findByName(s).getProdotto());
                    }
                    for (String s:shopServicesChooserDialog.getSelectedServices()){
                        serviziM.add(ServizioBusiness.getInstance().findByName(s).getServizio());
                    }
                    int st_e = PuntoVenditaBusiness.getInstance().updateShopAndWarehouse(shopOperationDialog.getID(), shopOperationDialog.getTxtShopVia(), shopOperationDialog.getTxtShopCAP(), shopOperationDialog.getTxtShopCitta(), shopOperationDialog.getTxtMagVia(), shopOperationDialog.getTxtMagCitta(), shopOperationDialog.getTxtMagCAP(), shopManagerChooserDialog.getSelectedUser(), prodottiM, serviziM);
                    if (st_e >= 2+prodottiM.size()+serviziM.size()){
                        String esit = "Punto vendita e magazzino modificati con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        shopOperationDialog.dispose();
                        appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                    } else {
                        String esit = "Errore durante la modifica del punto vendita e/o del magazzino!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                    }
                } catch (NullPointerException exc){
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(appFrame, "Tutti i campi sono obbligatori!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_MANAGER:
                shopManagerChooserDialog = new ShopManagerChooserDialog(appFrame);
                break;
            case BTN_PRODUCTS:
                shopProductsChooserDialog = new ShopProductsChooserDialog(appFrame);
                break;
            case BTN_SERVICES:
                shopServicesChooserDialog = new ShopServicesChooserDialog(appFrame);
                break;
            case BTN_ADD_MANAGER:
                new ManagerRegistrationDialog(appFrame);
                break;
        }
    }
}
