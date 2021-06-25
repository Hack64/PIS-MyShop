package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {

    private final static PuntoVenditaDAO instance = new PuntoVenditaDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private PuntoVendita puntoVendita;

    private PuntoVenditaDAO(){
        conn=null;
        rs=null;
        puntoVendita=null;
    }

    public static PuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idPuntoVendita, via, CAP, citta, idMagazzino FROM myshopdb.PuntoVendita WHERE myshopdb.PuntoVendita.idPuntoVendita = '" + idPuntoVendita + "';");
        ProdottiPuntoVenditaDAO ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        ServiziPuntoVenditaDAO spvDAO = ServiziPuntoVenditaDAO.getInstance();
        UtentiPuntoVenditaDAO upvDAO = UtentiPuntoVenditaDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIdMagazzino(rs.getInt("idMagazzino"));
                puntoVendita.setIdManager(upvDAO.findShopManager(puntoVendita.getIdPuntoVendita()).getIdUtente());
                puntoVendita.setCatalogoProdottiPuntoVendita(ppvDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoServiziPuntoVendita(spvDAO.findServicesByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setClienti(upvDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita()));

                return puntoVendita;
            }
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
    public ArrayList<PuntoVendita> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idPuntoVendita, via, CAP, citta, idMagazzino FROM myshopdb.PuntoVendita;");
        ProdottiPuntoVenditaDAO ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        ServiziPuntoVenditaDAO spvDAO = ServiziPuntoVenditaDAO.getInstance();
        UtentiPuntoVenditaDAO upvDAO = UtentiPuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setIdMagazzino(rs.getInt("idMagazzino"));
                puntoVendita.setIdManager(upvDAO.findShopManager(puntoVendita.getIdPuntoVendita()).getIdUtente());
                puntoVendita.setCatalogoProdottiPuntoVendita(ppvDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoServiziPuntoVendita(spvDAO.findServicesByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setClienti(upvDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita()));

                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
    public int add(PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO PuntoVendita VALUES ('" + puntoVendita.getIdPuntoVendita() + "','" + puntoVendita.getVia() + "','" + puntoVendita.getCap() + "','" + puntoVendita.getCitta() + "','" + puntoVendita.getIdMagazzino() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM PuntoVendita WHERE PuntoVendita.idPuntoVendita = '" + idPuntoVendita + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE PuntoVendita SET idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "', via = '" + puntoVendita.getVia() + "', CAP = '" + puntoVendita.getCitta() + "', idMagazzino = '" + puntoVendita.getIdMagazzino() + "';");
        conn.close();
        return rowCount;
    }
}
