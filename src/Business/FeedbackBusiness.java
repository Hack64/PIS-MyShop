package Business;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DAO.Fornitore.IFornitoreDAO;
import Model.Feedback;
import Model.IProdotto;
import Model.Servizio;
import Model.Utente;

import java.time.LocalDate;

public class FeedbackBusiness {
    private static FeedbackBusiness instance;
    private IFeedbackDAO feedbackDAO;

    public static synchronized FeedbackBusiness getInstance() {
        if(instance == null) instance = new FeedbackBusiness();
        return instance;
    }

    private FeedbackBusiness() {}

    public int addNewFeedback(String commento, int valutazione, Utente u, IProdotto p, Servizio s){
        feedbackDAO = FeedbackDAO.getInstance();

        Feedback f = new Feedback();
        f.setUtente(u);
        f.setCommento(commento);
        f.setDataCreazione(LocalDate.now());
        f.setValutazione(valutazione);
        if (p != null && s == null){
            f.setProdotto(p);
        } else if (p == null && s != null){
            f.setServizio(s);
        }

        int st = feedbackDAO.add(f);
        if (st == 1){
            if (p != null && s == null){
                float mediaValutazioni = feedbackDAO.findAverageScore(f.getProdotto().getIdProdotto(), true);
                System.out.println(mediaValutazioni);
            } else if (p == null && s != null){
                float mediaValutazioni = feedbackDAO.findAverageScore(f.getServizio().getIdServizio(), false);
                System.out.println(mediaValutazioni);
            }
        }
        return st;
    }
}
