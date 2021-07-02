package DAO.PuntoVendita;

import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.ServiziPuntoVendita.ServiziPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
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
    private ProdottiPuntoVenditaDAO ppvDAO;
    private ServiziPuntoVenditaDAO spvDAO;
    private UtentiPuntoVenditaDAO upvDAO;
    private MagazzinoDAO mDAO;

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
        ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        spvDAO = ServiziPuntoVenditaDAO.getInstance();
        upvDAO = UtentiPuntoVenditaDAO.getInstance();
        mDAO = MagazzinoDAO.getInstance();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setMagazzino(mDAO.findByID(rs.getInt("idMagazzino")));
                puntoVendita.setManager(upvDAO.findShopManager(puntoVendita.getIdPuntoVendita()));
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
        ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        spvDAO = ServiziPuntoVenditaDAO.getInstance();
        upvDAO = UtentiPuntoVenditaDAO.getInstance();
        mDAO = MagazzinoDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                puntoVendita.setMagazzino(mDAO.findByID(rs.getInt("idMagazzino")));
                puntoVendita.setManager(upvDAO.findShopManager(puntoVendita.getIdPuntoVendita()));
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
        int rowCount = conn.executeUpdate("INSERT INTO PuntoVendita VALUES ('" + puntoVendita.getIdPuntoVendita() + "','" + puntoVendita.getVia() + "','" + puntoVendita.getCap() + "','" + puntoVendita.getCitta() + "','" + puntoVendita.getMagazzino().getIdMagazzino() + "');");
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
        int rowCount = conn.executeUpdate("UPDATE PuntoVendita SET via = '" + puntoVendita.getVia() + "', CAP = '" + puntoVendita.getCitta() + "' WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';");
        conn.close();
        return rowCount;
    }
}
