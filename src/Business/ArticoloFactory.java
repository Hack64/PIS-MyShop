package Business;

import Model.Articolo;
import Model.ICategoria;

public interface ArticoloFactory {

    Articolo crea();
    ICategoria creaCategoria();

}
