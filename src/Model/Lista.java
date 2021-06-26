package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Lista {

    private int idLista;
    private String nomeLista; //da aggiungere al db
    private LocalDate dataCreazione;
    private HashMap<IProdotto, String> prodotti = new HashMap<>();
    private ArrayList<Servizio> servizi = new ArrayList<>();
    public enum Stato {PAGATA, NON_PAGATA}
    private Stato stato;
    private float prezzoTotale;
    private int idUtente;

    public Lista(int idLista, String nomeLista, LocalDate dataCreazione, HashMap<IProdotto, String> prodotti, ArrayList<Servizio> servizi, Stato stato, float prezzoTotale, int idUtente) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
        this.dataCreazione = dataCreazione;
        this.prodotti = prodotti;
        this.servizi = servizi;
        this.stato = stato;
        this.prezzoTotale = prezzoTotale;
        this.idUtente = idUtente;
    }

    public Lista () {
        this.nomeLista="";
        this.dataCreazione=LocalDate.of(1970, 1, 1);
        this.prodotti = null;
        this.servizi = null;
        this.prezzoTotale = 0;
        this.stato = Stato.NON_PAGATA;
        this.idUtente = -1;
    }

    public Lista(String nomeLista){
        this.nomeLista = nomeLista;
        this.dataCreazione = LocalDate.now();
        this.stato = Stato.NON_PAGATA;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void setProdotti(HashMap<IProdotto, String> prodotti) {
        this.prodotti = prodotti;
    }

    public ArrayList<Servizio> getServizi() {
        return servizi;
    }

    public void setServizi(ArrayList<Servizio> servizi) {
        this.servizi = servizi;
    }

    public void aggiungiProdotto (IProdotto prodotto, String prenotato){
        this.prodotti.put(prodotto, prenotato);
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public HashMap<IProdotto, String> getProdotti() {
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

    public float getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(float prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
}
