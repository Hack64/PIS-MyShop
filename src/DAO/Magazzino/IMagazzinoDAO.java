package DAO.Magazzino;

import Model.Magazzino;

import java.util.ArrayList;

public interface IMagazzinoDAO {
    Magazzino findByID(int idMagazzino);
    ArrayList<Magazzino> findAll();
    int add(Magazzino magazzino);
    int removeByID(int idMagazzino);
    int update(Magazzino magazzino);
}
