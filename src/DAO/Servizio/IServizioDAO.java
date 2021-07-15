package DAO.Servizio;

import Model.Fornitore;
import Model.Servizio;

import java.util.ArrayList;

public interface IServizioDAO {
    Servizio findByID(int idServizio);
    Servizio getByName(String nomeServizio);
    boolean serviceExists(int idServizio);
    boolean serviceExists(String nomeServizio);
    ArrayList<Servizio> findAll();
    ArrayList<Servizio> findAllBySupplier(Fornitore fornitore);
    int add(Servizio servizio);
    int removeById(int idServizio);
    int update(Servizio servizio);
}
