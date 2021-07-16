package View;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.Listener.UsersPanelListener;
import View.TableModels.UsersTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UsersPanel extends JPanel {
    private AppFrame appFrame;

    public UsersPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        //TODO: trova un modo di cercare gli utenti per idPuntoVendita
        HashMap<Utente, String> utentiPuntoVendita = UtenteBusiness.getInstance().findAllUsersByShopManager((Utente)SessionManager.getInstance().getSession().get("loggedUser"));

        JTable tabellaUtenti = new JTable(new UsersTableModel(utentiPuntoVendita));

        JScrollPane scrollPane = new JScrollPane(tabellaUtenti);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Abilita / Disabilita Utente");
        JButton btnDelete = new JButton("Elimina Utente");

        btnEdit.setActionCommand("btnDisable");
        btnDelete.setActionCommand("btnDelete");

        UsersPanelListener usersPanelListener = new UsersPanelListener(appFrame, tabellaUtenti);
        btnEdit.addActionListener(usersPanelListener);
        btnDelete.addActionListener(usersPanelListener);

        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
