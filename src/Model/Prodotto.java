package Model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private int idProdotto;
    private List<CategoriaProdotto> categorie = new ArrayList<>();
    private Posizione posizione;
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

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
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
