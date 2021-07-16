package View.Dialog;

import View.AppFrame;
import View.Listener.EmailDialogListener;

import javax.swing.*;
import java.awt.*;

public class EmailDialog extends JDialog {

    private final JTextField txtOggetto;
    private final JTextArea txtTesto;
    private final JTextField txtEmail;

    public EmailDialog(AppFrame appFrame, String email) {
        super(appFrame, "Crea nuova email");

        JPanel form = new JPanel(new GridBagLayout());
        JPanel tasti = new JPanel(new FlowLayout());

        setLayout(new BorderLayout());
        setSize(350, 420);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JLabel lblOggetto = new JLabel("Oggetto: ");
        JLabel lblTesto = new JLabel("Testo: ");
        JLabel lblEmail = new JLabel("Email: ");

        txtOggetto = new JTextField(22);
        txtEmail = new JTextField(22);
        if(email != null){
            txtEmail.setText(email);
            txtEmail.setEnabled(false);
        } else {
            txtEmail.setText("BROADCAST");
            txtEmail.setEnabled(false);
        }

        txtTesto = new JTextArea();
        txtTesto.setWrapStyleWord(true);
        txtTesto.setLineWrap(true);
        txtTesto.setColumns(20);
        txtTesto.setRows(15);
        JScrollPane scrollText = new JScrollPane(txtTesto);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton btnOK = new JButton("Invia");
        JButton btnAnnulla = new JButton("Annulla");

        btnAnnulla.addActionListener(e -> dispose());

        if (email != null) {
            btnOK.setActionCommand("btnOK");
        } else {
            btnOK.setActionCommand("btnOKAll");
        }

        btnOK.addActionListener(new EmailDialogListener(appFrame, this));

        c.gridy=0;
        c.gridx=0;
        form.add(lblEmail, c);
        c.gridx=1;
        form.add(txtEmail, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblOggetto, c);
        c.gridx=1;
        form.add(txtOggetto, c);
        c.gridy++;
        c.gridx=0;
        form.add(lblTesto, c);
        c.gridx=1;
        form.add(scrollText, c);
        c.gridy++;
        c.gridx=0;

        tasti.add(btnOK);
        tasti.add(btnAnnulla);

        add(form, BorderLayout.CENTER);
        add(tasti, BorderLayout.SOUTH);
        setVisible(true);
    }

    public String getOggetto() {
        return txtOggetto.getText();
    }

    public String getTesto() {
        return txtTesto.getText();
    }

    public String getEmail(){
        return txtEmail.getText();
    }
}
