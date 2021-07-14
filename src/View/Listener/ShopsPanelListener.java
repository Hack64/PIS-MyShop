package View.Listener;

import Business.PuntoVenditaBusiness;
import Model.Responses.PuntoVenditaResponse;
import View.AppFrame;
import View.ShopOperationDialog;
import View.ShopsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopsPanelListener implements ActionListener {
    AppFrame appFrame;
    JTable table;

    public final static String BTN_ADD_SHOP = "btnAdd";
    public final static String BTN_EDIT_SHOP = "btnEdit";
    public final static String BTN_DELETE_SHOP = "btnDelete";

    public ShopsPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_SHOP:
                new ShopOperationDialog(appFrame, false, null,null);
                break;
            case BTN_EDIT_SHOP:
                if (table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    PuntoVenditaResponse pvr = PuntoVenditaBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new ShopOperationDialog(appFrame, true, pvr.getPuntoVendita(), pvr.getMagazzino());
                } else {
                    esit = "Devi selezionare un elemento per eliminarlo o modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_SHOP:
                if (table.getSelectedRow() == 1) {
                    int rowToDelete = table.getSelectedRow();
                    int colToDelete = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                    int i = PuntoVenditaBusiness.getInstance().deleteShop(idToDelete);
                    if (i==1){
                        esit = "Punto Vendita eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione del punto vendita";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    esit = "Devi selezionare un elemento per eliminarlo o modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
