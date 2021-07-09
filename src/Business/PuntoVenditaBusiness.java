package Business;

import DAO.PuntoVendita.PuntoVenditaDAO;
import Model.PuntoVendita;

import java.util.ArrayList;

public class PuntoVenditaBusiness {
    private static PuntoVenditaBusiness instance;
    private PuntoVenditaDAO puntoVenditaDAO;

    public static synchronized PuntoVenditaBusiness getInstance() {
        if(instance == null) instance = new PuntoVenditaBusiness();
        return instance;
    }

    private PuntoVenditaBusiness() {}

    public ArrayList<PuntoVendita> findAllShops(){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        return puntoVenditaDAO.findAll();
    }

}
