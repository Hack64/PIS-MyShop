package View.Panel;

import Business.MagazzinoBusiness;
import Business.ProdottiMagazzinoBusiness;
import Model.Disponibilita;
import Model.Magazzino;
import View.AppFrame;
import View.Listener.WarehousePanelListener;
import View.TableModels.MagazzinoTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WarehousesPanel extends JPanel {

    private AppFrame appFrame;

    public WarehousesPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        setLayout(new BorderLayout());
        Magazzino m = MagazzinoBusiness.getInstance().findWarehouseByShopID(appFrame.getPuntoVendita().getIdPuntoVendita());
        ArrayList<Disponibilita> prodottiMagazzino = ProdottiMagazzinoBusiness.getInstance().findProductsByWarehouse(m.getIdMagazzino());

        JTable tabellaProdotti = new JTable(new MagazzinoTableModel(prodottiMagazzino));

        JScrollPane scrollPane = new JScrollPane(tabellaProdotti);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Modifica Prodotto");

        WarehousePanelListener warehousesPanelListener = new WarehousePanelListener(appFrame, tabellaProdotti);
        btnEdit.setActionCommand("btnEdit");
        btnEdit.addActionListener(warehousesPanelListener);

        operazionitabella.add(btnEdit);

        add(operazionitabella, BorderLayout.SOUTH);
    }
}
