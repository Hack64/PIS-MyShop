package Model;

import java.util.ArrayList;

public class CategoriaProdotto extends Categoria implements ICategoria {

    private ArrayList<ICategoria> sottoCategorie;

    public CategoriaProdotto(int idCategoria, String nome, ICategoria categoriaPadre, ArrayList<ICategoria> sottoCategorie) {
        super(idCategoria, nome, categoriaPadre);
        this.sottoCategorie = sottoCategorie;
    }

    public CategoriaProdotto(){
        super();
        this.sottoCategorie = null;
    }

    public CategoriaProdotto(ArrayList<ICategoria> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    public ArrayList<ICategoria> getSottoCategorie() {
        return sottoCategorie;
    }

    public void setSottoCategorie(ArrayList<ICategoria> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    public void addSottoCategoria(CategoriaProdotto cp){
        if (this == cp) return;
        sottoCategorie.add(cp);
    }

}
