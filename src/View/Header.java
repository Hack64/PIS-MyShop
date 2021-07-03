package View;

import Business.SessionManager;
import Model.Utente;
import View.Listener.LoginButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Header extends JPanel {

    JPanel loggedIn = new JPanel();
    JPanel loggedOut = new JPanel();
    JLabel welcome;

    public Header(LoginButtonListener list) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        Border loweredetched;
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Registrati");
        btnLogin.setActionCommand("btnLoginForm");
        btnRegister.setActionCommand("btnRegisterForm");
        btnLogin.addActionListener(list);
        btnRegister.addActionListener(list);

        loggedOut.add(btnLogin);
        loggedOut.add(btnRegister);

        welcome = new JLabel("Benvenuto ");
        JButton btnLogout = new JButton("Logout");
        btnLogout.setActionCommand("btnLogout");
        btnLogout.addActionListener(list);
        loggedIn.add(welcome);
        loggedIn.add(btnLogout);

        add(loggedIn);
        add(loggedOut);

        setLoggedOutStatus();
        this.setBorder(loweredetched);
    }

    public void setLoggedInStatus() {
        loggedOut.setVisible(false);
        loggedIn.setVisible(true);
    }
    public void setLoggedOutStatus() {
        loggedIn.setVisible(false);
        loggedOut.setVisible(true);
    }

    public void refresh() {
        // 1. prendere l'utente loggato u
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        // 2. se u è null -> chiama setLoggedOutStatus()
        if(u==null) setLoggedOutStatus();

            // 3. altrimenti setLoggedInStatus();
        else {
            welcome.setText( u.getNome() + " " + u.getCognome() + " ");
            setLoggedInStatus();
        }
    }

}
