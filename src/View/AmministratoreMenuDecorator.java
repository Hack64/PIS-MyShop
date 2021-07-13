package View;

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
        JButton imprenditori = new JButton("Gestisci imprenditori");
        shops.setActionCommand("btnShops");
        catalog.setActionCommand("btnCatalog");
        imprenditori.setActionCommand("btnImprenditori");
        pulsanti.add(catalog);
        pulsanti.add(shops);
        pulsanti.add(imprenditori);
        return pulsanti;
    }
}
