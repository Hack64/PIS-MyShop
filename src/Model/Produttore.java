package Model;

import java.util.ArrayList;
import java.util.List;

public class Produttore extends Imprenditore  {

    private int idProduttore;
    private List<IProdotto> prodottiProduttore;

    public Produttore(String nome, String sito, String citta, String nazione, ArrayList<IProdotto> prodottiProduttore) {
        super(nome, sito, citta, nazione);
        this.prodottiProduttore = prodottiProduttore;
    }

    public Produttore() {
        super();
    }

    @Override
    public void addArticoloLista(Articolo art) {
        prodottiProduttore.add((Prodotto) art);
    }

    public List<IProdotto> getProdottiProduttore() {
        return prodottiProduttore;
    }

    public void setProdottiProduttore(List<IProdotto> prodottiProduttore) {
        this.prodottiProduttore = prodottiProduttore;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

}
