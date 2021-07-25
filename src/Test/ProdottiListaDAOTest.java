package Test;

import DAO.ProdottiLista.IProdottiListaDAO;
import DAO.ProdottiLista.ProdottiListaDAO;
import DbInterface.DbUser;
import org.junit.Assert;
import org.junit.Test;

public class ProdottiListaDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Test
    public void isProductAlreadyInListTest() {
        IProdottiListaDAO prodottiListaDAO = ProdottiListaDAO.getInstance();
        int qty = prodottiListaDAO.isProductAlreadyInList(65,65);
        Assert.assertEquals(5, qty);
    }

}
