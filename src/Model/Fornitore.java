package Model;

import java.util.ArrayList;
import java.util.List;

public class Fornitore extends Imprenditore{

    private List<Servizio> serviziFornitore = new ArrayList<>();

    public Fornitore(String nome, String sito, String citta, String nazione, ArrayList<Servizio> serviziFornitore) {
        super(nome, sito, citta, nazione);
        this.serviziFornitore = serviziFornitore;
    }

    public Fornitore() {
        super();
    }

    @Override
    public void addArticoloLista(Articolo art) {
        serviziFornitore.add((Servizio)art);
    }
}
