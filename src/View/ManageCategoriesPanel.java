package View;

import Business.CategoriaBusiness;
import Model.ICategoria;
import View.Listener.ManageCategoriesPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageCategoriesPanel extends JPanel {
    AppFrame appFrame;

    public ManageCategoriesPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        ArrayList<ICategoria> categorie = CategoriaBusiness.getInstance().findAll();

        JTable tabellaCategorie = new JTable(new CategoriesTableModel(categorie));

        JScrollPane scrollPane = new JScrollPane(tabellaCategorie);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi categoria / sottocategoria");
        JButton btnEdit = new JButton("Modifica Categoria");
        JButton btnDelete = new JButton("Elimina Categoria");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");

        ManageCategoriesPanelListener manageCategoriesPanelListener = new ManageCategoriesPanelListener(appFrame, tabellaCategorie);
        btnAdd.addActionListener(manageCategoriesPanelListener);
        btnEdit.addActionListener(manageCategoriesPanelListener);
        btnDelete.addActionListener(manageCategoriesPanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
