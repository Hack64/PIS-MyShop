package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lista {

    private String nomeLista; //da aggiungere al db
    private final LocalDate dataCreazione;
    private List<IProdotto> prodotti = new ArrayList<>();
    private enum Stato {PAGATA, NON_PAGATA}
    private Stato stato = Stato.NON_PAGATA;

    public Lista(String nomeLista, LocalDate dataCreazione, List<IProdotto> prodotti) {
        this.nomeLista = nomeLista;
        this.dataCreazione = dataCreazione;
        this.prodotti = prodotti;
    }

    public Lista () {
        this.nomeLista="";
        this.dataCreazione=LocalDate.of(1970, 1, 1);
    }

    public Lista(String nomeLista){
        this.nomeLista = nomeLista;
        this.dataCreazione = LocalDate.now();
    }

    public void aggiungiProdotto (Prodotto prodotto){
        this.prodotti.add(prodotto);
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public List<IProdotto> getProdotti() {
        return prodotti;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
}
