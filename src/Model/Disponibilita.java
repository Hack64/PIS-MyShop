package Model;

public class Disponibilita /*Questa classe sarebbe prodottiMagazzino nel db*/ {

    private int idMagazzino;
    private int idProdotto;
    private int qta;
    private Posizione posizione;

    public Disponibilita(int idMagazzino, int qta, Posizione posizione, int idProdotto) {
        this.idMagazzino = idMagazzino;
        this.qta = qta;
        this.posizione = posizione;
        this.idProdotto = idProdotto;
    }

    public Disponibilita() {
        this.qta = -1;
        this.idMagazzino = -1;
        this.posizione = null;
        this.idProdotto = -1;
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

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }
}
