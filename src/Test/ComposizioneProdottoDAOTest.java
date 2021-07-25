package Test;

import DAO.ComposizioneProdotto.ComposizioneProdottoDAO;
import DbInterface.DbUser;
import Model.ProdottoComposito;
import org.junit.Assert;
import org.junit.Test;

public class ComposizioneProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Test
    public void findByIDTest() {
        ComposizioneProdottoDAO pcDAO = ComposizioneProdottoDAO.getInstance();
        ProdottoComposito prodottoComposito = pcDAO.findByID(72);
        Assert.assertEquals("Cucina Sweet", prodottoComposito.getNome());
        Assert.assertEquals("Ariston", prodottoComposito.getProduttore().getNome());
    }

    @Test
    public void isCompositeTest(){
        ComposizioneProdottoDAO cpDAO = ComposizioneProdottoDAO.getInstance();
        boolean isComposite = cpDAO.isCompositeProduct(74);
        Assert.assertTrue(isComposite);
    }

    @Test
    public void isNotCompositeTest(){
        ComposizioneProdottoDAO cpDAO = ComposizioneProdottoDAO.getInstance();
        boolean isComposite = cpDAO.isCompositeProduct(70);
        Assert.assertFalse(isComposite);
    }

}
