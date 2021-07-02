package DAO.ProdottoCategoria;

import Model.*;

import java.util.ArrayList;

public interface IProdottoCategoriaDAO {
    ArrayList<IProdotto> getProductsByCategoryID(int idCategoria);
    ArrayList<ICategoria> getCategoriesByProductID(int idProdotto);
    int add(ICategoria categoriaProdotto, IProdotto prodotto);
    int removeByID(int idProdottoCategoria);
}
