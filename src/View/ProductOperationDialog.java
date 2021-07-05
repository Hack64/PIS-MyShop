package View;

import Business.ProduttoreBusiness;
import Model.IProdotto;
import View.Listener.ProductOperationListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductOperationDialog extends JDialog {

    ArrayList<String> producers;

    JPanel form;

    JLabel lblNome;
    JLabel lblDescrizione;
    JLabel lblPrezzo;
    JLabel lblProduttori;
    JLabel lblImgChooser;

    JTextField txtNome;
    JTextArea txtDescrizione;
    JTextField txtPrezzo;

    JComboBox boxProduttori;

    JButton btnImgChooser;
    JButton btnOk;
    JButton btnAnnulla;

    boolean editMode;
    IProdotto prodotto;

    public ProductOperationDialog(AppFrame appFrame, boolean editMode, IProdotto prodotto) {
        super(appFrame, "Aggiungi un prodotto");
        this.editMode = editMode;
        this.prodotto = prodotto;

        setLayout(new FlowLayout());
        setSize(475,425);

        form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,2,5,2);
        form.setBorder(new EmptyBorder(20, 10, 10, 10));

        producers = ProduttoreBusiness.getInstance().findAllProducersNames();
        boxProduttori = new JComboBox(producers.toArray());

        lblNome = new JLabel("Nome: ");
        lblDescrizione = new JLabel("Descrizione: ");
        lblProduttori = new JLabel("Produttore: ");
        lblPrezzo = new JLabel("Prezzo:");
        lblImgChooser = new JLabel("Immagine: ");

        txtNome = new JTextField(20);
        txtPrezzo = new JTextField(20);
        txtDescrizione = new JTextArea(10,20);
        txtDescrizione.setBorder(new LineBorder(Color.DARK_GRAY));

        if (editMode){
            setFields();
        }

        if (editMode){
            btnOk = new JButton("Aggiorna");
            btnOk.setActionCommand("btnEdit");
        } else {
            btnOk = new JButton("Aggiungi");
            btnOk.setActionCommand("btnAdd");
        }

        btnAnnulla = new JButton("Annulla");
        btnImgChooser = new JButton("Seleziona...");

        ProductOperationListener productOperationListener = new ProductOperationListener(appFrame, this);
        btnImgChooser.setActionCommand("btnImg");

        btnImgChooser.addActionListener(productOperationListener);
        btnOk.addActionListener(productOperationListener);

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        c.gridy=0;
        c.gridx=0;
        form.add(lblNome, c);
        c.gridx=1;
        form.add(txtNome, c);
        c.gridy=1;
        c.gridx=0;
        form.add(lblPrezzo, c);
        c.gridx=1;
        form.add(txtPrezzo, c);
        c.gridx=0;
        c.gridy=2;
        form.add(lblProduttori, c);
        c.gridx=1;
        form.add(boxProduttori, c);
        c.gridy=3;
        c.gridx=0;
        form.add(lblDescrizione, c);
        c.gridx=1;
        form.add(txtDescrizione, c);
        c.gridy=4;
        c.gridx=0;
        form.add(lblImgChooser, c);
        c.gridx=1;
        form.add(btnImgChooser, c);
        c.gridy=5;
        c.gridx=0;
        c.insets =  new Insets(30,0,5,0);
        form.add(btnOk, c);
        c.gridx=1;
        form.add(btnAnnulla, c);

        add(form);

        setResizable(false);
        setVisible(true);
    }


    public String getTxtNome() {
        return txtNome.getText();
    }

    public String getTxtDescrizione() {
        return txtDescrizione.getText();
    }

    public String getTxtPrezzo() {
        return txtPrezzo.getText();
    }

    public Object getProduttore() {
        return boxProduttori.getSelectedItem();
    }

    public void setFields(){
        if (editMode){
            txtNome.setText(prodotto.getNome());
            txtDescrizione.setText(prodotto.getDescrizione());
            txtPrezzo.setText(Float.toString(prodotto.getCosto()));
            boxProduttori.setSelectedItem(prodotto.getProduttore().getNome());
        }
    }

    public int getID(){
        if (editMode){
            return prodotto.getIdProdotto();
        }
        return -1;
    }
}
