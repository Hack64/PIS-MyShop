package Test;

import DAO.ProdottiLista.IProdottiListaDAO;
import DAO.ProdottiLista.ProdottiListaDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.IProdotto;
import Model.Lista;
import Model.Prodotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottiListaDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {
        IProdottiListaDAO prodottiListaDAO = ProdottiListaDAO.getInstance();

        prodottiListaDAO.add(null,null,null,0);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addTest() {
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        ProdottiListaDAO plDAO = ProdottiListaDAO.getInstance();
        ArrayList<IProdotto> prodotti = pDAO.findAll();
        Lista lista = new Lista();
        HashMap<IProdotto, String> hasProdotti = new HashMap<>();
        for (IProdotto p:prodotti){
            hasProdotti.put(p, "NO");
        }
        lista.setIdLista(1);
        lista.setNomeLista("ciao");
        lista.setDataCreazione(LocalDate.now());
        lista.setStato(Lista.Stato.NON_PAGATA);
        lista.setPrezzoTotale(100);
        //lista.setUtente();
        //lista.setProdotti(hasProdotti);

        //plDAO.add(lista, hasProdotti);
    }

    @Test
    public void updateTest() {
        ProdottiListaDAO plDAO = ProdottiListaDAO.getInstance();
        HashMap<Prodotto, Map.Entry<String, Integer>> prodotti = new HashMap<>();
        Lista lista = new Lista();
        lista.setIdLista(1);
        Prodotto p1 = new Prodotto();
        Prodotto p2 = new Prodotto();
        Prodotto p3 = new Prodotto();
        p1.setIdProdotto(1);
        p2.setIdProdotto(2);
        p3.setIdProdotto(3);

        prodotti.put(p1, new AbstractMap.SimpleEntry<String,Integer>("SI", 3));
        prodotti.put(p2, new AbstractMap.SimpleEntry<String,Integer>("SI", 2));
        prodotti.put(p3, new AbstractMap.SimpleEntry<String,Integer>("SI", 1));

        //plDAO.update(lista, prodotti);

    }
}
