package Model;

public abstract class Imprenditore {

    protected String nome;
    protected String sito;
    protected String citta;
    protected String nazione;

    public Imprenditore(String nome, String sito, String citta, String nazione) {
        this.nome = nome;
        this.sito = sito;
        this.citta = citta;
        this.nazione = nazione;
    }

    public Imprenditore () {
        this.nome = "";
        this.sito = "";
        this.citta = "";
        this.nazione = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
}
