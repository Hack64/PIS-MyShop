package View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        this.setBorder(new EtchedBorder());
        JLabel benvenuto = new JLabel("Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!");
        add(benvenuto);
    }
}
