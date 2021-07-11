package Model;

import java.util.ArrayList;

public class Prodotto extends Articolo implements IProdotto {

    private int idProdotto;
    private ArrayList<ICategoria> categorie = new ArrayList<>();
    private Produttore produttore;
    private int idProduttore;

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public ArrayList<ICategoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<ICategoria> categorie) {
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

    @Override
    public ArrayList<IProdotto> getSottoprodotti() {
        return null;
    }
}
