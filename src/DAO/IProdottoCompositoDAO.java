package DAO;

import Model.IProdotto;
import Model.ProdottoComposito;
import Model.Produttore;

import java.util.ArrayList;

public interface IProdottoCompositoDAO {
    //ProdottoComposito findByID(int idProdotto);
    ProdottoComposito getByName(String nomeProdotto);
    boolean productExists(String nomeProdotto);
    ArrayList<ProdottoComposito> findAll();
    ArrayList<IProdotto> findAllByProducer(Produttore produttore);
    int add(ProdottoComposito prodotto);
    int removeById(int idProdotto);
    int update(ProdottoComposito prodotto);
}
