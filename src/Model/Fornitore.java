package Model;

public class Fornitore extends Imprenditore{

    private int idFornitore;

    public Fornitore(String nome, String sito, String citta, String nazione) {
        super(nome, sito, citta, nazione);
    }

    public Fornitore() {
        super();
    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }
}
