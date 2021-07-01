package Test;

import DAO.ComposizioneProdotto.ComposizioneProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.Prodotto;
import Model.ProdottoComposito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ComposizioneProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findByIDTest() {
        ComposizioneProdottoDAO pcDAO = ComposizioneProdottoDAO.getInstance();
        ProdottoComposito prodottoComposito = pcDAO.findByID(7);
    }

    @Test
    public void findAllTest() {
        ComposizioneProdottoDAO pcDAO = ComposizioneProdottoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottoCompositos = new ArrayList<>();
        prodottoCompositos = pcDAO.findAll();
    }

    @Test
    public void findAllByProducerTest() {
        ComposizioneProdottoDAO pcDAO = ComposizioneProdottoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottoCompositos = new ArrayList<>();
        prodottoCompositos = pcDAO.findAllByProducerID(1);
    }

    @Test
    public void isCompositeTest(){
        ComposizioneProdottoDAO cpDAO = ComposizioneProdottoDAO.getInstance();
        System.out.println(cpDAO.isCompositeProduct(9));
    }

    @Test
    public void addTest() {
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> sottoprodotti = new ArrayList<>();
        Prodotto p1 = pDAO.findByID(2);
        Prodotto p2 = pDAO.findByID(5);
        Prodotto p3 = pDAO.findByID(1);

        sottoprodotti.add(p1);
        sottoprodotti.add(p2);
        sottoprodotti.add(p3);

        ProdottoComposito prodottoComposito = new ProdottoComposito();
        prodottoComposito.setIdProdotto(10);
        prodottoComposito.setSottoprodotti(sottoprodotti);

    }
}
