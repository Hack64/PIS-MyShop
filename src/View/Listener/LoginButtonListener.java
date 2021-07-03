package View.Listener;

import Business.SessionManager;
import View.AppFrame;
import View.LoginDialog;
import View.RegistrationDialog;
import View.WelcomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonListener implements ActionListener {

    AppFrame appFrame;
    LoginDialog loginDialog;
    RegistrationDialog registrationDialog;

    public final static String BTN_LOGIN_FORM = "btnLoginForm";
    public final static String BTN_LOGOUT = "btnLogout";
    public final static String BTN_REGISTER = "btnRegisterForm";

    public LoginButtonListener(AppFrame appFrame){
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case BTN_LOGIN_FORM:
                loginDialog = new LoginDialog(appFrame);
                break;
            case BTN_LOGOUT:
                SessionManager.getInstance().getSession().remove("loggedUser");
                appFrame.setCurrentMainPanel(new WelcomePanel());
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
                break;
            case BTN_REGISTER:
                registrationDialog = new RegistrationDialog(appFrame);
                break;
            default:
                break;
        }
    }
}
