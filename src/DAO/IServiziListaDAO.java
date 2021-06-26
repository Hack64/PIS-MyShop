package DAO;


import Model.Lista;
import Model.Servizio;

import java.util.ArrayList;

public interface IServiziListaDAO {
    ArrayList<Servizio> findAllServicesByListID(int idLista);
    ArrayList<Lista> findAllListsByServiceID(int idServizioLista);
}
