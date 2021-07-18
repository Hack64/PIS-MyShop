package View.Listener;

import Business.ListaBusiness;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.*;
import Model.Responses.UtenteResponse;
import View.AppFrame;
import View.Panel.BrowsePanel;
import View.Dialog.LoginDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class LoginListener implements ActionListener {

    private AppFrame appFrame;
    private LoginDialog loginDialog;
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
            PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
            UtenteResponse res = UtenteBusiness.getInstance().login(loginDialog.getUsername(), loginDialog.getPassword(), p);

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

                ArrayList<Integer> idProdottiPagati = UtenteBusiness.getInstance().getPaidProducts(u);
                ArrayList<Integer> idServiziPagati = UtenteBusiness.getInstance().getPaidServices(u);

                SessionManager.getInstance().getSession().put("loggedUser", u);
                SessionManager.getInstance().getSession().put("paidProducts", idProdottiPagati);
                SessionManager.getInstance().getSession().put("paidServices", idServiziPagati);

                loginDialog.setVisible(false);
                appFrame.setCurrentMainPanel(new BrowsePanel(appFrame));
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
            }
        }
    }
}
