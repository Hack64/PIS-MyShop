package Test;

import DAO.Categoria.CategoriaDAO;
import DAO.Feedback.FeedbackDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.Produttore.ProduttoreDAO;
import DbInterface.DbUser;
import Model.ICategoria;
import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp(){
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = new Prodotto();
        p.setNome("Lavastoviglie");
        p.setImmagine(new File("/tmp/test.png"));
        p.setDescrizione("Test");
        p.setCosto(250.0f);
        p.setNumeroCommenti(19);
        p.setMediaValutazione(4.5f);
        p.setIdProdotto(0);
        p.setProduttore(ProduttoreDAO.getInstance().findByID(23));
        p.setCategorie(null);

        prodottoDAO.add(p);
    }
    
    @After
    public void TearDown(){
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto p = prodottoDAO.getByName("Lavastoviglie");
        prodottoDAO.removeById(p.getIdProdotto());
    }

    @Test
    public void getByNameTest(){
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto p = prodottoDAO.getByName("Lavastoviglie");
        Assert.assertEquals(250.0f, p.getCosto(), 0.01);
    }

    @Test
    public void productExistTest(){
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        boolean exist = prodottoDAO.productExists("Lavastoviglie");
        Assert.assertTrue(exist);
    }

    @Test
    public void updateTest(){
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = (Prodotto)prodottoDAO.getByName("Lavastoviglie");
        p.setNumeroCommenti(10);
        prodottoDAO.update(p);
        p = (Prodotto)prodottoDAO.getByName("Lavastoviglie");
        Assert.assertEquals(10, p.getNumeroCommenti());
    }
    
}
