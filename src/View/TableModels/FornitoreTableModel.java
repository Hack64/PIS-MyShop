package View.TableModels;

import Model.Fornitore;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FornitoreTableModel extends AbstractTableModel {
    ArrayList<Fornitore> lista;

    public FornitoreTableModel(ArrayList<Fornitore> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID Fornitore";
            case 1 -> "Nome";
            case 2 -> "Sito web";
            case 3 -> "Città";
            case 4 -> "Nazione";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, sito, città, nazione
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornitore f = lista.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> f.getIdFornitore();
            case 1 -> f.getNome();
            case 2 -> f.getSito();
            case 3 -> f.getCitta();
            case 4 -> f.getNazione();
            default -> null;
        };
    }

    public ArrayList<Fornitore> getLista() {
        return lista;
    }
}
