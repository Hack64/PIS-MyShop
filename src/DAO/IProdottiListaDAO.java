package DAO;

import Model.IProdotto;
import Model.Lista;
import Model.Prodotto;

import java.util.ArrayList;
import java.util.HashMap;

public interface IProdottiListaDAO {
    HashMap<Prodotto, String> findAllProductsByListID(int idLista);
    HashMap<Prodotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdotto);
    //come lo faccio l'add e il resto????
    ArrayList<Integer> add(Lista lista, HashMap<Prodotto, String> prodotti);
    int removeByID(int idProdottoLista);
    int update(Lista lista, HashMap<Prodotto, String> prodotti);
}
