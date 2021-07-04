package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.Listener.ProductGridPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowsePanel extends JPanel {

    public BrowsePanel(AppFrame appFrame){
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProducts();
        setLayout(new GridLayout(0,3, 1, 1));
        for (IProdotto p:prodotti) {
            ProductGridPanelListener ppl = new ProductGridPanelListener(appFrame);
            add(new GridImagePanel(p.getImmagine(), p.getNome(), p.getIdProdotto(), ppl));
        }
        setVisible(true);
    }
}
