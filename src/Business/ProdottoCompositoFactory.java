package Business;

import Model.*;

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
