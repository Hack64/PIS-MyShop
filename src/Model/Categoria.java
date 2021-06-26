package Model;

public class Categoria {

    private int idCategoria;
    private String nome;
    private int idCategoriaPadre;

    public Categoria(int idCategoria, String nome, int idCategoriaPadre) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.idCategoriaPadre = idCategoriaPadre;
    }

    public Categoria() {
        this.idCategoria = -1;
        this.nome = null;
        this.idCategoriaPadre = -1;
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

    public int getIdCategoriaPadre() {
        return idCategoriaPadre;
    }

    public void setIdCategoriaPadre(int idCategoriaPadre) {
        this.idCategoriaPadre = idCategoriaPadre;
    }
}
