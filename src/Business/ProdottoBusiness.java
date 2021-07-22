package Business;

import DAO.ComposizioneProdotto.ComposizioneProdottoDAO;
import DAO.ComposizioneProdotto.IComposizioneProdottoDAO;
import DAO.Posizione.IPosizioneDAO;
import DAO.Posizione.PosizioneDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import Model.*;
import Model.Responses.ProdottoResponse;

import java.io.File;
import java.util.ArrayList;

public class ProdottoBusiness {
    private static ProdottoBusiness instance;
    private IProdottoDAO prodottoDAO;
    private IPosizioneDAO posizioneDAO;
    private IComposizioneProdottoDAO composizioneProdottoDAO;

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

    public ArrayList<IProdotto> findAllProductsByShop(PuntoVendita puntoVendita){
        ProdottiPuntoVenditaDAO prodottiPuntoVenditaDAO = ProdottiPuntoVenditaDAO.getInstance();

        return prodottiPuntoVenditaDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita());
    }

    public int deleteByID(int id){
        prodottoDAO = ProdottoDAO.getInstance();
        IProdotto p = prodottoDAO.findByID(id);
        if (p != null){
            p.getImmagine().delete();
        }

        return prodottoDAO.removeById(id);
    }

    public int addNew(String nome, File immagine, String descrizione, float costo, Produttore produttore, ArrayList<ICategoria> categorie, int scaffale, int corsia ){
        prodottoDAO = ProdottoDAO.getInstance();
        posizioneDAO = PosizioneDAO.getInstance();
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
        int rowCount = prodottoDAO.add(p); //1
        IProdotto p2 = prodottoDAO.getByName(nome);
        if (rowCount==1) {
            rowCount+=CategoriaBusiness.getInstance().addCategoriesToProduct(p2, categorie); //2
        }
        if (rowCount==2){
            Posizione posizione = new Posizione();
            posizione.setScaffale(scaffale);
            posizione.setCorsia(corsia);
            posizione.setProdotto(p2);
            rowCount+=posizioneDAO.add(posizione); //3
        }
        return rowCount;
    }

    public int addNewComp(String nome, File immagine, String descrizione, float costo, Produttore produttore, ArrayList<ICategoria> categorie, ArrayList<IProdotto> sottoProdotti, int scaffale, int corsia){
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
            Posizione posizione = new Posizione();
            posizione.setScaffale(scaffale);
            posizione.setCorsia(corsia);
            posizione.setProdotto(p);
            rowCount+=posizioneDAO.add(posizione);
        }
        return rowCount;
    }

    public int update(String nome, File immagine, String descrizione, float costo, int idProdotto, int scaffale, int corsia){
        prodottoDAO = ProdottoDAO.getInstance();
        posizioneDAO = PosizioneDAO.getInstance();
        IProdotto p1 = prodottoDAO.findByID(idProdotto);
        if (p1 != null && !immagine.getName().equals(p1.getImmagine().getName())){
            p1.getImmagine().delete();
        }

        ProdottoFactory prodottoFactory = (ProdottoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO);
        Prodotto p = (Prodotto) prodottoFactory.crea();
        p.setIdProdotto(idProdotto);
        p.setNome(nome);
        p.setImmagine(immagine);
        p.setDescrizione(descrizione);
        p.setCosto(costo);
        /*MODIFICA QUALCOSA*/
        p.setNumeroCommenti(0);
        p.setMediaValutazione(0);

        int rowCount = prodottoDAO.update(p);
        if (rowCount == 1) {
            Posizione posizione = new Posizione();
            posizione.setCorsia(corsia);
            posizione.setScaffale(scaffale);
            posizione.setProdotto(prodottoDAO.findByID(p.getIdProdotto()));

            rowCount+=posizioneDAO.update(posizione);
        }
        return rowCount;
    }

    public int updateComposite(String nome, File immagine, String descrizione, float costo, int idProdotto, ArrayList<IProdotto> sottoprodotti, int scaffale, int corsia){
        composizioneProdottoDAO = ComposizioneProdottoDAO.getInstance();
        prodottoDAO = ProdottoDAO.getInstance();


        IProdotto p1 = prodottoDAO.findByID(idProdotto);
        if (p1 != null && !immagine.getName().equals(p1.getImmagine().getName())){
            p1.getImmagine().delete();
        }

        ProdottoCompositoFactory prodottoCompositoFactory = (ProdottoCompositoFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.PRODOTTO_COMPOSITO);
        ProdottoComposito pc = (ProdottoComposito) prodottoCompositoFactory.crea();
        pc.setIdProdotto(idProdotto);
        pc.setNome(nome);
        pc.setImmagine(immagine);
        pc.setDescrizione(descrizione);
        pc.setCosto(costo);
        /*MODIFICA QUALCOSA*/
        pc.setNumeroCommenti(0);
        pc.setMediaValutazione(0);
        pc.setSottoprodotti(sottoprodotti);

        int st = 0;
        st+=prodottoDAO.update(pc);

        if (st==1){
            Posizione posizione = new Posizione();
            posizione.setScaffale(scaffale);
            posizione.setCorsia(corsia);
            posizione.setProdotto(pc);
            st+=posizioneDAO.add(posizione);
        }

        st += composizioneProdottoDAO.update(pc);

        return st;
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

    public int deleteCompositeByID(int idProdotto){
        composizioneProdottoDAO = ComposizioneProdottoDAO.getInstance();
        prodottoDAO = ProdottoDAO.getInstance();

        if (composizioneProdottoDAO.isCompositeProduct(idProdotto)){
            IProdotto p = composizioneProdottoDAO.findByID(idProdotto);
            if (p != null){
                p.getImmagine().delete();
            }
            return prodottoDAO.removeById(idProdotto);
        } else {
            return -1;
        }
    }
}
