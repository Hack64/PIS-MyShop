package Test;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DbInterface.DbUser;
import Model.Utente;
import org.junit.After;
import org.junit.Before;

import java.time.LocalDate;

public class FeedbackDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        Utente u = new Utente("marco@rizzo.com", "Marco", "Rizzo", "1234", "Ruffano", "1234567890", "Studente", LocalDate.parse("2000-04-03"), "ute");
        //feedbackDAO.add(new Feedback(0, LocalDate.now(), "Ottimo", 4, u, );
    }

    @After
    public void tearDown() throws Exception {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        //Feedback feedback = feedbackDAO.
        //feedbackDAO.removeByID();
    }


}
