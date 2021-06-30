package DAO;

import Model.PuntoVendita;
import Model.Utente;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUtentiPuntoVenditaDAO {
    HashMap<Utente, String> findUsersByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByUserID(int idUtente);
    Utente findShopManager(int idPuntoVendita);
    int add(Utente utente, PuntoVendita puntoVendita, String disattivato, String isManager);
    int removeByID(int idUtente, int idPuntoVendita);
    int update(Utente utente, PuntoVendita puntoVendita, String disattivato, String isManager);
}
