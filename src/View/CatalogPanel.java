package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.Listener.CatalogPanelListener;
import View.TableModels.CatalogoTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogPanel extends JPanel {

    private AppFrame appFrame;

    public CatalogPanel(AppFrame appFrame, boolean compositeMode){

        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        ArrayList<IProdotto> prodottiCatalogo;

        if (!compositeMode){
            prodottiCatalogo = ProdottoBusiness.getInstance().findAllNonCompositeProducts();
        } else {
            prodottiCatalogo = ProdottoBusiness.getInstance().findAllCompositeProducts();
        }

        JTable tabellaProdotti = new JTable(new CatalogoTableModel(prodottiCatalogo));

        JScrollPane scrollPane = new JScrollPane(tabellaProdotti);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo prodotto");
        JButton btnEdit = new JButton("Modifica Prodotto");
        JButton btnDelete = new JButton("Elimina Prodotto");
        JButton btnCategories = new JButton("Gestisci Categorie");

        if (compositeMode){
            btnAdd.setActionCommand("btnAddComp");
            btnEdit.setActionCommand("btnEditComp");
            btnDelete.setActionCommand("btnDeleteComp");
        } else {
            btnAdd.setActionCommand("btnAdd");
            btnEdit.setActionCommand("btnEdit");
            btnDelete.setActionCommand("btnDelete");
        }

        btnCategories.setActionCommand("btnCategories");

        CatalogPanelListener catalogPanelListener = new CatalogPanelListener(appFrame, tabellaProdotti);
        btnAdd.addActionListener(catalogPanelListener);
        btnEdit.addActionListener(catalogPanelListener);
        btnDelete.addActionListener(catalogPanelListener);
        btnCategories.addActionListener(catalogPanelListener);

        operazionitabella.add(btnCategories);
        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
