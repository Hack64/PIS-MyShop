package Business;

import DAO.Lista.ListaDAO;
import Model.Lista;
import Model.Responses.ListaResponse;
import Model.Utente;

import java.util.ArrayList;

public class ListaBusiness {
    private static ListaBusiness instance;
    private ListaDAO listaDAO;

    public static synchronized ListaBusiness getInstance() {
        if(instance == null) instance = new ListaBusiness();
        return instance;
    }

    private ListaBusiness() {}

    public ListaResponse find(int idProdotto){
        ListaResponse res = new ListaResponse();
        res.setMessage("Errore non definito");

        listaDAO = ListaDAO.getInstance();

        res.setLista(listaDAO.findByID(idProdotto));
        res.setMessage("Lista trovata con successo");

        return res;
    }

    public ArrayList<Lista> findAllLists(){
        listaDAO = ListaDAO.getInstance();

        return listaDAO.findAll();
    }

    public ArrayList<Lista> findAllListsByUser(Utente u){
        listaDAO = ListaDAO.getInstance();

        return listaDAO.findAllByUserID(u.getIdUtente());
    }
}
