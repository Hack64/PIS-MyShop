package Business;

import DAO.Magazzino.MagazzinoDAO;
import Model.Disponibilita;
import Model.Magazzino;

import java.util.ArrayList;

public class MagazzinoBusiness {
    private static MagazzinoBusiness instance;
    private MagazzinoDAO magazzinoDAO;

    public static synchronized MagazzinoBusiness getInstance() {
        if(instance == null) instance = new MagazzinoBusiness();
        return instance;
    }

    private MagazzinoBusiness() {}

    public Magazzino findWarehouseByShopID(int idPuntoVendita){
        magazzinoDAO = MagazzinoDAO.getInstance();

        return magazzinoDAO.findByShopID(idPuntoVendita);
    }
}
