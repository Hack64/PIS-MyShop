package View.Listener;

import Business.MagazzinoBusiness;
import Business.ProdottiMagazzinoBusiness;
import Business.ProdottoBusiness;
import Model.Disponibilita;
import Model.Responses.ProdottoResponse;
import View.AppFrame;
import View.QuantityEditorDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehousePanelListener implements ActionListener {
    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_EDIT_QUANTITY = "btnEdit";

    public WarehousePanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        if (BTN_EDIT_QUANTITY.equals(cmd)) {
            System.out.println("Qui");
            if (table.getSelectedRowCount() == 1) {
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                ProdottoResponse pr = ProdottoBusiness.getInstance().findByName(table.getModel().getValueAt(rowToEdit, colToEdit).toString());
                int idMagazzino = MagazzinoBusiness.getInstance().findWarehouseByShopID(appFrame.getPuntoVendita().getIdPuntoVendita()).getIdMagazzino();
                Disponibilita d = ProdottiMagazzinoBusiness.getInstance().findByProductAndWarehouse(pr.getProdotto().getIdProdotto(), idMagazzino);
                new QuantityEditorDialog(appFrame, d);
            } else {
                esit = "Devi selezionare un elemento per modificarlo";
                JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
