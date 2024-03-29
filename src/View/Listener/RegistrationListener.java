package View.Listener;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.PuntoVendita;
import Model.Responses.UtenteResponse;
import Model.Utente;
import View.AppFrame;
import View.Dialog.RegistrationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistrationListener implements ActionListener {

    private AppFrame appFrame;
    private RegistrationDialog registrationDialog;
    public final static String BTN_REGISTER = "btnRegister";
    public final static String BTN_REGISTER_MANAGER = "btnRegisterManager";

    public RegistrationListener(AppFrame appFrame, RegistrationDialog dialog){
        this.appFrame = appFrame;
        this.registrationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
        if(BTN_REGISTER.equals(cmd)) {
            // chiamare la classe di business per fare registrazione
            UtenteResponse res = UtenteBusiness.getInstance().registration(registrationDialog.getNome(), registrationDialog.getCognome(), registrationDialog.getUsername(), registrationDialog.getPassword(), registrationDialog.getPassword2(), registrationDialog.getResidenza(), registrationDialog.getTelefono(), registrationDialog.getProfessione(), registrationDialog.getEta(), Utente.Ruoli.ute, p);
            registrationDialog.clearFields();
            Utente u = res.getUtente(); //potrebbe essere null in caso di login fallito

            if(u == null) {
                //registrazione fallita: avvisare l'utente
                String reason = res.getMessage(); // da mostare all'utente
                System.out.println(reason);
                //dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame,
                        reason,
                        "Registration error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                //registrazione ok
                String reason = res.getMessage();
                System.out.println("Registrazione ok!");
                JOptionPane.showMessageDialog(appFrame, reason, "Registrazione avvenuta", JOptionPane.INFORMATION_MESSAGE);
                u = UtenteBusiness.getInstance().findByEmail(u.getEmail()).getUtente();

                ArrayList<Integer> idProdottiPagati = UtenteBusiness.getInstance().getPaidProducts(u);
                ArrayList<Integer> idServiziPagati = UtenteBusiness.getInstance().getPaidServices(u);

                SessionManager.getInstance().getSession().put("loggedUser", u);
                SessionManager.getInstance().getSession().put("paidProducts", idProdottiPagati);
                SessionManager.getInstance().getSession().put("paidServices", idServiziPagati);

                registrationDialog.setVisible(false);
                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
            }
        }
        if (BTN_REGISTER_MANAGER.equals(cmd)){
            // chiamare la classe di business per fare registrazione
            UtenteResponse res = UtenteBusiness.getInstance().registration(registrationDialog.getNome(), registrationDialog.getCognome(), registrationDialog.getUsername(), registrationDialog.getPassword(), registrationDialog.getPassword2(), registrationDialog.getResidenza(), registrationDialog.getTelefono(), registrationDialog.getProfessione(), registrationDialog.getEta(), Utente.Ruoli.man, null);
            registrationDialog.clearFields();
            Utente u = res.getUtente(); //potrebbe essere null in caso di login fallito

            if(u == null) {
                //registrazione fallita: avvisare l'utente
                String reason = res.getMessage(); // da mostare all'utente
                System.out.println(reason);
                //dalle slide su java swing, prendere l'istruzione JDialog
                JOptionPane.showMessageDialog(appFrame,
                        reason,
                        "Registration error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                //registrazione ok
                String reason = res.getMessage();
                System.out.println("Registrazione ok!");
                JOptionPane.showMessageDialog(appFrame, reason, "Registrazione avvenuta", JOptionPane.INFORMATION_MESSAGE);
                registrationDialog.dispose();

                appFrame.getHeader().refresh();
                appFrame.getSideMenu().refresh();
            }
        }
    }
}
