package View.Panel;

import Business.ServizioBusiness;
import Model.Lista;
import Model.Servizio;
import View.AppFrame;
import View.Listener.ServiziPanelListener;
import View.TableModels.ServizioTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServiziPanel extends JPanel {

    private AppFrame appFrame;

    public ServiziPanel(AppFrame appFrame, Lista l) {

        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        ArrayList<Servizio> serviziCatalogo;

        if (l != null){
            serviziCatalogo = l.getServizi();
        } else {
           serviziCatalogo = ServizioBusiness.getInstance().findAllServices();
        }

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
        if (l != null) {
            btnDelete.setActionCommand("btnDelete");
        } else {
            btnDelete.setActionCommand("btnDeleteFromList");
        }
        btnCategories.setActionCommand("btnCategories");

        ServiziPanelListener serviziPanelListener = new ServiziPanelListener(appFrame, tabellaServizi);
        btnAdd.addActionListener(serviziPanelListener);
        btnEdit.addActionListener(serviziPanelListener);
        btnDelete.addActionListener(serviziPanelListener);
        btnCategories.addActionListener(serviziPanelListener);

        if (l == null) {
            operazionitabella.add(btnCategories);
            operazionitabella.add(btnAdd);
            operazionitabella.add(btnEdit);
        }
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
