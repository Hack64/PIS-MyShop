package Business;

import Model.Articolo;
import Model.CategoriaProdotto;
import Model.ICategoria;
import Model.ProdottoComposito;

public class ProdottoCompositoFactory implements ArticoloFactory {
    @Override
    public Articolo crea() {
        return new ProdottoComposito();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
