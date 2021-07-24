package Test;

import DAO.Fornitore.FornitoreDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.DbUser;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ServizioDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp(){
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio s = new Servizio();
        s.setNome("Trasporto");
        s.setImmagine(new File("/tmp/test.png"));
        s.setDescrizione("Test");
        s.setCosto(20.0f);
        s.setNumeroCommenti(19);
        s.setMediaValutazione(4.5f);
        s.setIdServizio(0);
        s.setFornitore(FornitoreDAO.getInstance().findByID(10));
        s.setCategorie(null);

        servizioDAO.add(s);
    }

    @After
    public void TearDown(){
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio s = servizioDAO.getByName("Trasporto");
        servizioDAO.removeById(s.getIdServizio());
    }

    @Test
    public void getByNameTest(){
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio s = servizioDAO.getByName("Trasporto");
        Assert.assertEquals("Stanley", s.getFornitore().getNome());
    }

    @Test
    public void productExistTest(){
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        boolean exist = servizioDAO.serviceExists("Trasporto");
        Assert.assertTrue(exist);
    }

    @Test
    public void updateTest(){
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio s = servizioDAO.getByName("Trasporto");
        s.setDescrizione("Test di trasporto");
        servizioDAO.update(s);
        s = servizioDAO.getByName("Trasporto");
        Assert.assertEquals("Test di trasporto", s.getDescrizione());
    }

}
