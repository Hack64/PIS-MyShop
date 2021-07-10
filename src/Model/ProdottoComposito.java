package Model;

import java.util.ArrayList;

public class ProdottoComposito extends Articolo implements IProdotto {

    private ArrayList<IProdotto> sottoprodotti = new ArrayList<>();
    private ArrayList<ICategoria> categorie = new ArrayList<>();
    private int idProdotto;
    private Produttore produttore;

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

    public ArrayList<IProdotto> getSottoprodotti() {
        return sottoprodotti;
    }

    public void setSottoprodotti(ArrayList<IProdotto> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }

    public void addSottoprodotto(IProdotto prodotto){
        //if (prodotto == this) return;
        sottoprodotti.add(prodotto);
    }

    @Override
    public float getCosto() {
        return this.costo;
    }

}
