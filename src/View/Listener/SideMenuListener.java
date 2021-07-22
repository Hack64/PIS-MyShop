package View.Listener;

import View.AppFrame;
import View.Panel.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuListener implements ActionListener {

    private AppFrame appFrame;
    public final static String BTN_BROWSE = "btnBrowse";
    public final static String BTN_LISTS = "btnLists";
    public final static String BTN_USER_PROFILE = "btnUserProfile";
    public final static String BTN_USERS = "btnUsers";
    public final static String BTN_WAREHOUSES = "btnWarehouses";
    public final static String BTN_SHOPS = "btnShops";
    public final static String BTN_CATALOG = "btnCatalog";
    public final static String BTN_IMPRENDITORI = "btnImprenditori";

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
                appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                break;
            case BTN_USER_PROFILE:
                System.out.println("premuto i tuoi dati");
                appFrame.setCurrentMainPanel(new UserProfilePanel(appFrame));
                break;
            case BTN_CATALOG:
                System.out.println("Premuto Gestisci Catalogo");
                appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, null));
                break;
            case BTN_SHOPS:
                System.out.println("Premuto punti vendita");
                appFrame.setCurrentMainPanel(new ShopsPanel(appFrame));
                break;
            case BTN_USERS:
                System.out.println("Premuto utenti");
                appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                break;
            case BTN_IMPRENDITORI:
                System.out.println("Premuto imprenditori");
                appFrame.setCurrentMainPanel(new ImprenditoriPanel(appFrame));
                break;
            case BTN_WAREHOUSES:
                System.out.println("Premuto magazzino");
                appFrame.setCurrentMainPanel(new WarehousesPanel(appFrame));
        }

    }
}
