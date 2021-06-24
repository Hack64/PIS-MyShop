package DAO;

import Model.CategoriaProdotto;
import Model.IProdotto;

import java.util.ArrayList;

public interface IProdottoCategoriaDAO {
    ArrayList<IProdotto> getProductsByCategoryID(int idCategoria);
    ArrayList<CategoriaProdotto> getCategoriesByProductID(int idProdotto);
    int add();
    int remove();
}
