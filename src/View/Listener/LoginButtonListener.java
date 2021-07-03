package View.Listener;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.LoginResponse;
import Model.Utente;
import View.AppFrame;
import View.LoginDialog;
import View.WelcomePanel;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonListener implements ActionListener {

    AppFrame appFrame;
    LoginDialog loginDialog;

    public final static String BTN_LOGIN_FORM = "btnLoginForm";
    public final static String BTN_LOGOUT = "btnLogout";

    public LoginButtonListener(AppFrame appFrame){
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (BTN_LOGIN_FORM.equals(cmd)){
            loginDialog = new LoginDialog(appFrame);
        }
        if(BTN_LOGOUT.equals(cmd)) {
            // reset della view mostrando interfaccia guest
            SessionManager.getInstance().getSession().remove("loggedUser");
            appFrame.getHeader().refresh();
            appFrame.getSideMenu().refresh();
        }
    }
}
