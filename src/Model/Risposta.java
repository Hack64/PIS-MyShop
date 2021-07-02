package Model;

import java.time.LocalDate;

public class Risposta {

    private int idRisposta;
    private LocalDate dataCreazione;
    private String testo;
    private Utente utente;
    private Feedback feedback;

    public Risposta(int idRisposta, LocalDate dataCreazione, String testo, Utente utente, Feedback feedback) {
        this.idRisposta = idRisposta;
        this.dataCreazione = dataCreazione;
        this.testo = testo;
        this.utente = utente;
        this.feedback = feedback;
    }

    public Risposta(){
        this.idRisposta = -1;
        this.dataCreazione = null;
        this.testo = null;
        this.utente = null;
        this.feedback = null;
    }

    public int getIdRisposta() {
        return idRisposta;
    }

    public void setIdRisposta(int idRisposta) {
        this.idRisposta = idRisposta;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
