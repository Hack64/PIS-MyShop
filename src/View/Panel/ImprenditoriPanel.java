package View.Panel;

import View.AppFrame;

import javax.swing.*;
import java.awt.*;

public class ImprenditoriPanel extends JPanel {

    public ImprenditoriPanel(AppFrame appFrame){
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel produttoriPanel = new ProduttoriPanel(appFrame);
        JPanel fornitoriPanel = new FornitoriPanel(appFrame);

        tabbedPane.addTab("Produttori", null, produttoriPanel, "Gestisci i produttori");
        tabbedPane.addTab("Fornitori", null, fornitoriPanel, "Gestisci i fornitori");

        add(tabbedPane, BorderLayout.CENTER);
    }
}
