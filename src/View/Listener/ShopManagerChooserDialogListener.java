package View.Listener;

import View.AppFrame;
import View.ShopManagerChooserDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopManagerChooserDialogListener implements ActionListener {
    private AppFrame appFrame;
    private ShopManagerChooserDialog shopManagerChooserDialog;

    public final static String BTN_ADD_MANAGER = "btnOk";

    public ShopManagerChooserDialogListener(AppFrame appFrame, ShopManagerChooserDialog shopManagerChooserDialog){
        this.appFrame = appFrame;
        this.shopManagerChooserDialog = shopManagerChooserDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_MANAGER:
                shopManagerChooserDialog.setVisible(false);
                break;
        }
    }
}
