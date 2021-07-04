package Business;

import DAO.Prodotto.ProdottoDAO;
import Model.IProdotto;
import Model.Responses.ProdottoResponse;

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
}
