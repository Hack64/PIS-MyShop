package View;

import Business.SessionManager;
import Model.PuntoVendita;
import Model.Utente;
import View.Listener.LoginButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Header extends JPanel {

    private JPanel loggedIn = new JPanel();
    private JPanel loggedOut = new JPanel();
    private JPanel userPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JLabel welcome;
    private JLabel lblNegozio;
    private AppFrame appFrame;


    public Header(LoginButtonListener list, AppFrame appFrame) {

        this.appFrame = appFrame;

        setLayout(new BorderLayout());
        Border loweredetched;
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        infoPanel.setLayout(new GridBagLayout());
        userPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

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

        userPanel.add(loggedIn);
        userPanel.add(loggedOut);

        add(infoPanel, BorderLayout.WEST);
        add(userPanel, BorderLayout.EAST);

        setLoggedOutStatus();
        this.setVisible(false);
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
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");

        if (!this.isVisible()){
            this.setVisible(true);
        }

        if (lblNegozio == null){
            lblNegozio = new JLabel("Sei nel punto vendita di " + p.getVia() + ", " + p.getCitta());
            infoPanel.add(lblNegozio);
        }

        // 2. se u è null -> chiama setLoggedOutStatus()
        if(u==null) setLoggedOutStatus();

            // 3. altrimenti setLoggedInStatus();
        else {
            welcome.setText( u.getNome() + " " + u.getCognome() + " ");
            setLoggedInStatus();
        }
    }

}
