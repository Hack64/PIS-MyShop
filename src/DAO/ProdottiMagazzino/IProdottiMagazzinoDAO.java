package DAO.ProdottiMagazzino;

import Model.Disponibilita;
import Model.Magazzino;

import java.util.ArrayList;

public interface IProdottiMagazzinoDAO {
    Disponibilita findByProductAndWarehouseID(int idProdotto, int idMagazzino);
    ArrayList<Disponibilita> findAllProductsByWarehouseID(int idMagazzino);
    ArrayList<Magazzino> findAllWarehousesByProductID(int idProdotto);
    boolean isProductAlreadyInList(int idProdotto, int idLista);
    int add(Disponibilita disponibilita);
    int remove(Disponibilita disponibilita);
    int update(Disponibilita disponibilita);
}
