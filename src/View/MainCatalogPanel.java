package View;

import javax.swing.*;
import java.awt.*;

public class MainCatalogPanel extends JPanel {

    public MainCatalogPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel catalogPanel = new CatalogPanel(appFrame);
        JPanel serviziPanel = new ServiziPanel(appFrame);

        tabbedPane.addTab("Prodotti", null, catalogPanel, "Gestisci i prodotti");
        tabbedPane.addTab("Servizi", null, serviziPanel, "Gestisci i servizi");

        add(tabbedPane, BorderLayout.CENTER);
    }
}
