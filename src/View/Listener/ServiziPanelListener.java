package View.Listener;

import Business.ServizioBusiness;
import Model.Responses.ServizioResponse;
import View.AppFrame;
import View.CustomOperationDialogView;
import View.MainCatalogPanel;
import View.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiziPanelListener implements ActionListener {

    AppFrame appFrame;
    JTable table;

    public final static String BTN_ADD_SERVICE = "btnAdd";
    public final static String BTN_EDIT_SERVICE = "btnEdit";
    public final static String BTN_DELETE_SERVICE = "btnDelete";
    public final static String BTN_MANAGE_CATEGORIES = "btnCategories";

    public ServiziPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_SERVICE:
                new CustomOperationDialogView(appFrame, false, false);
                break;
            case BTN_EDIT_SERVICE:
                int rowToEdit = table.getSelectedRow();
                int colToEdit = 0;
                ServizioResponse sr = ServizioBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                new CustomOperationDialogView(appFrame, sr.getServizio(), false);
                break;
            case BTN_DELETE_SERVICE:
                String esit;
                int rowToDelete = table.getSelectedRow();
                int colToDelete = 0;
                int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                int i = ServizioBusiness.getInstance().deleteByID(idToDelete);
                if (i==1){
                    esit = "Servizio eliminato con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                } else{
                    esit = "Errore durante l'eliminazione";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_MANAGE_CATEGORIES:
                appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                break;
        }
    }
}