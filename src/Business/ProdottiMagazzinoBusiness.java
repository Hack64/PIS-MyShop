package Business;

import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import Model.Disponibilita;

import java.util.ArrayList;

public class ProdottiMagazzinoBusiness {
    private static ProdottiMagazzinoBusiness instance;
    private IProdottiMagazzinoDAO prodottiMagazzinoDAO;

    public static synchronized ProdottiMagazzinoBusiness getInstance() {
        if(instance == null) instance = new ProdottiMagazzinoBusiness();
        return instance;
    }

    private ProdottiMagazzinoBusiness() {}

    public ArrayList<Disponibilita> findProductsByWarehouse(int idMagazzino){
        prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();

        return prodottiMagazzinoDAO.findAllProductsByWarehouseID(idMagazzino);
    }

    public Disponibilita findByProductAndWarehouse(int idProdotto, int idMagazzino){
        prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();

        return prodottiMagazzinoDAO.findByProductAndWarehouseID(idProdotto, idMagazzino);
    }

    public int update(Disponibilita d){
        prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();

        return prodottiMagazzinoDAO.update(d);
    }

}
