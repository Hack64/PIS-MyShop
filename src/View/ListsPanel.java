package View;

import Business.ListaBusiness;
import Business.SessionManager;
import Model.Lista;
import Model.Utente;
import View.Listener.ListPanelListener;
import View.TableModels.ListaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListsPanel extends JPanel {

    public ListsPanel(AppFrame appFrame){
        setLayout(new BorderLayout());

        ArrayList<Lista> listeUtente = ListaBusiness.getInstance().findAllListsByUser((Utente)SessionManager.getInstance().getSession().get("loggedUser"));

        JTable tabellaListe = new JTable(new ListaTableModel(listeUtente));

        JScrollPane scrollPane = new JScrollPane(tabellaListe);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Crea Lista");
        btnAdd.setActionCommand("btnAdd");
        JButton btnEdit = new JButton("Modifica Lista");
        btnEdit.setActionCommand("btnEdit");
        JButton btnDelete = new JButton("Elimina Lista");
        btnDelete.setActionCommand("btnDelete");

        ListPanelListener listPanelListener = new ListPanelListener(appFrame, tabellaListe);
        btnAdd.addActionListener(listPanelListener);
        btnDelete.addActionListener(listPanelListener);
        btnEdit.addActionListener(listPanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
