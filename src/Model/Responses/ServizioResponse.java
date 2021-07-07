package Model.Responses;

import Model.Servizio;

public class ServizioResponse {

    String message;
    Servizio servizio;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }
}
