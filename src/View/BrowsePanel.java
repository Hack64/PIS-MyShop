package View;

import Business.ProdottoBusiness;
import DAO.Prodotto.ProdottoDAO;
import Model.IProdotto;
import View.Listener.ProductPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowsePanel extends JPanel {

    public BrowsePanel(AppFrame appFrame){
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProducts();
        setLayout(new GridLayout(4,prodotti.size(), 1, 1));
        for (IProdotto p:prodotti) {
            ProductPanelListener ppl = new ProductPanelListener(appFrame);
            add(new GridImagePanel(p.getImmagine(), p.getNome(), p.getIdProdotto(), ppl));
        }
        setVisible(true);
    }
}
