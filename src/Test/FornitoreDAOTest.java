package Test;

import DAO.Fornitore.FornitoreDAO;
import DAO.Fornitore.IFornitoreDAO;
import DbInterface.DbUser;
import Model.Fornitore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FornitoreDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        fornitoreDAO.add(new Fornitore("Luca", "luca@sito.com", "Torrepaduli", "Italia"));
    }

    @After
    public void tearDown() throws Exception {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.getByName("Luca");
        fornitoreDAO.removeById(fornitore.getIdFornitore());
    }

    @Test
    public void getByNameTest() {
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.getByName("Luca");
        Assert.assertEquals("Luca", fornitore.getNome());
    }

    @Test
    public void supplierExists(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        boolean exists = fornitoreDAO.supplierExists("Luca");
        Assert.assertTrue(exists);
    }

    @Test
    public void updateTest(){
        IFornitoreDAO fornitoreDAO = FornitoreDAO.getInstance();
        Fornitore fornitore = fornitoreDAO.getByName("Luca");
        fornitore.setCitta("Ruffano");
        fornitoreDAO.update(fornitore);
        fornitore = fornitoreDAO.getByName("Luca");
        Assert.assertEquals("Ruffano", fornitore.getCitta());
    }

}
