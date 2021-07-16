package View.Panel;

import View.AppFrame;

import javax.swing.*;
import java.awt.*;

public class BrowsePanel extends JPanel{

    public BrowsePanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel productsPanel = new BrowseProductsPanel(appFrame);
        JPanel servicesPanel = new BrowseServicesPanel(appFrame);

        tabbedPane.addTab("Prodotti", null, productsPanel, "Gestisci i prodotti");
        tabbedPane.addTab("Servizi", null, servicesPanel, "Gestisci i servizi");

        add(tabbedPane, BorderLayout.CENTER);
    }
}
