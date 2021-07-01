package DAO.ComposizioneProdotto;

import Model.IProdotto;
import Model.ProdottoComposito;
import Model.Produttore;

import java.util.ArrayList;

public interface IComposizioneProdottoDAO {
    ProdottoComposito findByID(int idProdotto);
    ArrayList<ProdottoComposito> findAll();
    ArrayList<ProdottoComposito> findAllByProducerID(int idProduttore);
    boolean isCompositeProduct(int idProdotto);
    ArrayList<Integer> add(ProdottoComposito prodotto);
    int removeById(int idProdotto);
}
