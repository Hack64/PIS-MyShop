package Business;

import DAO.Lista.IListaDAO;
import DAO.Lista.ListaDAO;
import DAO.Magazzino.IMagazzinoDAO;
import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiLista.IProdottiListaDAO;
import DAO.ProdottiLista.ProdottiListaDAO;
import DAO.ProdottiMagazzino.IProdottiMagazzinoDAO;
import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DAO.ServiziLista.IServiziListaDAO;
import DAO.ServiziLista.ServiziListaDAO;
import Model.*;
import Model.Responses.ListaResponse;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaBusiness {
    private static ListaBusiness instance;
    private IListaDAO listaDAO;

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

    public ArrayList<Lista> findAllListsByUserAndState(Utente u, Lista.Stato stato){
        listaDAO = ListaDAO.getInstance();

        return listaDAO.findAllByUserAndState(u.getIdUtente(), stato);
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

    public int addProductToList(Lista lista, IProdotto prodotto, int quantita){
        listaDAO = ListaDAO.getInstance();
        IProdottiListaDAO prodottiListaDAO = ProdottiListaDAO.getInstance();
        IProdottiMagazzinoDAO prodottiMagazzinoDAO = ProdottiMagazzinoDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        PuntoVendita p = (PuntoVendita)SessionManager.getInstance().getSession().get("currentShop");
        Disponibilita disp = prodottiMagazzinoDAO.findByProductAndWarehouseID(prodotto.getIdProdotto(), magazzinoDAO.findByShopID(p.getIdPuntoVendita()).getIdMagazzino());
        int st=0;
        if (disp.getQta()<quantita){
            if (prodottiMagazzinoDAO.isProductAlreadyInList(prodotto.getIdProdotto(), lista.getIdLista())) {
                st+=prodottiListaDAO.update(lista, prodotto, "SI", quantita);
            } else
                st+=prodottiListaDAO.add(lista, prodotto, "SI", quantita);
        } else {
            if (prodottiMagazzinoDAO.isProductAlreadyInList(prodotto.getIdProdotto(), lista.getIdLista())) {
                st+=prodottiListaDAO.update(lista, prodotto, "NO", quantita);
            } else
                st+=prodottiListaDAO.add(lista, prodotto, "NO", quantita);
        }
        lista.setPrezzoTotale(prodotto.getCosto()*quantita+listaDAO.getListPrice(lista.getIdLista()));
        st+=listaDAO.update(lista);

        return st;
    }

    public int addServiceToList(Lista lista, Servizio servizio){
        IServiziListaDAO serviziListaDAO = ServiziListaDAO.getInstance();
        listaDAO = ListaDAO.getInstance();

        int st=0;
        if (!serviziListaDAO.isServiceAlreadyInList(lista.getIdLista(), servizio.getIdServizio())){
            st+=serviziListaDAO.add(lista, servizio);
            lista.setPrezzoTotale(servizio.getCosto()+listaDAO.getListPrice(lista.getIdLista()));
            st+=listaDAO.update(lista);
            return st;
        } else {
            return -1;
        }
    }


}
