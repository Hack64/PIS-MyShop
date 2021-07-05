package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import Model.Responses.ProdottoResponse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CatalogoTableModel extends AbstractTableModel {
    ArrayList<IProdotto> lista;

    public CatalogoTableModel(ArrayList<IProdotto> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "Prezzo";
            case 3 -> "Produttore";
            case 4 -> "Media Valutazioni";
            case 5 -> "Numero Commenti";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, prezzo, produttore, media val, num commenti
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IProdotto p = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return p.getIdProdotto();
            case 1: return p.getNome();
            case 2: return p.getCosto();
            case 3: return p.getProduttore().getNome();
            case 4: return p.getMediaValutazione();
            case 5: return p.getNumeroCommenti();
        }
        return null;
    }

    public ArrayList<IProdotto> getLista() {
        return lista;
    }
}
