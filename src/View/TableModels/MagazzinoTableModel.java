package View.TableModels;

import Model.Disponibilita;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MagazzinoTableModel extends AbstractTableModel {

    ArrayList<Disponibilita> lista;
    public MagazzinoTableModel(ArrayList<Disponibilita> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Prodotto";
            case 1 -> "Quantità";
            case 2 -> "Scaffale";
            case 3 -> "Corsia";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //prodotto, quantità, scaffale, corsia
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Disponibilita d = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> d.getProdotto().getNome();
            case 1 -> d.getQta();
            case 2 -> d.getPosizione().getScaffale();
            case 3 -> d.getPosizione().getCorsia();
            default -> null;
        };
    }

    public ArrayList<Disponibilita> getLista() {
        return lista;
    }
}
