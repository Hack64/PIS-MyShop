package DAO;

import Model.IProdotto;
import Model.PuntoVendita;

import java.util.ArrayList;

public interface IProdottiPuntoVenditaDAO {
    ArrayList<IProdotto> findProductsByShopID(int idPuntoVendita);
    ArrayList<PuntoVendita> findShopsByProductID(int idProdotto);
}
