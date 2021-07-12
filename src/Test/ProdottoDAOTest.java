package Test;

import Business.ProdottoBusiness;
import DAO.Categoria.CategoriaDAO;
import DAO.Feedback.FeedbackDAO;
import DAO.Fornitore.FornitoreDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.Produttore.ProduttoreDAO;
import DbInterface.DbUser;
import Model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
        p.setNome("Lavello8");
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
            for (ICategoria c:prodotto.getCategorie()){
                System.out.println(c.getNome());
            }
        }


    }

    @Test
    public void findAllTest(){
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        FeedbackDAO feedbackDAO = FeedbackDAO.getInstance();

        ArrayList<IProdotto> prodotti = prodottoDAO.findAll();


        for (IProdotto prodotto : prodotti){
            System.out.println(prodotto.getNome());
            for (ICategoria c:prodotto.getCategorie()){
                System.out.println(c.getNome());
            }
        }

        Scanner in = new Scanner(System.in);
        in.nextLine();

    }
    @Test
    public void findByIDTest(){
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IProdotto prodotto = prodottoDAO.findByID(1);
        System.out.println(prodotto.getNome());
        for (ICategoria c:prodotto.getCategorie()){
            System.out.println(c.getNome());
        }

        Scanner in = new Scanner(System.in);
        in.nextLine();

    }
}
