package DAO;

import Model.Categoria;
import Model.IProdotto;

import java.util.ArrayList;

public interface IProdottoCategoriaDAO {
    ArrayList<IProdotto> getProductsByCategoryID(int idCategoria);
    ArrayList<? super Categoria> getCategoriesByProductID(int idProdotto);
    int add();
    int remove();
}
