package View;

import View.Listener.RegistrationListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegistrationDialog extends JDialog {
    private final JTextField txtNome;
    private final JTextField txtCognome;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JPasswordField txtPassword2;
    private final JTextField txtResidenza;
    private final JTextField txtTelefono;
    private final JTextField txtProfessione;
    private final JFormattedTextField txtEta;
    protected JButton btnRegister;

    public RegistrationDialog(AppFrame appFrame){
        super(appFrame, "Registrati");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Insets i = new Insets(5,2,5,2);
        setLayout(new FlowLayout());
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(20, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();

        setSize(295,390);

        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblCognome = new JLabel("Cognome: ");
        JLabel lblUsername = new JLabel("Email: ");
        JLabel lblPassword = new JLabel("Password: ");
        JLabel lblPassword2 = new JLabel("Ripeti password: ");
        JLabel lblResidenza = new JLabel("Residenza: ");
        JLabel lblTelefono = new JLabel("Telefono: ");
        JLabel lblProfessione = new JLabel("Professione: ");
        JLabel lblEta = new JLabel("Data di nascita: ");


        btnRegister = new JButton("Registrati");

        txtNome = new JTextField(10);
        txtCognome = new JTextField(10);
        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);
        txtPassword2 = new JPasswordField(10);
        txtResidenza = new JTextField(10);
        txtTelefono = new JTextField(10);
        txtProfessione = new JTextField(10);
        txtEta = new JFormattedTextField(dateFormat);
        txtEta.setColumns(10);
        txtEta.setText("aaaa-mm-gg");

        c.insets = i;

        c.gridx = 0;
        c.gridy = 0;
        form.add(lblNome, c);
        c.gridx = 1;
        form.add(txtNome, c);
        c.gridy = 1;
        c.gridx = 0;
        form.add(lblCognome,c);
        c.gridx = 1;
        form.add(txtCognome, c);
        c.gridy = 2;
        c.gridx = 0;
        form.add(lblUsername, c);
        c.gridx = 1;
        form.add(txtUsername, c);
        c.gridy = 3;
        c.gridx = 0;
        form.add(lblPassword, c);
        c.gridx = 1;
        form.add(txtPassword, c);
        c.gridy = 4;
        c.gridx = 0;
        form.add(lblPassword2, c);
        c.gridx = 1;
        form.add(txtPassword2, c);
        c.gridy = 5;
        c.gridx = 0;
        form.add(lblResidenza, c);
        c.gridx = 1;
        form.add(txtResidenza, c);
        c.gridy = 6;
        c.gridx = 0;
        form.add(lblTelefono, c);
        c.gridx = 1;
        form.add(txtTelefono, c);
        c.gridy = 7;
        c.gridx = 0;
        form.add(lblProfessione, c);
        c.gridx = 1;
        form.add(txtProfessione, c);
        c.gridy = 8;
        c.gridx = 0;
        form.add(lblEta, c);
        c.gridx = 1;
        form.add(txtEta, c);
        add(form);

        RegistrationListener registrationListener = new RegistrationListener(appFrame, this);
        btnRegister.addActionListener(registrationListener);
        btnRegister.setActionCommand("btnRegister");
        add(btnRegister);

        setResizable(true);
        setVisible(true);
    }

    public String getNome(){
        return this.txtNome.getText();
    }

    public String getCognome(){
        return this.txtCognome.getText();
    }

    public String getResidenza(){
        return this.txtResidenza.getText();
    }

    public String getTelefono(){
        return this.txtTelefono.getText();
    }

    public String getEta(){
        return this.txtEta.getText();
    }

    public String getUsername(){
        return this.txtUsername.getText();
    }

    public String getPassword(){
        return new String(this.txtPassword.getPassword());
    }

    public String getPassword2(){
        return new String(this.txtPassword2.getPassword());
    }

    public String getProfessione(){
        return this.txtProfessione.getText();
    }

    public void clearFields() {
        txtNome.setText("");
        txtCognome.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
        txtResidenza.setText("");
        txtTelefono.setText("");
        txtProfessione.setText("");
        txtEta.setText("");
    }

}
