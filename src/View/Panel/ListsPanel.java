package View.Panel;

import Business.ListaBusiness;
import Business.SessionManager;
import Model.Lista;
import Model.Utente;
import View.AppFrame;
import View.Listener.ListPanelListener;
import View.TableModels.ListaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListsPanel extends JPanel {

    public ListsPanel(AppFrame appFrame, boolean systemView, Utente utente){
        setLayout(new BorderLayout());

        ArrayList<Lista> listeUtente;

        if (systemView) {
            listeUtente = ListaBusiness.getInstance().findAllListsByUserAndState(utente, Lista.Stato.NON_PAGATA);
        } else {
            listeUtente = ListaBusiness.getInstance().findAllListsByUser((Utente) SessionManager.getInstance().getSession().get("loggedUser"));
        }

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
        JButton btnPay = new JButton("Imposta come pagata");
        btnPay.setActionCommand("btnPay");

        ListPanelListener listPanelListener = new ListPanelListener(appFrame, tabellaListe);
        btnAdd.addActionListener(listPanelListener);
        btnDelete.addActionListener(listPanelListener);
        btnEdit.addActionListener(listPanelListener);
        btnPay.addActionListener(listPanelListener);

        if (!systemView){
            operazionitabella.add(btnAdd);
            operazionitabella.add(btnEdit);
            operazionitabella.add(btnDelete);
        } else {
            operazionitabella.add(btnPay);
        }


        add(operazionitabella, BorderLayout.SOUTH);
    }
}
