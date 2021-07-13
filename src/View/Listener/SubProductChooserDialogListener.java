package View.Listener;

import View.AppFrame;
import View.SubProductChooserDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubProductChooserDialogListener implements ActionListener {
    AppFrame appFrame;
    SubProductChooserDialog subProductChooserDialog;

    public final static String BTN_ADD_CATEGORIES = "btnOk";

    public SubProductChooserDialogListener(AppFrame appFrame, SubProductChooserDialog subProductChooserDialog){
        this.appFrame = appFrame;
        this.subProductChooserDialog = subProductChooserDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_CATEGORIES:
                subProductChooserDialog.setVisible(false);
                break;
        }
    }
}
