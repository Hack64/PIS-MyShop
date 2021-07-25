package Business;

import DAO.Magazzino.IMagazzinoDAO;
import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DAO.ProdottiPuntoVendita.IProdottiPuntoVenditaDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
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
        IProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();
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

        st=puntoVenditaDAO.add(p); //1
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV);
        m.setPuntoVendita(p);
        st+=magazzinoDAO.add(m); //2
        m=magazzinoDAO.findByShopID(p.getIdPuntoVendita());
        if (!utentiPuntoVenditaDAO.isUserShopManagerSomewhere(manager.getIdUtente())){
            st+=utentiPuntoVenditaDAO.add(manager, p, 0, 1, 0); //3
        } else {
            st+=utentiPuntoVenditaDAO.updateManager(manager, p, true);
        }
        for (IProdotto pr:prodotti){
            st+= prodottiPuntoVenditaDAO.add(p, pr);
        }
        for (Servizio s:servizi){
            st+= serviziPuntoVenditaDAO.add(s, p);
        }

        for (IProdotto pr:prodotti){
            Disponibilita d = new Disponibilita();
            d.setMagazzino(m);
            d.setProdotto(pr);
            d.setQta(0);
            st+=prodottiMagazzinoDAO.add(d);
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
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

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

        st=puntoVenditaDAO.update(p); //1
        p = puntoVenditaDAO.findByAddress(cittaPV, viaPV);
        m.setPuntoVendita(p);
        st+=magazzinoDAO.update(m); //2
        m=magazzinoDAO.findByShopID(p.getIdPuntoVendita());

        st+=utentiPuntoVenditaDAO.updateManager(manager, p, false); //3
        prodottiPuntoVenditaDAO.removeAllPrductsByShopID(p.getIdPuntoVendita());
        for (IProdotto pr:prodotti){
            st+=prodottiPuntoVenditaDAO.add(p, pr); //3+prodotti.size()
        }

        /* Costruisco arraylist create dalle intersezioni e opero su queste per mantenere i dati che mi servono nel db e rimuovere/aggiungere gli altri */
        ArrayList<Integer> idProdottiAttuali = new ArrayList<>();
        for (Disponibilita d:prodottiMagazzinoDAO.findAllProductsByWarehouseID(m.getIdMagazzino())){
            idProdottiAttuali.add(d.getProdotto().getIdProdotto());
        }

        ArrayList<Integer> idProdottiNuovi = new ArrayList<>();
        for (IProdotto prod:prodotti){
            idProdottiNuovi.add(prod.getIdProdotto());
        }

        ArrayList<Integer> backup = new ArrayList<>(idProdottiNuovi);
        idProdottiNuovi.removeAll(idProdottiAttuali);
        for (int i:idProdottiNuovi){
            Disponibilita d = new Disponibilita();
            d.setProdotto(prodottoDAO.findByID(i));
            d.setQta(0);
            d.setMagazzino(m);
            prodottiMagazzinoDAO.add(d);
        }

        idProdottiNuovi = new ArrayList<>(backup);
        idProdottiAttuali.removeAll(idProdottiNuovi);
        for (int i:idProdottiAttuali){
            Disponibilita d = new Disponibilita();
            d.setProdotto(prodottoDAO.findByID(i));
            d.setMagazzino(m);
            prodottiMagazzinoDAO.remove(d);
        }

        serviziPuntoVenditaDAO.removeAllServicesByShopID(p.getIdPuntoVendita());
        for (Servizio s:servizi){
            st+= serviziPuntoVenditaDAO.add(s, p); //3+prodotti.size()+servizi.size()
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

    public PuntoVenditaResponse findShopByManagerID(int idManager){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        PuntoVenditaResponse res = new PuntoVenditaResponse();
        res.setMessage("Errore non definito");
        res.setPuntoVendita(utentiPuntoVenditaDAO.findShopByShopManagerID(idManager));

        if (res.getPuntoVendita() != null){
            res.setMessage("Punto vendita trovato con successo");
        } else
        {
            res.setMessage("Errore durante la ricerca del punto vendita");
            return res;
        }

        return res;
    }
}
