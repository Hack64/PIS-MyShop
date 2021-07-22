package View.Panel;

import Model.Lista;
import View.AppFrame;

import javax.swing.*;
import java.awt.*;

public class MainListsPanel extends JPanel {

    public MainListsPanel(AppFrame appFrame) {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel paidListsPanel = new ListsPanel(appFrame, false, Lista.Stato.PAGATA, null);
        JPanel nonPaidListsPanel = new ListsPanel(appFrame, false, Lista.Stato.NON_PAGATA, null);
        tabbedPane.addTab("Non Pagate", null, nonPaidListsPanel, "Gestisci le liste non pagate");
        tabbedPane.addTab("Pagate", null, paidListsPanel, "Gestisci le liste pagate");

        add(tabbedPane, BorderLayout.CENTER);
    }
}
