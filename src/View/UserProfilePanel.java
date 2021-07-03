package View;

import Business.SessionManager;
import Model.Utente;

import javax.swing.*;

public class UserProfilePanel extends JPanel {

    public UserProfilePanel(){
        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        add(new JLabel(u.getCognome()));

        setVisible(true);
    }
}
