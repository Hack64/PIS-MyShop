package View.Listener;

import Business.ListaBusiness;
import Business.ServizioBusiness;
import Model.Lista;
import Model.Responses.ServizioResponse;
import Model.Servizio;
import View.AppFrame;
import View.Dialog.CustomOperationDialogView;
import View.Panel.MainCatalogPanel;
import View.Panel.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiziPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;
    private Lista l;

    public final static String BTN_ADD_SERVICE = "btnAdd";
    public final static String BTN_EDIT_SERVICE = "btnEdit";
    public final static String BTN_DELETE_SERVICE = "btnDelete";
    public final static String BTN_DELETE_SERVICE_FROM_LIST = "btnDeleteFromList";
    public final static String BTN_MANAGE_CATEGORIES = "btnCategories";

    public ServiziPanelListener(AppFrame appFrame, JTable table, Lista l){
        this.l = l;
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_SERVICE:
                new CustomOperationDialogView(appFrame, false, false);
                break;
            case BTN_EDIT_SERVICE:
                if (table.getSelectedRowCount()==1){
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    ServizioResponse sr = ServizioBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new CustomOperationDialogView(appFrame, sr.getServizio(), false);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case BTN_DELETE_SERVICE:
                if (table.getSelectedRowCount()==1){
                    int rowToDelete = table.getSelectedRow();
                    int colToDelete = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                    int i = ServizioBusiness.getInstance().deleteByID(idToDelete);
                    if (i==1){
                        esit = "Servizio eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, null));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_SERVICE_FROM_LIST:
                if (table.getSelectedRowCount() == 1){
                    if (l != null){
                        int rowToEdit = table.getSelectedRow();
                        int col = 0;
                        Servizio s = ServizioBusiness.getInstance().find((Integer)table.getModel().getValueAt(rowToEdit, col)).getServizio();
                        int st = ListaBusiness.getInstance().deleteServiceFromList(l, s);
                        if (st == 1){
                            JOptionPane.showMessageDialog(appFrame, "Servizio eliminato dalla lista con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                            appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, ListaBusiness.getInstance().find(l.getIdLista()).getLista()));
                        } else {
                            JOptionPane.showMessageDialog(appFrame, "Errore durante la rimozione del prodotto dalla lista", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    esit = "Devi selezionare un servizio per eliminarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_MANAGE_CATEGORIES:
                appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                break;
        }
    }
}
