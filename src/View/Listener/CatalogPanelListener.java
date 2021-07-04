package View.Listener;

import View.AppFrame;
import View.ProductAdditionDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogPanelListener  implements ActionListener {

    AppFrame appFrame;
    ProductAdditionDialog productAdditionDialog;

    public final static String BTN_ADD_PRODUCT = "btnAdd";
    public final static String BTN_EDIT_PRODUCT = "btnEdit";
    public final static String BTN_DELETE_PRODUCT = "btnDelete";

    public CatalogPanelListener(AppFrame appFrame){
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_PRODUCT:
                new ProductAdditionDialog(appFrame);
                break;
        }

    }
}
