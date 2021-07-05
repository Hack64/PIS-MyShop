package View;

import Model.Lista;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaTableModel extends AbstractTableModel {

    ArrayList<Lista> lista;

    public ListaTableModel(ArrayList<Lista> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "Data Creazione";
            case 3 -> "Stato";
            case 4 -> "Prezzo";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, data, stato, prezzo
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lista l = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return l.getIdLista();
            case 1: return l.getNomeLista();
            case 2: return l.getDataCreazione();
            case 3: return l.getStato().toString();
            case 4: return l.getPrezzoTotale();
        }
        return null;
    }

    public Object getRow(int rowIndex){
        return lista.get(rowIndex);
    }

    public ArrayList<Lista> getLista() {
        return lista;
    }
}
