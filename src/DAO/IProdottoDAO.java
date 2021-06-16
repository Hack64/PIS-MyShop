package DAO;

import Model.Prodotto;
import Model.Utente;

import java.util.ArrayList;

public interface IProdottoDAO {
    Prodotto findByID(int idProdotto);
    Prodotto getByName(String nomeProdotto);
    boolean productExists(String nomeProdotto);
    ArrayList<Prodotto> findAll();
    int add(Prodotto prodotto);
    int removeById(int idProdotto);
    int update(Prodotto prodotto);
}
