package DAO.Lista;

import Model.Lista;

import java.util.ArrayList;

public interface IListaDAO {

    Lista findByID(int idLista);
    ArrayList<Lista> findAll();
    ArrayList<Lista> findAllByUserID(int idUtente);
    ArrayList<Lista> findAllByState(Lista.Stato stato);
    ArrayList<Lista> findAllByUserAndState(int idUtente, Lista.Stato stato);
    int add(Lista lista);
    int removeById(int idLista);
    int update(Lista lista);

}
