package View.Listener;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.LoginResponse;
import Model.Utente;
import View.AppFrame;
import View.LoginDialog;
import View.UserProfilePanel;
import View.WelcomePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    AppFrame appFrame;
    LoginDialog loginDialog;
    public final static String BTN_LOGIN = "btnLogin";


    public LoginListener(AppFrame appFrame, LoginDialog dialog){
        this.appFrame = appFrame;
        this.loginDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if(BTN_LOGIN.equals(cmd)) {
            // chiamare la classe di business per fare login

            LoginResponse res = UtenteBusiness.getInstance().login(loginDialog.getUsername(), loginDialog.getPassword());
            loginDialog.clearFields();
            Utente u = res.getUtente(); //potrebbe essere null in caso di login fallito

            if(u == null) {
                //login fallito: avvisare l'utente
                String reason = res.getMessage(); // da mostare all'utente
                System.out.println(reason);
                //dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame,
                        reason,
                        "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                String reason = res.getMessage();
                //login ok
                JOptionPane.showMessageDialog(appFrame, reason, "Login avvenuto", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Login ok!");
                SessionManager.getInstance().getSession().put("loggedUser", u);
                loginDialog.setVisible(false);
                appFrame.setCurrentMainPanel(new UserProfilePanel());
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
            }
        }
    }
}
