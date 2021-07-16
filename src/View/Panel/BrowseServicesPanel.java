package View.Panel;

import Business.ServizioBusiness;
import Business.SessionManager;
import Model.PuntoVendita;
import Model.Servizio;
import View.AppFrame;
import View.Listener.GridPanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrowseServicesPanel extends JPanel{

    public BrowseServicesPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollPane;
        PuntoVendita p = (PuntoVendita)SessionManager.getInstance().getSession().get("currentShop");
        ArrayList<Servizio> servizi = ServizioBusiness.getInstance().findAllServicesByShopID(p.getIdPuntoVendita());
        panel.setLayout(new GridLayout(0,5, 4, 4));
        GridPanelListener gpl = new GridPanelListener(appFrame, true);
        for (Servizio s:servizi) {
            panel.add(new GridImagePanel(s.getImmagine(), s.getNome(), s.getIdServizio() , gpl, appFrame));
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
