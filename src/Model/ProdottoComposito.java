package Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private ArrayList<IProdotto> sottoprodotti = new ArrayList<>();
    private ArrayList<? super Categoria> categorie = new ArrayList<>();
    private int idProdotto;
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

    public ArrayList<IProdotto> getSottoprodotti() {
        return sottoprodotti;
    }

    public void setSottoprodotti(ArrayList<IProdotto> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }

    public void addSottoprodotto(IProdotto prodotto){
        if (prodotto == this) return;
        sottoprodotti.add(prodotto);
    }

    @Override
    public float getCosto() {
        float prezzoFinale=0;
        for (IProdotto p:sottoprodotti){
            prezzoFinale+=p.getCosto();
        }
        return prezzoFinale;
    }
}
