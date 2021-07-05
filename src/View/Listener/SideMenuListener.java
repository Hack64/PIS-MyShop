package View.Listener;

import View.AppFrame;
import View.BrowsePanel;
import View.CatalogPanel;
import View.ListsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuListener implements ActionListener {

    AppFrame appFrame;
    public final static String BTN_BROWSE = "btnBrowse";
    public final static String BTN_LISTS = "btnLists";
    public final static String BTN_USERS = "btnUsers";
    public final static String BTN_WAREHOUSES = "btnWarehouses";
    public final static String BTN_SHOPS = "btnShops";
    public final static String BTN_CATALOG = "btnCatalog";

    public SideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_BROWSE:
                System.out.println("premuto browse");
                appFrame.setCurrentMainPanel(new BrowsePanel(appFrame));
                break;
            case BTN_LISTS:
                System.out.println("premuto lists");
                appFrame.setCurrentMainPanel(new ListsPanel(appFrame));
                break;
            case BTN_CATALOG:
                System.out.println("Premuto Gestisci Catalogo");
                appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                break;
        }

    }
}
