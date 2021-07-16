package View.Listener;

import Business.ProdottoBusiness;
import Model.Responses.ProdottoResponse;
import View.AppFrame;
import View.Dialog.CustomOperationDialogView;
import View.Panel.MainCatalogPanel;
import View.Panel.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogPanelListener  implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_ADD_PRODUCT = "btnAdd";
    public final static String BTN_ADD_COMP_PRODUCT = "btnAddComp";
    public final static String BTN_EDIT_PRODUCT = "btnEdit";
    public final static String BTN_EDIT_COMP_PRODUCT = "btnEditComp";
    public final static String BTN_DELETE_PRODUCT = "btnDelete";
    public final static String BTN_DELETE_COMP_PRODUCT = "btnDeleteComp";
    public final static String BTN_MANAGE_CATEGORIES = "btnCategories";

    public CatalogPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_PRODUCT:
                System.out.println("Aggiungi prodotto");
                new CustomOperationDialogView(appFrame, true, false);
                break;
            case BTN_EDIT_PRODUCT:
                if (table.getSelectedRowCount()==1) {
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    ProdottoResponse pr = ProdottoBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new CustomOperationDialogView(appFrame, pr.getProdotto(), true, false);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    int rowToDelete = table.getSelectedRow();
                    int colToDelete = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                    int i = ProdottoBusiness.getInstance().deleteByID(idToDelete);
                    if (i==1){
                        esit = "Prodotto eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_COMP_PRODUCT:
                System.out.println("Aggiungi prodotto composito");
                new CustomOperationDialogView(appFrame, true, true);
                break;
            case BTN_EDIT_COMP_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    System.out.println("Modifica prodotto composito");
                    ProdottoResponse prc = ProdottoBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
                    new CustomOperationDialogView(appFrame, prc.getProdotto(),true, true);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_COMP_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    int idToDeleteComp = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                    int i_comp = ProdottoBusiness.getInstance().deleteCompositeByID(idToDeleteComp);
                    if (i_comp==1){
                        esit = "Prodotto eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_MANAGE_CATEGORIES:
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                    break;
        }
    }
}
