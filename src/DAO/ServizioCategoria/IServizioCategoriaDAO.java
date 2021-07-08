package DAO.ServizioCategoria;

import Model.ICategoria;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioCategoriaDAO {
    ArrayList<Servizio> getServicesByCategoryID(int idCategoria);
    ArrayList<ICategoria> getCategoriesByServiceID(int idServizio);
    int add(ICategoria categoria, Servizio servizio);
    int removeByID(int idCategoria, int idServizio);
}
