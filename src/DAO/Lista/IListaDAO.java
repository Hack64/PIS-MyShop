package DAO.Lista;

import Model.Lista;

import java.util.ArrayList;

public interface IListaDAO {
    //TODO: fornire un metodo al manager per poter impostare le liste come pagate
    Lista findByID(int idLista);
    ArrayList<Lista> findAll();
    ArrayList<Lista> findAllByUserID(int idUtente);
    ArrayList<Lista> findAllByState(Lista.Stato stato);
    ArrayList<Lista> findAllByUserAndState(int idUtente, Lista.Stato stato);
    float getListPrice(int idLista);
    int setListPayment(int idLista, Lista.Stato stato);
    int add(Lista lista);
    int removeById(int idLista);
    int update(Lista lista);
    int editName(Lista lista);

}
