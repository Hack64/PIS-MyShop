package Model;

import java.time.LocalDate;

public class Risposta {

    private int idRisposta;
    private LocalDate dataCreazione;
    private String testo;
    private int idUtente;
    private int idFeedback;

    public Risposta(int idRisposta, LocalDate dataCreazione, String testo, int idUtente, int idFeedback) {
        this.idRisposta = idRisposta;
        this.dataCreazione = dataCreazione;
        this.testo = testo;
        this.idUtente = idUtente;
        this.idFeedback = idFeedback;
    }

    public Risposta(){
        this.idRisposta = -1;
        this.dataCreazione = null;
        this.testo = null;
        this.idUtente = -1;
        this.idFeedback = -1;
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

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }
}
