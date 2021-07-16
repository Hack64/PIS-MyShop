package Test;

import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DbInterface.DbUser;
import Model.Disponibilita;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottiMagazzinoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCategoriesByProductIDTest() {
        ProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();

        ArrayList<Disponibilita> disponibilitas = prodottiMagazzinoDAO.findAllProductsByWarehouseID(12);

        for (Disponibilita d:disponibilitas){
            System.out.println(d.getProdotto().getNome());
            System.out.println(d.getQta());
            System.out.println(d.getPosizione().getCorsia());
            System.out.println(d.getPosizione().getScaffale());
        }

    }

}
