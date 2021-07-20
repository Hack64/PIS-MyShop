package View.TableModels;

import Model.Feedback;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FeedbackTableModel extends AbstractTableModel {
    List<Feedback> feedbacks;
    public FeedbackTableModel(List<Feedback> feedbacks){
        this.feedbacks = feedbacks;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Data Creazione";
            case 2 -> "Commento";
            case 3 -> "Valutazione";
            case 4 -> "Prodotto";
            case 5 -> "Servizio";
            case 6 -> "Utente";
            default -> null;
        };
    }

    @Override
    public int getRowCount() {
        return feedbacks.size();
    }

    @Override
    public int getColumnCount() {
        //id, data, commento, valutazione, prodotto, servizio, utente
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Feedback f = feedbacks.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> f.getIdFeedback();
            case 1 -> f.getDataCreazione();
            case 2 -> f.getCommento();
            case 3 -> f.getValutazione();
            case 4 -> f.getProdotto().getNome();
            case 5 -> f.getServizio().getNome();
            case 6 -> f.getUtente().getEmail();
            default -> null;
        };
    }

    public List<Feedback> getLista() {
        return feedbacks;
    }
}
