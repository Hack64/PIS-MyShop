package View.Panel;

import View.AppFrame;

import javax.swing.*;
import java.awt.*;

public class MainCatalogPanel extends JPanel {

    public MainCatalogPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel catalogPanel = new CatalogPanel(appFrame, false);
        JPanel serviziPanel = new ServiziPanel(appFrame);
        JPanel compositeProductsPanel = new CatalogPanel(appFrame, true);

        tabbedPane.addTab("Prodotti", null, catalogPanel, "Gestisci i prodotti");
        tabbedPane.addTab("Prodotti Compositi", null, compositeProductsPanel ,"Gestisci i prodotti compositi");
        tabbedPane.addTab("Servizi", null, serviziPanel, "Gestisci i servizi");

        add(tabbedPane, BorderLayout.CENTER);
    }
}
