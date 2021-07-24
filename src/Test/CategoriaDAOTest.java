package Test;

import DAO.Categoria.CategoriaDAO;
import DAO.Categoria.ICategoriaDAO;
import DbInterface.DbUser;
import Model.Categoria;
import Model.ICategoria;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoriaDAOTest {
    DbUser dbUser = DbUser.getInstance();

    boolean updated = false;

    @Before
    public void setUp() throws Exception {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        categoriaDAO.add(new Categoria(0,"Sanitari", null));
    }

    @After
    public void tearDown() throws Exception {
        if (updated){
            ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
            ICategoria categoria = categoriaDAO.findByName("WC");
            categoriaDAO.removeByID(categoria.getIdCategoria());
        } else {
            ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
            ICategoria categoria = categoriaDAO.findByName("Sanitari");
            categoriaDAO.removeByID(categoria.getIdCategoria());
        }
    }

    @Test
    public void findByNameTest() {
        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        ICategoria c = categoriaDAO.findByName("Sanitari");
        Assert.assertEquals("Sanitari", c.getNome());
    }

    @Test
    public void updateTest(){

        ICategoriaDAO categoriaDAO = CategoriaDAO.getInstance();
        Categoria categoria = (Categoria) categoriaDAO.findByName("Sanitari");
        categoria.setNome("WC");
        categoriaDAO.update(categoria);
        categoria = (Categoria) categoriaDAO.findByID(categoria.getIdCategoria());
        updated = true;
        Assert.assertEquals("WC", categoria.getNome());
    }

}
