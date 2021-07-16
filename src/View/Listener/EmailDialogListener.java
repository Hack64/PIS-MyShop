package View.Listener;

import Business.ComunicazioneEmail;
import Business.EmailSenderAPI;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.PuntoVendita;
import Model.Utente;
import View.AppFrame;
import View.Dialog.EmailDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class EmailDialogListener implements ActionListener {

    private AppFrame appFrame;
    private EmailDialog dialog;

    private final static String BTN_SEND = "btnOK";
    private final static String BTN_SEND_ALL = "btnOKAll";

    public EmailDialogListener(AppFrame appFrame, EmailDialog dialog){
        this.appFrame = appFrame;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
        if (BTN_SEND.equals(cmd)){
            ComunicazioneEmail email = new ComunicazioneEmail(dialog.getEmail(), dialog.getOggetto(), dialog.getTesto(), new EmailSenderAPI());
            int status = email.invia();
            if (status == 0){
                JOptionPane.showMessageDialog(appFrame, "Email inviata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(appFrame, "Errore durante l'invio dell'email!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            dialog.dispose();
        } else if (BTN_SEND_ALL.equals(cmd)){
            int status=0;
            for(Map.Entry<Utente, String> entry:UtenteBusiness.getInstance().findAllUsersByShop(p).entrySet()){
                if (entry.getValue().equals("0")){
                    ComunicazioneEmail email = new ComunicazioneEmail(entry.getKey().getEmail(), dialog.getOggetto(), dialog.getTesto(), new EmailSenderAPI());
                    status += email.invia();
                }
            }
            if (status == 0){
                JOptionPane.showMessageDialog(appFrame, "Email inviate con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(appFrame, "Errore durante l'invio dell'email!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
