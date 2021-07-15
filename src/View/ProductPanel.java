package View;

import Model.ICategoria;
import Model.IProdotto;
import Model.Servizio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProductPanel extends JPanel {

    public ProductPanel(Icon img, IProdotto prodotto){
        setLayout(new BorderLayout());

        JPanel imageSide = new JPanel(new GridLayout(3,1));
        JLabel label = new JLabel(img);
        imageSide.setBorder(new EtchedBorder());
        imageSide.add(label);

        JPanel productInfoPanel = new JPanel(new BorderLayout());
        JPanel productDetailsPanel = new JPanel(new GridLayout(8,1));
        JPanel productDescriptionPanel = new JPanel(new GridLayout(1,1));
        JPanel productButtonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,10,5,10);

        productInfoPanel.setBorder(new EmptyBorder(10,10,10,10));
        /*productDetailsPanel.setBorder(new LineBorder(Color.RED));
        productButtonsPanel.setBorder(new LineBorder(Color.BLUE));
        productDescriptionPanel.setBorder(new LineBorder(Color.GREEN));*/

        //ProductDetails
        ArrayList<String> categorie = new ArrayList<>();
        for (ICategoria c1:prodotto.getCategorie()){
            categorie.add(c1.getNome());
        }
        JLabel lblNomeProdotto = new JLabel(prodotto.getNome());
        JLabel lblCostoProdotto = new JLabel("Costo:      €" + prodotto.getCosto());
        JLabel lblMediaValutazioni = new JLabel("Media Valutazioni:      " + prodotto.getMediaValutazione());
        JLabel lblCategorieProdotti = new JLabel("Categorie: " + categorie);

        JButton btnCommenti = new JButton("Visualizza commenti");
        JButton btnAggiungi = new JButton("Aggiungi a una lista");


        //ProductDescription
        JTextArea txtAreaDescrizione = new JTextArea(prodotto.getDescrizione(), 7, 1);
        txtAreaDescrizione.setLineWrap(true);
        txtAreaDescrizione.setWrapStyleWord(true);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Descrizione");
        title.setTitleJustification(TitledBorder.LEFT);
        title.setTitleFont(new Font("Noto Sans", Font.BOLD, 16));
        txtAreaDescrizione.setBorder(title);

        lblNomeProdotto.setFont(new Font("Noto Sans", Font.BOLD, 40));
        lblCostoProdotto.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        lblMediaValutazioni.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        txtAreaDescrizione.setFont(new Font("Noto Sans", Font.PLAIN, 18));

        productDetailsPanel.add(lblNomeProdotto);
        productDetailsPanel.add(lblCostoProdotto);
        productDetailsPanel.add(lblMediaValutazioni);
        productDetailsPanel.add(lblCategorieProdotti);

        c.gridx=0;
        c.gridy=0;
        productButtonsPanel.add(btnAggiungi,c);
        c.gridx=1;
        productButtonsPanel.add(btnCommenti,c);

        productDescriptionPanel.add(txtAreaDescrizione);

        add(imageSide, BorderLayout.WEST);
        productInfoPanel.add(productDetailsPanel, BorderLayout.NORTH);
        productInfoPanel.add(productDescriptionPanel, BorderLayout.SOUTH);
        productInfoPanel.add(productButtonsPanel, BorderLayout.CENTER);
        add(productInfoPanel);
        setVisible(true);
    }

    public ProductPanel(Icon img, Servizio prodotto){
        setLayout(new BorderLayout());

        JPanel imageSide = new JPanel(new GridLayout(3,1));
        JLabel label = new JLabel(img);
        imageSide.setBorder(new EtchedBorder());
        imageSide.add(label);

        JPanel productInfoPanel = new JPanel(new BorderLayout());
        JPanel productDetailsPanel = new JPanel(new GridLayout(8,1));
        JPanel productDescriptionPanel = new JPanel(new GridLayout(1,1));
        JPanel productButtonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,10,5,10);

        productInfoPanel.setBorder(new EmptyBorder(10,10,10,10));
        /*productDetailsPanel.setBorder(new LineBorder(Color.RED));
        productButtonsPanel.setBorder(new LineBorder(Color.BLUE));
        productDescriptionPanel.setBorder(new LineBorder(Color.GREEN));*/

        //ProductDetails
        ArrayList<String> categorie = new ArrayList<>();
        for (ICategoria c1:prodotto.getCategorie()){
            categorie.add(c1.getNome());
        }
        JLabel lblNomeProdotto = new JLabel(prodotto.getNome());
        JLabel lblCostoProdotto = new JLabel("Costo:      €" + prodotto.getCosto());
        JLabel lblMediaValutazioni = new JLabel("Media Valutazioni:      " + prodotto.getMediaValutazione());
        JLabel lblCategorieProdotti = new JLabel("Categorie: " + categorie);

        JButton btnCommenti = new JButton("Visualizza commenti");
        JButton btnAggiungi = new JButton("Aggiungi a una lista");


        //ProductDescription
        JTextArea txtAreaDescrizione = new JTextArea(prodotto.getDescrizione(), 7, 1);
        txtAreaDescrizione.setLineWrap(true);
        txtAreaDescrizione.setWrapStyleWord(true);
        TitledBorder title;
        title = BorderFactory.createTitledBorder("Descrizione");
        title.setTitleJustification(TitledBorder.LEFT);
        title.setTitleFont(new Font("Noto Sans", Font.BOLD, 16));
        txtAreaDescrizione.setBorder(title);

        lblNomeProdotto.setFont(new Font("Noto Sans", Font.BOLD, 40));
        lblCostoProdotto.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        lblMediaValutazioni.setFont(new Font("Noto Sans", Font.PLAIN, 20));
        txtAreaDescrizione.setFont(new Font("Noto Sans", Font.PLAIN, 18));

        productDetailsPanel.add(lblNomeProdotto);
        productDetailsPanel.add(lblCostoProdotto);
        productDetailsPanel.add(lblMediaValutazioni);
        productDetailsPanel.add(lblCategorieProdotti);

        c.gridx=0;
        c.gridy=0;
        productButtonsPanel.add(btnAggiungi,c);
        c.gridx=1;
        productButtonsPanel.add(btnCommenti,c);

        productDescriptionPanel.add(txtAreaDescrizione);

        add(imageSide, BorderLayout.WEST);
        productInfoPanel.add(productDetailsPanel, BorderLayout.NORTH);
        productInfoPanel.add(productDescriptionPanel, BorderLayout.SOUTH);
        productInfoPanel.add(productButtonsPanel, BorderLayout.CENTER);
        add(productInfoPanel);
        setVisible(true);
    }
}
