package Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private List<IProdotto> sottoprodotti = new ArrayList<>();

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
