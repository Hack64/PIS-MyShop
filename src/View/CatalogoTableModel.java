package View;

import Model.ICategoria;
import Model.IProdotto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CatalogoTableModel extends AbstractTableModel {
    ArrayList<IProdotto> lista;
    ArrayList<String> categorie;

    public CatalogoTableModel(ArrayList<IProdotto> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "Categorie";
            case 3 -> "Prezzo";
            case 4 -> "Produttore";
            case 5 -> "Media Valutazioni";
            case 6 -> "Numero Commenti";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, categoria, prezzo, produttore, media val, num commenti
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IProdotto p = lista.get(rowIndex);
        categorie = new ArrayList<>();
        for (ICategoria c:p.getCategorie()){
            categorie.add(c.getNome());
        }
        return switch (columnIndex) {
            case 0 -> p.getIdProdotto();
            case 1 -> p.getNome();
            case 2 -> categorie.toString();
            case 3 -> p.getCosto();
            case 4 -> p.getProduttore().getNome();
            case 5 -> p.getMediaValutazione();
            case 6 -> p.getNumeroCommenti();
            default -> null;
        };
    }

    public ArrayList<IProdotto> getLista() {
        return lista;
    }
}
