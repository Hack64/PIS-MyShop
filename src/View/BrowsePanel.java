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
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProducts();
        panel.setLayout(new GridLayout(0,5, 4, 4));
        ProductGridPanelListener ppl = new ProductGridPanelListener(appFrame);
        for (IProdotto p:prodotti) {
            panel.add(new GridImagePanel(p.getImmagine(), p.getNome(), p.getIdProdotto(), ppl));
        }
        /*panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ProductGridPanelListener pgpl = new ProductGridPanelListener(appFrame);
        c.gridy=0;
        c.gridx=0;
        for (int i=0; i<prodotti.size(); i++){
            if (i%4 != 0 || i==0) {
                panel.add(new GridImagePanel(prodotti.get(i).getImmagine(), prodotti.get(i).getNome(), prodotti.get(i).getIdProdotto(), pgpl), c);
                c.gridx++;
            } else{
                c.gridx=3;
                panel.add(new GridImagePanel(prodotti.get(i).getImmagine(), prodotti.get(i).getNome(), prodotti.get(i).getIdProdotto(), pgpl), c);
                c.gridy++;
                c.gridx=0;
            }
        }*/
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
