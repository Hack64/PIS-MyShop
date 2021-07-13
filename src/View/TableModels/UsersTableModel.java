package View.TableModels;

import Model.Utente;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;

public class UsersTableModel extends AbstractTableModel {
    HashMap<Utente, String> lista;
    public UsersTableModel(HashMap<Utente, String> lista){
        this.lista = lista;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Nome";
            case 2 -> "Cognome";
            case 3 -> "Email";
            case 4 -> "Residenza";
            case 5 -> "Telefono";
            case 6 -> "Professione";
            case 7 -> "Età";
            case 8 -> "Disattivato";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //id, nome, cognome, email, residenza, telefono, professione, età
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Utente u =(Utente) lista.keySet().toArray()[rowIndex];
        Object disattivato = lista.get(u);

        return switch (columnIndex) {
            case 0 -> u.getIdUtente();
            case 1 -> u.getNome();
            case 2 -> u.getCognome();
            case 3 -> u.getEmail();
            case 4 -> u.getResidenza();
            case 5 -> u.getTelefono();
            case 6 -> u.getProfessione();
            case 7 -> u.getEta();
            case 8 -> disattivato.toString();
            default -> null;
        };
    }

    public HashMap<Utente, String> getLista() {
        return lista;
    }
}
