package View.Listener;

import Business.ComunicazioneEmail;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.PuntoVendita;
import Model.Responses.UtenteResponse;
import Model.Utente;
import Utils.EmailSenderAPI;
import View.AppFrame;
import View.Dialog.LoginDialog;
import View.Panel.BrowsePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class LoginListener implements ActionListener {

    private AppFrame appFrame;
    private LoginDialog loginDialog;
    public final static String BTN_LOGIN = "btnLogin";
    public final static String BTN_FORGOT_PASSWORD = "btnPassword";


    public LoginListener(AppFrame appFrame, LoginDialog dialog){
        this.appFrame = appFrame;
        this.loginDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        switch (cmd) {
            case BTN_LOGIN:
                // chiamare la classe di business per fare login
                PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
                UtenteResponse res = UtenteBusiness.getInstance().login(loginDialog.getEmail(), loginDialog.getPassword(), p);

                loginDialog.clearFields();
                Utente u = res.getUtente(); //potrebbe essere null in caso di login fallito

                if (u == null) {
                    //login fallito: avvisare l'utente
                    String reason = res.getMessage(); // da mostare all'utente
                    System.out.println(reason);
                    //dalle slide su java swing, prendere l'istruzione JDialog
                    JOptionPane.showMessageDialog(appFrame,
                            reason,
                            "Login error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
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
                break;
            case BTN_FORGOT_PASSWORD:
                System.out.println(loginDialog.getEmail());
                if (!loginDialog.getEmail().isBlank() || !loginDialog.getEmail().isEmpty() || !loginDialog.getEmail().equals("")){
                    String email = loginDialog.getEmail();
                    if (UtenteBusiness.getInstance().userExists(email)){
                        String generatedPwd = this.getSaltString();
                        System.out.println(generatedPwd);
                        ComunicazioneEmail emailPassword = new ComunicazioneEmail(email, "Recupero password", "La tua nuova password è: " + generatedPwd + "\n Si prega di cambiarla al più presto. \n Cordiali saluti", new EmailSenderAPI());
                        emailPassword.invia();
                        int st = UtenteBusiness.getInstance().updateUserCredentials(email, generatedPwd);
                        if (st == 1){
                            JOptionPane.showMessageDialog(appFrame, "Password generata con successo, controlla la tua email.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(appFrame, "Errore durante la generazione della password.", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Non esiste nessun utente con questa email", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Devi compilare il campo email per recuperare la tua password", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
}
