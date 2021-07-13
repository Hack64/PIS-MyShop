package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.Listener.ProductGridPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowsePanel extends JPanel {

    public BrowsePanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProductsByShop(appFrame.getPuntoVendita());
        panel.setLayout(new GridLayout(0,5, 4, 4));
        ProductGridPanelListener ppl = new ProductGridPanelListener(appFrame);
        for (IProdotto p:prodotti) {
            panel.add(new GridImagePanel(p.getImmagine(), p.getNome(), p.getIdProdotto(), ppl));
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
