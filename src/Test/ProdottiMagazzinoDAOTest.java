package Test;

import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DbInterface.DbUser;
import Model.Disponibilita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
