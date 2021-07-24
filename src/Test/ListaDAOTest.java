package Test;

import DAO.Lista.IListaDAO;
import DAO.Lista.ListaDAO;
import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbUser;
import Model.Lista;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ListaDAOTest {

    DbUser dbUser = DbUser.getInstance();
    private Utente u;

    @Before
    public void setUp() throws Exception {
        IListaDAO listaDAO = ListaDAO.getInstance();
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        utenteDAO.add(new Utente("marco@rizzo.it", "Marco", "Rizzo", "1234", "Ruffano", "123456789012", "Studente", LocalDate.parse("2000-04-03"), "ute"));
        u = utenteDAO.findByEmail("marco@rizzo.it");

        listaDAO.add(new Lista(0, "Test Lista", LocalDate.now(), null, null, Lista.Stato.NON_PAGATA, 100.0f,  u));
    }

    @After
    public void tearDown() throws Exception {
        IListaDAO listaDAO = ListaDAO.getInstance();
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        Lista l = listaDAO.findByUserDateAndName(u.getIdUtente(), LocalDate.now(), "Test Lista");
        listaDAO.removeById(l.getIdLista());
        utenteDAO.removeByEmail("marco@rizzo.it");
    }

    @Test
    public void getListPriceTest() {
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista l = listaDAO.findByUserDateAndName(u.getIdUtente(), LocalDate.now(), "Test Lista");
        float price = listaDAO.getListPrice(l.getIdLista());
        Assert.assertEquals(100.0f, price, 0.01);
    }

    @Test
    public void setListPaymentTest(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista l = listaDAO.findByUserDateAndName(u.getIdUtente(), LocalDate.now(), "Test Lista");
        listaDAO.setListPayment(l.getIdLista(), Lista.Stato.PAGATA);
        l = listaDAO.findByID(l.getIdLista());
        Assert.assertEquals(Lista.Stato.PAGATA, l.getStato());
    }

    @Test
    public void editNameTest(){
        IListaDAO listaDAO = ListaDAO.getInstance();
        Lista lista = listaDAO.findByUserDateAndName(u.getIdUtente(), LocalDate.now(), "Test Lista");
        lista.setNomeLista("Lista Test");
        listaDAO.editName(lista);
        lista = listaDAO.findByID(lista.getIdLista());
        Assert.assertEquals("Lista Test", lista.getNomeLista());
    }
}
