package View.Panel;

import Model.Lista;
import View.AppFrame;

import javax.swing.*;
import java.awt.*;

public class MainCatalogPanel extends JPanel {

    public MainCatalogPanel(AppFrame appFrame, Lista l){
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        if (l != null) {
            JPanel catalogPanel = new CatalogPanel(appFrame, false, l);
            JPanel serviziPanel = new ServiziPanel(appFrame, l);
            tabbedPane.addTab("Prodotti", null, catalogPanel, "Gestisci i prodotti della lista");
            tabbedPane.addTab("Servizi", null, serviziPanel, "Gestisci i servizi della lista");
        } else {
            JPanel catalogPanel = new CatalogPanel(appFrame, false, null);
            JPanel compositeProductsPanel = new CatalogPanel(appFrame, true, null);
            JPanel serviziPanel = new ServiziPanel(appFrame, null);
            tabbedPane.addTab("Prodotti", null, catalogPanel, "Gestisci i prodotti");
            tabbedPane.addTab("Prodotti Compositi", null, compositeProductsPanel ,"Gestisci i prodotti compositi");
            tabbedPane.addTab("Servizi", null, serviziPanel, "Gestisci i servizi");
        }




        add(tabbedPane, BorderLayout.CENTER);
    }
}
