package Business;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.Risposta.RispostaDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import Model.*;

import java.time.LocalDate;
import java.util.List;

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
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IServizioDAO servizioDAO = ServizioDAO.getInstance();

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
                int nCommenti = feedbackDAO.findNumberOfFeedbacks(f.getProdotto().getIdProdotto(), true);

                prodottoDAO.updateScore(mediaValutazioni, p);
                prodottoDAO.updateComments(nCommenti, p);
            } else if (p == null && s != null){
                float mediaValutazioni = feedbackDAO.findAverageScore(f.getServizio().getIdServizio(), false);
                int nCommenti = feedbackDAO.findNumberOfFeedbacks(f.getServizio().getIdServizio(), false);
                servizioDAO.updateScore(mediaValutazioni, s);
                servizioDAO.updateComments(nCommenti, s);
            }
        }
        return st;
    }

    public List<Feedback> getAllListsByProductID(int idProdotto){
        feedbackDAO = FeedbackDAO.getInstance();

        return feedbackDAO.findAllByProductID(idProdotto);
    }

    public List<Feedback> getAllListsByServiceID(int idServizio){
        feedbackDAO = FeedbackDAO.getInstance();

        return feedbackDAO.findAllByServiceID(idServizio);
    }

    public Risposta findReplyByFeedbackID(int idFeedback){
        RispostaDAO rispostaDAO = RispostaDAO.getInstance();

        return rispostaDAO.findByFeedbackID(idFeedback);
    }

    public Feedback findByID(int idFeedback){
        feedbackDAO = FeedbackDAO.getInstance();

        return feedbackDAO.findByID(idFeedback);
    }

    public int addNewRisposta(Risposta r){
        RispostaDAO rispostaDAO = RispostaDAO.getInstance();

        return rispostaDAO.add(r);
    }
}
