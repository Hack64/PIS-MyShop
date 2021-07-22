package View.Dialog;

import Business.ComunicazioneEmail;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import Utils.EmailSenderAPI;
import View.AppFrame;
import View.Panel.UserProfilePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PasswordEditDialog extends JDialog {

    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtNewPassword2;

    public PasswordEditDialog(AppFrame appFrame){
        super(appFrame, "Modifica Password");
        setLayout(new BorderLayout());

        setSize(500,300);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JPanel tasti = new JPanel(new FlowLayout());

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        JLabel lblOldPassword = new JLabel("Vecchia password: ");
        JLabel lblNewPassword = new JLabel("Nuova password: ");
        JLabel lblNewPassword2 = new JLabel("Ripeti nuova password: ");

        txtOldPassword = new JPasswordField(20);
        txtNewPassword = new JPasswordField(20);
        txtNewPassword2 = new JPasswordField(20);

        JButton btnOk = new JButton("Ok");
        JButton btnAnnulla = new JButton("Annulla");

        btnAnnulla.addActionListener(e->dispose());
        btnOk.addActionListener(e -> {
            if (UtenteBusiness.getInstance().checkCredentials(u.getEmail(), txtOldPassword.getPassword())){
                if (Arrays.equals(txtNewPassword.getPassword(), txtNewPassword2.getPassword())){
                    int st = UtenteBusiness.getInstance().updateUserCredentials(u.getEmail(), txtNewPassword.getText());
                    if (st == 1){
                        ComunicazioneEmail email = new ComunicazioneEmail(u.getEmail(), "Modifica password", "Attenzione, la tua password è stata modificata con successo. \nSe non hai modificato la password, contatta immediatamente il manager di riferimento.", new EmailSenderAPI());
                        email.invia();
                        JOptionPane.showMessageDialog(appFrame, "Password Modificata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        appFrame.setCurrentMainPanel(new UserProfilePanel(appFrame));
                    } else {
                        ComunicazioneEmail email = new ComunicazioneEmail(u.getEmail(), "Tentativo di modifica password", "Attenzione, abbiamo rilevato un tentativo di modifica password. \nSe non hai modificato la password, contatta immediatamente il manager di riferimento.", new EmailSenderAPI());
                        email.invia();
                        JOptionPane.showMessageDialog(appFrame, "Errore durante la modifica della password!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Le nuove password non corrispondono!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(appFrame, "La vecchia password non è corretta!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        c.gridy=0;
        c.gridx=0;
        form.add(lblOldPassword, c);
        c.gridx=1;
        form.add(txtOldPassword, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblNewPassword, c);
        c.gridx=1;
        form.add(txtNewPassword, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblNewPassword2, c);
        c.gridx=1;
        form.add(txtNewPassword2, c);

        tasti.add(btnOk);
        tasti.add(btnAnnulla);

        add(form, BorderLayout.CENTER);
        add(tasti, BorderLayout.SOUTH);

        setVisible(true);
    }

}
