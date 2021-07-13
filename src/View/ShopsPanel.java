package View;

import Business.PuntoVenditaBusiness;
import Model.PuntoVendita;
import View.Listener.ShopsPanelListener;
import View.TableModels.ShopTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShopsPanel extends JPanel {
    AppFrame appFrame;

    public ShopsPanel(AppFrame appFrame){
        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        ArrayList<PuntoVendita> puntiVendita = PuntoVenditaBusiness.getInstance().findAllShops();

        JTable tabellaPuntiVendita = new JTable(new ShopTableModel(puntiVendita));

        JScrollPane scrollPane = new JScrollPane(tabellaPuntiVendita);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo P.V.");
        JButton btnEdit = new JButton("Modifica P.V.");
        JButton btnDelete = new JButton("Elimina P.V.");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");

        ShopsPanelListener shopsPanelListener = new ShopsPanelListener(appFrame, tabellaPuntiVendita);
        btnAdd.addActionListener(shopsPanelListener);
        btnEdit.addActionListener(shopsPanelListener);
        btnDelete.addActionListener(shopsPanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
