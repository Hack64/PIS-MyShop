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

    public ListsPanel(AppFrame appFrame, boolean systemView, Lista.Stato stato, Utente utente){
        setLayout(new BorderLayout());

        ArrayList<Lista> listeUtente;

        if (systemView) {
            listeUtente = ListaBusiness.getInstance().findAllListsByUserAndState(utente, Lista.Stato.NON_PAGATA);
        } else if (stato.equals(Lista.Stato.NON_PAGATA)){
            listeUtente = ListaBusiness.getInstance().findAllListsByUserAndState((Utente) SessionManager.getInstance().getSession().get("loggedUser"), Lista.Stato.NON_PAGATA);
        } else {
            listeUtente = ListaBusiness.getInstance().findAllListsByUserAndState((Utente) SessionManager.getInstance().getSession().get("loggedUser"), Lista.Stato.PAGATA);
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
        JButton btnShowProducts = new JButton("Visualizza prodotti");
        btnShowProducts.setActionCommand("btnShowProducts");
        JButton btnGeneratePDF = new JButton("Genera PDF");
        btnGeneratePDF.setActionCommand("btnGeneratePDF");
        JButton btnPay = new JButton("Imposta come pagata");
        btnPay.setActionCommand("btnPay");
        JButton btnDuplicate = new JButton("Duplica Lista");
        btnDuplicate.setActionCommand("btnDuplicate");


        ListPanelListener listPanelListener = new ListPanelListener(appFrame, tabellaListe);
        btnAdd.addActionListener(listPanelListener);
        btnDelete.addActionListener(listPanelListener);
        btnEdit.addActionListener(listPanelListener);
        btnPay.addActionListener(listPanelListener);
        btnShowProducts.addActionListener(listPanelListener);
        btnGeneratePDF.addActionListener(listPanelListener);
        btnDuplicate.addActionListener(listPanelListener);

        if (systemView){
            operazionitabella.add(btnPay);
        } else if (stato.equals(Lista.Stato.NON_PAGATA)) {
            operazionitabella.add(btnAdd);
            operazionitabella.add(btnEdit);
            operazionitabella.add(btnDelete);
            operazionitabella.add(btnGeneratePDF);
        }
        operazionitabella.add(btnShowProducts);
        operazionitabella.add(btnDuplicate);


        add(operazionitabella, BorderLayout.SOUTH);
    }
}
