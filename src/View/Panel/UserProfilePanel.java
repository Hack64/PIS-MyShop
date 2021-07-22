package View.Panel;

import Business.SessionManager;
import Model.Utente;
import View.AppFrame;
import View.Listener.LoginButtonListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class UserProfilePanel extends JPanel {


    public UserProfilePanel(AppFrame appFrame) {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");

        JLabel lblEmail = new JLabel("Email: ");
        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblCognome = new JLabel("Cognome: ");
        JLabel lblResidenza = new JLabel("Residenza: ");
        JLabel lblTelefono = new JLabel("Telefono: ");
        JLabel lblProfessione = new JLabel("Professione: ");
        JLabel lblDDN = new JLabel("Data di nascita: ");
        JLabel lblPassword = new JLabel("Password: ");

        JTextField txtEmail = new JTextField(u.getEmail(), 20);
        JTextField txtNome = new JTextField(u.getNome(),20);
        JTextField txtCognome = new JTextField(u.getCognome(), 20);
        JTextField txtResidenza = new JTextField(u.getResidenza(), 20);
        JTextField txtTelefono = new JTextField(u.getTelefono(),20);
        JTextField txtProfessione = new JTextField(u.getProfessione(), 20);
        JTextField txtDDN = new JTextField(u.getEta().toString(), 20);
        JTextField txtPassword = new JPasswordField(u.getPasswordHash(),20);

        txtEmail.setEditable(false);
        txtNome.setEditable(false);
        txtCognome.setEditable(false);
        txtResidenza.setEditable(false);
        txtTelefono.setEditable(false);
        txtProfessione.setEditable(false);
        txtDDN.setEditable(false);
        txtPassword.setEditable(false);

        JButton btnEditPassword = new JButton("Modifica password");
        btnEditPassword.setActionCommand("btnEditPassword");
        btnEditPassword.addActionListener(new LoginButtonListener(appFrame));

        c.gridy=0;
        c.gridx=0;
        form.add(lblNome, c);
        c.gridx=1;
        form.add(txtNome, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblCognome, c);
        c.gridx=1;
        form.add(txtCognome, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblResidenza, c);
        c.gridx=1;
        form.add(txtResidenza, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblProfessione, c);
        c.gridx=1;
        form.add(txtProfessione, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblTelefono, c);
        c.gridx=1;
        form.add(txtTelefono, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblDDN, c);
        c.gridx=1;
        form.add(txtDDN, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblEmail, c);
        c.gridx=1;
        form.add(txtEmail, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblPassword, c);
        c.gridx=1;
        form.add(txtPassword, c);
        c.gridx=2;
        form.add(btnEditPassword, c);

        form.setBorder(new TitledBorder("Info"));

        add(form, BorderLayout.CENTER);

        setVisible(true);

    }
}
