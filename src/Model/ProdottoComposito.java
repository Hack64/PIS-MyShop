package Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Prodotto {

    private ArrayList<IProdotto> sottoprodotti = new ArrayList<>();

    public ArrayList<IProdotto> getSottoprodotti() {
        return sottoprodotti;
    }

    public void setSottoprodotti(ArrayList<IProdotto> sottoprodotti) {
        this.sottoprodotti = sottoprodotti;
    }

    public void addSottoprodotto(IProdotto prodotto){
        if (prodotto == this) return;
        sottoprodotti.add(prodotto);
    }

    @Override
    public float getCosto() {
        float prezzoFinale=0;
        for (IProdotto p:sottoprodotti){
            prezzoFinale+=p.getCosto();
        }
        return prezzoFinale;
    }
}
