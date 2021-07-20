package DAO.UtentiPuntoVendita;

import Model.PuntoVendita;
import Model.Utente;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUtentiPuntoVenditaDAO {
    HashMap<Utente, String> findUsersByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByUserID(int idUtente);
    Utente findShopManagerByShopID(int idPuntoVendita);
    PuntoVendita findShopByShopManagerID(int idUtente);
    boolean isUserBanned(int idUtente, int idPuntoVendita);
    boolean isUserShopManager(int idUtente, int idPuntoVendita);
    boolean isUserShopManagerSomewhere(int idUtente);
    boolean isUserRegisteredInShop(int idUtente, int idPuntoVendita);
    boolean isUserPreferredShop(int idUtente, int idPuntoVendita);
    int add(Utente utente, PuntoVendita puntoVendita, int disattivato, int isManager, int preferito);
    int removeByID(int idUtente, int idPuntoVendita);
    int update(Utente utente, PuntoVendita puntoVendita, int disattivato, int isManager, int preferito);
    int updateManager(Utente utente, PuntoVendita puntoVendita, boolean isNew);
}
