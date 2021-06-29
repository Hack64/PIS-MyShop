package Model;

public class Disponibilita /*Questa classe sarebbe prodottiMagazzino nel db*/ {

    private int idProdottoMagazzino;
    private int idMagazzino;
    private int qta;
    private Posizione posizione;
    private Prodotto prodotto;

    public Disponibilita(int idProdottoMagazzino, int idMagazzino, int qta, Posizione posizione, Prodotto prodotto) {
        this.idProdottoMagazzino = idProdottoMagazzino;
        this.idMagazzino = idMagazzino;
        this.qta = qta;
        this.posizione = posizione;
        this.prodotto = prodotto;
    }

    public Disponibilita() {
        this.qta = -1;
        this.idMagazzino = -1;
        this.idProdottoMagazzino = -1;
        this.posizione = null;
        this.prodotto = null;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getIdProdottoMagazzino() {
        return idProdottoMagazzino;
    }

    public void setIdProdottoMagazzino(int idProdottoMagazzino) {
        this.idProdottoMagazzino = idProdottoMagazzino;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }
}
