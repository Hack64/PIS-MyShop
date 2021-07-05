package DAO.Categoria;

import Model.ICategoria;

import java.util.ArrayList;

public interface ICategoriaDAO {
    ICategoria findByID(int idCategoria);
    ICategoria findByName(String nome);
    ArrayList<ICategoria> findAll();
    ArrayList<ICategoria> findAllSubcategoriesByCategoryID(int idCategoria);
    int add(ICategoria categoria);
    int removeByID(int idCategoria);
    int update(ICategoria categoria);
}
