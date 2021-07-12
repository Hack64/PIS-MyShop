package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.ServizioBusiness;
import Model.Responses.ServizioResponse;
import View.*;

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

        switch (cmd){
            case BTN_ADD_SHOP:
                new ShopOperationDialog(appFrame);
                break;
            case BTN_EDIT_SHOP:
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                ServizioResponse sr = ServizioBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                new CustomOperationDialogView(appFrame, sr.getServizio(), false);
                break;
            case BTN_DELETE_SHOP:
                String esit;
                int rowToDelete = table.getSelectedRow();
                int colToDelete = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                int i = PuntoVenditaBusiness.getInstance().deleteShop(idToDelete);
                if (i==1){
                    esit = "Punto Vendita eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione del punto vendita";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
