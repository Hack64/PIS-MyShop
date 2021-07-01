package DAO.ProdottiLista;

import Model.Lista;
import Model.Prodotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface IProdottiListaDAO {
    HashMap<Prodotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista);
    HashMap<Prodotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdotto);
    int add(Lista lista, Prodotto prodotto, String prenotato, int quantita);
    int removeByID(int idProdotto, int idLista);
    int update(Lista lista, Prodotto prodotto, String prenotato, int quantita);
}
