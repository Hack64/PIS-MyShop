package Model;

public abstract class Imprenditore {

    protected String nome;
    protected String sito;
    protected String citta;
    protected String nazione;
    //protected List<Articolo> articoliImprenditore = new ArrayList<>();

    public Imprenditore(String nome, String sito, String citta, String nazione) {
        this.nome = nome;
        this.sito = sito;
        this.citta = citta;
        this.nazione = nazione;
        //this.articoliImprenditore = articoliImprenditore;
    }

    public Imprenditore () {
        this.nome = "";
        this.sito = "";
        this.citta = "";
        this.nazione = "";
    }

    public abstract void addArticoloLista(Articolo art);

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
