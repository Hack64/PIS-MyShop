package Model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private int idProdotto;
    private List<CategoriaProdotto> categorie = new ArrayList<>();
    private int idProduttore;

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

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

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    @Override
    public float getCosto() {
        return this.costo;
    }
}
