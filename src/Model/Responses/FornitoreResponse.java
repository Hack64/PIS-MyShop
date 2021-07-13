package Model.Responses;

import Model.Fornitore;

public class FornitoreResponse {

    String message;
    Fornitore fornitore;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }
}
