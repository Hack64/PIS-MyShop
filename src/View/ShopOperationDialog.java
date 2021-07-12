package View;

import View.Listener.ListDialogListener;
import View.Listener.ShopOperationDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class ShopOperationDialog extends JDialog {

    AppFrame appFrame;

    JPanel form;

    JButton btnOK;
    JButton btnAnnulla;

    JLabel lblShopVia;
    JLabel lblShopCAP;
    JLabel lblShopCitta;
    JLabel lblMagVia;
    JLabel lblMagCAP;
    JLabel lblMagCitta;
    JLabel lblMagScaffali;
    JLabel lblMagCorsie;

    JTextField txtShopVia;
    JTextField txtShopCAP;
    JTextField txtShopCitta;
    JTextField txtMagVia;
    JTextField txtMagCAP;
    JTextField txtMagCitta;
    JSpinner txtMagScaffali;
    JSpinner txtMagCorsie;

    public ShopOperationDialog(AppFrame appFrame){
        super(appFrame, "Aggiungi");
        Insets i = new Insets(5,2,5,2);
        setLayout(new FlowLayout());
        form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(20, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();

        setSize(320,380);

        lblShopVia = new JLabel("Via P.V.: ");
        lblShopCAP = new JLabel("CAP P.V.: ");
        lblShopCitta = new JLabel("Città P.V.: ");
        lblMagVia = new JLabel("Via magazzino: ");
        lblMagCAP = new JLabel("CAP magazzino: ");
        lblMagCitta = new JLabel("Città magazzino: ");
        lblMagScaffali = new JLabel("N° scaffali magazzino:");
        lblMagCorsie = new JLabel("N° corsie magazzino: ");

        btnAnnulla = new JButton("Annulla");
        btnAnnulla.addActionListener(e -> dispose());

        txtShopVia = new JTextField(10);
        txtShopCAP = new JTextField(10);
        txtShopCitta = new JTextField(10);
        txtMagVia = new JTextField(10);
        txtMagCAP = new JTextField(10);
        txtMagCitta = new JTextField(10);
        txtMagScaffali = new JSpinner();
        JComponent editor = txtMagScaffali.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setColumns(5);
        txtMagCorsie = new JSpinner();
        editor = txtMagCorsie.getEditor();
        tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setColumns(5);

        c.insets = i;
        c.gridx=0;
        c.gridy=0;

        form.add(lblShopVia, c);
        c.gridx=1;
        form.add(txtShopVia, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblShopCAP, c);
        c.gridx=1;
        form.add(txtShopCAP, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblShopCitta, c);
        c.gridx=1;
        form.add(txtShopCitta, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblMagVia, c);
        c.gridx=1;
        form.add(txtMagVia, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblMagCAP, c);
        c.gridx=1;
        form.add(txtMagCAP, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblMagCitta, c);
        c.gridx=1;
        form.add(txtMagCitta, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblMagScaffali, c);
        c.gridx=1;
        form.add(txtMagScaffali, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblMagCorsie, c);
        c.gridx=1;
        form.add(txtMagCorsie, c);
        c.gridx=0;
        c.gridy++;

        btnOK = new JButton("OK");
        btnOK.setActionCommand("btnAdd");
        btnOK.addActionListener(new ShopOperationDialogListener(appFrame, this));
        add(form);
        add(btnOK);
        add(btnAnnulla);

        setResizable(true);
        setVisible(true);
    }

    public String getTxtShopVia() {
        return txtShopVia.getText();
    }

    public String getTxtShopCAP() {
        return txtShopCAP.getText();
    }

    public String getTxtShopCitta() {
        return txtShopCitta.getText();
    }

    public String getTxtMagVia() {
        return txtMagVia.getText();
    }

    public String getTxtMagCAP() {
        return txtMagCAP.getText();
    }

    public String getTxtMagCitta() {
        return txtMagCitta.getText();
    }

    public Integer getTxtMagScaffali() {
        return (Integer)txtMagScaffali.getValue();
    }

    public Integer getTxtMagCorsie() {
        return (Integer)txtMagCorsie.getValue();
    }
}
