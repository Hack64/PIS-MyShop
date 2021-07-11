package DAO.ComposizioneProdotto;

import Model.IProdotto;

import java.util.ArrayList;

public interface IComposizioneProdottoDAO {
    IProdotto findByID(int idProdotto);
    ArrayList<IProdotto> findAll();
    ArrayList<IProdotto> findAllByProducerID(int idProduttore);
    boolean isCompositeProduct(int idProdotto);
    int add(IProdotto prodotto);
    int removeById(int idProdotto);
    int update (IProdotto prodotto);
}
