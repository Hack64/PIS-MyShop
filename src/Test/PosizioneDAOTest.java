package Test;

import DAO.Posizione.IPosizioneDAO;
import DAO.Posizione.PosizioneDAO;
import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbUser;
import Model.Posizione;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class PosizioneDAOTest {
    DbUser dbUser = DbUser.getInstance();


    @Test
    public void findByIDTest() {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione p = posizioneDAO.findByID(9);
        Assert.assertEquals(1, p.getCorsia());
        Assert.assertEquals(3, p.getScaffale());
    }

    @Test
    public void findByProductIDTest(){
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione p = posizioneDAO.findByProductID(68);
        Assert.assertEquals(1, p.getCorsia());
        Assert.assertEquals(4, p.getScaffale());
    }

}
