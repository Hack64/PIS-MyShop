package Business;

import DAO.Prodotto.ProdottoDAO;
import Model.Articolo;
import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;
import Model.Responses.ProdottoResponse;

import java.io.File;
import java.util.ArrayList;

public class ProdottoBusiness {
    private static ProdottoBusiness instance;
    private ProdottoDAO prodottoDAO;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {}

    public ProdottoResponse find(int idProdotto){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoResponse res = new ProdottoResponse();
        res.setMessage("Errore non definito");

        if (!prodottoDAO.productExists(idProdotto)){
            res.setMessage("Prodotto non trovato");
            return res;
        }

        res.setProdotto(prodottoDAO.findByID(idProdotto));
        res.setMessage("Prodotto trovato con successo");

        return res;
    }

    public ArrayList<IProdotto> findAllProducts(){
        prodottoDAO = ProdottoDAO.getInstance();

        return prodottoDAO.findAll();
    }

    public int deleteByID(int id){
        prodottoDAO = ProdottoDAO.getInstance();

        return prodottoDAO.removeById(id);
    }

    public int addNew(String nome, File immagine, String descrizione, float costo, Produttore produttore){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoFactory prodottoFactory = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Prodotto p = prodottoFactory.crea();
        p.setNome(nome);
        p.setImmagine(immagine);
        p.setDescrizione(descrizione);
        p.setCosto(costo);
        p.setProduttore(produttore);
        /*MODIFICA QUALCOSA*/
        p.setNumeroCommenti(0);
        p.setMediaValutazione(0);

        return prodottoDAO.add(p);
    }

    public int update(String nome, File immagine, String descrizione, float costo, Produttore produttore, int idProdotto){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoFactory prodottoFactory = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Prodotto p = prodottoFactory.crea();
        p.setIdProdotto(idProdotto);
        p.setNome(nome);
        p.setImmagine(immagine);
        p.setDescrizione(descrizione);
        p.setCosto(costo);
        p.setProduttore(produttore);
        /*MODIFICA QUALCOSA*/
        p.setNumeroCommenti(0);
        p.setMediaValutazione(0);

        return prodottoDAO.update(p);
    }
}
