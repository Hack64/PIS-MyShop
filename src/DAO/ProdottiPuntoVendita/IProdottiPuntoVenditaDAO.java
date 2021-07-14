package DAO.ProdottiPuntoVendita;

import Model.IProdotto;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IProdottiPuntoVenditaDAO {
    ArrayList<IProdotto> findProductsByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByProductID(int idProdotto);
    int removeAllPrductsByShopID(int idPuntoVendita);
    int add(PuntoVendita puntoVendita, IProdotto prodotto);
    int removeByID(int idProdotto, int idPuntoVendita);
}
