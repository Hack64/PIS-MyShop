package Test;

import DAO.ProdottoCategoriaDAO;
import DAO.ProdottoCompositoDAO;
import DbInterface.DbUser;
import Model.Categoria;
import Model.CategoriaProdotto;
import Model.ProdottoComposito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoCompositoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findByIDTest() {
        ProdottoCompositoDAO pcDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = pcDAO.findByID(7);
    }

    @Test
    public void findAllTest() {
        ProdottoCompositoDAO pcDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottoCompositos = new ArrayList<>();
        prodottoCompositos = pcDAO.findAll();
    }

    @Test
    public void findAllByProducerTest() {
        ProdottoCompositoDAO pcDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottoCompositos = new ArrayList<>();
        prodottoCompositos = pcDAO.findAllByProducerID(1);
    }

    @Test
    public void updateTest() {

    }
}
