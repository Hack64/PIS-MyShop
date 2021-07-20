package DAO.ProdottiLista;

import Model.IProdotto;
import Model.Lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface IProdottiListaDAO {
    HashMap<IProdotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista);
    HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdotto);
    int isProductAlreadyInList(int idProdotto, int idLista);
    int add(Lista lista, IProdotto prodotto, String prenotato, int quantita);
    int removeByID(int idProdotto, int idLista);
    int update(Lista lista, IProdotto prodotto, String prenotato, int quantita);
}
