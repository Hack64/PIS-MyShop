package Test;

import DAO.CategoriaDAO;
import DAO.ProdottiListaDAO;
import DAO.ProdottoDAO;
import DbInterface.DbUser;
import Model.Categoria;
import Model.Lista;
import Model.Prodotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottiListaDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addTest() {
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        ProdottiListaDAO plDAO = ProdottiListaDAO.getInstance();
        ArrayList<Prodotto> prodotti = pDAO.findAll();
        Lista lista = new Lista();
        HashMap<Prodotto, String> hasProdotti = new HashMap<>();
        for (Prodotto p:prodotti){
            hasProdotti.put(p, "NO");
        }
        lista.setIdLista(1);
        lista.setNomeLista("ciao");
        lista.setDataCreazione(LocalDate.now());
        lista.setStato(Lista.Stato.NON_PAGATA);
        lista.setPrezzoTotale(100);
        lista.setIdUtente(1);
        lista.setProdotti(hasProdotti);

        plDAO.add(lista, hasProdotti);
    }

    @Test
    public void updateTest() {

    }
}
