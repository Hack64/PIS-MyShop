package View;

import Model.ICategoria;
import Model.Servizio;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ServizioTableModel extends AbstractTableModel {
    ArrayList<Servizio> lista;
    ArrayList<String> categorie;
    public ServizioTableModel(ArrayList<Servizio> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "Categoria";
            case 3 -> "Prezzo";
            case 4 -> "Fornitore";
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
        Servizio s = lista.get(rowIndex);
        categorie = new ArrayList<>();
        for (ICategoria c:s.getCategorie()){
            categorie.add(c.getNome());
        }
        return switch (columnIndex) {
            case 0 -> s.getIdServizio();
            case 1 -> s.getNome();
            case 2 -> categorie.toString();
            case 3 -> s.getCosto();
            case 4 -> s.getFornitore().getNome();
            case 5 -> s.getMediaValutazione();
            case 6 -> s.getNumeroCommenti();
            default -> null;
        };
    }

    public ArrayList<Servizio> getLista() {
        return lista;
    }
}
