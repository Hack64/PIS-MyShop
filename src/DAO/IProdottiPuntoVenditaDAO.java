package DAO;

import Model.IProdotto;
import Model.Prodotto;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IProdottiPuntoVenditaDAO {
    ArrayList<IProdotto> findProductsByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByProductID(int idProdotto);
    int add(PuntoVendita puntoVendita, Prodotto prodotto);
    int removeByID(int idProdottiPuntoVendita);
    int update(PuntoVendita puntoVendita, Prodotto prodotto);
}
