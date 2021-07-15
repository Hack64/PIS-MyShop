package Business;

import DAO.Magazzino.IMagazzinoDAO;
import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DAO.ProdottiPuntoVendita.IProdottiPuntoVenditaDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.PuntoVendita.IPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.ServiziPuntoVendita.IServiziPuntoVenditaDAO;
import DAO.ServiziPuntoVendita.ServiziPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.IUtentiPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import Model.*;
import Model.Responses.PuntoVenditaResponse;

import java.util.ArrayList;

public class PuntoVenditaBusiness {
    private static PuntoVenditaBusiness instance;
    private IPuntoVenditaDAO puntoVenditaDAO;

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

    public int addNewShop(String viaPV, String capPV, String cittaPV, String viaM, String cittaM, String capM, Utente manager, ArrayList<IProdotto> prodotti, ArrayList<Servizio> servizi) {
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IProdottiPuntoVenditaDAO prodottiPuntoVenditaDAO = ProdottiPuntoVenditaDAO.getInstance();
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        IServiziPuntoVenditaDAO serviziPuntoVenditaDAO = ServiziPuntoVenditaDAO.getInstance();

        PuntoVendita p = new PuntoVendita();
        Magazzino m = new Magazzino();

        p.setCitta(cittaPV);
        p.setCap(capPV);
        p.setVia(viaPV);

        m.setCap(capM);
        m.setCitta(cittaM);
        m.setVia(viaM);

        int st=0;

        st=puntoVenditaDAO.add(p);
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV);
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
        for (Servizio s:servizi){
            st+= serviziPuntoVenditaDAO.add(s, p);
        }
        return st;
    }

    public PuntoVenditaResponse findByID(int idPuntoVendita){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

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

    public int updateShopAndWarehouse(int idPuntoVendita, String viaPV, String capPV, String cittaPV, String viaM, String cittaM, String capM, Utente manager, ArrayList<IProdotto> prodotti, ArrayList<Servizio> servizi){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        IProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        IProdottiPuntoVenditaDAO prodottiPuntoVenditaDAO = ProdottiPuntoVenditaDAO.getInstance();
        IServiziPuntoVenditaDAO serviziPuntoVenditaDAO = ServiziPuntoVenditaDAO.getInstance();

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

        int st=0;

        st=puntoVenditaDAO.update(p);
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV);
        m.setPuntoVendita(p);
        st+=magazzinoDAO.update(m);
        /*int idPC = utentiPuntoVenditaDAO.findShopByShopManagerID(manager.getIdUtente()).getIdPuntoVendita();
        if (idPC!=0){
            utentiPuntoVenditaDAO.removeByID(manager.getIdUtente(), idPC);
        }*/
        st+=utentiPuntoVenditaDAO.updateManager(manager, p);
        prodottiPuntoVenditaDAO.removeAllPrductsByShopID(p.getIdPuntoVendita());
        for (IProdotto pr:prodotti){
            st+=prodottiPuntoVenditaDAO.add(p, pr);
        }
        serviziPuntoVenditaDAO.removeAllServicesByShopID(p.getIdPuntoVendita());
        for (Servizio s:servizi){
            st+= serviziPuntoVenditaDAO.add(s, p);
        }

        return st;
    }

    public ArrayList<String> findAllShopsAddresses(){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<String> indirizzi = new ArrayList<>();
        for (PuntoVendita p: puntoVenditaDAO.findAll()){
            String indirizzo =  p.getVia() + " , " + p.getCitta();
            indirizzi.add(indirizzo);
        }
        return indirizzi;
    }

    public PuntoVenditaResponse findShopByAddress(String indirizzo){
        puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVenditaResponse pvr = new PuntoVenditaResponse();
        pvr.setMessage("Errore non definito");

        String[] indirizzi = indirizzo.split(", ");

        PuntoVendita p = puntoVenditaDAO.findByAddress(indirizzi[1],indirizzi[0]);
        if (p!=null){
            pvr.setMessage("Punto vendita trovato con successo");
            pvr.setPuntoVendita(p);
        } else {
            pvr.setMessage("Errore durante la ricerca del punto vendita");
            return pvr;
        }

        return pvr;
    }
}
