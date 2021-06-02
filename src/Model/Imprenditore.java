package Model;

import java.util.ArrayList;
import java.util.List;

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
}
