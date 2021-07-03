package View;

import DAO.Prodotto.ProdottoDAO;
import Model.IProdotto;
import View.Listener.ProductPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowsePanel extends JPanel {

    ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

    public BrowsePanel(AppFrame appFrame){
        ArrayList<IProdotto> prodotti = prodottoDAO.findAll();
        setLayout(new GridLayout((prodotti.size()/2)+1,(prodotti.size()/2)+1, 1, 1));
        for (IProdotto p:prodotti) {
            ProductPanelListener ppl = new ProductPanelListener(appFrame);
            add(new GridImagePanel(p.getImmagine(), p.getNome(), p.getIdProdotto(), ppl));
        }
        setVisible(true);
    }
}
