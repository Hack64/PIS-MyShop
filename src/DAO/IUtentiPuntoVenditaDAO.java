package DAO;

import Model.PuntoVendita;
import Model.Utente;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUtentiPuntoVenditaDAO {
    HashMap<Utente, String> findUsersByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByUserID(int idUtente);
    Utente findShopManager(int idPuntoVendita);
}
