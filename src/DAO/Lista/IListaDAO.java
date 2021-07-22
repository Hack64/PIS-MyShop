package DAO.Lista;

import Model.Lista;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IListaDAO {
    Lista findByID(int idLista);
    Lista findByUserDateAndName(int idUtente, LocalDate data, String nome);
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
