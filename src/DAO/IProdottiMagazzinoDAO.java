package DAO;

import Model.Disponibilita;
import Model.IProdotto;
import Model.Magazzino;

import java.util.ArrayList;

public interface IProdottiMagazzinoDAO {
    ArrayList<Disponibilita> findAllProductsByWarehouseID(int idMagazzino);
    ArrayList<Magazzino> findAllWarehousesByProductID(int idProdotto);
    int add(Disponibilita disponibilita);
    int remove(Disponibilita disponibilita);
    int update(Disponibilita disponibilita);
}
