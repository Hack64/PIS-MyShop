package Test;

import DAO.Feedback.FeedbackDAO;
import DAO.Risposta.IRispostaDAO;
import DAO.Risposta.RispostaDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbUser;
import Model.Risposta;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class RispostaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp(){
        IRispostaDAO rispostaDAO = RispostaDAO.getInstance();

        Risposta r = new Risposta();
        r.setFeedback(FeedbackDAO.getInstance().findByID(21));
        r.setTesto("Grazie per aver acquistato");
        r.setDataCreazione(LocalDate.now());
        r.setIdRisposta(0);
        r.setUtente(UtenteDAO.getInstance().findByID(38));

        rispostaDAO.add(r);
    }

    @After
    public void tearDown(){
        IRispostaDAO rispostaDAO = RispostaDAO.getInstance();
        Risposta r = rispostaDAO.findByFeedbackID(21);
        rispostaDAO.removeByID(r.getIdRisposta());
    }

    @Test
    public void updateTest(){
        IRispostaDAO rispostaDAO = RispostaDAO.getInstance();
        Risposta r = rispostaDAO.findByFeedbackID(21);
        r.setTesto("Arrivederci");
        rispostaDAO.update(r);
        r = rispostaDAO.findByFeedbackID(21);
        Assert.assertEquals("Arrivederci", r.getTesto());
    }

}
