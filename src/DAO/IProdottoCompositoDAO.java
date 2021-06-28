package DAO;

import Model.IProdotto;
import Model.ProdottoComposito;
import Model.Produttore;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    ProdottoComposito findByID(int idProdotto);
    ArrayList<ProdottoComposito> findAll();
    ArrayList<ProdottoComposito> findAllByProducerID(int idProduttore);
    int add(ProdottoComposito prodotto);
    int removeById(int idProdotto);
    int update(ProdottoComposito prodotto);
}
