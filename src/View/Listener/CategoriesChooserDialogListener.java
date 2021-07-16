package View.Listener;

import View.AppFrame;
import View.Dialog.CategoriesChooserDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesChooserDialogListener implements ActionListener {
    private AppFrame appFrame;
    private CategoriesChooserDialog categoriesChooserDialog;

    public final static String BTN_ADD_CATEGORIES = "btnOk";

    public CategoriesChooserDialogListener(AppFrame appFrame, CategoriesChooserDialog categoriesChooserDialog){
        this.appFrame = appFrame;
        this.categoriesChooserDialog = categoriesChooserDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_CATEGORIES:
                categoriesChooserDialog.setVisible(false);
                break;
        }
    }
}
