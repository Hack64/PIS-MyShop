package Test;

import DAO.UtentiPuntoVendita.IUtentiPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import Model.PuntoVendita;
import Model.Utente;
import org.junit.Assert;
import org.junit.Test;

public class UtentiPuntoVenditaDAOTest {

    @Test
    public void findShopManagerByShopIDTest() {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        Utente manager = utentiPuntoVenditaDAO.findShopManagerByShopID(22);
        Assert.assertEquals("Luca", manager.getNome());
    }

    @Test
    public void findShopByShopManagerIDTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = utentiPuntoVenditaDAO.findShopByShopManagerID(38);
        Assert.assertEquals("Via Aspromonte", puntoVendita.getVia());
    }

    @Test
    public void isUserBannedTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        boolean banned = utentiPuntoVenditaDAO.isUserBanned(45, 22);
        Assert.assertTrue(banned);
    }

    @Test
    public void isUserNotShopManagerTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        boolean isManager = utentiPuntoVenditaDAO.isUserShopManager(45, 22);
        Assert.assertFalse(isManager);
    }

    @Test
    public void isUserShopManagerTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        boolean isManager = utentiPuntoVenditaDAO.isUserShopManager(38, 22);
        Assert.assertTrue(isManager);
    }

    @Test
    public void isUserRegisteredInShopTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        boolean isRegistered = utentiPuntoVenditaDAO.isUserRegisteredInShop(38, 32);
        Assert.assertFalse(isRegistered);
    }

    @Test
    public void isUserPreferredShopTest(){
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        boolean isPreferred = utentiPuntoVenditaDAO.isUserPreferredShop(72, 22);
        Assert.assertTrue(isPreferred);
    }
}
