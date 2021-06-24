package Model;

import java.time.LocalDate;

public class Feedback {

    private int idFeedback;
    private LocalDate dataCreazione;
    private String commento;
    private int valutazione;
    private int idUtente;
    private int idProdotto;
    private int idServizio;

    public Feedback(int idFeedback, LocalDate dataCreazione, String commento, int valutazione, int idUtente, int idProdotto, int idServizio) {
        this.idFeedback = idFeedback;
        this.dataCreazione = dataCreazione;
        this.commento = commento;
        this.valutazione = valutazione;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.idServizio = idServizio;
    }

    public Feedback(){
        this.idFeedback = -1;
        this.dataCreazione = null;
        this.commento = "";
        this.valutazione = -1;
        this.idUtente = -1;
        this.idProdotto = -1;
        this.idServizio = -1;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }
}
