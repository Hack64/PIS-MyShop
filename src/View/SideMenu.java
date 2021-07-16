package View;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.Decorator.*;
import View.Decorator.Menu;
import View.Listener.SideMenuListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SideMenu extends JPanel {

    private SideMenuListener listener;
    private AppFrame appFrame;

    public SideMenu(SideMenuListener listener, AppFrame appFrame) { /* AppFrame appFrame*/
        this.appFrame = appFrame;
        setLayout(new GridLayout(20,1));
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Menu");
        title.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(title);

        this.listener = listener;

        View.Decorator.Menu menu = new GuestMenu();
        for(JButton btn:menu.getPulsanti()) {
            btn.addActionListener(listener);
            add(btn);
        }

        this.setVisible(false);
    }

    public void refresh() {

        if (!this.isVisible()){
            this.setVisible(true);
        }
        removeAll();
        Menu menu = new GuestMenu();
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if(u != null) {
            menu = new ClienteMenuDecorator(menu);

            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP, appFrame.getPuntoVendita())) //manager
                menu = new ManagerMenuDecorator(menu);
            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.ADMIN_SYSTEM, appFrame.getPuntoVendita())) //amministratore
                menu = new AmministratoreMenuDecorator(menu);
        }

        for(JButton btn: menu.getPulsanti()) {
            btn.addActionListener(listener);
            add(btn);
        }

        invalidate();
        validate();
        repaint();

    }
}
