package DAO.Prodotto;

import Model.IProdotto;
import Model.Produttore;

import java.util.ArrayList;

public interface IProdottoDAO {
    IProdotto findByID(int idProdotto);
    IProdotto getByName(String nomeProdotto);
    boolean productExists(int idProdotto);
    boolean productExists(String nomeProdotto);
    ArrayList<IProdotto> findAll();
    ArrayList<IProdotto> findAllByProducer(Produttore produttore);
    int add(IProdotto prodotto);
    int removeById(int idProdotto);
    int update(IProdotto prodotto);
    int updateScore(float media, IProdotto prodotto);
    int updateComments(int commenti, IProdotto prodotto);
}
