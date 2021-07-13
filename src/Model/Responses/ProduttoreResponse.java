package Model.Responses;

import Model.Produttore;

public class ProduttoreResponse {

    String message;
    Produttore produttore;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }
}
