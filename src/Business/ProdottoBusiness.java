package Business;

import DAO.ComposizioneProdotto.ComposizioneProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import Model.*;
import Model.Responses.ProdottoResponse;

import java.io.File;
import java.util.ArrayList;

public class ProdottoBusiness {
    private static ProdottoBusiness instance;
    private ProdottoDAO prodottoDAO;
    private ComposizioneProdottoDAO composizioneProdottoDAO;

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

    public ProdottoResponse findByName(String nomeProdotto){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoResponse res = new ProdottoResponse();
        res.setMessage("Errore non definito");

        if (!prodottoDAO.productExists(nomeProdotto)){
            res.setMessage("Prodotto non trovato");
            return res;
        }

        res.setProdotto(prodottoDAO.getByName(nomeProdotto));
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

    public int addNew(String nome, File immagine, String descrizione, float costo, Produttore produttore, ArrayList<ICategoria> categorie ){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoFactory prodottoFactory = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Prodotto p = (Prodotto) prodottoFactory.crea();
        p.setNome(nome);
        p.setImmagine(immagine);
        p.setDescrizione(descrizione);
        p.setCosto(costo);
        p.setProduttore(produttore);
        /*MODIFICA QUALCOSA*/
        p.setNumeroCommenti(0);
        p.setMediaValutazione(0);
        int rowCount = prodottoDAO.add(p);
        if (rowCount==1) {
            IProdotto p2 = prodottoDAO.getByName(nome);
            rowCount+=CategoriaBusiness.getInstance().addCategoriesToProduct(p2, categorie);

        }
        return rowCount;
    }

    public int addNewComp(String nome, File immagine, String descrizione, float costo, Produttore produttore, ArrayList<ICategoria> categorie, ArrayList<IProdotto> sottoProdotti){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoCompositoFactory prodottoCompositoFactory = (ProdottoCompositoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO_COMPOSITO);
        ProdottoComposito p = (ProdottoComposito) prodottoCompositoFactory.crea();
        p.setNome(nome);
        p.setImmagine(immagine);
        p.setDescrizione(descrizione);
        p.setCosto(costo);
        p.setProduttore(produttore);
        /*MODIFICA QUALCOSA*/
        p.setNumeroCommenti(0);
        p.setMediaValutazione(0);
        p.setSottoprodotti(sottoProdotti);
        int rowCount = prodottoDAO.add(p);
        IProdotto p2 = prodottoDAO.getByName(nome);
        if (rowCount==1) {
            rowCount+=CategoriaBusiness.getInstance().addCategoriesToProduct(p2, categorie);
        }
        if (rowCount==2){
            p.setIdProdotto(p2.getIdProdotto());
            rowCount+=this.addSubProductsToCompositeProduct(p);
        }
        return rowCount;
    }

    public int update(String nome, File immagine, String descrizione, float costo, Produttore produttore, int idProdotto){
        prodottoDAO = ProdottoDAO.getInstance();
        ProdottoFactory prodottoFactory = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Prodotto p = (Prodotto) prodottoFactory.crea();
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

    public ArrayList<IProdotto> findAllNonCompositeProducts() {
        prodottoDAO = ProdottoDAO.getInstance();
        composizioneProdottoDAO = ComposizioneProdottoDAO.getInstance();
        ArrayList<IProdotto> prodotti = prodottoDAO.findAll();
        ArrayList<IProdotto> prodottiNonCompositi = new ArrayList<>();
        ArrayList<IProdotto> prodottiCompositi = composizioneProdottoDAO.findAll();
        if (prodottiCompositi.isEmpty()){
            return prodotti;
        }
        for (IProdotto p:prodotti){
            if (!composizioneProdottoDAO.isCompositeProduct(p.getIdProdotto())){
                prodottiNonCompositi.add(p);
            }
        }
        return prodottiNonCompositi;
    }

    public ArrayList<IProdotto> findAllCompositeProducts(){
        composizioneProdottoDAO = ComposizioneProdottoDAO.getInstance();

        return composizioneProdottoDAO.findAll();
    }

    public int addSubProductsToCompositeProduct(IProdotto p){
        composizioneProdottoDAO = ComposizioneProdottoDAO.getInstance();

        return composizioneProdottoDAO.add(p);
    }
}
