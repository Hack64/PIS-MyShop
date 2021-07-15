package Test;

import DAO.Categoria.CategoriaDAO;
import DAO.Feedback.FeedbackDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
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

public class ProdottiMagazzinoDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCategoriesByProductIDTest() {
        ProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();

        ArrayList<Disponibilita> disponibilitas = prodottiMagazzinoDAO.findAllProductsByWarehouseID(12);

        for (Disponibilita d:disponibilitas){
            System.out.println(d.getProdotto().getNome());
            System.out.println(d.getQta());
            System.out.println(d.getPosizione().getCorsia());
            System.out.println(d.getPosizione().getScaffale());
        }

    }

}
