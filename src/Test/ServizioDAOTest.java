package Test;

import DAO.Servizio.ServizioDAO;
import DbInterface.DbUser;
import Model.Servizio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ServizioDAOTest {    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void findAllTest(){
        ServizioDAO sDAO = ServizioDAO.getInstance();

        Servizio servizio;

        ArrayList<Servizio> servizi;
        servizi = sDAO.findAll();

        for (Servizio s : servizi){
            System.out.println(s.getNome());
        }


    }
}
