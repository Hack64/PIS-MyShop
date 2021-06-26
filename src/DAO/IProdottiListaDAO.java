package DAO;

import Model.IProdotto;
import Model.Lista;

import java.util.ArrayList;
import java.util.HashMap;

public interface IProdottiListaDAO {
    HashMap<IProdotto, String> findAllProductsByListID(int idLista);
    HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato);
    ArrayList<Lista> findAllListsByProductID(int idProdottoLista);
    //come lo faccio l'add e il resto????
}
