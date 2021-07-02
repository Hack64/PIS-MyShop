package Business;

import Model.Articolo;
import Model.Servizio;
import Model.Categoria;
import Model.CategoriaServizio;

public class ServizioFactory implements ArticoloFactory{


    @Override
    public Articolo crea() {
        return new Servizio();
    }

    @Override
    public Categoria creaCategoria() {
        return new CategoriaServizio();
    }
}