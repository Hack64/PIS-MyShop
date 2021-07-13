package View;

import Business.FornitoreBusiness;
import Model.Fornitore;
import View.Listener.FornitorePanelListener;
import View.TableModels.FornitoreTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FornitoriPanel extends JPanel {

    AppFrame appFrame;

    public FornitoriPanel(AppFrame appFrame){

        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        ArrayList<Fornitore> fornitori;

        fornitori = FornitoreBusiness.getInstance().findAllSuppliers();

        JTable tabellaProduttori = new JTable(new FornitoreTableModel(fornitori));

        JScrollPane scrollPane = new JScrollPane(tabellaProduttori);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnAdd = new JButton("Aggiungi nuovo fornitore");
        JButton btnEdit = new JButton("Modifica fornitore");
        JButton btnDelete = new JButton("Elimina fornitore");

        btnAdd.setActionCommand("btnAdd");
        btnEdit.setActionCommand("btnEdit");
        btnDelete.setActionCommand("btnDelete");

        FornitorePanelListener fornitorePanelListener = new FornitorePanelListener(appFrame, tabellaProduttori);
        btnAdd.addActionListener(fornitorePanelListener);
        btnEdit.addActionListener(fornitorePanelListener);
        btnDelete.addActionListener(fornitorePanelListener);

        operazionitabella.add(btnAdd);
        operazionitabella.add(btnEdit);
        operazionitabella.add(btnDelete);

        add(operazionitabella, BorderLayout.SOUTH);

    }
}
