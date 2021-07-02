package Model;

import java.util.ArrayList;

public class CategoriaServizio extends Categoria implements ICategoria{

    @Override
    public ArrayList<ICategoria> getSottoCategorie() {
        return null;
    }
}
