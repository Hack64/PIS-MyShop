package Test;

import Business.ProdottoBusiness;
import DbInterface.DbUser;
import Model.IProdotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
