package View.Listener;

import View.AppFrame;
import View.ShopServicesChooserDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopServicesChooserDialogListener implements ActionListener {
    private AppFrame appFrame;
    private ShopServicesChooserDialog shopServicesChooserDialog;

    public final static String BTN_ADD_SERVICES = "btnOk";

    public ShopServicesChooserDialogListener(AppFrame appFrame, ShopServicesChooserDialog shopServicesChooserDialog){
        this.appFrame = appFrame;
        this.shopServicesChooserDialog = shopServicesChooserDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case BTN_ADD_SERVICES:
                shopServicesChooserDialog.setVisible(false);
                break;
        }
    }
}
