package View;

import Business.ServizioBusiness;
import Model.Servizio;
import View.Listener.CatalogPanelListener;
import View.Listener.ServiziPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServiziPanel extends JPanel {

    AppFrame appFrame;

    public ServiziPanel(AppFrame appFrame) {

        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        ArrayList<Servizio> serviziCatalogo = ServizioBusiness.getInstance().findAllServices();

        JTable tabellaServizi = new JTable(new ServizioTableModel(serviziCatalogo));

        JScrollPane scrollPane = new JScrollPane(tabellaServizi);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo servizio");
        JButton btnEdit = new JButton("Modifica Servizio");
        JButton btnDelete = new JButton("Elimina Servizio");
        JButton btnCategories = new JButton("Gestisci Categorie");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");
        btnCategories.setActionCommand("btnCategories");

        ServiziPanelListener serviziPanelListener = new ServiziPanelListener(appFrame, tabellaServizi);
        btnAdd.addActionListener(serviziPanelListener);
        btnEdit.addActionListener(serviziPanelListener);
        btnDelete.addActionListener(serviziPanelListener);
        btnCategories.addActionListener(serviziPanelListener);

        operazionitabella.add(btnCategories);
        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
