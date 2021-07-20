package View.Panel;

import Business.MagazzinoBusiness;
import Business.ProdottiMagazzinoBusiness;
import Business.SessionManager;
import Model.*;
import View.AppFrame;
import View.Listener.ProductPanelListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProductPanel extends JPanel {

    public ProductPanel(AppFrame appFrame, Icon img, IProdotto prodotto, Servizio servizio){
        setLayout(new BorderLayout());

        JLabel lblNomeProdotto = null;
        JLabel lblCostoProdotto = null;
        JLabel lblMediaValutazioni = null;
        JLabel lblCategorieProdotti = null;
        JLabel lblQuantitaMagazzino = null;

        JButton btnAggiungi = null;
        JButton btnCommenti = null;
        JButton btnFeedback = null;

        JTextArea txtAreaDescrizione = null;

        JPanel imageSide = new JPanel(new GridLayout(3,1));
        JLabel label = new JLabel(img);
        //imageSide.setBorder(new EtchedBorder());
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
        ProductPanelListener productPanelListener;
        if (servizio == null){
            PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");

            Disponibilita d = ProdottiMagazzinoBusiness.getInstance().findByProductAndWarehouse(prodotto.getIdProdotto(), MagazzinoBusiness.getInstance().findWarehouseByShopID(p.getIdPuntoVendita()).getIdMagazzino());
            productPanelListener = new ProductPanelListener(appFrame, prodotto.getIdProdotto());
            ArrayList<String> categorie = new ArrayList<>();
            for (ICategoria c1:prodotto.getCategorie()){
                categorie.add(c1.getNome());
            }
            lblNomeProdotto = new JLabel(prodotto.getNome());
            lblCostoProdotto = new JLabel("Costo:      €" + prodotto.getCosto());
            lblMediaValutazioni = new JLabel("Media Valutazioni: " + prodotto.getMediaValutazione() + " | Commenti: " + prodotto.getNumeroCommenti());
            lblCategorieProdotti = new JLabel("Categorie: " + categorie);
            lblQuantitaMagazzino = new JLabel("Disponibili: " + d.getQta());

            btnCommenti = new JButton("Visualizza commenti");
            btnAggiungi = new JButton("Aggiungi a una lista");
            btnFeedback = new JButton("Lascia Feedback");

            btnAggiungi.setActionCommand("btnAddProduct");
            btnAggiungi.addActionListener(productPanelListener);
            btnCommenti.setActionCommand("btnShowProductFeedbacks");
            btnCommenti.addActionListener(productPanelListener);
            btnFeedback.setActionCommand("btnAddProductFeedback");
            btnFeedback.addActionListener(productPanelListener);

            lblQuantitaMagazzino.setFont(new Font("Noto Sans", Font.PLAIN, 18));

            //ProductDescription
            txtAreaDescrizione = new JTextArea(prodotto.getDescrizione(), 7, 1);
        }else if(prodotto == null){
            productPanelListener = new ProductPanelListener(appFrame, servizio.getIdServizio());
            ArrayList<String> categorie = new ArrayList<>();
            for (ICategoria c1:servizio.getCategorie()){
                categorie.add(c1.getNome());
            }
            lblNomeProdotto = new JLabel(servizio.getNome());
            lblCostoProdotto = new JLabel("Costo:      €" + servizio.getCosto());
            lblMediaValutazioni = new JLabel("Media Valutazioni: " + servizio.getMediaValutazione() + " | Commenti: " + servizio.getNumeroCommenti());
            lblCategorieProdotti = new JLabel("Categorie: " + categorie);

            btnCommenti = new JButton("Visualizza commenti");
            btnAggiungi = new JButton("Aggiungi a una lista");
            btnFeedback = new JButton("Lascia Feedback");

            btnAggiungi.setActionCommand("btnAddService");
            btnAggiungi.addActionListener(productPanelListener);
            btnCommenti.setActionCommand("btnShowServiceFeedbacks");
            btnCommenti.addActionListener(productPanelListener);
            btnFeedback.setActionCommand("btnAddServiceFeedback");
            btnFeedback.addActionListener(productPanelListener);

            //ProductDescription
            txtAreaDescrizione = new JTextArea(servizio.getDescrizione(), 7, 1);
        }

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
        lblCategorieProdotti.setFont(new Font("Noto Sans", Font.PLAIN, 18));
        txtAreaDescrizione.setFont(new Font("Noto Sans", Font.PLAIN, 18));

        productDetailsPanel.add(lblNomeProdotto);
        productDetailsPanel.add(lblCostoProdotto);
        productDetailsPanel.add(lblMediaValutazioni);
        productDetailsPanel.add(lblCategorieProdotti);
        if (prodotto != null){
            productDetailsPanel.add(lblQuantitaMagazzino);
        }

        c.gridx=0;
        c.gridy=0;
        productButtonsPanel.add(btnAggiungi,c);
        c.gridx=1;
        productButtonsPanel.add(btnCommenti,c);
        c.gridx=2;
        productButtonsPanel.add(btnFeedback, c);

        productDescriptionPanel.add(txtAreaDescrizione);

        add(imageSide, BorderLayout.WEST);
        productInfoPanel.add(productDetailsPanel, BorderLayout.NORTH);
        productInfoPanel.add(productDescriptionPanel, BorderLayout.SOUTH);
        productInfoPanel.add(productButtonsPanel, BorderLayout.CENTER);
        add(productInfoPanel);
        setVisible(true);
    }
}
