package Test;

import Business.ProdottoBusiness;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.ICategoria;
import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class ProdottoBusinessTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findAllNonCompositeProductsTest(){
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllNonCompositeProducts();
        for (IProdotto p:prodotti){
            System.out.println(p.getNome());
        }
    }
}
