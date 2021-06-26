package Business;

import Model.Articolo;
import Model.CategoriaProdotto;
import Model.ICategoria;
import Model.Servizio;

public class ServizioFactory implements ArticoloFactory{


    @Override
    public Articolo crea() {
        return new Servizio();
    }

    @Override
    public ICategoria creaCategoria() {
        //return new CategoriaProdotto();
        return null;
    }
}