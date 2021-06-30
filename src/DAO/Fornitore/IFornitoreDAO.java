package DAO.Fornitore;

import Model.Fornitore;

import java.util.ArrayList;

public interface IFornitoreDAO {
    Fornitore findByID(int idFornitore);
    Fornitore getByName(String nomeFornitore);
    boolean supplierExists(String nomeFornitore);
    ArrayList<Fornitore> findAll();
    int add(Fornitore fornitore);
    int removeById(int idFornitore);
    int update(Fornitore fornitore);
}
