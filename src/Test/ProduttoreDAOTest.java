package Test;

import DAO.Produttore.IProduttoreDAO;
import DAO.Produttore.ProduttoreDAO;
import DbInterface.DbUser;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProduttoreDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();

        produttoreDAO.add(new Produttore("Marco", "marco@rizzo.it","Lecce", "Italia", null));
    }

    @After
    public void tearDown() throws Exception {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore p = produttoreDAO.getByName("Marco");
        produttoreDAO.removeById(p.getIdProduttore());
    }


    @Test
    public void producerExitsDAO(){
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        boolean exists = produttoreDAO.producerExists("Marco");
        Assert.assertTrue(exists);
    }

    @Test
    public void updateTest(){
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        Produttore p_orig = produttoreDAO.getByName("Marco");
        Produttore p1 = new Produttore("Marco", "marco@rizzo.it","Ruffano", "Italia", null);
        p1.setIdProduttore(p_orig.getIdProduttore());
        produttoreDAO.update(p1);
        p1 = produttoreDAO.findByID(p1.getIdProduttore());
        Assert.assertEquals("Ruffano", p1.getCitta());
    }

}
