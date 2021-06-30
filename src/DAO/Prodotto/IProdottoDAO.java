package DAO.Prodotto;

import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findByID(int idProdotto);
    Prodotto getByName(String nomeProdotto);
    boolean productExists(String nomeProdotto);
    ArrayList<Prodotto> findAll();
    ArrayList<IProdotto> findAllByProducer(Produttore produttore);
    int add(Prodotto prodotto);
    int removeById(int idProdotto);
    int update(Prodotto prodotto);
}
