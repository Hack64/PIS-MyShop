package DAO.ProdottiLista;

import Model.IProdotto;
import Model.Lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface IProdottiListaDAO {
    //TODO: carica tutte le liste pagate e trova tutti i prodotti delle liste
    //TODO: trova un modo di caricare i prodotti univocamente
    HashMap<IProdotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista);
    HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdotto);
    int isProductAlreadyInList(int idProdotto, int idLista);
    int add(Lista lista, IProdotto prodotto, String prenotato, int quantita);
    int removeByID(int idProdotto, int idLista);
    int update(Lista lista, IProdotto prodotto, String prenotato, int quantita);
}
