package View;

import View.Listener.LoginListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginDialog extends JDialog {
    private JPanel form;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginDialog(AppFrame appFrame){
        super(appFrame, "Accedi");
        Insets i = new Insets(5,2,5,2);
        setLayout(new FlowLayout());
        form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(40, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();

        setSize(275,250);

        JLabel lblUsername = new JLabel("Username: ");
        JLabel lblPassword = new JLabel("Password: ");

        JButton btnLogin = new JButton("Accedi");

        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);

        c.insets = i;

        c.gridx = 0;
        c.gridy = 0;
        form.add(lblUsername, c);
        c.gridx = 1;
        form.add(txtUsername, c);
        c.gridy = 1;
        c.gridx = 0;
        form.add(lblPassword, c);
        c.gridx = 1;
        form.add(txtPassword, c);
        c.gridy = 2;
        c.gridx = 0;
        add(form);

        LoginListener loginListener = new LoginListener(appFrame, this);
        btnLogin.addActionListener(loginListener);
        btnLogin.setActionCommand("btnLogin");
        add(btnLogin);

        setResizable(true);
        setVisible(true);
    }

    public String getUsername(){
        return this.txtUsername.getText();
    }

    public String getPassword(){
        return new String(this.txtPassword.getPassword());
    }

    public void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public void setVisibility(boolean state){
        this.setVisible(state);
    }
}
