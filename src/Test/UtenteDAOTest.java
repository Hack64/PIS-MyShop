package Test;


import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbUser;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UtenteDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        utenteDAO.add(new Utente("marco@rizzo.it", "Marco", "Rizzo", "1234", "Ruffano", "12345678901234", "Studente", LocalDate.parse("2000-04-03"), "ute"));
    }

    @After
    public void tearDown() throws Exception {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeByEmail("marco@rizzo.it");
    }

    @Test
    public void findByEmailTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByEmail("marco@rizzo.it");
        Assert.assertEquals("Marco", utente.getNome());
    }

    @Test
    public void userExitsDAO(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean exists = utenteDAO.userExists("marco@rizzo.it");
        Assert.assertTrue(exists);
    }

    @Test
    public void updateTest(){
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("marco@rizzo.it", "Marco", "Rizzo", "1234", "Ruffano", "12345678901234", "Studente", LocalDate.parse("2000-05-30"), "ute");
        utenteDAO.update(utente);
        utente = utenteDAO.findByEmail("marco@rizzo.it");
        Assert.assertEquals(LocalDate.parse("2000-05-30"), utente.getEta());
    }

}