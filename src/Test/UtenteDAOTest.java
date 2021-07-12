package Test;

import DAO.Utente.UtenteDAO;
import DbInterface.DbUser;
import Model.Utente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addTest(){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = new Utente();
        u.setNome("test");
        u.setCognome("test");
        u.setRuolo(Utente.Ruoli.amm);
        u.setEta(LocalDate.parse("2000-04-03"));
        u.setProfessione("coglione");
        u.setResidenza("Ruffano");
        u.setEmail("marco@si.com");
        u.setPasswordHash("a");
        u.setTelefono("90");

        utenteDAO.add(u);
    }
    @Test
    public void findAllByRoleTest(){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> managers = utenteDAO.findAllByRole(Utente.Ruoli.ute);

        for (Utente u:managers){
            System.out.println(u.getEmail());
        }
    }
}
