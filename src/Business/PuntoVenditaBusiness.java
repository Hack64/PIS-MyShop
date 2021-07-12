package Business;

import DAO.Magazzino.MagazzinoDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import Model.Magazzino;
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

    public int deleteShop(int idPuntoVendita){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        return puntoVenditaDAO.removeById(idPuntoVendita);
    }

    public int addNewShop(String viaPV, String capPV, String cittaPV, String viaM, String cittaM, String capM, int corsie, int scaffali) {
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        PuntoVendita p = new PuntoVendita();
        Magazzino m = new Magazzino();

        p.setCitta(cittaPV);
        p.setCap(capPV);
        p.setVia(viaPV);

        m.setCap(capM);
        m.setCitta(cittaM);
        m.setVia(viaM);
        m.setNumeroScaffali(scaffali);
        m.setNumeroCorsie(corsie);

        int st=0;

        st=puntoVenditaDAO.add(p);
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV, capPV);
        m.setPuntoVendita(p);
        st+=magazzinoDAO.add(m);

        return st;
    }

}
