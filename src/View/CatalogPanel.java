package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.Listener.CatalogPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogPanel extends JPanel {

    public CatalogPanel(AppFrame appFrame){
        setLayout(new BorderLayout());

        ArrayList<IProdotto> prodottiCatalogo = ProdottoBusiness.getInstance().findAllProducts();

        JTable tabellaListe = new JTable(new CatalogoTableModel(prodottiCatalogo));

        JScrollPane scrollPane = new JScrollPane(tabellaListe);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo prodotto");
        JButton btnEdit = new JButton("Modifica Prodotto");
        JButton btnDelete = new JButton("Elimina Prodotto");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");

        CatalogPanelListener catalogPanelListener = new CatalogPanelListener(appFrame);
        btnAdd.addActionListener(catalogPanelListener);
        btnEdit.addActionListener(catalogPanelListener);
        btnDelete.addActionListener(catalogPanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
