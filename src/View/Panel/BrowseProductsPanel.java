package View.Panel;

import Business.ProdottoBusiness;
import Business.SessionManager;
import Model.IProdotto;
import Model.PuntoVendita;
import View.AppFrame;
import View.Listener.GridPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseProductsPanel extends JPanel {

    public BrowseProductsPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProductsByShop(p);
        panel.setLayout(new GridLayout(0,4, 4, 4));
        GridPanelListener gpl = new GridPanelListener(appFrame, false);
        for (IProdotto pr:prodotti) {
            panel.add(new GridImagePanel(pr.getImmagine(), pr.getNome(), pr.getIdProdotto(), gpl, appFrame));
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
