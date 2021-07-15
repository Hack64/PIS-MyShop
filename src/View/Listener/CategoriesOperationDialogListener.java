package View.Listener;

import Business.CategoriaBusiness;
import Model.Categoria;
import View.AppFrame;
import View.CategoriesOperationDialog;
import View.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesOperationDialogListener implements ActionListener {
    private AppFrame appFrame;
    private CategoriesOperationDialog categoriesOperationDialog;

    public final static String BTN_ADD = "btnAdd";
    public final static String BTN_ADD_SUB = "btnAddSub";
    public final static String BTN_EDIT = "btnEdit";


    public CategoriesOperationDialogListener(AppFrame appFrame, CategoriesOperationDialog dialog){
        this.appFrame = appFrame;
        this.categoriesOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int st;
        switch(cmd){
            case BTN_ADD:
                st = CategoriaBusiness.getInstance().addNewCategory(categoriesOperationDialog.getTxtNome(), null);
                if (st == 1){
                    String esit = "Categoria aggiunta con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    categoriesOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta della categoria!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                }
                break;
            case BTN_ADD_SUB:
                st = CategoriaBusiness.getInstance().addNewCategory(categoriesOperationDialog.getTxtNome(), (Categoria) categoriesOperationDialog.getCategoria());
                if (st == 1){
                    String esit = "Categoria aggiunta con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    categoriesOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta della categoria!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                }
                break;
            case BTN_EDIT:
                st = CategoriaBusiness.getInstance().update(categoriesOperationDialog.getTxtNome(), categoriesOperationDialog.getCategoria().getIdCategoria());
                if (st == 1){
                    String esit = "Categoria modificata con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    categoriesOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                } else {
                    String esit = "Errore durante la modifica della categoria!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                }
                break;
        }
    }
}
