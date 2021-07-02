package Business;

import Model.Articolo;
import Model.Categoria;
import Model.CategoriaServizio;
import Model.Servizio;

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