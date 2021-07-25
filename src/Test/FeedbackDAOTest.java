package Test;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DbInterface.DbUser;
import Model.Feedback;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedbackDAOTest {

    private DbUser dbUser = DbUser.getInstance();
    private Feedback f;

    @Before
    public void setUp() throws Exception {
        IFeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        f = feedbackDAO.findByID(21);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAverageScoreTest(){
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        float score = feedbackDAO.findAverageScore(65, true);
        Assert.assertEquals(4.0f, score, 0.01);
    }

    @Test
    public void findNumberOfFeedbacksTest(){
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();
        int number = feedbackDAO.findNumberOfFeedbacks(70, true);
        Assert.assertEquals(1,number);
    }


}
