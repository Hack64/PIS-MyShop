package View.Listener;

import Business.CategoriaBusiness;
import Model.ICategoria;
import View.AppFrame;
import View.Dialog.CategoriesOperationDialog;
import View.Panel.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageCategoriesPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;
    private CategoriesOperationDialog categoriesOperationDialog;

    public final static String BTN_ADD_CATEGORIES = "btnAdd";
    public final static String BTN_EDIT_CATEGORIES = "btnEdit";
    public final static String BTN_DELETE_CATEGORIES = "btnDelete";

    public ManageCategoriesPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_CATEGORIES:
                if (table.getSelectionModel().isSelectionEmpty()){
                    new CategoriesOperationDialog(appFrame, false, false, null);
                } else {
                    ICategoria cat = CategoriaBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
                    new CategoriesOperationDialog(appFrame, false, true, cat);
                }
                break;
            case BTN_EDIT_CATEGORIES:
                if (table.getSelectedRowCount()==1){
                    ICategoria cat = CategoriaBusiness.getInstance().findByID(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
                    new CategoriesOperationDialog(appFrame, true, false, cat);
                }else {
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_CATEGORIES:
                if (table.getSelectedRowCount()==1){
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                    int i = CategoriaBusiness.getInstance().deleteByID(idToDelete);
                    if (i==1){
                        esit = "Prodotto eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }

                break;
        }
    }
}
