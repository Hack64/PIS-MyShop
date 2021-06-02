package Business;

import Model.Articolo;
import Model.ICategoria;
import Model.Prodotto;

public interface ArticoloFactory {

    Articolo crea();
    ICategoria creaCategoria();

}
