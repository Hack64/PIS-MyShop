package DAO.ServiziPuntoVendita;

import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.PuntoVendita;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiziPuntoVenditaDAO implements IServiziPuntoVenditaDAO {
    private final static ServiziPuntoVenditaDAO instance = new ServiziPuntoVenditaDAO();

    private IDbConnection conn;
    private ResultSet rs;

    private ServiziPuntoVenditaDAO(){
        conn=null;
        rs=null;
    }

    public static ServiziPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<Servizio> findServicesByShopID(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizio FROM myshopdb.ServiziPuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        ArrayList<Servizio> serviziPuntoVendita = new ArrayList<>();
        ServizioDAO sDAO = ServizioDAO.getInstance();
        try {
            while (rs.next()){
                serviziPuntoVendita.add(sDAO.findByID(rs.getInt("idServizio")));
            }
            return serviziPuntoVendita;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<PuntoVendita> findShopsByServiceID(int idServizio) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idPuntoVendita FROM myshopdb.ServiziPuntoVendita WHERE idServizio = '" + idServizio + "';");
        ArrayList<PuntoVendita> puntiVenditaServizio = new ArrayList<>();
        PuntoVenditaDAO pvDAO = PuntoVenditaDAO.getInstance();
        try {
            while (rs.next()){
                puntiVenditaServizio.add(pvDAO.findByID(rs.getInt("idPuntoVendita")));
            }
            return puntiVenditaServizio;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Servizio servizio, PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO ServiziPuntoVendita VALUES ('" + servizio.getIdServizio() + "','" + puntoVendita.getIdPuntoVendita() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idServizio, int idPuntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ServiziPuntoVendita WHERE idServizio = '" + idServizio + "' AND idPuntoVendita = '" + idPuntoVendita + "';");
        conn.close();
        return rowCount;
    }

}
