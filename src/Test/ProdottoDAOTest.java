package Test;

import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.Prodotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCategoriesByProductIDTest() {
        ProdottoDAO pDAO = ProdottoDAO.getInstance();

        Prodotto prodotto = new Prodotto();
        prodotto = pDAO.findByID(7);

    }

    @Test
    public void updateTest() {

    }
}
