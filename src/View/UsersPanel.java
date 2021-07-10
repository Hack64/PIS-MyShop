package View;

import Business.PuntoVenditaBusiness;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.Utente;
import View.Listener.ServiziPanelListener;
import View.TableModels.ShopTableModel;
import View.TableModels.UsersTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UsersPanel extends JPanel {
    AppFrame appFrame;

    public UsersPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        //TODO: trova un modo di cercare gli utenti per idPuntoVendita
        HashMap<Utente, String> utentiPuntoVendita = UtenteBusiness.getInstance().findAllUsersByShopManager((Utente)SessionManager.getInstance().getSession().get("loggedUser"));

        JTable tabellaPuntiVendita = new JTable(new UsersTableModel(utentiPuntoVendita));

        JScrollPane scrollPane = new JScrollPane(tabellaPuntiVendita);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Modifica Utente");
        JButton btnDelete = new JButton("Elimina Utente");

        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");

        ServiziPanelListener serviziPanelListener = new ServiziPanelListener(appFrame, tabellaPuntiVendita);
        btnEdit.addActionListener(serviziPanelListener);
        btnDelete.addActionListener(serviziPanelListener);

        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
