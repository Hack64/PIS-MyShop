package Model;

import java.util.ArrayList;
import java.util.List;

public class Fornitore {

    private String nome;
    private String sito;
    private String citta;
    private String nazione;
    private List<Servizio> serviziFornitore = new ArrayList<>();

    public List<Servizio> getServiziFornitore() {
        return serviziFornitore;
    }

    public void setServiziFornitore(List<Servizio> serviziFornitore) {
        this.serviziFornitore = serviziFornitore;
    }

    public void addServzioFornitore(Servizio servizio){
        serviziFornitore.add(servizio);
    }
}
