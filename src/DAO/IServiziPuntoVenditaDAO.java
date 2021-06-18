package DAO;

import Model.PuntoVendita;
import Model.Servizio;

import java.util.ArrayList;

public interface IServiziPuntoVenditaDAO {
    ArrayList<Servizio> findServicesByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByServiceID(int idServizio);
}
