package Model;

import java.util.ArrayList;
import java.util.List;

public class Prodotto extends Articolo implements IProdotto {

    private int idProdotto;
    private ArrayList<? super Categoria> categorie = new ArrayList<>();
    private int idProduttore;

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public ArrayList<? super Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<? super Categoria> categorie) {
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
