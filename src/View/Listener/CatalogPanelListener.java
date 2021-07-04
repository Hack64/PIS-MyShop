package View.Listener;

import Business.ProdottoBusiness;
import View.AppFrame;
import View.CatalogPanel;
import View.CatalogoTableModel;
import View.ProductAdditionDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogPanelListener  implements ActionListener {

    AppFrame appFrame;
    JTable table;
    ProductAdditionDialog productAdditionDialog;

    public final static String BTN_ADD_PRODUCT = "btnAdd";
    public final static String BTN_EDIT_PRODUCT = "btnEdit";
    public final static String BTN_DELETE_PRODUCT = "btnDelete";

    public CatalogPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_PRODUCT:
                new ProductAdditionDialog(appFrame);
                break;
            case BTN_DELETE_PRODUCT:
                String esit;
                int row = table.getSelectedRow();
                int col = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(row, col).toString());
                int i = ProdottoBusiness.getInstance().deleteByID(idToDelete);
                if (i==1){
                    esit = "Prodotto eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }
}
