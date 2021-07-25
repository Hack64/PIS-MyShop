package Test;

import DAO.PuntoVendita.IPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DbInterface.DbUser;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuntoVenditaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        puntoVenditaDAO.add(new PuntoVendita("Via Piave", "73049", "Ruffano"));
    }

    @After
    public void tearDown() throws Exception {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita p = puntoVenditaDAO.findByAddress("Ruffano", "Via Piave");
        puntoVenditaDAO.removeById(p.getIdPuntoVendita());
    }

    @Test
    public void updateTest(){
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita p = puntoVenditaDAO.findByAddress("Ruffano", "Via Piave");
        PuntoVendita p1 = new PuntoVendita("Via Piave", "73100", "Ruffano");
        p1.setIdPuntoVendita(p.getIdPuntoVendita());
        puntoVenditaDAO.update(p1);
        p1 = puntoVenditaDAO.findByID(p1.getIdPuntoVendita());

        Assert.assertEquals("73100", p1.getCap());
    }

}
