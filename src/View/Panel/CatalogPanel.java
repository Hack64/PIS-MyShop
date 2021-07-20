package View.Panel;

import Business.ProdottoBusiness;
import Model.Articolo;
import Model.IProdotto;
import Model.Lista;
import View.AppFrame;
import View.Listener.CatalogPanelListener;
import View.TableModels.CatalogoTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class CatalogPanel extends JPanel {

    private AppFrame appFrame;
    private Lista l = null;

    public CatalogPanel(AppFrame appFrame, boolean compositeMode, Lista l){
        this.l = l;
        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        ArrayList<IProdotto> prodottiCatalogo = new ArrayList<>();

        if (l != null){
            for (Map.Entry<IProdotto, Map.Entry<String, Integer>> entry:l.getProdotti().entrySet()){
                prodottiCatalogo.add(entry.getKey());
            }
        } else if (!compositeMode){
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

        if (l != null){
            btnDelete.setActionCommand("btnDeleteFromList");
        } else if (compositeMode){
            btnAdd.setActionCommand("btnAddComp");
            btnEdit.setActionCommand("btnEditComp");
            btnDelete.setActionCommand("btnDeleteComp");
        } else {
            btnAdd.setActionCommand("btnAdd");
            btnEdit.setActionCommand("btnEdit");
            btnDelete.setActionCommand("btnDelete");
        }

        btnCategories.setActionCommand("btnCategories");

        CatalogPanelListener catalogPanelListener = new CatalogPanelListener(appFrame, tabellaProdotti, l);
        btnAdd.addActionListener(catalogPanelListener);
        btnEdit.addActionListener(catalogPanelListener);
        btnDelete.addActionListener(catalogPanelListener);
        btnCategories.addActionListener(catalogPanelListener);

        if (l == null){
            operazionitabella.add(btnCategories);
            operazionitabella.add(btnAdd);
            operazionitabella.add(btnEdit);
        }
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }

    public Lista getLista(){
        return l;
    }
}
