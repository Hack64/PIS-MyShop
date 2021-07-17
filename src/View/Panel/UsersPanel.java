package View.Panel;

import Business.SessionManager;
import Business.UtenteBusiness;
import Model.PuntoVendita;
import Model.Utente;
import View.AppFrame;
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

        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
        HashMap<Utente, String> utentiPuntoVendita = UtenteBusiness.getInstance().findAllUsersByShop(p);

        JTable tabellaUtenti = new JTable(new UsersTableModel(utentiPuntoVendita));

        JScrollPane scrollPane = new JScrollPane(tabellaUtenti);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Abilita / Disabilita Utente");
        JButton btnDelete = new JButton("Elimina Utente");
        JButton btnSendEmail = new JButton("Invia comunicazione");
        JButton btnShowLists = new JButton("Visualizza liste");

        btnEdit.setActionCommand("btnDisable");
        btnDelete.setActionCommand("btnDelete");
        btnSendEmail.setActionCommand("btnSend");
        btnShowLists.setActionCommand("btnShow");

        UsersPanelListener usersPanelListener = new UsersPanelListener(appFrame, tabellaUtenti);
        btnEdit.addActionListener(usersPanelListener);
        btnDelete.addActionListener(usersPanelListener);
        btnSendEmail.addActionListener(usersPanelListener);
        btnShowLists.addActionListener(usersPanelListener);

        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);
        operazionitabella.add(btnSendEmail);
        operazionitabella.add(btnShowLists);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
