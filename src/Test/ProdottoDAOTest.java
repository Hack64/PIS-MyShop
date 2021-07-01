package Test;

import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.Prodotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
    public void addTest() {
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = new Prodotto();
        p.setNome("Cucina");
        p.setImmagine(new File("./img/Cucina.jpg"));
        p.setDescrizione("una bellissima cucina");
        p.setNumeroCommenti(0);
        p.setCosto(5000000);
        p.setMediaValutazione(5);
        p.setIdProduttore(1);

        prodottoDAO.add(p);
    }

    @Test
    public void getByNameTest() {
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p;
        p = prodottoDAO.getByName("Cucina");

        System.out.println(p.getIdProdotto());
        System.out.println(p.getNome());
        System.out.println(p.getImmagine().getName());
    }
}
