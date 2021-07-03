package View;

import javax.swing.*;

public class GuestMenu extends Menu{

    public GuestMenu() {
        JButton browse = new JButton("Sfoglia il catalogo");
        browse.setActionCommand("btnBrowse");
        pulsanti.add(browse);
    }
}
