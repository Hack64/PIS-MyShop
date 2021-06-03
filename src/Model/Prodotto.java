package Model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private int idProdotto;
    private List<CategoriaProdotto> categorie = new ArrayList<>();
    private Produttore produttore;

    public int getIdProdotto() {
        return idProdotto;
    }

    public List<CategoriaProdotto> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CategoriaProdotto> categorie) {
        this.categorie = categorie;
    }

    public void addCategoria(CategoriaProdotto categoria){
        categorie.add(categoria);
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    @Override
    public float getCosto() {
        return this.costo;
    }
}
