package View;

import Business.ListaBusiness;
import Business.SessionManager;
import Model.Lista;
import Model.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListsPanel extends JPanel {

    public ListsPanel(){
        setLayout(new BorderLayout());

        ArrayList<Lista> listeUtente = ListaBusiness.getInstance().findAllListsByUser((Utente)SessionManager.getInstance().getSession().get("loggedUser"));

        JTable tabellaListe = new JTable(new ListaTableModel(listeUtente));

        JScrollPane scrollPane = new JScrollPane(tabellaListe);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Modifica Lista");
        JButton btnDelete = new JButton("Elimina Lista");

        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}