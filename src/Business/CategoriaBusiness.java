package Business;

import DAO.Categoria.CategoriaDAO;
import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import Model.ICategoria;
import Model.IProdotto;

import java.util.ArrayList;

public class CategoriaBusiness {

    private static CategoriaBusiness instance;
    private CategoriaDAO categoriaDAO;
    private ProdottoCategoriaDAO prodottoCategoriaDAO;

    public static synchronized CategoriaBusiness getInstance() {
        if(instance == null) instance = new CategoriaBusiness();
        return instance;
    }

    private CategoriaBusiness() {}

    public ArrayList<ICategoria> findAll(){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.findAll();
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

    public ICategoria findByName(String nome){
        categoriaDAO = CategoriaDAO.getInstance();

        return categoriaDAO.findByName(nome);
    }
}
