package Business;

import DAO.Fornitore.FornitoreDAO;
import Model.Fornitore;
import Model.Responses.FornitoreResponse;

import java.util.ArrayList;

public class FornitoreBusiness {
    private static FornitoreBusiness instance;
    private FornitoreDAO fornitoreDAO;

    public static synchronized FornitoreBusiness getInstance() {
        if(instance == null) instance = new FornitoreBusiness();
        return instance;
    }

    private FornitoreBusiness() {}

    public ArrayList<Fornitore> findAllSuppliers(){
        fornitoreDAO = FornitoreDAO.getInstance();

        return fornitoreDAO.findAll();
    }

    public ArrayList<String> findAllSuppliersNames(){
        fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<String> nomi = new ArrayList<>();

        for (Fornitore f:fornitoreDAO.findAll()){
            nomi.add(f.getNome());
        }

        return nomi;
    }

    public FornitoreResponse findByName(String nome){
        fornitoreDAO = FornitoreDAO.getInstance();
        FornitoreResponse res = new FornitoreResponse();
        res.setMessage("Errore non definito");

        Fornitore f = fornitoreDAO.getByName(nome);
        if (f!=null){
            res.setMessage("Fornitore trovato con successo");
            res.setFornitore(f);
        } else {
            res.setMessage("Errore durante la ricerca del fornitore");
        }
        return res;
    }

    public FornitoreResponse findByID(int idFornitore){
        fornitoreDAO = FornitoreDAO.getInstance();
        FornitoreResponse res = new FornitoreResponse();
        res.setMessage("Errore non definito");

        Fornitore f = fornitoreDAO.findByID(idFornitore);
        if (f!=null){
            res.setMessage("Fornitore trovato con successo");
            res.setFornitore(f);
        } else {
            res.setMessage("Errore durante la ricerca del fornitore");
        }
        return res;
    }

    public int addNewSupplier(String nome, String sito, String citta, String nazione){
        fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore f = new Fornitore();

        f.setNome(nome);
        f.setSito(sito);
        f.setCitta(citta);
        f.setNazione(nazione);

        return fornitoreDAO.add(f);
    }

    public int update(int idFornitore, String nome, String sito, String citta, String nazione){
        fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore f = new Fornitore();

        f.setIdFornitore(idFornitore);
        f.setNome(nome);
        f.setSito(sito);
        f.setCitta(citta);
        f.setNazione(nazione);

        return fornitoreDAO.update(f);
    }

    public int deleteByID(int idProduttore){
        fornitoreDAO = FornitoreDAO.getInstance();

        return fornitoreDAO.removeById(idProduttore);
    }
}
