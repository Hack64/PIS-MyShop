package View;

import Business.PuntoVenditaBusiness;
import View.Listener.WelcomePanelListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WelcomePanel extends JPanel {

    JComboBox puntiVenditaBox;

    public WelcomePanel(AppFrame appFrame) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JLabel lblWelcome = new JLabel("Benvenuto, seleziona il punto vendita di questo totem.");
        ArrayList<String> puntiVendita = PuntoVenditaBusiness.getInstance().findAllShopsAddresses();
        puntiVenditaBox = new JComboBox(puntiVendita.toArray());
        JButton btnOk = new JButton("OK");
        btnOk.setActionCommand("btnOk");
        btnOk.addActionListener(new WelcomePanelListener(appFrame, this));

        puntiVenditaBox.setFont(new Font("Noto Sans", Font.PLAIN, 18));
        lblWelcome.setFont(new Font("Noto Sans", Font.PLAIN, 25));

        c.gridx=0;
        c.gridy=0;
        add(lblWelcome, c);
        c.gridy++;
        add(puntiVenditaBox, c);
        c.gridy++;
        add(btnOk, c);
    }

    public String getSelectedPuntoVendita() {
        return puntiVenditaBox.getSelectedItem().toString();
    }
}
