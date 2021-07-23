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

    @Before
    public void setUp() throws Exception {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();

        posizioneDAO.add(new Posizione(10,10));
    }

    @After
    public void tearDown() throws Exception {
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();

        //posizioneDAO.removeByID();
    }

    @Test
    public void findByEmailTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByEmail("marco@rizzo.com");
        Assert.assertEquals("Marco", utente.getNome());
    }

    @Test
    public void userExitsDAO(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean exists = utenteDAO.userExists("marco@rizzo.com");
        Assert.assertTrue(exists);
    }

    @Test
    public void updateTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("marco@rizzo.com", "Marco", "Rizzo", "1234", "Ruffano", "1234567890", "Studente", LocalDate.parse("2000-05-30"), "ute");
        utenteDAO.update(utente);
        utente = utenteDAO.findByEmail("marco@rizzo.com");
        Assert.assertEquals(LocalDate.parse("2000-05-30"), utente.getEta());
    }

}
