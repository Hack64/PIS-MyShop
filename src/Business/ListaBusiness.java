package Business;

import DAO.Lista.ListaDAO;
import Model.Lista;
import Model.Responses.ListaResponse;
import Model.Utente;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaBusiness {
    private static ListaBusiness instance;
    private ListaDAO listaDAO;

    public static synchronized ListaBusiness getInstance() {
        if(instance == null) instance = new ListaBusiness();
        return instance;
    }

    private ListaBusiness() {}

    public ListaResponse find(int idLista){
        ListaResponse res = new ListaResponse();
        res.setMessage("Errore non definito");

        listaDAO = ListaDAO.getInstance();

        res.setLista(listaDAO.findByID(idLista));
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

    public int deleteByID(int idLista){
        listaDAO = ListaDAO.getInstance();

        return listaDAO.removeById(idLista);
    }

    public int createNewList(String nome, String dataCreazione){
        listaDAO = ListaDAO.getInstance();
        Lista l = new Lista();
        l.setNomeLista(nome);
        System.out.println(l.getNomeLista());
        l.setDataCreazione(LocalDate.parse(dataCreazione));
        System.out.println(l.getDataCreazione().toString());
        l.setStato(Lista.Stato.NON_PAGATA);
        System.out.println(l.getStato().toString());
        l.setPrezzoTotale(0);
        System.out.println(l.getPrezzoTotale());
        l.setUtente((Utente)SessionManager.getInstance().getSession().get("loggedUser"));
        System.out.println(l.getUtente().getIdUtente());

        return listaDAO.add(l);
    }

    public int updateListName(String nome, int idLista){
        listaDAO = ListaDAO.getInstance();
        Lista l = new Lista();
        l.setIdLista(idLista);
        l.setNomeLista(nome);
        return listaDAO.editName(l);
    }
}
