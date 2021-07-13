package View;

import Model.Magazzino;
import Model.PuntoVendita;
import Model.Utente;
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
    JButton btnProducts;
    JButton btnManager;
    JButton btnNewManager;

    JLabel lblShopVia;
    JLabel lblShopCAP;
    JLabel lblShopCitta;
    JLabel lblMagVia;
    JLabel lblMagCAP;
    JLabel lblMagCitta;
    JLabel lblMagScaffali;
    JLabel lblMagCorsie;
    JLabel lblManager;
    JLabel lblProducts;

    JTextField txtShopVia;
    JTextField txtShopCAP;
    JTextField txtShopCitta;
    JTextField txtMagVia;
    JTextField txtMagCAP;
    JTextField txtMagCitta;
    JSpinner txtMagScaffali;
    JSpinner txtMagCorsie;

    PuntoVendita p;
    Magazzino m;

    public ShopOperationDialog(AppFrame appFrame, boolean editMode, PuntoVendita p, Magazzino m){
        super(appFrame, "Aggiungi");
        this.p=p;
        this.m=m;
        Insets i = new Insets(5,2,5,2);
        setLayout(new FlowLayout());
        form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(15, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();

        setSize(385,470);

        lblShopVia = new JLabel("Via P.V.: ");
        lblShopCAP = new JLabel("CAP P.V.: ");
        lblShopCitta = new JLabel("Città P.V.: ");
        lblMagVia = new JLabel("Via magazzino: ");
        lblMagCAP = new JLabel("CAP magazzino: ");
        lblMagCitta = new JLabel("Città magazzino: ");
        lblMagScaffali = new JLabel("N° scaffali magazzino:");
        lblMagCorsie = new JLabel("N° corsie magazzino: ");
        lblManager = new JLabel("Assegna un manager: ");
        lblProducts = new JLabel("Scegli prodotti: ");

        btnAnnulla = new JButton("Annulla");
        btnAnnulla.addActionListener(e -> dispose());

        txtShopVia = new JTextField(15);
        txtShopCAP = new JTextField(15);
        txtShopCitta = new JTextField(15);
        txtMagVia = new JTextField(15);
        txtMagCAP = new JTextField(15);
        txtMagCitta = new JTextField(15);
        txtMagScaffali = new JSpinner();
        JComponent editor = txtMagScaffali.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setColumns(5);
        txtMagCorsie = new JSpinner();
        editor = txtMagCorsie.getEditor();
        tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setColumns(5);

        ShopOperationDialogListener shopOperationDialogListener = new ShopOperationDialogListener(appFrame, this);

        btnProducts = new JButton("Scegli...");
        btnProducts.setActionCommand("btnProducts");
        btnProducts.addActionListener(shopOperationDialogListener);
        btnManager = new JButton("Assegna");
        btnManager.setActionCommand("btnManager");
        btnManager.addActionListener(shopOperationDialogListener);
        btnNewManager = new JButton("+");
        btnNewManager.setActionCommand("btnAddManager");
        btnNewManager.addActionListener(shopOperationDialogListener);

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
        form.add(lblManager, c);
        c.gridx=1;
        form.add(btnManager, c);
        c.gridx=2;
        c.insets = new Insets(0,0,0,0);
        form.add(btnNewManager, c);
        c.insets = new Insets(5,2,5,2);
        c.gridx=0;
        c.gridy++;
        form.add(lblProducts, c);
        c.gridx=1;
        form.add(btnProducts, c);

        if (editMode){
            setFields();
            btnOK = new JButton("Modifica");
            btnOK.setActionCommand("btnEdit");
        } else {
            btnOK = new JButton("OK");
            btnOK.setActionCommand("btnAdd");
        }
        btnOK.addActionListener(shopOperationDialogListener);

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


    public void setFields(){
        txtShopVia.setText(p.getVia());
        txtShopCAP.setText(p.getCap());
        txtShopCitta.setText(p.getCitta());
        txtMagVia.setText(m.getVia());
        txtMagCAP.setText(m.getCap());
        txtMagCitta.setText(m.getCitta());
        txtMagScaffali.setValue(m.getNumeroScaffali());
        txtMagCorsie.setValue(m.getNumeroCorsie());
    }

    public int getID(){
        if (p!=null){
            return p.getIdPuntoVendita();
        }
        return -1;
    }
}
