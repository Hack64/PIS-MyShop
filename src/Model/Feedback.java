package Model;

import java.time.LocalDate;

public class Feedback {

    private int idFeedback;
    private LocalDate dataCreazione;
    private String commento;
    private int valutazione;
    private Utente utente;
    private IProdotto prodotto;
    private Servizio servizio;

    public Feedback(int idFeedback, LocalDate dataCreazione, String commento, int valutazione, Utente utente, IProdotto prodotto, Servizio servizio) {
        this.idFeedback = idFeedback;
        this.dataCreazione = dataCreazione;
        this.commento = commento;
        this.valutazione = valutazione;
        this.utente = utente;
        this.prodotto = prodotto;
        this.servizio = servizio;
    }

    public Feedback(){
        this.idFeedback = -1;
        this.dataCreazione = null;
        this.commento = "";
        this.valutazione = -1;
        this.utente = null;
        this.prodotto = null;
        this.servizio = null;
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public Utente getUtente() {
        return this.utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }
}
