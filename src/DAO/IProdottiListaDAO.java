package DAO;

import Model.Lista;
import Model.Prodotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface IProdottiListaDAO {
    HashMap<Prodotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista);
    HashMap<Prodotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdotto);
    //come lo faccio l'add e il resto????
    ArrayList<Integer> add(Lista lista, HashMap<Prodotto, Map.Entry<String, Integer>> prodotti);
    int removeByID(int idProdotto, int idLista);
    ArrayList<Integer> update(Lista lista, HashMap<Prodotto, Map.Entry<String, Integer>> prodotti);
}
