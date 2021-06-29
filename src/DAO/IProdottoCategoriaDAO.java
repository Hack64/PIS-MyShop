package DAO;

import Model.Categoria;
import Model.CategoriaProdotto;
import Model.IProdotto;
import Model.Prodotto;

import java.util.ArrayList;

public interface IProdottoCategoriaDAO {
    ArrayList<IProdotto> getProductsByCategoryID(int idCategoria);
    ArrayList<? super Categoria> getCategoriesByProductID(int idProdotto);
    int add(CategoriaProdotto categoriaProdotto, Prodotto prodotto);
    int removeByID(int idProdottoCategoria);
    int update(CategoriaProdotto categoriaProdotto, Prodotto prodotto);

}
