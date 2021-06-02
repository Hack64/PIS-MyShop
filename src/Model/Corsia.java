package Model;

import java.util.ArrayList;
import java.util.List;

public class Corsia {

    private int numeroCorsia;
    private List<Scaffale> scaffali = new ArrayList<>();

    public List<IProdotto> getProdottiCorsia() {
        ArrayList<IProdotto> prodottiCorsia = new ArrayList<>();
        for (Scaffale s:scaffali){
            prodottiCorsia.add(s.getProdotto());
        }
        return prodottiCorsia;
    }
}
