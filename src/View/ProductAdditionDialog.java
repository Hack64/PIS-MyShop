package View;

import Business.ProduttoreBusiness;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductAdditionDialog extends JDialog {

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
    JButton btnAggiungi;
    JButton btnAnnulla;

    public ProductAdditionDialog(AppFrame appFrame) {
        super(appFrame, "Aggiungi un prodotto");

        setLayout(new FlowLayout());
        setSize(275,350);

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

        txtNome = new JTextField(10);
        txtPrezzo = new JTextField(10);
        txtDescrizione = new JTextArea(5,10);

        btnAggiungi = new JButton("Aggiungi");
        btnAnnulla = new JButton("Annulla");
        btnImgChooser = new JButton("Seleziona...");

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
        c.insets =  new Insets(40,0,5,0);
        form.add(btnAggiungi, c);
        c.gridx=1;
        form.add(btnAnnulla, c);

        add(form);

        setResizable(false);
        setVisible(true);
    }

}
