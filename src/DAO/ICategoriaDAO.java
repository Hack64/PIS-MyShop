package DAO;

import Model.Categoria;

import java.util.ArrayList;

public interface ICategoriaDAO {
    Categoria findByID(int idCategoria);
    ArrayList<Categoria> findAll();
    ArrayList<? super Categoria> findAllSubcategoriesByCategoryID(int idCategoria);
    int add(Categoria categoria);
    int removeByID(int idCategoria);
    int update(Categoria categoria);
}
