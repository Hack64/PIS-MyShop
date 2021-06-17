package DAO;

import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {
    Produttore findByID(int idProduttore);
    Produttore getByName(String nomeProduttore);
    boolean producerExists(String nomeProduttore);
    ArrayList<Produttore> findAll();
    int add(Produttore produttore);
    int removeById(int idProduttore);
    int update(Produttore produttore);
}
