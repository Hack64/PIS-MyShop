package Test;

import DAO.Magazzino.IMagazzinoDAO;
import DAO.Magazzino.MagazzinoDAO;
import DAO.PuntoVendita.IPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DbInterface.DbUser;
import Model.Magazzino;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MagazzinoDAOTest {
    DbUser dbUser = DbUser.getInstance();
    private PuntoVendita p;

    @Before
    public void setUp() throws Exception {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        p = new PuntoVendita("Via 20 Settembre", "73040", "Torrepaduli");
        puntoVenditaDAO.add(p);
        p = PuntoVenditaDAO.getInstance().findByAddress(p.getCitta(), p.getVia());

        magazzinoDAO.add(new Magazzino(0, "Via Aspromonte", "73049", "Ruffano", p, null));
    }

    @After
    public void tearDown() throws Exception {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();

        puntoVenditaDAO.removeById(p.getIdPuntoVendita()); //magazzino cade in cascata
    }

    @Test
    public void findByShopIDTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino m = magazzinoDAO.findByShopID(p.getIdPuntoVendita());

        Assert.assertEquals("73049", m.getCap());
        Assert.assertEquals("Via Aspromonte", m.getVia());
    }

    @Test
    public void updateTest(){
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = magazzinoDAO.findByShopID(p.getIdPuntoVendita());
        magazzino.setCitta("Torrepaduli");
        magazzino.setCap("73040");
        magazzinoDAO.update(magazzino);
        magazzino = magazzinoDAO.findByShopID(p.getIdPuntoVendita());
        Assert.assertEquals("73040", magazzino.getCap());
        Assert.assertEquals("Torrepaduli", magazzino.getCitta());
    }
}
