package View.Listener;

import Business.ListaBusiness;
import View.AppFrame;
import View.Dialog.ListOperationDialog;
import View.Panel.MainListsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDialogListener implements ActionListener {
    private AppFrame appFrame;
    private ListOperationDialog listOperationDialog;
    public final static String BTN_ADD_LIST = "btnAdd";
    public final static String BTN_EDIT_LIST = "btnEdit";


    public ListDialogListener(AppFrame appFrame, ListOperationDialog dialog){
        this.appFrame = appFrame;
        this.listOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_LIST:
                int st = ListaBusiness.getInstance().createNewList(listOperationDialog.getNomeLista(), listOperationDialog.getDataCreazione());
                if (st == 1) {
                    String esit = "Lista creata con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    listOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                } else {
                    String esit = "Errore durante la creazione della lsita!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                }
                break;
            case BTN_EDIT_LIST:
                int st_e = ListaBusiness.getInstance().updateListName(listOperationDialog.getNomeLista(), listOperationDialog.getIdLista());
                if (st_e == 1){
                    String esit = "Lista modificata con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    listOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica della lsita!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                }
                break;
        }
    }
}

