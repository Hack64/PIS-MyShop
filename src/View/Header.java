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
        setLayout(new BorderLayout());
        Border loweredetched;
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Registrati");
        btnLogin.setActionCommand("btnLoginForm");
        btnRegister.setActionCommand("btnRegister");
        btnLogin.addActionListener(list);

        loggedOut.add(btnLogin);
        loggedOut.add(btnRegister);

        welcome = new JLabel("Benvenuto ");
        JButton btnLogout = new JButton("Logout");
        btnLogout.setActionCommand("btnLogout");
        btnLogout.addActionListener(list);
        loggedIn.add(welcome);
        loggedIn.add(btnLogout);

        add(loggedIn, BorderLayout.CENTER);
        add(loggedOut, BorderLayout.EAST);

        setLoggedOutStatus();
        this.setBorder(loweredetched);
    }

    public void setLoggedInStatus() {
        loggedIn.setVisible(true);
        loggedOut.setVisible(false);
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
            welcome.setText("Benvenuto, " + u.getNome());
            setLoggedInStatus();
        }
    }

}
