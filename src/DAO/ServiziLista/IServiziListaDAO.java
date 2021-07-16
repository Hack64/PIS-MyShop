package DAO.ServiziLista;


import Model.Lista;
import Model.Servizio;

import java.util.ArrayList;

public interface IServiziListaDAO {
    ArrayList<Servizio> findAllServicesByListID(int idLista);
    ArrayList<Lista> findAllListsByServiceID(int idServizioLista);
    boolean isServiceAlreadyInList(int idLista, int idServizio);
    int add(Lista lista, Servizio servizio);
    int removeByID(int idLista, int idServizio);
}
