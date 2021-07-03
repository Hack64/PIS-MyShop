package Model;

public class ProdottoResponse {

    String message;
    IProdotto prodotto;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }
}
