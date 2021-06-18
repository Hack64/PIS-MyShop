package DAO;

import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {
    PuntoVendita getByID(int idPuntoVendita);
    ArrayList<PuntoVendita> findAll();
    int add(PuntoVendita puntoVendita);
    int removeById(int idPuntoVendita);
    int update(PuntoVendita puntoVendita);
}
