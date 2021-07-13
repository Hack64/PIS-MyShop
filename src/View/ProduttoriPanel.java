package View;

import Business.ProduttoreBusiness;
import Model.Produttore;
import View.Listener.ProduttorePanelListener;
import View.TableModels.ProduttoreTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProduttoriPanel extends JPanel {

    AppFrame appFrame;

    public ProduttoriPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        ArrayList<Produttore> produttori;

        produttori = ProduttoreBusiness.getInstance().findAllProducers();

        JTable tabellaProduttori = new JTable(new ProduttoreTableModel(produttori));

        JScrollPane scrollPane = new JScrollPane(tabellaProduttori);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo produttore");
        JButton btnEdit = new JButton("Modifica Produttore");
        JButton btnDelete = new JButton("Elimina Produttore");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");


        ProduttorePanelListener produttorePanelListener = new ProduttorePanelListener(appFrame, tabellaProduttori);
        btnAdd.addActionListener(produttorePanelListener);
        btnEdit.addActionListener(produttorePanelListener);
        btnDelete.addActionListener(produttorePanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
