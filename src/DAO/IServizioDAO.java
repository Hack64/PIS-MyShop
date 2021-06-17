package DAO;

import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {
    Servizio findByID(int idServizio);
    Servizio getByName(String nomeServizio);
    boolean serviceExists(String nomeServizio);
    ArrayList<Servizio> findAll();
    int add(Servizio servizio);
    int removeById(int idServizio);
    int update(Servizio servizio);
}
