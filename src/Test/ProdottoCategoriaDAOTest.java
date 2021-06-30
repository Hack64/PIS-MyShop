package Test;

import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import DbInterface.DbUser;
import Model.Categoria;
import Model.CategoriaProdotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoCategoriaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCategoriesByProductIDTest() {
        ProdottoCategoriaDAO pcDAO = ProdottoCategoriaDAO.getInstance();
        ArrayList<? super Categoria> categorieProdotto = pcDAO.getCategoriesByProductID(3);

        CategoriaProdotto c = (CategoriaProdotto) categorieProdotto.get(0);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getIdCategoriaPadre());

        /*c = (CategoriaProdotto) categorieProdotto.get(1);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getIdCategoriaPadre());*/
    }

    @Test
    public void updateTest() {

    }
}
