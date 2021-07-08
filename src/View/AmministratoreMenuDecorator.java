package View;

import View.Listener.CatalogPanelListener;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator {

    public AmministratoreMenuDecorator(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti() {
        if(pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());
        JButton shops = new JButton("Gestisci P.Vendita");
        JButton catalog = new JButton("Gestisci Catalogo");
        shops.setActionCommand("btnShops");
        catalog.setActionCommand("btnCatalog");
        pulsanti.add(catalog);
        pulsanti.add(shops);
        return pulsanti;
    }
}
