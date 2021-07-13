package View.TableModels;

import Model.ICategoria;
import Model.IProdotto;
import Model.Produttore;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProduttoreTableModel extends AbstractTableModel {
    ArrayList<Produttore> lista;

    public ProduttoreTableModel(ArrayList<Produttore> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID Produttore";
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
        Produttore p = lista.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getIdProduttore();
            case 1 -> p.getNome();
            case 2 -> p.getSito();
            case 3 -> p.getCitta();
            case 4 -> p.getNazione();
            default -> null;
        };
    }

    public ArrayList<Produttore> getLista() {
        return lista;
    }
}
