package Model;

import java.util.ArrayList;

public class Categoria implements ICategoria {

    private int idCategoria;
    private String nome;
    private ICategoria categoriaPadre;

    public Categoria(int idCategoria, String nome, ICategoria categoriaPadre) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.categoriaPadre = categoriaPadre;
    }

    public Categoria() {
        this.idCategoria = -1;
        this.nome = null;
        this.categoriaPadre = null;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ICategoria getCategoriaPadre() {
        return categoriaPadre;
    }

    @Override
    public ArrayList<ICategoria> getSottoCategorie() {
        return null;
    }

    public void setCategoriaPadre(ICategoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
}
