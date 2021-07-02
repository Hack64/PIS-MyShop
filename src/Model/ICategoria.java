package Model;

import java.util.ArrayList;

public interface ICategoria {
    int getIdCategoria();
    String getNome();
    ICategoria getCategoriaPadre();
    ArrayList<ICategoria> getSottoCategorie();
}
