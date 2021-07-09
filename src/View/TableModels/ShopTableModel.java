package View.TableModels;
import Model.PuntoVendita;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShopTableModel extends AbstractTableModel {
    ArrayList<PuntoVendita> lista;
    ArrayList<String> categorie;
    public ShopTableModel(ArrayList<PuntoVendita> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Citta";
            case 2 -> "CAP";
            case 3 -> "Via";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, citta, cap, via
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PuntoVendita pv = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> pv.getIdPuntoVendita();
            case 1 -> pv.getCitta();
            case 2 -> pv.getCap();
            case 3 -> pv.getVia();
            default -> null;
        };
    }

    public ArrayList<PuntoVendita> getLista() {
        return lista;
    }
}
