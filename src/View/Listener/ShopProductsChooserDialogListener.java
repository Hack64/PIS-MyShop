package View.Listener;

import View.AppFrame;
import View.ShopProductsChooserDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopProductsChooserDialogListener implements ActionListener {
    private AppFrame appFrame;
    private ShopProductsChooserDialog shopProductsChooserDialog;

    public final static String BTN_ADD_PRODUCTS = "btnOk";

    public ShopProductsChooserDialogListener(AppFrame appFrame, ShopProductsChooserDialog shopProductsChooserDialog){
        this.appFrame = appFrame;
        this.shopProductsChooserDialog = shopProductsChooserDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case BTN_ADD_PRODUCTS:
                shopProductsChooserDialog.setVisible(false);
                break;
        }
    }
}
