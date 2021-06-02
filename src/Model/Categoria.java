package Model;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements ICategoria{

    private String nome;
    private List<Categoria> sottoCategorie = new ArrayList<>();

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Categoria> getSottoCategorie() {
        return sottoCategorie;
    }

    public void setSottoCategorie(List<Categoria> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    @Override
    public String getNome() {
        return this.nome;
    }
}
