package Test;

import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DbInterface.DbUser;
import Model.Disponibilita;
import org.junit.Assert;
import org.junit.Test;

public class ProdottiMagazzinoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Test
    public void findByProductAndWarehouseIDTest(){
        IProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();
        Disponibilita d = prodottiMagazzinoDAO.findByProductAndWarehouseID(67, 20);
        Assert.assertEquals(10, d.getQta());
        Assert.assertEquals("Lampadario spirale", d.getProdotto().getNome());
    }

}
