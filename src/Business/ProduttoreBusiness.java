package Business;

import DAO.Produttore.ProduttoreDAO;
import Model.Produttore;
import Model.Responses.ProduttoreResponse;

import java.util.ArrayList;

public class ProduttoreBusiness {
    private static ProduttoreBusiness instance;
    private ProduttoreDAO produttoreDAO;

    public static synchronized ProduttoreBusiness getInstance() {
        if(instance == null) instance = new ProduttoreBusiness();
        return instance;
    }

    private ProduttoreBusiness() {}

    public ArrayList<Produttore> findAllProducers(){
        produttoreDAO = ProduttoreDAO.getInstance();

        return produttoreDAO.findAll();
    }

    public ArrayList<String> findAllProducersNames(){
        produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<String> nomi = new ArrayList<>();

        for (Produttore p:produttoreDAO.findAll()){
            nomi.add(p.getNome());
        }

        return nomi;
    }

    public ProduttoreResponse findByName(String nome){
        produttoreDAO = ProduttoreDAO.getInstance();
        ProduttoreResponse res = new ProduttoreResponse();
        res.setMessage("Errore non definito");

        Produttore p = produttoreDAO.getByName(nome);
        if (p!=null){
            res.setMessage("Produttore trovato con successo");
            res.setProduttore(p);
        } else {
            res.setMessage("Errore durante la ricerca del produttore");
        }
        return res;
    }

    public ProduttoreResponse findByID(int idProduttore){
        produttoreDAO = ProduttoreDAO.getInstance();
        ProduttoreResponse res = new ProduttoreResponse();
        res.setMessage("Errore non definito");

        Produttore p = produttoreDAO.findByID(idProduttore);
        if (p!=null){
            res.setMessage("Produttore trovato con successo");
            res.setProduttore(p);
        } else {
            res.setMessage("Errore durante la ricerca del produttore");
        }
        return res;
    }

    public int addNewProducer(String nome, String sito, String citta, String nazione){
        produttoreDAO = ProduttoreDAO.getInstance();
        Produttore p = new Produttore();
        p.setNome(nome);
        p.setSito(sito);
        p.setCitta(citta);
        p.setNazione(nazione);

        return produttoreDAO.add(p);
    }

    public int update(int idProduttore, String nome, String sito, String citta, String nazione){
        produttoreDAO = ProduttoreDAO.getInstance();
        Produttore p = new Produttore();

        p.setIdProduttore(idProduttore);
        p.setNome(nome);
        p.setSito(sito);
        p.setCitta(citta);
        p.setNazione(nazione);

        return produttoreDAO.update(p);
    }

    public int deleteByID(int idProduttore){
        produttoreDAO = ProduttoreDAO.getInstance();

        return produttoreDAO.removeById(idProduttore);
    }
}
