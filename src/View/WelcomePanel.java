package View;

import javax.swing.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        JLabel benvenuto = new JLabel("Ciao, effettua il login per iniziare, oppure sfoglia il catalogo usando il pulsante a lato!");
        add(benvenuto);
    }
}
