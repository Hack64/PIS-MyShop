package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ItemOperationDialog extends OperationDialog{

    public ItemOperationDialog(){
        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblDescrizione = new JLabel("Descrizione: ");
        JLabel lblPrezzo = new JLabel("Prezzo: ");
        JLabel lblImgChooser = new JLabel("Immagine: ");
        JLabel lblCategorie = new JLabel("Categorie: ");

        JTextArea txtDescrizione = new JTextArea(10, 20);
        txtDescrizione.setWrapStyleWord(true);
        txtDescrizione.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtDescrizione);
        scrollPane.setBorder(new LineBorder(Color.DARK_GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTextField txtNome = new JTextField(20);
        JTextField txtPrezzo = new JTextField(20);

        JButton btnImgChooser = new JButton("Seleziona...");
        btnImgChooser.setActionCommand("btnImg");
        JButton btnCategorie = new JButton("Scegli...");
        btnCategorie.setActionCommand("btnCategories");
        JButton btnAnnulla = new JButton("Annulla");

        labels.add(lblNome);
        labels.add(lblCategorie);
        labels.add(lblDescrizione);
        labels.add(lblImgChooser);
        labels.add(lblPrezzo);

        buttons.add(btnImgChooser);
        buttons.add(btnCategorie);
        buttons.add(btnAnnulla);

        fields.add(txtNome);
        fields.add(txtPrezzo);

        jTextArea = txtDescrizione;
        this.scrollPane = scrollPane;

    }
}
