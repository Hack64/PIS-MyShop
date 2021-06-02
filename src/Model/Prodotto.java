package Model;

public class Prodotto extends Articolo {

    private int idProdotto;
    private Categoria categoria;
    private Posizione posizione;
    private Produttore produttore;

    public int getIdProdotto() {
        return idProdotto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }
}
