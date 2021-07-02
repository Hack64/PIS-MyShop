package Model;

public class Servizio extends Articolo {

    private int idServizio;
    private ICategoria categoria;
    private Fornitore fornitore;

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = this.fornitore;
    }

    public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }
}
