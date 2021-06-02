package Business;

import Model.Articolo;
import Model.Categoria;
import Model.ICategoria;
import Model.Prodotto;

public class ProdottoFactory implements ArticoloFactory {

    @Override
    public Articolo crea() {
        return new Prodotto();
    }

    @Override
    public ICategoria creaCategoria() {
        return new Categoria();
    }
}
