package Business;

import Model.Articolo;
import Model.Categoria;
import Model.CategoriaProdotto;
import Model.Prodotto;

public class ProdottoFactory implements ArticoloFactory {

    @Override
    public Articolo crea() {
        return new Prodotto();
    }

    @Override
    public Categoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
