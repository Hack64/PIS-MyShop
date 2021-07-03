package Business;

import DAO.Prodotto.ProdottoDAO;
import Model.ProdottoResponse;

public class ProdottoBusiness {
    private static ProdottoBusiness instance;

    public static synchronized ProdottoBusiness getInstance() {
        if(instance == null) instance = new ProdottoBusiness();
        return instance;
    }

    private ProdottoBusiness() {}

    public ProdottoResponse find(int idProdotto){
        ProdottoResponse res = new ProdottoResponse();
        res.setMessage("Errore non definito");

        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        if (!prodottoDAO.productExists(idProdotto)){
            res.setMessage("Prodotto non trovato");
            return res;
        }

        res.setProdotto(prodottoDAO.findByID(idProdotto));
        res.setMessage("Prodotto trovato con successo");

        return res;
    }
}
