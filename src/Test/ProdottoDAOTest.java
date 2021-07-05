package Test;

import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.ICategoria;
import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ProdottoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCategoriesByProductIDTest() {
        ProdottoDAO pDAO = ProdottoDAO.getInstance();

        IProdotto prodotto;
        prodotto = pDAO.findByID(7);

    }

    @Test
    public void addTest() {
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto p = new Prodotto();
        Produttore prd = new Produttore();
        prd.setIdProduttore(1);
        p.setNome("Lavello");
        p.setImmagine(new File("./img/Lavello.jpg"));
        p.setDescrizione("un bellissimo lavello");
        p.setNumeroCommenti(0);
        p.setCosto(502300000);
        p.setMediaValutazione((float)3.4);
        p.setProduttore(prd);

        prodottoDAO.add(p);
    }

    @Test
    public void getByNameTest() {
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto p;
        p = prodottoDAO.getByName("Scopa");

        System.out.println(p.getIdProdotto());
        System.out.println(p.getNome());
        System.out.println(p.getImmagine().getPath());
        ArrayList<String> categorie = new ArrayList<>();
        for (ICategoria c:p.getCategorie()){
            categorie.add(c.getNome());
        }
        String categorieConcat = String.join(" , ", categorie);
        System.out.println(categorieConcat);
    }

    @Test
    public void findAllByProducerTest(){
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        Produttore p = new Produttore();
        p.setIdProduttore(1);

        ArrayList<IProdotto> prodotti;
        prodotti = prodottoDAO.findAllByProducer(p);

        for (IProdotto prodotto : prodotti){
            System.out.println(prodotto.getNome());
        }


    }

    @Test
    public void findAllTest(){
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

        ArrayList<IProdotto> prodotti;
        prodotti = prodottoDAO.findAll();

        for (IProdotto prodotto : prodotti){
            System.out.println(prodotto.getNome());
            ArrayList<String> categorie = new ArrayList<>();
            for (ICategoria c:prodotto.getCategorie()){
                categorie.add(c.getNome());
            }
            String categorieConcat = String.join(" , ", categorie);
            System.out.println(categorieConcat);
        }


    }
}
