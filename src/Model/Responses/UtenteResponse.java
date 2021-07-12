package Model.Responses;

import Model.Utente;

public class UtenteResponse {

    String message;
    Utente utente;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}