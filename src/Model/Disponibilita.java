package Model;

public class Disponibilita /*Questa classe sarebbe prodottiMagazzino nel db*/ {

    private Magazzino magazzino;
    private IProdotto prodotto;
    private int qta;
    private Posizione posizione;

    public Disponibilita(Magazzino magazzino, int qta, Posizione posizione, IProdotto prodotto) {
        this.magazzino = magazzino;
        this.qta = qta;
        this.posizione = posizione;
        this.prodotto = prodotto;
    }

    public Disponibilita() {
        this.qta = -1;
        this.magazzino = null;
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

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
}
