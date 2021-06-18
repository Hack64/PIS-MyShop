package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.PuntoVendita;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UtentiPuntoVenditaDAO implements IUtentiPuntoVenditaDAO {

    private final static UtentiPuntoVenditaDAO instance = new UtentiPuntoVenditaDAO();

    IDbConnection conn;
    ResultSet rs;

    private UtentiPuntoVenditaDAO(){
        conn = null;
        rs = null;
    }

    public static UtentiPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public HashMap<Utente, String> findUsersByShopID(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idUtente, disattivato FROM myshopdb.UtentePuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';");
        HashMap<Utente, String> utentiPuntoVendita = new HashMap<>();
        UtenteDAO uDAO = UtenteDAO.getInstance();
        try {
            while (rs.next()){
                utentiPuntoVendita.put(uDAO.findByID(rs.getInt("idUtente")), rs.getString("disattivato"));
            }
            return utentiPuntoVendita;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<PuntoVendita> findShopsByUserID(int idUtente) {
        sdfghn<sdiogfsdhn
        return null;
    }

    @Override
    public Utente findShopManager(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idUtente FROM myshopdb.UtentePuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "' AND isManager = 'SI';");
        UtenteDAO uDAO = UtenteDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1){
                Utente shopManager;
                shopManager=uDAO.findByID(rs.getInt("idUtente"));
                return shopManager;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            conn.close();
        }
        return null;
    }
}
