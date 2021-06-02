package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria{

    private String nome;
    private List<CategoriaProdotto> sottoCategorie = new ArrayList<>();

    public List<CategoriaProdotto> getSottoCategorie() {
        return sottoCategorie;
    }

    public void setSottoCategorie(List<CategoriaProdotto> sottoCategorie) {
        this.sottoCategorie = sottoCategorie;
    }

    public void addSottoCategoria(CategoriaProdotto cp){
        if (this == cp) return;
        sottoCategorie.add(cp);
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
