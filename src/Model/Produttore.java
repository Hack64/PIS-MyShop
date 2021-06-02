package Model;

import java.util.ArrayList;
import java.util.List;

public class Produttore {

    private List<Prodotto> prodottiProduttore = new ArrayList<>();

    public List<Prodotto> getProdottiProduttore() {
        return prodottiProduttore;
    }

    public void setProdottiProduttore(List<Prodotto> prodottiProduttore) {
        this.prodottiProduttore = prodottiProduttore;
    }

    public void addProdottoProduttore(Prodotto prodotto){
        prodottiProduttore.add(prodotto);
    }
}
