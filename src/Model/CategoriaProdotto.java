package Model;

import java.util.ArrayList;

public class CategoriaProdotto extends Categoria {

    private ArrayList<? super Categoria> sottoCategorie;

    public CategoriaProdotto(int idCategoria, String nome, int idCategoriaPadre, ArrayList<? super Categoria> sottoCategorie) {
        super(idCategoria, nome, idCategoriaPadre);
        this.sottoCategorie = sottoCategorie;
    }

    public CategoriaProdotto(){
        super();
        this.sottoCategorie = null;
    }

    public CategoriaProdotto(ArrayList<? super Categoria> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    public ArrayList<? super Categoria> getSottoCategorie() {
        return sottoCategorie;
    }

    public void setSottoCategorie(ArrayList<? super Categoria> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    public void addSottoCategoria(CategoriaProdotto cp){
        if (this == cp) return;
        sottoCategorie.add(cp);
    }



}
