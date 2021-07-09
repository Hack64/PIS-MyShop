package View.TableModels;

import Model.ICategoria;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CategoriesTableModel extends AbstractTableModel {
    ArrayList<ICategoria> lista;

    public CategoriesTableModel(ArrayList<ICategoria> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "ID Categoria Padre";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, id padre
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ICategoria c = lista.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> c.getIdCategoria();
            case 1 -> c.getNome();
            case 2 -> c.getCategoriaPadre().getIdCategoria();
            default -> null;
        };
    }

    public ArrayList<ICategoria> getLista() {
        return lista;
    }
}
