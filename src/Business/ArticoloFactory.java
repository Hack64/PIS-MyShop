package Business;

import Model.Articolo;
import Model.Categoria;

public interface ArticoloFactory {

    Articolo crea();
    Categoria creaCategoria();

}
