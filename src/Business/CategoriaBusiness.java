package Business;

import DAO.Categoria.CategoriaDAO;
import DAO.Categoria.ICategoriaDAO;
import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import DAO.ServizioCategoria.ServizioCategoriaDAO;
import Model.Categoria;
import Model.ICategoria;
import Model.IProdotto;
import Model.Servizio;

import java.util.ArrayList;

public class CategoriaBusiness {

    private static CategoriaBusiness instance;
    private ICategoriaDAO categoriaDAO;
    private ProdottoCategoriaDAO prodottoCategoriaDAO;
    private ServizioCategoriaDAO servizioCategoriaDAO;

    public static synchronized CategoriaBusiness getInstance() {
        if(instance == null) instance = new CategoriaBusiness();
        return instance;
    }

    private CategoriaBusiness() {}

    public ArrayList<ICategoria> findAll(){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.findAll();
    }

    public ICategoria findByID(int id){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.findByID(id);
    }

    public int addCategoriesToProduct(IProdotto p, ArrayList<ICategoria> categorie){
        categoriaDAO = CategoriaDAO.getInstance();
        prodottoCategoriaDAO = ProdottoCategoriaDAO.getInstance();
        int rowCount = 0;
        for (ICategoria c:categorie){
             rowCount = prodottoCategoriaDAO.add(c, p);
        }
        return rowCount;
    }

    public int addCategoryToService(Servizio s, ICategoria categoria){
        categoriaDAO = CategoriaDAO.getInstance();
        servizioCategoriaDAO = ServizioCategoriaDAO.getInstance();
        int rowCount = servizioCategoriaDAO.add(categoria, s);
        return rowCount;
    }

    public ICategoria findByName(String nome){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.findByName(nome);
    }

    public int deleteByID(int id){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.removeByID(id);
    }

    public int addNewCategory(String nome, Categoria categoriaPadre){
        categoriaDAO = CategoriaDAO.getInstance();

        Categoria c = new Categoria();
        c.setNome(nome);
        c.setCategoriaPadre(categoriaPadre);

        return categoriaDAO.add(c);
    }

    public int update(String nome, int id){
        categoriaDAO = CategoriaDAO.getInstance();

        Categoria c = (Categoria)categoriaDAO.findByID(id);
        c.setNome(nome);

        return categoriaDAO.update(c);
    }
}
