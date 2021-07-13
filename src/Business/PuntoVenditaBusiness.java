package Business;

import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import Model.IProdotto;
import Model.Magazzino;
import Model.PuntoVendita;
import Model.Responses.PuntoVenditaResponse;
import Model.Utente;

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

    public int addNewShop(String viaPV, String capPV, String cittaPV, String viaM, String cittaM, String capM, int corsie, int scaffali, Utente manager, ArrayList<IProdotto> prodotti) {
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ProdottiPuntoVenditaDAO prodottiPuntoVenditaDAO = ProdottiPuntoVenditaDAO.getInstance();
        UtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

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
        if (!utentiPuntoVenditaDAO.isUserShopManagerSomewhere(manager.getIdUtente())){
            st+=utentiPuntoVenditaDAO.add(manager, p, 0, 1);
        } else {
            //TODO: trova un modo di capire quando il manager è già assignato e di dirlo all'utente
            //TODO: finisci la modifica dei punti vendita
            //TODO: quando fai l'update, cancella il precedente manager!
            System.out.println(manager.getNome() + " è già un manager!!!");
        }
        for (IProdotto pr:prodotti){
            st+= prodottiPuntoVenditaDAO.add(p, pr);
        }
        return st;
    }

    public PuntoVenditaResponse findByID(int idPuntoVendita){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        PuntoVenditaResponse res = new PuntoVenditaResponse();
        res.setMessage("Errore non definito");

        PuntoVendita p = puntoVenditaDAO.findByID(idPuntoVendita);
        if (p!=null){
            res.setPuntoVendita(p);
        } else {
            res.setMessage("Errore durante la ricerca del punto vendita");
            return res;
        }

        Magazzino m = magazzinoDAO.findByShopID(p.getIdPuntoVendita());
        if (m!=null){
            res.setMessage("Punto Vendita e Magazzino trovati con successo");
            res.setMagazzino(m);
        } else {
            res.setMessage("Errore durante la ricerca del magazzino");
            return res;
        }

        return res;
    }

    public int updateShopAndWarehouse(int idPuntoVendita, String viaPV, String capPV, String cittaPV, String viaM, String cittaM, String capM, int corsie, int scaffali, Utente manager, ArrayList<IProdotto> prodotti){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

        PuntoVendita p = new PuntoVendita();
        Magazzino m;

        p.setIdPuntoVendita(idPuntoVendita);
        p.setCitta(cittaPV);
        p.setCap(capPV);
        p.setVia(viaPV);

        m = magazzinoDAO.findByShopID(p.getIdPuntoVendita());

        m.setCap(capM);
        m.setCitta(cittaM);
        m.setVia(viaM);
        m.setNumeroScaffali(scaffali);
        m.setNumeroCorsie(corsie);

        int st=0;

        st=puntoVenditaDAO.update(p);
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV, capPV);
        m.setPuntoVendita(p);
        st+=magazzinoDAO.update(m);

        return st;
    }
}
