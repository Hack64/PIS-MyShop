package View;

import Business.ProdottoBusiness;
import Business.ServizioBusiness;
import Model.IProdotto;
import Model.Servizio;
import View.Listener.ProductGridPanelListener;
import View.Listener.ServiceGridPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseServicesPanel extends JPanel{

    public BrowseServicesPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        ArrayList<Servizio> servizi = ServizioBusiness.getInstance().findAllServicesByShopID(appFrame.getPuntoVendita().getIdPuntoVendita());
        panel.setLayout(new GridLayout(0,5, 4, 4));
        ServiceGridPanelListener ppl = new ServiceGridPanelListener(appFrame);
        for (Servizio s:servizi) {
            panel.add(new GridImagePanel(s.getImmagine(), s.getNome(), s.getIdServizio(), ppl));
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
