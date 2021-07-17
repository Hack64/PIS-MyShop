package View.Listener;

import Business.ListaBusiness;
import Model.Lista;
import View.AppFrame;
import View.Dialog.ListOperationDialog;
import View.Panel.ListsPanel;
import View.Panel.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_ADD_LIST = "btnAdd";
    public final static String BTN_EDIT_LIST = "btnEdit";
    public final static String BTN_DELETE_LIST = "btnDelete";
    public final static String BTN_SET_PAID = "btnPay";

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
                        appFrame.setCurrentMainPanel(new ListsPanel(appFrame, false, null));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_SET_PAID:
                if (table.getSelectedRowCount()==1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idLista = (Integer)table.getModel().getValueAt(row, col);
                    int st = 0;
                    Lista l = ListaBusiness.getInstance().find(idLista).getLista();
                    if (l.getStato().equals(Lista.Stato.NON_PAGATA)){
                        st = ListaBusiness.getInstance().setListPaymentStatus(l, Lista.Stato.PAGATA);
                    }
                    if (st == 1){
                        JOptionPane.showMessageDialog(appFrame, "Impostato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Errore durante l'impostazione!", "Errore", JOptionPane.INFORMATION_MESSAGE);
                    }
                    appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                } else {
                    esit = "Devi selezionare un elemento per modificarne lo stato";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }
}
