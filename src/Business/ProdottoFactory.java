package Business;

import Model.*;

public class ProdottoFactory implements ArticoloFactory {

    @Override
    public Articolo crea() {
        return new Prodotto();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
