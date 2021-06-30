package Test;

import DAO.Categoria.CategoriaDAO;
import DbInterface.DbUser;
import Model.Categoria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findAllSubcategoriesByCategoryIDTest() {
        CategoriaDAO cDAO = CategoriaDAO.getInstance();
        ArrayList<Categoria> sottocategorie = (ArrayList<Categoria>) cDAO.findAllSubcategoriesByCategoryID(6);

        Categoria c = sottocategorie.get(0);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getIdCategoriaPadre());
        c = sottocategorie.get(1);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getIdCategoriaPadre());
    }

    @Test
    public void updateTest() {

    }
}
