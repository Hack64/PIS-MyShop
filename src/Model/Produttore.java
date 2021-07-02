package Model;

import java.util.ArrayList;


public class Produttore extends Imprenditore  {

    private int idProduttore;
    private ArrayList<IProdotto> prodottiProduttore = new ArrayList<>();

    public Produttore(String nome, String sito, String citta, String nazione, ArrayList<IProdotto> prodottiProduttore) {
        super(nome, sito, citta, nazione);
        this.prodottiProduttore = prodottiProduttore;
    }

    public Produttore() {
        super();
    }

    @Override
    public void addArticoloLista(Articolo art) {
        prodottiProduttore.add((IProdotto) art);
    }

    public ArrayList<IProdotto> getProdottiProduttore() {
        return prodottiProduttore;
    }

    public void setProdottiProduttore(ArrayList<IProdotto> prodottiProduttore) {
        this.prodottiProduttore = prodottiProduttore;
    }

    public void addListToProdottiProduttore(ArrayList<IProdotto> prodottiProduttore){
        this.prodottiProduttore.addAll(prodottiProduttore);
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

}
