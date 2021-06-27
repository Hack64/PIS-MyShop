package DAO;

import Model.Categoria;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioCategoriaDAO {
    ArrayList<Servizio> getServicesByCategoryID(int idCategoria);
    ArrayList<? super Categoria> getCategoriesByServiceID(int idServizio);
    int add();
    int remove();
}
