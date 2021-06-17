package Model;

import java.util.ArrayList;

public class Fornitore extends Imprenditore{

    private int idFornitore;

    private ArrayList<Servizio> serviziFornitore = new ArrayList<>();

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

    public ArrayList<Servizio> getServiziFornitore() {
        return serviziFornitore;
    }

    public void setServiziFornitore(ArrayList<Servizio> serviziFornitore) {
        this.serviziFornitore = serviziFornitore;
    }

    public int getIdFornitore() {
        return idFornitore;
    }

    public void setIdFornitore(int idFornitore) {
        this.idFornitore = idFornitore;
    }
}
