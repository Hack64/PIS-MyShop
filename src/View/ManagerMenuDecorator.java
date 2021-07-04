package View;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator {

    public ManagerMenuDecorator(Menu menu){
        this.menu = menu;
    }

    @Override
    public List<JButton> getPulsanti(){
        if(pulsanti.size() > 0) return pulsanti;
        pulsanti.addAll(this.menu.getPulsanti());
        JButton users = new JButton("Lista Utenti");
        JButton warehouses = new JButton("Gestisci Magazzino");
        users.setActionCommand("btnUsers");
        warehouses.setActionCommand("btnWarehouses");
        pulsanti.add(warehouses);
        pulsanti.add(users);
        return pulsanti;
    }
}
