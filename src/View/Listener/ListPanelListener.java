package View.Listener;

import Business.ListaBusiness;
import Model.Lista;
import View.AppFrame;
import View.Dialog.ListOperationDialog;
import View.Panel.ListsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_ADD_LIST = "btnAdd";
    public final static String BTN_EDIT_LIST = "btnEdit";
    public final static String BTN_DELETE_LIST = "btnDelete";

    public ListPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_LIST:
                new ListOperationDialog(appFrame, null);
                break;
            case BTN_EDIT_LIST:
                if (table.getSelectedRowCount()==1){
                    Lista lista = ListaBusiness.getInstance().find((Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()))).getLista();
                    new ListOperationDialog(appFrame, lista);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_LIST:
                if (table.getSelectedRowCount()==1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(row, col).toString());
                    int i = ListaBusiness.getInstance().deleteByID(idToDelete);
                    if (i==1){
                        esit = "Lista eliminata con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new ListsPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }

                break;
        }

    }
}
