package Test;

import DAO.Categoria.CategoriaDAO;
import DbInterface.DbUser;
import Model.Categoria;
import Model.ICategoria;
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
        ArrayList<ICategoria> sottocategorie = (ArrayList<ICategoria>) cDAO.findAllSubcategoriesByCategoryID(6);

        ICategoria c = sottocategorie.get(0);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getCategoriaPadre().getIdCategoria());
        c = sottocategorie.get(1);
        System.out.println(c.getNome());
        System.out.println(c.getIdCategoria());
        System.out.println(c.getCategoriaPadre().getIdCategoria());
    }

    @Test
    public void findByIDTest() {
        CategoriaDAO cDAO = CategoriaDAO.getInstance();

        ICategoria c = cDAO.findByID(1);

        System.out.println(c.getNome());
        System.out.println(c.getCategoriaPadre().getIdCategoria());
        System.out.println(c.getCategoriaPadre().getNome());
    }

    @Test
    public void findAllTest() {
        CategoriaDAO cDAO = CategoriaDAO.getInstance();
        ArrayList<ICategoria> categorie = cDAO.findAll();

        for(ICategoria c:categorie){
            System.out.println(c.getNome());
        }
    }
}
