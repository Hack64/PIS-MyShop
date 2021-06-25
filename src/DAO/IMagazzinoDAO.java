package DAO;

import Model.Magazzino;

import java.util.ArrayList;

public interface IMagazzinoDAO {
    Magazzino findByID(int idMagazzino);
    ArrayList<Magazzino> findAll();
}
