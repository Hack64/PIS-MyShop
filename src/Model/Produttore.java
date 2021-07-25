package Model;

public class Produttore extends Imprenditore  {

    private int idProduttore;

    public Produttore(String nome, String sito, String citta, String nazione) {
        super(nome, sito, citta, nazione);
    }

    public Produttore() {
        super();
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

}
