package Model;

public class Servizio extends Articolo {

    private int idServizio;
    private CategoriaServizio categoria;
    private Fornitore fornitore;

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public CategoriaServizio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaServizio categoria) {
        this.categoria = categoria;
    }
}
