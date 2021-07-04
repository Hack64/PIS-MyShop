package View;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.Listener.SideMenuListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SideMenu extends JPanel {

    private SideMenuListener listener;

    public SideMenu( SideMenuListener listener ) { /* AppFrame appFrame*/

        setLayout(new GridLayout(20,1));
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Menu");
        title.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(title);

        this.listener = listener;

        Menu menu = new GuestMenu();
        for(JButton btn:menu.getPulsanti()) {
            btn.addActionListener(listener);
            add(btn);
        }

        refresh();
    }

    public void refresh() {

        removeAll();
        Menu menu = new GuestMenu();
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        if(u != null) {
            menu = new ClienteMenuDecorator(menu);

            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.MANAGE_SHOP)) //manager
                menu = new ManagerMenuDecorator(menu);
            if(UtenteBusiness.getInstance().userCan(u, UtenteBusiness.Privilegio.ADMIN_SYSTEM)) //amministratore
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
