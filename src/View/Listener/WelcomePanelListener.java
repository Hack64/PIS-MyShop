package View.Listener;

import Business.PuntoVenditaBusiness;
import Model.PuntoVendita;
import View.AppFrame;
import View.Panel.BrowsePanel;
import View.Panel.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanelListener implements ActionListener {

    private AppFrame appFrame;
    private WelcomePanel panel;
    public final static String BTN_SET_SHOP = "btnOk";

    public WelcomePanelListener(AppFrame appFrame, WelcomePanel panel){
        this.appFrame = appFrame;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (BTN_SET_SHOP.equals(cmd)){
            PuntoVendita p = PuntoVenditaBusiness.getInstance().findShopByAddress(panel.getSelectedPuntoVendita()).getPuntoVendita();
            appFrame.setPuntoVendita(p);
            appFrame.getHeader().refresh();
            appFrame.getSideMenu().refresh();
            appFrame.setCurrentMainPanel(new BrowsePanel(appFrame));
        }
    }
}
